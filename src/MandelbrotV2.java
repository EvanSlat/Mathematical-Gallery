

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class MandelbrotV2 implements MathFunction{
	private List<MPoint> points;
	private List<Color> colors;
	private int screenWidth, screenHeight;
	private double calcWidth, calcHeight;
	private double calcOriginX, calcOriginY;
	private int maxIterations;
	
	public MandelbrotV2(double cox, double coy, double cw, double ch, int mi, int sw, int sh) {
		screenWidth = sw;
		screenHeight = sh;
		calcWidth = cw;
		calcHeight = ch;
		calcOriginX = cox;
		calcOriginY = coy;
		maxIterations = mi;
		setupPoints();
		setUpColors();
		runCalculations();
	}
	
	
	
	private void setupPoints() {
		this.points = new ArrayList<MPoint>();
		double wps = calcWidth / screenWidth;
		double hps = calcHeight / screenHeight;
		for(int x = 0; x<screenWidth;x++) {
			for(int y = 0; y<screenHeight;y++) {
				points.add(new MPoint(x*wps-(calcWidth/2)+calcOriginX, y*hps-(calcHeight/2)+calcOriginY, x, y));
			}
		}
	}
	
	private void setUpColors() {
		colors = new ArrayList<Color>();
		int s = maxIterations/10;
		for(int i = 0; i<=maxIterations;i++) {
			int red =   (int) (((256.0/maxIterations)*i*s)%256);
			int blue = 	(int) (((256.0/maxIterations)*i*s+(256/3))%256);
			int green = (int) (((256.0/maxIterations)*i*s+(256*2/3))%256);
			colors.add(new Color(red,green,blue));
//			System.out.println(red+":"+green+":"+blue);
		}
	}
	
	private void runCalculations() {
		points.parallelStream().forEach(m->{
			m.runItteration(maxIterations);
		});
	}
	
	@Override
	public void drawFrame(Graphics2D g, int timeStep) {
		// TODO Auto-generated method stub
		Rectangle r = new Rectangle(1,1);
		for(MPoint m : points) {
			r.translate(m.xPixel, m.yPixel);
			g.setColor(colors.get(m.iteration));
			g.fill(r);
			g.draw(r);
			r.translate(-m.xPixel, -m.yPixel);
			
		}
		r.translate(screenWidth/2, screenHeight/2);
		g.setColor(Color.black);
		g.fill(r);
		g.draw(r);
		
	}

	
	private class MPoint extends IPoint{

		Point originalP;
		//save pixel position on for drawing
		int xPixel, yPixel;
		int iteration;
		boolean processing;
		
		public MPoint(double r, double i, int xP, int yP) {
			super(0, 0);
			this.originalP = new Point(r, i);
			this.iteration = 0;
			this.processing = true;
			this.xPixel = xP;
			this.yPixel = yP;
		}
		
		public void runItteration(int remainingIterations) {
			if(remainingIterations == 0) {
				return;
			}
			if(processing) {
				this.mult(this);
				this.add(originalP);
				iteration++;
				double dO = this.distanceOrigin();
				if(dO >= 2.0 || dO <= -2.0) {
					processing = false;
				}
				this.runItteration(remainingIterations-1);
			}else {
				return;
			}
		}
	}
}

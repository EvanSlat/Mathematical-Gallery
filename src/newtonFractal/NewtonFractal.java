package newtonFractal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import auxilory.IPoint;
import auxilory.MathFunction;
import auxilory.Point;

public class NewtonFractal implements MathFunction {

	private Point[] roots;
	private List<NPoint> points;
	private List<Color> colors;
	private int screenWidth, screenHeight;
	private double calcWidth, calcHeight;
	private double calcOriginX, calcOriginY;
	private int maxIterations;
	
	private final int THRESHHOLDRANGE = 4;
	
	public NewtonFractal(double cox, double coy, double cw, double ch, int mi, int sw, int sh, Point[] roots) {
		screenWidth = sw;
		screenHeight = sh;
		calcWidth = cw;
		calcHeight = ch;
		calcOriginX = cox;
		calcOriginY = coy;
		maxIterations = mi;
		this.roots = roots;
		setupPoints();
		setUpColors();
		runCalculations();
	}
	
	private void setupPoints() {
		
		double threshhold = roots[0].distance(roots[1])/THRESHHOLDRANGE;
		for(int i = 0; i<roots.length;i++) {
			for(int j = 1; j<roots.length;j++) {
				if(i == j) {
					continue;
				}
				
				if(threshhold > roots[0].distance(roots[1])/THRESHHOLDRANGE) {					
					threshhold = roots[0].distance(roots[1])/THRESHHOLDRANGE;
				}
			}
		}
		
		
		this.points = new ArrayList<NPoint>();
		double wps = calcWidth / screenWidth;
		double hps = calcHeight / screenHeight;
		for(int x = 0; x<screenWidth;x++) {
			for(int y = 0; y<screenHeight;y++) {
				points.add(new NPoint(x*wps-(calcWidth/2)+calcOriginX, y*hps-(calcHeight/2)+calcOriginY, x, y, roots, threshhold));
			}
		}
	}
	

	
	private void setUpColors() {
		colors = new ArrayList<Color>();
		for(int i = 0; i<= roots.length;i++) {
			int red =   (int) (((256.0/ roots.length)*i)%256);
			int blue = 	(int) (((256.0/ roots.length)*i+(256/3))%256);
			int green = (int) (((256.0/ roots.length)*i+(256*2/3))%256);
//			System.out.println("Red:"+red+"  Blue:"+blue+"  Green:"+green);
			colors.add(new Color(red,green,blue));
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
		for(NPoint m : points) {
			r.translate(m.xPixel, m.yPixel);
			g.setColor(colors.get(m.closestPoint()));
//			System.out.println(m.closestPoint());
			g.fill(r);
			g.draw(r);
			r.translate(-m.xPixel, -m.yPixel);
			
		}
		r.translate(screenWidth/2, screenHeight/2);
		g.setColor(Color.black);
		g.fill(r);
		g.draw(r);

	}

	
	
	private class NPoint extends IPoint{;
		int xPixel, yPixel;
		Point[] roots;
		double threshhold;
		HashMap<Integer, IPoint> recursiveMemory;
		public NPoint(double r, double i, int xP, int yP, Point[] roots, double threshhold) {
			super(r, i);
			this.xPixel = xP;
			this.yPixel = yP;
			this.roots = roots;
			this.threshhold = threshhold;
		}
		
		
		
		private IPoint calcFunc() {
			return reCalcFunc(roots.length-1);
		}
		
		//TODO add memoty hashmap
		private IPoint reCalcFunc(int i) {
			if(i == -1) {
				return new IPoint(1,0);
			}else {
				//does not work
				IPoint toReturn = reCalcFunc(i-1).mult((IPoint) new IPoint(x, y).subtract(roots[i]));
				recursiveMemory.put(i, new IPoint(toReturn.x, toReturn.y));
				return toReturn;
			}
		}
		
		private IPoint calcDerivative() {
			return reCalcDeri(roots.length-1);
		}
		
		private IPoint reCalcDeri(int i) {
			if(i == -1) {
				return new IPoint(0, 0);
			}else {
				IPoint left;
				if(recursiveMemory.containsKey(i-1)) {
					left = recursiveMemory.get(i-1);
				}else {
					left = reCalcFunc(i-1);
				}
				IPoint right = reCalcDeri(i-1).mult((IPoint) new IPoint(x, y).subtract(roots[i]));
				return (IPoint) left.add(right);
			}
		}
		
		//TODO make this be shortened
		public void runItteration(int remainingItterations) {
			if(remainingItterations == 0) {
				return;
			}
			if(this.distance(roots[closestPoint()]) < threshhold) {
				return;
			}
			recursiveMemory = new HashMap<Integer, IPoint>();
			subtract(calcFunc().divide(calcDerivative()));
			runItteration(remainingItterations -1);
		}
		
		public int closestPoint() {
			double min = roots[0].distance(this);
			int minIndex = 0;
			for(int i = 1; i < roots.length;i++){
				if(roots[i].distance(this) < min) {
					minIndex = i;
					min = roots[i].distance(this);
				}
			}
			return minIndex;
		}
		
	}
}

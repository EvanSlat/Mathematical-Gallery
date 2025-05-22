

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Mandelbrot implements MathFunction {

	private List<MPoint> points;
	private List<Color> colors;
	
	private double x;
	private double y;
	private double width;
	private double height;
	private int widthPoints;
	private int heightPoints;
	private int maxIterations;
	
	 private int sW;
	 private int sH;
	 
	 
	 public Mandelbrot(int maxIterations, int widthPoints, int heightPoints) {
		this.x = -2;
		this.y = -2;
		this.width = 4;
		this.height = 4;
		this.widthPoints = widthPoints;
		this.heightPoints = heightPoints;
		this.maxIterations = maxIterations;
		
		this.sW = 100;
		this.sH = 100;
		setupPoints();
		runCalculations();
		setUpColors();
	 }
	/**
	 * 
	 * @param x origin of covered area
	 * @param y origin of covered area
	 * @param width size of covered area
	 * @param height size of covered area
	 * @param widthPoints number of points along the x axis
	 * @param heightPoints number of points along the y axis
	 * @param sX origin of view screen
	 * @param sY origin of view screen
	 * @param sW scaling of view screen
	 * @param sH scaling of view screen
	 * 
	 * Treat sX and sY like how I would treat the radius. They are what I scall off of to get the correct sizing
	 */
	public Mandelbrot(double x, double y, double width, double height, int widthPoints, int heightPoints, int maxIterations, int sW, int sH) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.widthPoints = widthPoints;
		this.heightPoints = heightPoints;
		this.maxIterations = maxIterations;
		
		this.sW = sW;
		this.sH = sH;
		setupPoints();
		runCalculations();
		setUpColors();
	}
	
	private void setupPoints() {
		this.points = new ArrayList<MPoint>();
		double wps = width / (widthPoints-1);
		double hps = height / (heightPoints-1);
		for(int i = 0; i < widthPoints; i++) {
			for(int j = 0; j < heightPoints; j++) {
				points.add(new MPoint(x + wps*i, y+hps*j));
			}
		}
	}
	
	//TODO make this be parralelized 
	private void runCalculations() {
		
//		for(MPoint m :points) {
//				for(int j = 0; j<maxIterations;j++) {						
//					m.runItteration();
//				}
//			}
		points.parallelStream().forEach(m->{
			for(int i = 0;i<maxIterations;i++) {
				m.runItteration();
			}
		});
//		ForkJoinPool pool = new ForkJoinPool();
//		List<MPoint> syncList = Collections.synchronizedList(points);
//		ParallelMandel task= new ParallelMandel(syncList,maxIterations);
//		int r = pool.invoke(task);
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
	
	//TODO may need to entirly remove MathFunction or change it up since while everything needs a frame, everything does not always need 
	@Override
	public void drawFrame(Graphics2D g, int timeStep) {
		double wps = width / (widthPoints-1);
		double hps = height / (heightPoints-1);
			//create rectangle
		Rectangle r = new Rectangle(new Dimension((int)(wps*sW), (int)(hps*sH)));
		for(MPoint m:points) {
			//move rectangle to desired location
			g.translate(m.originalP.x*sW,m.originalP.y*sH);
			//set its color
			g.setColor(colors.get(m.iteration));
			g.fill(r);
			g.draw(r);
			//move it back
			g.translate(-m.originalP.x*sW,-m.originalP.y*sH);
		}

	}
	//This is to keep track of original data and for color data
	
	private class MPoint extends IPoint{

		Point originalP;;
		int iteration;
		boolean processing;
		
		public MPoint(double r, double i) {
			super(0, 0);
			this.originalP = new Point(r, i);
			this.iteration = 0;
			this.processing = true;
		}
		
		public void runItteration() {
			if(processing) {
				this.mult(this);
				this.add(originalP);
				iteration++;
				double dO = this.distanceOrigin();
				if(dO >= 2.0 || dO <= -2.0) {
					processing = false;
				}
			}
		}
	}
	
	//https://www.geeksforgeeks.org/parallel-programming-in-java/
	static class ParallelMandel extends RecursiveTask<Integer>{
		private static final int THRESHOLD = 1000;
		private List<MPoint> points;
		private int start, end, mI;
		
		public ParallelMandel(List<MPoint>p,int start, int end, int mI) {
			this.points = p;
			this.start = start;
			this.end = end;
			this.mI = mI;
		}
		
		public ParallelMandel(List<MPoint>p,int mI) {
			this.points = p;
			start = 0;
			end = p.size()-1;
			this.mI = mI;
		}
		
		@Override
		protected Integer compute() {
			// TODO Auto-generated method stub
			if(end-start<= THRESHOLD) {
				for(int i = start; i<end;i++) {
					MPoint temp = points.get(i);
					for(int j = 0; j<mI;j++) {						
						temp.runItteration();
					}
				}
				return 1;
			}else {
                int mid = (start + end) / 2;
                ParallelMandel leftTask = new ParallelMandel(points, start, mid,mI);
                ParallelMandel rightTask = new ParallelMandel(points, mid, end,mI);

                // Fork left task
                leftTask.fork();
                // Directly compute right task and join results
                int rightResult = rightTask.compute();
                int leftResult = leftTask.join();

                return leftResult + rightResult;
			}
		}
		
	}

	
}

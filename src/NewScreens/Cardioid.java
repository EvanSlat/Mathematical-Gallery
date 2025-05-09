package NewScreens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

public class Cardioid implements MathFunction {

	private static Color black = new Color(0, 0, 0);
	
	private int totalPoints;
	private int radius;
	private double stepScale;

	private int[] xPoints;
	private int[] yPoints;

	private ArrayList<Line2D.Double> lines;
	
	public Cardioid(int points, double stepScale, int radius) {
		// TODO Auto-generated constructor stub
		this.totalPoints = points;
		this.radius = radius;
		this.stepScale = stepScale;
		this.xPoints = new int[points];
		this.yPoints = new int[points];
		this.lines = new ArrayList<>();
		assignPoints();
	}
	
	public void assignPoints() {
		double degree = (2 * Math.PI) / this.totalPoints;

		for (int i = 0; i < this.totalPoints; i++) {
			this.xPoints[i] = (int) (this.radius * Math.cos(i * degree) + 0.5);
			this.yPoints[i] = (int) (this.radius * Math.sin(i * degree) + 0.5);
		}
	}
	
	@Override
	public void drawFrame(Graphics2D g, int timeStep) {
		// TODO Auto-generated method stub
		
		
		g.drawOval(-radius, -radius, radius*2, radius*2);
		double bluetint = 0;
		for(int i = 0; i<xPoints.length; i++) {
			int pointTo = (int) (i * timeStep * stepScale + 0.5);
			pointTo = pointTo % (int)((totalPoints/1));
			Line2D.Double line = new Double(xPoints[i], yPoints[i], xPoints[pointTo], yPoints[pointTo]);
			bluetint += 255.0/xPoints.length;
			g.setColor(new Color(0, 0, (int) bluetint, 50));
			g.draw(line);
		}
		g.setColor(black);

	}

	@Override
	public int getRadius() {
		// TODO Auto-generated method stub
		return this.radius;
	}

	@Override
	public void setRadius(int r) {
		this.radius = r;
		assignPoints();
	}

}

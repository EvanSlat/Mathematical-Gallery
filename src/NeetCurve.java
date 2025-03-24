
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

public class NeetCurve {

	private static Color black = new Color(0, 0, 0);
	
	private int totalPoints;
	private int radius;

	private int[] xPoints;
	private int[] yPoints;

	private ArrayList<Line2D.Double> lines;

	public NeetCurve(int points, int radius) {
		this.totalPoints = points;
		this.xPoints = new int[points];
		this.yPoints = new int[points];
		this.radius = radius;
		this.lines = new ArrayList<>();
		assignPoints();
	}

	public NeetCurve() {
		this.totalPoints = 100;
		this.xPoints = new int[100];
		this.yPoints = new int[100];
		this.radius = 100;
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

	public void createCurve(double degree) {
		for (int i = 0; i < xPoints.length; i++) {
			int pointTo = (int) (i * degree + 0.5);
			pointTo = pointTo%totalPoints;
			Line2D.Double line = new Double(xPoints[i], yPoints[i], xPoints[pointTo], yPoints[pointTo]);
			lines.add(line);
		}
	}

	
	public void clearCurve() {
		//lines.clear();
	}
	
	
	public void drawOn(Graphics2D g) {
		g.drawOval(-radius, -radius, radius*2, radius*2);
		double bluetint = 0;
		for(int i = 0; i<lines.size(); i++) {
			bluetint += 255.0/lines.size();
			g.setColor(new Color(0, 0, (int) bluetint, 50));
			g.draw(lines.get(i));
		}
		g.setColor(black);
	}


	/**
	 * this shoudl be more effishent 
	 * @param g
	 * @param degree
	 */
	public void allInOne(Graphics2D g, double degree) {

		
		g.drawOval(-radius, -radius, radius*2, radius*2);
		double bluetint = 0;
		for(int i = 0; i<xPoints.length; i++) {
			int pointTo = (int) (i * degree + 0.5);
			pointTo = pointTo % (int)((totalPoints/1));
			Line2D.Double line = new Double(xPoints[i], yPoints[i], xPoints[pointTo], yPoints[pointTo]);
			bluetint += 255.0/xPoints.length;
			g.setColor(new Color(0, 0, (int) bluetint, 50));
			g.draw(line);
		}
		g.setColor(black);
		
	}
	
	public void incrementRadius(int amount) {
		this.radius += amount;
		assignPoints();
	}
	
	public int getRadius() {
		return this.radius;
	}
	
}

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class CurveComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double currentDegree = 1;
	private NeetCurve curve;
	private int points;
	private int speed;
	public CurveComponent(int points,int radius,double degree, int speed) {
		this.speed = speed;
		this.currentDegree = degree;
		this.points = points;
		this.curve = new NeetCurve(points, radius);
	}
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double hold = ((int) (currentDegree * 10000))/10000.0;
		g2.drawString("A and D to go forwards and backwards by 1 step", 0, 20);
		g2.drawString("Current Degree: "+hold+"", 0, 35);
		g2.drawString("W and S to increase and decraese Radius", 0, 50);
		g2.drawString("Current Radius: "+curve.getRadius()+"", 0, 65);
		g2.drawString("Press Space to pause", 0, 80);
		//Radius
		g2.translate(this.getWidth()/2, this.getHeight()/2);
		curve.allInOne(g2, currentDegree);
//		curve.createCurve(currentDegree);
//		curve.drawOn(g2);
		
	}

	public void tick() {
		currentDegree += (0.0001*speed);
		this.repaint();
		if(currentDegree > points*2) {
			currentDegree -= points*2;
		}
	}
	
	public void setDegree(double d) {
		this.currentDegree = d;
	}
	
	public void incrementRadius(int amount) {
		this.curve.incrementRadius(amount);
	}
	
	public void incrementDegree(int direction) {
		this.currentDegree += (0.0001*speed)*direction;
	}
	
	
	@Override
	public void repaint() {
		curve.clearCurve();
		super.repaint();
	}
	
}

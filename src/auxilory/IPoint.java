package auxilory;
public class IPoint extends Point{
	
	public IPoint(double r, double i) {
		super(r,i);
	}
	
	
	public IPoint mult(IPoint p2) {
		double tempx = x*p2.x - y*p2.y;
		double tempy = y*p2.x + x*p2.y;
		x= tempx;
		y = tempy;
		return this;
	}


	public Point divide(IPoint denominator) {
		double tempx = x*denominator.x + y*denominator.y;
		double tempy = y*denominator.x - x*denominator.y;
		double div = denominator.x*denominator.x +denominator.y*denominator.y;
		tempx = tempx/div;
		tempy = tempy/div;
		x = tempx;
		y = tempy;
		return this;
	}
}

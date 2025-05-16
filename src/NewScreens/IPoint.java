package NewScreens;


public class IPoint extends Point{
	
	public IPoint(double r, double i) {
		super(r,i);
	}
	
	
	public void mult(IPoint p2) {
		double tempx = x*p2.x - y*p2.y;
		double tempy = y*p2.x + x*p2.y;
		x= tempx;
		y = tempy;
	}
}

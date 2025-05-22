//TODO make this standard for future points
package auxilory;



public class Point {
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Point p2) {
		x += p2.x;
		y += p2.y; 
	}
	
	public double distance(Point p2) {
		return Math.sqrt(Math.pow(x-p2.x, 2)+Math.pow(y-p2.y, 2));
	}
	
	public double distanceOrigin() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	//TODO figure out if mult should be a thing
}

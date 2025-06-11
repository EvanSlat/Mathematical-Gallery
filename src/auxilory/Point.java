//TODO make this standard for future points
package auxilory;


//oporators that edit a point should always return themselves
public class Point implements Comparable<Point>{
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point add(Point p2) {
		x += p2.x;
		y += p2.y;
		return this;
	}
	
	public Point subtract(Point p2) {
		x -= p2.x;
		y -= p2.y;
		return this;
	}
	
	public double distance(Point p2) {
		return Math.sqrt(Math.pow(x-p2.x, 2)+Math.pow(y-p2.y, 2));
	}
	
	public double distanceOrigin() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}

	@Override
	public int compareTo(Point o) {
		return (int) (100*(Math.abs(x-o.x)+Math.abs(y-o.y)));
	}
	
	//TODO figure out if mult should be a thing
}

package auxilory;

public class PhysicsPoint extends Point{
	
	double vX;
	double vY;
	double mass;
	public PhysicsPoint(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public PhysicsPoint step(double dt) {
		this.x += vX*dt;
		this.y += vY*dt;
		return this;
	}
	
	public PhysicsPoint addV(PhysicsPoint pp) {
		this.vX += pp.vX;
		this.vY += pp.vY;
		return this;
	}
	
	public PhysicsPoint subtractV(PhysicsPoint pp) {
		this.vX -= pp.vX;
		this.vY -= pp.vY;
		return this;
	}
	public PhysicsPoint scalerMultV(double s) {
		this.vX *= s;
		this.vY *= s;
		return this;
		
	}
	public double dotProduct(PhysicsPoint pp) {
		return this.vX*pp.vX + this.vY*pp.vY;
	}
	
	//this should affect and change both of the PP objects
	public PhysicsPoint collision(PhysicsPoint pp) {
		//calculate normalized dot product
		double nd = ((this.vX-pp.vX)*(this.x-pp.x)+(this.vY-pp.vY)*(this.y-pp.y))/(distance(pp)*distance(pp));
		
		PhysicsPoint dv1 = new PhysicsPoint(this.x-pp.x, this.y-pp.y);
		PhysicsPoint dv2 = new PhysicsPoint(pp.x-this.x, pp.y-this.y);
		
		dv1.scalerMultV(nd).scalerMultV(2*pp.mass/(this.mass+pp.mass));
		dv2.scalerMultV(nd).scalerMultV(2*this.mass/(this.mass+pp.mass));
		
		
		pp.subtractV(dv2);
		return subtractV(dv1);
	}
}

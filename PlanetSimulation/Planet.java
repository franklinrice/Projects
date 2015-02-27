public class Planet {
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	double mass;
	String img;
	double xNetForce = 0;
	double yNetForce = 0;
	double xAccel;
	double yAccel;

	public Planet(double xPos, double yPos, double xVelo, double yVelo, double m, String picName) {
		x = xPos;
		y = yPos;
		xVelocity = xVelo;
		yVelocity = yVelo;
		mass = m;
		img = picName;
	}

	public double calcDistance(Planet p) {
		double dx = this.x - p.x;
		double dy = this.y - p.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	public double calcPairwiseForce(Planet p) {
		double force = 6.67e-11 * p.mass * this.mass / this.calcDistance(p)/this.calcDistance(p);
		return force;
	}

	public double calcPairwiseForceX(Planet p) {
		double forceX = this.calcPairwiseForce(p)*(p.x - this.x)/this.calcDistance(p);
		return forceX;
	}

	public double calcPairwiseForceY(Planet p) {
		double forceY = this.calcPairwiseForce(p)*(p.y - this.y)/this.calcDistance(p);
		return forceY;
	}

	public void setNetForce(Planet[] planets) {
		xNetForce = 0;
		yNetForce = 0;
		for (Planet p : planets) {
			if (p == this) {
			} else {
				this.xNetForce += this.calcPairwiseForceX(p);
				this.yNetForce += this.calcPairwiseForceY(p);
			}
		}
	}

	public void draw() {
		StdDraw.picture(this.x, this.y, "images/" + this.img);
	}

	public void update(double dt) {
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;
		xVelocity += dt * xAccel;
		yVelocity += dt * yAccel;
		x += dt* xVelocity;
		y += dt * yVelocity;
	}
}
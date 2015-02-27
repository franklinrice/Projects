public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In in = new In(filename);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planetArray = new Planet[N];
		for (int i=0; i < N; i++) {
			planetArray[i] = getPlanet(in);
		}
		StdDraw.setScale(-R, R);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p : planetArray) {
			p.draw();
		}
		double time = 0;
		while (time < T) {
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planetArray) {
				p.setNetForce(planetArray);
				p.update(dt);
				p.draw();
			}
			StdDraw.show(10);
			time += dt;
		}
		StdOut.printf("%d\n", N);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < N; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   planetArray[i].x, planetArray[i].y, planetArray[i].xVelocity, planetArray[i].yVelocity, planetArray[i].mass, planetArray[i].img);
    	}
	}

	public static Planet getPlanet(In info) {
		Planet rock = new Planet(info.readDouble(), info.readDouble(), info.readDouble(), info.readDouble(), info.readDouble(), info.readString());
		return rock;
	}
}
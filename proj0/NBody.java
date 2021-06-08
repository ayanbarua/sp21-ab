public class NBody {

	public static void main(String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-1 * radius, radius);
		StdDraw.enableDoubleBuffering();

		for (double time = 0; time < T; time += dt) {
			animateUniverse(dt, planets);
		}

		printUniverse(radius, planets);
	}

	private static void animateUniverse(double dt, Planet[] planets){
		int planetCount = planets.length;
		double[] xForces = new double[planetCount];
		double[] yForces = new double[planetCount];
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet planet:planets) {
      		planet.draw();
      	}

      	for (int i = 0; i < planetCount; i++) {
      		xForces[i] = planets[i].calcNetForceExertedByX(planets);
      		yForces[i] = planets[i].calcNetForceExertedByY(planets);
      	}

      	for (int i = 0; i < planetCount; i ++) {
      		planets[i].update(dt, xForces[i], yForces[i]);
      	}

		StdDraw.show();
		StdDraw.pause(10);
	}

	private static void printUniverse(double radius, Planet[] planets) {
		StdOut.printf("%d\n", planets.length);
    	StdOut.printf("%.2e\n", radius);

    	for (int i = 0; i < planets.length; i++) {
      		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
        		planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}

	public static double readRadius(String fileName){
		In in = new In(fileName);
		int planetCount = in.readInt();
		double readRadius = in.readDouble();
		return readRadius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int planetCount = in.readInt();
		double readRadius = in.readDouble();
		Planet[] allPlanets = new Planet[planetCount];

		for (int i = 0; i < planetCount; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			allPlanets[i] = planet;
		}
		return allPlanets;
	}
}
public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11; 

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet rocinate) {
		double dx = this.xxPos - rocinate.xxPos;
		double dy = this.yyPos - rocinate.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet rocinate) {
		double force = (G * this.mass * rocinate.mass) / (this.calcDistance(rocinate) * this.calcDistance(rocinate));
		return force;
	}

	public double calcForceExertedByX(Planet rocinate){
		double r = this.calcDistance(rocinate);
		double dx = rocinate.xxPos - this.xxPos;
		double force = this.calcForceExertedBy(rocinate);
		double forceX = (force * dx) / r;
		return forceX; 
	}

	public double calcForceExertedByY(Planet rocinate){
		double r = this.calcDistance(rocinate);
		double dy = rocinate.yyPos - this.yyPos;
		double force = this.calcForceExertedBy(rocinate);
		double forceY = (force * dy) / r;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sumX = 0;
		
		for (Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			} else{
				sumX += this.calcForceExertedByX(p);
			}
		}

		return sumX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double sumX = 0;
		
		for (Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			} else{
				sumX += this.calcForceExertedByY(p);
			}
		}

		return sumX;
	}




}
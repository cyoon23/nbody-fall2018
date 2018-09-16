
public class Body {
private double myXPos;
private double myYPos;
private double myXVel;
private double myYVel;
private double myMass;
private String myFileName;
public double G = 6.67 * 1e-11; 

/*Create a body from parameters xp (initial x position), yp (initial y position), xv (initial x velocity), yv (initial y velocity), etc. 
 * and equate instance variables to the parameters
 */
public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
	myXPos = xp;
	myYPos = yp;
	myXVel = xv;
	myYVel = yv;
	myMass = mass;
	myFileName = filename;
	 
}

// Copy constructor - copy instance variables from one body to this one 
// use "this" to refer to other constructor and sets its variables equal to that of the body  
public Body(Body b) { 
	this(b.getX(),b.getY(),b.getXVel(),b.getYVel(),b.getMass(),b.getName());
	this.myXPos = b.myXPos;
	this.myYPos = b.myYPos;
	this.myXVel = b.myXVel;
	this.myYVel = b.myYVel;
	this.myMass = b.myMass;
	this.myFileName = b.myFileName; 
}

public double getX() {
	return myXPos;
}

public double getY() {
	return myYPos;
	
}
public double getXVel() {
	return myXVel;
	
}

public double getYVel() {
	return myYVel;
	
}

public double getMass() {
	return myMass;
	
}

public String getName() {
	return myFileName;
}

/*
return distance between this body and another 
 b the other body to which distance r is calculated 
return distance between this body and b
*/
public double calcDistance(Body b) { 
	double dx = b.myXPos - myXPos;
	double dy = b.myYPos - myYPos;
	double r = Math.sqrt((dx*dx)+(dy*dy));
	return r;
}


/* 
* Uses Newton's Law of Universal Gravitation to find
  the force of gravity F between two bodies
 */

public double calcForceExertedBy(Body p) {
	double r = calcDistance(p);
	double F = G * myMass * p.myMass / (r*r);
	return F;
}

/* Calculate the x-component of the force Fx 
 * Using force F from calcForceExertedBy, 
 * difference between the x-coordinates 
 * and distance between the two bodies r  
 */
public double calcForceExertedByX(Body p) {
	double dx = p.myXPos - myXPos;
	double r = calcDistance(p);
	double F = calcForceExertedBy(p);
	double Fx = F * dx / r ;
	return Fx;
}
/* Calculate the x-component of the force Fy 
 * Using force F from calcForceExertedBy, 
 * difference between the y-coordinates 
 * and distance between the two bodies r  
 */
public double calcForceExertedByY(Body p) {
	double dy = p.myYPos - myYPos;
	double r = calcDistance(p);
	double F = calcForceExertedBy(p);
	double Fy = F * dy / r ;
	return Fy;
}
/* Calculate the net force netFx in the x-direction exerted on the body.
 * The for loop will loop over all the bodies and
 * the if statement will check for if the force is not that of the body. 
 */
public double calcNetForceExertedByX(Body[] bodies) {
	double netFx=0; 
	for (int i=0;i<bodies.length;i++) {
	if (! bodies[i].equals(this)) 
		netFx+=calcForceExertedByX(bodies[i]);
	}
	return netFx;
}
/* Calculate the net force netFy in the y-direction exerted on the body.
 * The for loop will loop over all the bodies and
 * the if statement will check for if the force is not that of the body. 
 */
public double calcNetForceExertedByY(Body[] bodies) {
	double netFy=0; 
	for (int i=0;i<bodies.length;i++) {
	if (! bodies[i].equals(this)) 
		netFy+=calcForceExertedByY(bodies[i]);
	}
	return netFy;
}

public void update(double deltaT, double xforce, double yforce) {  
	double ax = xforce/myMass;
	double ay = yforce/myMass;
	double nvx = myXVel + deltaT * ax;
	double nvy = myYVel + deltaT * ay;
	double nx = myXPos + deltaT * nvx;
	double ny = myYPos + deltaT * nvy;
	myXPos = nx;
	myYPos = ny;
	myXVel = nvx;
	myYVel = nvy;
}

public void draw() {
	StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
}
}







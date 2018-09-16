	

/**
 * @author christine yoon
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class NBody { 
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// use scanner to read values at beginning of file to find the radius.
		// The first scan for an int returns the number of bodies numBodies.
		// The next scan for a double returns the value of the radius of the universe.
		
		int numBodies = s.nextInt();
		double radius = s.nextDouble();
		
		System.out.println(s.nextLine());
		
		s.close(); 
		
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			int nb = s.nextInt(); // # bodies to be read
			double radius = s.nextDouble();
			
			// TODO: read # bodies, create array, ignore radius

			Body[] bodies = new Body[nb];
			
			for(int k=0; k < nb; k++) {
				
				// read data for each body
				// construct new body object and add to array
				
				double x = s.nextDouble();
				double y = s.nextDouble();
				double xv = s.nextDouble();
				double yv = s.nextDouble();
				double mass = s.nextDouble();
				String filename = s.next();
				
			Body bod = new Body(x, y, xv, yv, mass, filename); //creates object for place in for loop that contains info about position, velocity, mass, filename
			bodies[k] = bod; //places object in bodies index--replaces the '0'
				
	}
			s.close();
			
			// return array of body objects read
			return bodies;
	}

	public static void main(String[] args) throws FileNotFoundException{
		//Body b = new Body(); 
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			for (int i=0;i<bodies.length;i++) {
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}
			
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			
			for (int i=0; i<bodies.length;i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			
			for (int i=0;i<bodies.length;i++) {
				bodies[i].draw();
			}
			
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}

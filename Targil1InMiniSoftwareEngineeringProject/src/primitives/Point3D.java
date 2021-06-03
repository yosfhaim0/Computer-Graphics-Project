package primitives;

import static primitives.Util.*;

/**
 * A point in three-dimensional space of this shape: (x,y,z)
 * 
 * @author yosefHaim
 *
 */
public class Point3D {
	/**
	 * 
	 * x , y , z present the three dimension
	 */
	final Coordinate x, y, z;
	/**
	 * ZERO=(0,0,0)
	 */
	public static Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * point contractor whit 3 double number this number are Are replaced to
	 * coordinate 3D world
	 * 
	 * @param x Axis
	 * @param y Axis
	 * @param z Axis
	 */
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * 
	 * @return double number*
	 */
	public double getX() {
		return this.x.coord;
	}

	/**
	 * 
	 * @return double number*
	 */
	public double getY() {
		return this.y.coord;
	}

	/**
	 * 
	 * @return double number*
	 */
	public double getZ() {
		return this.z.coord;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	/**
	 * subtract((x1,y1,z1),(x2,y2,z2))=(x1-x2,y1-y2,z1-z2)
	 * 
	 * @param p the head of the vector for subtract
	 * @return vector (this point less given point)
	 */
	public Vector subtract(Point3D p) {
		return new Vector(this.x.coord - p.x.coord, this.y.coord - p.y.coord, this.z.coord - p.z.coord);
	}

	/**
	 * add((x1,y1,z1),(x2,y2,z2))=(x1+x2,y1+y2,z1+z2)
	 * 
	 * @param v vector new Add to existing vector
	 * @return new point the sum of the two point 3D
	 */
	public Point3D add(Vector v) {
		return new Point3D(this.x.coord + v.head.x.coord, //
				this.y.coord + v.head.y.coord, //
				this.z.coord + v.head.z.coord);

	}

	/**
	 * The distance between two points squares
	 * distanceSquared((x1,y1,z1)(x2,y2,z2))=(x1-x2)^2+(y1-y2)^2+(z1-z2)^2
	 * 
	 * @param other
	 * @return double
	 */
	public double distanceSquared(Point3D other) {
		return (this.x.coord - other.x.coord) * (this.x.coord - other.x.coord)
				+ (this.y.coord - other.y.coord) * (this.y.coord - other.y.coord)
				+ (this.z.coord - other.z.coord) * (this.z.coord - other.z.coord);
	}

	/**
	 * Distance between 2 points Uses the previous function named: double
	 * distanceSquared(Point3D) distance(x,y,z)=sqrt(x^2+y^2+z^2)
	 * 
	 * @param other Point3D
	 * @return double
	 */
	public double distance(Point3D other) {
		return Math.sqrt(this.distanceSquared(other));
	}

	/**
	 * find the Absolute minimum Coordinate
	 * 
	 * @return if Coordinate x is minimum return 'x'<br>
	 *         if Coordinate y is minimum return 'y'<br>
	 *         if Coordinate z is minimum return 'z'<br>
	 *         if all Coordinates are equal return 'x'
	 */
	public char findAbsoluteMinimumCoordinate() {
		double minimum = this.x.coord < 0 ? -this.x.coord : this.x.coord; // abs(x)
		char index = 'x';
		double y = this.y.coord < 0 ? -this.y.coord : this.y.coord; // abs(y)
		if (y < minimum) {
			minimum = y;
			index = 'y';
		}
		double z = this.z.coord < 0 ? -this.z.coord : this.z.coord; // abs(z)
		if (z < minimum) {
			index = 'z';
		}
		return index;
	}

	/**
	 * Function to the center of the circle,<br>
	 * which receives a circle,<br>
	 * and returns a random point on the circle
	 * 
	 * @param dir    The normal exiting the circle,<br>
	 *               together with the radius or height and length representing the
	 *               circle,
	 * @param radius radius of circle
	 * @return Returns a random point on the circle
	 */
	public Point3D randomPointOnRadius(Vector dir, double radius) {
		double diameter = radius * 2;
		Point3D p = randomPointOnRectangle(dir, diameter, diameter);
		double t = p.distance(this);
		while (t > radius) {
			p = randomPointOnRectangle(dir, diameter, diameter);
			t = p.distance(this);
		}
		return p;
	}

	/**
	 * Function to the center of the square,<br>
	 * which receives a square,<br>
	 * and returns a random point on the square
	 * 
	 * @param dir    The normal exiting the square,<br>
	 *               together with the radius or height and length representing the
	 *               square,
	 * @param width  of the square
	 * @param height of the square
	 * @return Returns a random point on the square
	 */
	public Point3D randomPointOnRectangle(Vector dir, double width, double height) {
		Vector firstNormal = dir.createOrthogonalVector();
		Vector secondNormal = firstNormal.crossProduct(dir).normalize();
		Point3D randomCirclePoint = this;
		double r;
		double wHalf = width / 2;
		r = random(0, wHalf);
		double x = random(-r, r);
		double hHalf = height / 2;
		r = random(0, hHalf);
		double y = random(-r, r);
		if (x != 0)
			randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
		if (y != 0)
			randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
		return randomCirclePoint;
	}
}
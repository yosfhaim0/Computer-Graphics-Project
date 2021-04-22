package primitives;

import static primitives.Util.*;

import java.util.List;

/**
 * (ray) - A fundamental object in geometry - the group of points on a straight
 * line that are on one relative side To a given point on the line called the
 * beginning of the ray. Defined by point and direction (unit vector) ...
 * 
 * @author yosefHaim
 *
 */

public class Ray {
	private Point3D p0;
	private Vector dir;

	/**
	 * ray ctor need point & vector
	 * 
	 * @param p point of the begin
	 * @param v direction of the ray
	 */
	public Ray(Point3D p, Vector v) {
		this.dir = v.normalized();
		this.p0 = p;
	}

	@Override
	public String toString() {
		return "p0: " + this.p0.toString() + " dir: " + this.dir.toString() + "\n";
	}

	/**
	 * retunr the start of ray
	 * 
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * direction
	 * 
	 * @return the direction
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * Calculates point on the ray at certain distance from the ray head
	 * @param t the distance from the ray head
	 * @return the point at the distance
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return p0.equals(other.p0) && dir.equals(other.dir);
	}
	/**
	 * Chooses the Closest Point to p0
	 * If the list is empty or null - the function returns null
	 * @param list of points in the ray
	 * @return point are the closest to p0
	 */
	public Point3D getClosestPoint(List<Point3D> list) {
		if(list==null)
			return null;
		double minDistance = Double.POSITIVE_INFINITY;
		Point3D closest = null;
		for (Point3D p : list) {
			double d = p0.distance(p);
			if (d < minDistance) {
				minDistance = d;
				closest = p;
			}
		}
		return closest;
	}
}

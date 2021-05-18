package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * (ray) - A fundamental object in geometry - the group of points on a straight
 * line that are on one relative side To a given point on the line called the
 * beginning of the ray. Defined by point and direction (unit vector) ...
 * 
 * @author yosefHaim
 *
 */

public class Ray {
	/**
	 * p0 is the start of ray
	 */
	private Point3D p0;
	/**
	 * the direction of ray
	 */
	private Vector dir;
	/**
	 * const for move head ray shadow
	 */
	private static final double DELTA = 0.1;

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

	/**
	 * 
	 * @param head
	 * @param direction
	 * @param normal
	 */
	public Ray(Point3D head, Vector direction, Vector normal) {
		this.dir = direction.normalized();
		double sign = alignZero(dir.dotProduct(normal));
		head = head.add(normal.scale(sign > 0?DELTA:-DELTA));
		this.p0 = head;
	}

	@Override
	public String toString() {
		return "p0: " + this.p0.toString() + " dir: " + this.dir.toString() + "\n";
	}

	/**
	 * Return the start of ray
	 * 
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * direction
	 * 
	 * @return the direction (unit vector)
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * Calculates point on the ray at certain distance from the ray head
	 * 
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
	 * Chooses the Closest GeoPoint to p0
	 * 
	 * @param list of points in the ray
	 * @return point are the closest to p0, <br>
	 *         If the list is empty or null - the function returns null
	 */
	public GeoPoint getClosestGeoPoint(List<GeoPoint> list) {
		if (list == null)
			return null;
		double minDistance = Double.POSITIVE_INFINITY;
		GeoPoint closest = null;
		for (GeoPoint p : list) {
			double d = p0.distance(p.point);
			if (d < minDistance) {
				minDistance = d;
				closest = p;
			}
		}
		return closest;
	}

}

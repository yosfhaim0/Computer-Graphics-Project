package primitives;

import java.util.LinkedList;
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
	 * ctor for for ray tracer(class)
	 * 
	 * @param head      of the ray
	 * @param direction dir of ray -most be normalize!-
	 * @param normal    normal for find the delta
	 * 
	 */
	public Ray(Point3D head, Vector direction, Vector normal) {
		double sign = alignZero(direction.dotProduct(normal));
		head = head.add(normal.scale(sign > 0 ? DELTA : -DELTA));
		this.dir = direction;
		this.p0 = head;
	}

	@Override
	public String toString() {
		return "p0: " + this.p0.toString() + "\n dir: " + this.dir.toString() + "\n";
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

	/**
	 * 
	 * *******For Glassy And Blurry*******<br>
	 * Gets the num of rays and the area's degrees where all the rays will be
	 * 
	 * @param normal    vector for calculate the direction of rays are turning
	 * @param NumOfRays num of additional rays
	 * @param radius    of the area for all the rays
	 * @param distance  of the radius circle from the head of the ray
	 * @return original ray among with the additional rays are randomly
	 */
	public List<Ray> raySplitter(Vector normal, int NumOfRays, double radius, double distance) {

		if (NumOfRays == 0)
			return List.of(this);

		double nv = alignZero(normal.dotProduct(dir));
		List<Ray> splittedRays = new LinkedList<>();
		Point3D centerCirclePoint = null;

		try {
			centerCirclePoint = this.getPoint(distance);
		} catch (Exception e) {
			centerCirclePoint = p0;
		}
		Point3D randomCirclePoint = null;
		double nr;
		for (int i = 0; i < NumOfRays; i++) {
			randomCirclePoint = randomPointOnRadius(centerCirclePoint, radius);
			Vector v = randomCirclePoint.subtract(p0);
			nr = normal.dotProduct(v);
			if (checkSign(nr, nv))
				splittedRays.add(new Ray(p0, v));
		}
		splittedRays.add(this);
		return splittedRays;
	}

	/**
	 * *******for antiAliasing*******<br>
	 * Gets the num of rays and the area's degrees where all the rays will be
	 * 
	 * @param NumOfRays num of additional rays
	 * @param radius    of the area for all the rays
	 * @param distance  of the radius circle from the head of the ray
	 * @return rays are randomly whitout the ray itself
	 */
	public List<Ray> raySplitter(int NumOfRays, double radius, double distance) {
		List<Ray> splittedRays = new LinkedList<>();
		Point3D centerCirclePoint = null;

		try {
			centerCirclePoint = this.getPoint(distance);
		} catch (Exception e) {
			centerCirclePoint = p0;
		}
		Point3D randomCirclePoint = null;
		for (int i = 0; i < NumOfRays; i++) {
			randomCirclePoint = randomPointOnRadius(centerCirclePoint, radius);
			Vector v = randomCirclePoint.subtract(p0);
			splittedRays.add(new Ray(p0, v));
		}
		return splittedRays;
	}

	/**
	 * *******for Depth Of Filed*******<br>
	 * Gets the num of rays and the area's degrees where all the rays will be
	 * 
	 * @param NumOfRays  num of additional rays
	 * @param radius     of the area for all the rays
	 * @param distance   of the radius circle from the head of the ray
	 * @param focalPoint target point, all the rays will go through this point
	 * @return original ray among with the additional rays
	 */
	public List<Ray> raySplitter(int NumOfRays, double radius, double distance, Point3D focalPoint) {
		List<Ray> splittedRays = new LinkedList<>();
		for (int i = 0; i < NumOfRays; i++) {
			Point3D point3d = randomPointOnRadius(p0, radius);
			Vector v = focalPoint.subtract(point3d);
			splittedRays.add(new Ray(point3d, v));
		}
		splittedRays.add(this);
		return splittedRays;
	}

	/**
	 * 
	 * 
	 * @param centerOfCircle center
	 * @param radius         radius of circle
	 * @return random point on the circle, or null if
	 */
	public Point3D randomPointOnRadius(Point3D centerOfCircle, double radius) {
		double kuter = radius * 2;
		Point3D p = randomPointOnRectangle(centerOfCircle, kuter, kuter);
		double t = p.distance(centerOfCircle);
		while (t > radius) {
			p = randomPointOnRectangle(centerOfCircle, kuter, kuter);
			t = p.distance(centerOfCircle);
		}
		return p;
	}

	/**
	 * 
	 * 
	 * @param centerOfCircle center
	 * @param radius         radius of circle
	 * @return random point on the circle
	 */
	public Point3D randomPointOnRectangle(Point3D centerOfCircle, double width, double height) {
		Vector firstNormal = dir.createVerticalVector();
		Vector secondNormal = firstNormal.crossProduct(dir).normalize();
		Point3D randomCirclePoint = centerOfCircle;
		double x = 0, y = 0, r = 0;
		x = random(-1, 1);
		y = random(-1, 1);
		r = random(-width / 2, width / 2);
		x = alignZero(x * r);
		r = random(-height / 2, height / 2);
		y = alignZero(y * r);
		if (x != 0)
			randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
		if (y != 0)
			randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
		return randomCirclePoint;
	}

}

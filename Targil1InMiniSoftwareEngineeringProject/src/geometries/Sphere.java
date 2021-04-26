package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Sphere = ball 3D Sphere represent by point and radius CTOR args:
 * point3d,radius
 * 
 * @author yosefHaim
 *
 */
public class Sphere implements Geometry {
	/**
	 * the center of Sphere
	 */
	private Point3D center;
	/**
	 * the radius of Sphere
	 */
	private double radius;
	private double rSquared; // squared radius

	/**
	 * ctor of Sphere The center = Point3D center
	 * 
	 * @param cen Point3D of center of Sphere
	 * @param rad double number
	 */
	public Sphere(Point3D cen, double rad) {
		this.center = cen;
		this.radius = rad;
		this.rSquared = rad * rad;
	}

	@Override
	public Vector getNormal(Point3D p) {
		/**
		 * n=normalize(P-Center)
		 */
		return p.subtract(center).normalize();
	}

	/**
	 * getter
	 * 
	 * @return Point3D the center of the Sphere
	 */
	public Point3D getCenter() {
		return this.center;
	}

	/**
	 * getter
	 * 
	 * @return double Radius of the Sphere
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return this.center.toString();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {

		// all the names is like the Instructions in a booklet d= distance center form
		// the ray(ortegonal line..) u= vector form the ray.p0 to center

		Vector u;

		// if center - getP0 =vector zero ERROR!!

		try {
			u = center.subtract(ray.getP0());
		} catch (IllegalArgumentException e) {
			return List.of(ray.getPoint(radius));
		}

		double tm = ray.getDir().dotProduct(u);
		double dSquared = alignZero(u.lengthSquared() - (tm * tm));

		// if distance center form the ray(orthogonal line..) >= radius there are no
		// intersections

		if (alignZero(dSquared - rSquared) >= 0)
			return null;

		// the point are t1,2 = tm+-th p = p0 + ti*v

		double th = Math.sqrt(rSquared - dSquared);
		double t1 = alignZero(tm - th);
		double t2 = alignZero(tm + th);
		// NB: t1 is always lower than t2!

		if (t2 <= 0)
			return null;
		return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
	}

}

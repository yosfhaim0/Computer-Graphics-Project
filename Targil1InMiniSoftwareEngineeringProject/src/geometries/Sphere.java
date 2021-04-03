package geometries;

import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Sphere = ball 3D
 * Sphere represent by point and radius
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

	/**
	 * ctor of Sphere The center = Point3D center
	 * 
	 * @param cen Point3D of center of Sphere
	 * @param red double number
	 */
	public Sphere(Point3D cen, double red) {
		this.center = cen;
		this.radius = red;
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
		/**
		 * all the names is like the Instructions in a booklet 
		 * d= distance center form the ray(ortegonal line..)
		 * u= vector form the ray.p0 to center
		 */
		double tm, d, uLength;
		Vector u;
		/**
		 * if center - getP0 =vector zero ERROR!!
		 */
		try {
			u = center.subtract(ray.getP0());
			tm = ray.getDir().dotProduct(u);
			uLength=u.length();
			d = alignZero(Math.sqrt(uLength * uLength - (tm * tm)));
		} catch (Exception e) {
			tm = 0;
			d = alignZero(Math.sqrt(tm * tm));
		}
		/**
		 * if distance center form the ray(ortegonal line..) > radius 
		 * there are no intersections
		 */
		if (d >= radius)
			return null;
		/**
		 * the point are t1,2 = tm+-th
		 * p = p0 + ti*v
		 */
		double th = Math.sqrt(radius * radius - d * d);
		if (alignZero(tm - th) <= 0 && alignZero(tm + th) <= 0) {
			return null;
		}
		// i konw ther are point i craet list
		List<Point3D> arr = new LinkedList<>();

		if (alignZero(tm - th) > 0)
			arr.add(ray.getP0().add(ray.getDir().scale(tm - th)));
		if (alignZero(tm + th) > 0)
			arr.add(ray.getP0().add(ray.getDir().scale(tm + th)));

		return arr;

	}

}


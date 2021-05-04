package geometries;

import static primitives.Util.*;

import java.util.List;

import primitives.*;

/**
 * Plane is infinite linear 3D surface The class represents Plane entity in our
 * 3D model
 * 
 * @author yosefHaim
 *
 */
public class Plane extends Geometry {
	/**
	 * q0 one of the point plane
	 */
	private Point3D q0;
	/**
	 * normal vector to the plane i.e. unit vector orthogonal to the plane
	 */
	private Vector normal;

	/**
	 * Constructor whit point and vector
	 * 
	 * @param p point on the Plane
	 * @param v normal horizontal to the Plane
	 */
	public Plane(Point3D p, Vector v) {
		this.q0 = p;
		this.normal = v.normalized();
	}

	/**
	 * Constructor that builds Plane from 3 points in the plane
	 * 
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		this.normal = v1.crossProduct(v2).normalize();
		this.q0 = p1;
	}

	/**
	 * q0 is the on of the par who present plane(there is a normal too)
	 * 
	 * @return point on the plane
	 */
	public Point3D getq0() {
		return this.q0;
	}

	@Override
	public Vector getNormal(Point3D p) {
		/**
		 * Normal form the q0
		 */
		return this.normal;
	}

	/**
	 * the normal present plane
	 * 
	 * @return Vector
	 */
	public Vector getNormal() {
		return this.normal;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		double nQMinusP0;
		double nv = normal.dotProduct(ray.getDir());
		try {
			nQMinusP0 = normal.dotProduct(q0.subtract(ray.getP0()));
		} catch (Exception e) {
			// if q0 == p0 there are no intersection
			return null;
		}
		// Because nv is equal to 0 it means that: 1. plane and the ray are parallel ->
		// no intersection points 2. they are contained each other (ray and the plane)
		// -> infinite intersection points
		if (isZero(nv))
			return null;

		// if t<0 it mean there are no intersection whit the ray
		// if t==0 it mean the begin of the ray are contained in the plane
		double t = alignZero(nQMinusP0 / nv);
		return t > 0 ? List.of(new GeoPoint(this, ray.getPoint(t))) : null;
	}
}

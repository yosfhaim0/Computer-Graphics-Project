package geometries;

import static primitives.Util.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.*;

/**
 * plane is Unlimited surface The class contains a vector and point, the vector
 * is perpendicular to the Plane, and the point is any point contained in the
 * Plane
 * 
 * @author yosefHaim
 *
 */
public class Plane implements Geometry {
	/**
	 * q0 one of the point plane
	 */
	private Point3D q0;
	/**
	 * normal form q0
	 */
	private Vector normal;

	/**
	 * Contractor whit point and vector
	 * 
	 * @param p point on the Plane
	 * @param v normal horizontal to the Plane
	 */
	public Plane(Point3D p, Vector v) {
		this.q0 = p;
		this.normal = v.normalized();
	}

	/**
	 * Constructor whit 3 point Creates two vectors from the points, cross Product
	 * between them, And in addition normalizes the result, And selects one of the
	 * points as the q0
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		Vector v1 = p1.subtract(p2);
		Vector v2 = p1.subtract(p3);
		this.normal = v1.crossProduct(v2).normalize();
		this.q0 = p1;
	}

	/**
	 * the fun create vector q0->p and dot dotProduct whit normal of plane if =0 the
	 * vectors are Vertical it mean the point in the plane return true otherwise the
	 * point is not in the plane return false
	 * 
	 * @param p point you want to check if it is within the plain
	 * @return true if point contained the plane and false otherwise
	 */
	public boolean chackIfPointInPlane(Point3D p) {
		Vector v = p.subtract(q0);
		if (alignZero(v.dotProduct(normal)) == 0)
			return true;
		return false;
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
	public List<Point3D> findIntersections(Ray ray) {
		double nQMinusP0;
		double nv = normal.dotProduct(ray.getDir());
		try {
			nQMinusP0 = normal.dotProduct(q0.subtract(ray.getP0()));
		} catch (Exception e) {
			// if q0 == p0 there are no intersection
			return null;
		}

		if (isZero(nv))
			/**
			 * Because nv is equal to 0 it means that: 1. plane and the ray are parallel ->
			 * no intersection points 2. they are contained each other (ray and the plane)
			 * -> infinite intersection points
			 */
			return null;
		// if t<0 it mean there are no intersection whit the ray
		// if t==0 it mean the begin of the ray are contained in the plane
		double t = alignZero(nQMinusP0 / nv);
		if (t > 0) {
			return List.of(ray.getPoint(t));
		}
		return null;
	}
}

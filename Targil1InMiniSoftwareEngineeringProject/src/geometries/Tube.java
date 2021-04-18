package geometries;

import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube with unlimited height tube represent by direction and radius
 * 
 * @author yosefHaim
 *
 */
public class Tube implements Geometry {
	/**
	 * the ray of the tube he present the direction of tube
	 */
	protected Ray axisRay;
	/**
	 * radius of the tube
	 */
	protected double radius;

	/**
	 * ctor of: tube Infinity shape
	 * 
	 * @param ray    the direction of Tube
	 * @param radius of the tube
	 */
	public Tube(Ray ray, double radius) {
		this.axisRay = ray;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D p) {

		Vector v = this.axisRay.getDir();
		Point3D p0 = axisRay.getP0();
		/**
		 * נ�‘¡ = נ�‘£ גˆ™ (נ�‘ƒ גˆ’ נ�‘ƒ0)//distance between the level high of p0 and p
		 */

		double t = alignZero(v.dotProduct(p.subtract(p0)));
		Point3D o;
		if (t != 0) {
			/**
			 * t =is the distance scalr whit unit vector v O = the center of the tube in the
			 * level of the point p נ�‘‚ = נ�‘ƒ0 + נ�‘¡ גˆ™ נ�‘£ if(t==0) vector 0
			 * build=error
			 */
			o = axisRay.getPoint(t);
		} else {
			/**
			 * if t=0 o and p in the same "level" and: נ�‘‚ = נ�‘ƒ0
			 */
			o = p0;
		}
		return p.subtract(o).normalize();
	}

	/**
	 * getter ray of tube
	 * 
	 * @return Ray
	 */
	public Ray getAxisRay() {
		return this.axisRay;
	}

	/**
	 * getter radius
	 * 
	 * @return double
	 */
	public double getRadius() {
		return this.radius;
	}

	@Override
	public String toString() {
		return "AxisRay: " + this.axisRay.toString() + " Radius: " + this.radius + "\n";
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		Vector vAxis = axisRay.getDir();
		Vector v = ray.getDir();
		Point3D p0 = ray.getP0();

		// At^2+Bt+C=0
		double a = 0;
		double b = 0;
		double c = 0;

		double vVa = alignZero(v.dotProduct(vAxis));
		Vector vVaVa;
		Vector vMinusVVaVa;
		if (vVa == 0) // the ray is orthogonal to the axis
			vMinusVVaVa = v;
		else {
			vVaVa = vAxis.scale(vVa);
			try {
				vMinusVVaVa = v.subtract(vVaVa);
			} catch (IllegalArgumentException e1) { // the rays is parallel to axis
				return null;
			}
		}
		// A = (v-(v*va)*va)^2
		a = vMinusVVaVa.lengthSquared();

		Vector deltaP = null;
		try {
			deltaP = p0.subtract(axisRay.getP0());
		} catch (IllegalArgumentException e1) { // the ray begins at axis P0
			if (vVa == 0) // the ray is orthogonal to Axis
				return List.of(ray.getPoint(radius));
			return List.of(ray.getPoint(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared())));
		}

		double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
		Vector dPVaVa;
		Vector dPMinusdPVaVa;
		if (dPVAxis == 0)
			dPMinusdPVaVa = deltaP;
		else {
			dPVaVa = vAxis.scale(dPVAxis);
			try {
				dPMinusdPVaVa = deltaP.subtract(dPVaVa);
			} catch (IllegalArgumentException e1) {
				return List.of(ray.getPoint(Math.sqrt(radius * radius / a)));
			}
		}

		// B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
		b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
		c = dPMinusdPVaVa.lengthSquared() - radius * radius;

		// A*t^2 + B*t + C = 0 - lets resolve it
		double discr = alignZero(b * b - 4 * a * c);
		if (discr <= 0)
			return null; // the ray is outside or tangent to the tube

		double doubleA = 2 * a;
		double tm = alignZero(-b / doubleA);
		double th = Math.sqrt(discr) / doubleA;
		if (isZero(th))
			return null; // the ray is tangent to the tube

		double t1 = alignZero(tm - th);
		double t2 = alignZero(tm + th);
		if (t1 > 0 && t2 > 0)
			return List.of(ray.getPoint(t1), ray.getPoint(t2));
		if (t1 > 0)
			return List.of(ray.getPoint(t1));
		if (t2 > 0)
			return List.of(ray.getPoint(t2));

		return null;
	}

}

package geometries;

import static primitives.Util.*;

import java.util.List;

import primitives.*;

/**
 * Tube with unlimited height tube represent by direction and radius
 * 
 * @author yosefHaim
 *
 */
public class Tube extends Geometry {
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

		// distance between the level high of p0 and p
		double t = v.dotProduct(p.subtract(p0));
		// if t=0 p0 and p in the same "level" and o=p0
		// else t =is the distance scalr whit unit vector v O = the center of the tube
		// in the
		// level of the point p
		Point3D o = isZero(t) ? p0 : axisRay.getPoint(t);

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
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
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
		if (isZero(vVa)) // the ray is orthogonal to the axis
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
			if (isZero(vVa)) { // the ray is orthogonal to Axis
				return (alignZero(radius - maxDistance) <= 0) ? List.of(new GeoPoint(this, ray.getPoint(radius)))
						: null;// for unshaded
			}
			double rSlasVminusV = Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared());
			return (alignZero(rSlasVminusV - maxDistance) <= 0)
					? List.of(new GeoPoint(this, ray.getPoint(rSlasVminusV)))
					: null;// for unshaded
		}

		double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
		Vector dPVaVa;
		Vector dPMinusdPVaVa;
		double rrSlasA;
		if (isZero(dPVAxis))
			dPMinusdPVaVa = deltaP;
		else {
			dPVaVa = vAxis.scale(dPVAxis);
			try {
				dPMinusdPVaVa = deltaP.subtract(dPVaVa);
			} catch (IllegalArgumentException e1) {
				rrSlasA = Math.sqrt(radius * radius / a);
				return (alignZero(rrSlasA - maxDistance) <= 0) ? List.of(new GeoPoint(this, ray.getPoint(rrSlasA)))
						: null;
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
		double t2 = alignZero(tm + th); // always: t2 > t1
		if (t2 <= 0)
			return null;
		if (alignZero(t2 - maxDistance) <= 0)
			return t1 > 0 ? List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)))
					: List.of(new GeoPoint(this, ray.getPoint(t2)));
		return alignZero(t1 - maxDistance) < 0 && t1 > 0 ? List.of(new GeoPoint(this, ray.getPoint(t1))) : null;
	}

	@Override
	protected void CreateBoundingBox() {

		double x = axisRay.GetXCordOfHead(), y = axisRay.GetYCordOfHead(), z = axisRay.GetZCordOfHead();
		if (x == 0) {
			maxX = axisRay.getP0().getX() + radius;
			minX = axisRay.getP0().getX() - radius;
		} else {
			minX = Double.NEGATIVE_INFINITY;
			maxX = Double.POSITIVE_INFINITY;
		}
		if (y == 0) {
			maxY = axisRay.getP0().getY() + radius;
			minY = axisRay.getP0().getY() - radius;
		} else {
			minY = Double.NEGATIVE_INFINITY;
			maxY = Double.POSITIVE_INFINITY;
		}
		if (z == 0) {
			maxZ = axisRay.getP0().getZ() + radius;
			minZ = axisRay.getP0().getZ() - radius;
		} else {
			minZ = Double.NEGATIVE_INFINITY;
			maxZ = Double.POSITIVE_INFINITY;
		}
	}
}

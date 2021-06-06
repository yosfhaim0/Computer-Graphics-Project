package geometries;

import java.util.LinkedList;
import static primitives.Util.*;
import java.util.List;

import primitives.*;

/**
 * Tube with limited height
 * 
 * @author yosefHaim
 *
 */
public class Cylinder extends Tube {
	/**
	 * the height of tube more parm for cylinder is height
	 */
	private double height;
	private Plane base1;
	private Plane base2;

	/**
	 * ctor for: Tube with limited height
	 * 
	 * @param ray    the start of the Cylinder
	 * @param rad    The width(rad*2) of the Cylinder
	 * @param height the height of the Cylinder
	 */
	public Cylinder(Ray ray, double rad, double height) {
		super(ray, rad);
		this.height = height;
		Vector v = ray.getDir();
		Point3D o1 = ray.getP0();
		Point3D o2 = ray.getPoint(height);
		base1 = new Plane(o1, v);
		base2 = new Plane(o2, v);
	}

	/**
	 * return the Height of Cylinder
	 * 
	 * @return double
	 */
	public double getHeight() {
		return this.height;
	}

	@Override
	public Vector getNormal(Point3D p) {
		Vector v = this.axisRay.getDir();
		Point3D p0 = axisRay.getP0();

		// distance between the level high of p0 and p
		double t = v.dotProduct(p.subtract(p0));
		// if t=0 or t=height - the point is on one of the bases
		if (isZero(t) || isZero(t - this.height))
			return v;

		// else t =is the distance scalr whit unit vector v O = the center of the tube
		// in the level of the point p
		Point3D o = axisRay.getPoint(t);
		return p.subtract(o).normalize();
	}

	@Override
	public String toString() {
		return super.toString() + " height: " + height;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		GeoPoint p1 = null;
		GeoPoint p2 = null;
		Point3D o1 = base1.getq0();
		var ints = base1.findGeoIntersections(ray, maxDistance);
		if (ints != null) {
			GeoPoint p = ints.get(0);
			if (alignZero(o1.distance(p.point) - radius) <= 0)
				p1 = p;
		}
		Point3D o2 = base2.getq0();
		ints = base2.findGeoIntersections(ray, maxDistance);
		if (ints != null) {
			GeoPoint p = ints.get(0);
			if (alignZero(o2.distance(p.point) - radius) <= 0)
				p2 = p;
		}
		if (p1 != null && p2 != null) {
			p1.geometry = this;
			p2.geometry = this;
			return List.of(p1, p2);
		}

		List<GeoPoint> pointIntersectTube = super.findGeoIntersections(ray, maxDistance);
		if (pointIntersectTube == null) {
			if (p1 != null) {
				p1.geometry = this;
				return List.of(p1);
			}
			if (p2 != null) {
				p2.geometry = this;
				return List.of(p2);
			}
			return null;
		}

		Vector v = axisRay.getDir();

		if (p1 == null && p2 == null) {
			// bases are not intersected
			// therefore all tube intersections - either all inside or all outside
			for (GeoPoint p : pointIntersectTube) {
				double distanceFromLowBase = alignZero(p.point.subtract(o1).dotProduct(v));
				if (distanceFromLowBase <= 0 || alignZero(distanceFromLowBase - height) >= 0)
					return null;
			}
			for (GeoPoint geoPoint : pointIntersectTube) {
				geoPoint.geometry = this;
			}
			return pointIntersectTube;
		}

		// if we are here - one base is intersected
		List<GeoPoint> resultList = new LinkedList<GeoPoint>(List.of(p1 == null ? p2 : p1));
		for (GeoPoint p : pointIntersectTube) {
			double distanceFromLowBase = alignZero(p.point.subtract(o1).dotProduct(v));
			if (distanceFromLowBase > 0 && alignZero(distanceFromLowBase - height) < 0)
				resultList.add(p);
		}
		resultList.get(0).geometry = this;
		return resultList;
	}

	@Override
	protected void setBox() {
		double x = axisRay.getDir().getHead().getX(), y = axisRay.getDir().getHead().getY(),
				z = axisRay.getDir().getHead().getZ();
		Ray ray = new Ray(axisRay.getP0(), axisRay.getDir().createOrthogonalVector());
		if (x == 0) {
			minX = ray.getPoint(-radius).getX();
			maxX = ray.getPoint(radius).getX();
		} else {
			minX = axisRay.getPoint(-radius).getX();
			maxX = axisRay.getPoint(radius + height).getX();
		}
		if (y == 0) {
			minY = ray.getPoint(-radius).getY();
			maxY = ray.getPoint(radius).getY();
		} else {
			minY = axisRay.getPoint(-radius).getY();
			maxY = axisRay.getPoint(radius + height).getY();
		}
		if (z == 0) {
			minZ = ray.getPoint(-radius).getZ();
			maxZ = ray.getPoint(radius).getZ();
		} else {
			minZ = axisRay.getPoint(-radius).getZ();
			maxZ = axisRay.getPoint(radius + height).getZ();
		}
		middlePoint = getMiddlePoint();
		finity = true;
	}
}
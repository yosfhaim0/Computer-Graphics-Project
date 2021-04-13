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
		/**
		 * downPlane=the plane low base contained upPlane=the plane up base contained
		 */
		Plane downPlane = new Plane(axisRay.getP0(), axisRay.getDir());
		Plane upPlane = new Plane(axisRay.getP0().add(axisRay.getDir().scale(height)), axisRay.getDir());
		/**
		 * if point if the one of the planes(up or down) return the dir of Cylinder
		 * otherwise return normal form the side and use the tube.getNormal()
		 */
		if (downPlane.chackIfPointInPlane(p) || upPlane.chackIfPointInPlane(p)) {
			return this.axisRay.getDir();
		} else {
			return super.getNormal(p);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " height: " + height;
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> pointIntersectTube = super.findIntersections(ray);
		List<Point3D> pointIntersectCylinder = new LinkedList<>();
		// Check if point on tube are in the cylinder
		if (pointIntersectTube != null) {
			for (Point3D i : pointIntersectTube) {
				double distansFormLowBase = i.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
				if (alignZero(distansFormLowBase) >= 0 && distansFormLowBase <= height) {
					pointIntersectCylinder.add(i);
				}
			}
		}
		// Check if ray intersect whit low base
		Point3D tempPointForLowBase = findIntersectionCircal(axisRay.getP0(), ray);
		if (tempPointForLowBase != null && pointIntersectCylinder.indexOf(tempPointForLowBase) == -1)
			pointIntersectCylinder.add(tempPointForLowBase);
		// Check if ray intersect whit up base
		Point3D tempPointForUpBase = findIntersectionCircal(axisRay.getPoint(height), ray);
		if (tempPointForUpBase != null && pointIntersectCylinder.indexOf(tempPointForUpBase) == -1)
			pointIntersectCylinder.add(tempPointForUpBase);
		// if ther is no point return null
		if (pointIntersectCylinder.isEmpty())
			return null;
		return pointIntersectCylinder;

	}

	/**
	 * return the point are intersect whit the base of cylinder
	 * 
	 * @param p   point of the center of base of the cylinder
	 * @param ray are supposed to intersect white the base
	 * @return point3d intersect or null otherwise
	 */
	public Point3D findIntersectionCircal(Point3D p, Ray ray) {
		// plane of the base
		Plane planeContainBase = new Plane(p, axisRay.getDir());
		List<Point3D> intersectPlaneContainBase = planeContainBase.findIntersections(ray);
		if (intersectPlaneContainBase != null) {
			if (intersectPlaneContainBase.get(0).distance(p) <= radius) {
				return intersectPlaneContainBase.get(0);
			}
		}
		return null;

	}

	
}

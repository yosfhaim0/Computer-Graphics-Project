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
	if (isZero(t) || isZero(t - this.height)) return v;

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
    public List<Point3D> findIntersections(Ray ray) {
	Point3D p1 = null;
	Point3D p2 = null;
	Point3D o1 = base1.getq0();
	var ints = base1.findIntersections(ray);
	if (ints != null) {
	    Point3D p = ints.get(0);
	    if (alignZero(o1.distance(p) - radius) <= 0) p1 = p;
	}
	Point3D o2 = base2.getq0();
	ints = base2.findIntersections(ray);
	if (ints != null) {
	    Point3D p = ints.get(0);
	    if (alignZero(o2.distance(p) - radius) <= 0) p2 = p;
	}
	if (p1 != null && p2 != null) return List.of(p1, p2);

	List<Point3D> pointIntersectTube = super.findIntersections(ray);
	if (pointIntersectTube == null) {
	    if (p1 != null) return List.of(p1);
	    if (p2 != null) return List.of(p2);
	    return null;
	}

	Vector v = axisRay.getDir();
	for (Point3D p : pointIntersectTube) {
	    double distanceFromLowBase = alignZero(p.subtract(o1).dotProduct(v));
	    if (distanceFromLowBase > 0 && alignZero(distanceFromLowBase - height))
	}
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
	if (pointIntersectCylinder.isEmpty()) return null;
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

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

	if (p1 == null && p2 == null) {
		// bases are not intersected
		// therefore all tube intersections - either all inside or all outside
		for (Point3D p : pointIntersectTube) {
		    double distanceFromLowBase = alignZero(p.subtract(o1).dotProduct(v));
		    if (distanceFromLowBase <= 0 || alignZero(distanceFromLowBase - height) >= 0)
		    	return null;
		}
		return pointIntersectTube;
	}
	
	// if we are here - one base is intersected
	List<Point3D> resultList = new LinkedList<Point3D>(List.of(p1 == null? p2 : p1));
	for (Point3D p : pointIntersectTube) {
	    double distanceFromLowBase = alignZero(p.subtract(o1).dotProduct(v));
	    if (distanceFromLowBase > 0 && alignZero(distanceFromLowBase - height)<0)
	    	resultList.add(p);
	}
	return resultList;


    }

}

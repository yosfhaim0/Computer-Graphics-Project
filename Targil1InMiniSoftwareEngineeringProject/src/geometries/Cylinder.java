package geometries;

import primitives.*;

/**
 * Tube with limited height
 * 
 * @author yosefHaim
 *
 */
public class Cylinder extends Tube {
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

	/**
	 * return the Height of Cylinder
	 * 
	 * @return double
	 */
	public double getHeight() {
		return this.height;
	}

	@Override
	public String toString() {
		return super.toString() + " height: " + height;
	}
}

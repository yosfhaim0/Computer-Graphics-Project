package geometries;

import primitive.*;
/**
 * Tube with limited height
 * @author yosefHaim 
 *
 */
public class Cylinder extends Tube{
	private double height;
	/**
	 * 
	 * @param ray the start of the Cylinder
	 * @param rad The width(rad*2) of the Cylinder
	 * @param height the height of the Cylinder
	 */
	public Cylinder(Ray ray,double rad,double height) {
		super(ray,rad);
		this.height=height;
	}
	@Override
	public Vector getNormal(Point3D p) {
	if(trueIfPointOnOneOfTheBase(p)) {
		return this.axisRay.getDir();
	}else {
	return super.getNormal(p);}
	}
	/**
	 * A private function that checks whether 
	 * the point obtained is in one of the bases of the cylinder or not
	 * @param Point3D p
	 * @return boolean if true it on a base
	 */
	private boolean trueIfPointOnOneOfTheBase(Point3D p){
		Vector v = p.subtract(this.axisRay.getP0());
		Vector v1=p.subtract(this.axisRay.getDir().normalized().scale(height).getHead());
		if(v.dotProduct(this.axisRay.getDir())==0||v1.dotProduct(this.axisRay.getDir())==0) {
			return true;
		}
		return false;
	}
	public double getHeight() {return this.height;}
	@Override
	public String toString() {
		return super.toString()+" height: "+height;
	}
}

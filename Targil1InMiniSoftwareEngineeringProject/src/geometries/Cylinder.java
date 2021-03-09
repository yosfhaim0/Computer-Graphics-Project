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
	public Vector getNormal(Point3D p) {
		return null;
	}
	public double getHeight() {return this.height;}
	@Override
	public String toString() {
		return super.toString()+" height: "+height;
	}
}

package geometries;
import primitive.*;
public class Cylinder extends Tube{
	private double height;
	public Vector getNormal(Point3D p) {
		return null;
	}
	public Cylinder(Ray ray,double rad,double height) {
		super(ray,rad);
		this.height=height;
	}
	public double getHeight() {return this.height;}
	@Override
	public String toString() {
		return super.toString()+" height: "+height;
	}
}

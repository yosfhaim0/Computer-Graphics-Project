package geometries;

import primitive.Point3D;
import primitive.Ray;
import primitive.Vector;
/**
 * Tube with unlimited height
 * @author yosefHaim
 *
 */
public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;
	public Tube(Ray ray, double radius) {
		// TODO Auto-generated constructor stub
		this.axisRay=ray;
		this.radius=radius;
	}
	@Override
	public Vector getNormal(Point3D p) {
		Vector v=this.axisRay.getDir();
		double t=v.dotProduct(p.subtract(axisRay.getP0()));
		Point3D o;
		if(t!=0) {
		o= axisRay.getP0().add(v.scale(t));
		}else {
			o=axisRay.getP0();
		}
		return p.subtract(o).normalize();		
	}
	public Ray getAxisRay() {return this.axisRay;}
	public double getRadius() {return this.radius;}
	@Override
	public String toString() {
		return "AxisRay: "+this.axisRay.toString()+" Radius: "+this.radius+"\n";
	}
}

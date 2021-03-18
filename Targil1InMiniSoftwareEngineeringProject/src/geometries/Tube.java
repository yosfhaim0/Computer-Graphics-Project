package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
		/**
		 * 𝑡 = 𝑣 ∙ (𝑃 − 𝑃0)//distance between the level high of p0 and  p
		 */
		double t=v.dotProduct(p.subtract(axisRay.getP0()));
		Point3D o;
		try {
			/**
			 * t =is the distance scalr whit unit vector v
			 * O = the center of the tube in the level of the point p
			 * 𝑂 = 𝑃0 + 𝑡 ∙ 𝑣
			 * if(t==0) vector 0 build=error
			 */
		o= axisRay.getP0().add(v.scale(t));
		}catch (IllegalArgumentException e) {
			/**
			 * if t=0 o and p in the same "level" and:
			 * 𝑂 = 𝑃0
			 */
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
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}

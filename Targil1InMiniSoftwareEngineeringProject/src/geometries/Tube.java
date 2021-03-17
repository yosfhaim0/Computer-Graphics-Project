package geometries;

import java.util.List;
import static primitives.Util.*;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube with unlimited height
 * 
 * @author yosefHaim
 *
 */
public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;

	/**
	 * ctor of: tube Infinity shape
	 * 
	 * @param ray    the direction of Tube
	 * @param radius of the tube
	 */
	public Tube(Ray ray, double radius) {
		// TODO Auto-generated constructor stub
		this.axisRay = ray;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point3D p) {

		Vector v = this.axisRay.getDir();
		Point3D p0 = axisRay.getP0();
		/**
		 * 𝑡 = 𝑣 ∙ (𝑃 − 𝑃0)//distance between the level high of p0 and p
		 */

		double t = alignZero(v.dotProduct(p.subtract(p0)));
		Point3D o;
		if (t != 0) {
			/**
			 * t =is the distance scalr whit unit vector v O = the center of the tube in the
			 * level of the point p 𝑂 = 𝑃0 + 𝑡 ∙ 𝑣 if(t==0) vector 0 build=error
			 */
			o = p0.add(v.scale(t));
		} else {
			/**
			 * if t=0 o and p in the same "level" and: 𝑂 = 𝑃0
			 */
			o = p0;
		}
		return p.subtract(o).normalize();
	}

	/**
	 * getter
	 * 
	 * @return Ray
	 */
	public Ray getAxisRay() {
		return this.axisRay;
	}

	/**
	 * getter
	 * 
	 * @return double
	 */
	public double getRadius() {
		return this.radius;
	}

	@Override
	public String toString() {
		return "AxisRay: " + this.axisRay.toString() + " Radius: " + this.radius + "\n";
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}

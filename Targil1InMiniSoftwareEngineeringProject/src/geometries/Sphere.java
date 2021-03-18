package geometries;

import primitive.Point3D;
import primitive.Vector;
/**
 * Sphere = ball
 * @author yosefHaim
 *
 */
public class Sphere implements Geometry {
	private Point3D center;
	private double radius;
	/**
	 * The center  = Point3D cen
	 * @param cen
	 */
	 public Sphere(Point3D cen,double red) {
		 this.center=cen;
		 this.radius=red;
	 }
	@Override
	public Vector getNormal(Point3D p) {
		return p.subtract(center).normalize();
	}
	/**
	 * 
	 * @return Point3D the center of the Sphere
	 */
	public Point3D getCenter() {return this.center;}
	/**
	 * 
	 * @return double Radius of the Sphere
	 */
	public double getRadius() {return radius;}
	@Override
	public String toString() {
		return this.center.toString();
	}
	
}

package geometries;

import primitive.Point3D;
import primitive.Vector;

public class Sphere implements Geometry {
	private Point3D center;
	 public Sphere(Point3D cen) {
		 this.center=cen;
	 }
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * this getter is the only in the class(supposed to be radius too but 
	 * in this level there are the orders )
	 * @return return the center of the Sphere
	 */
	public Point3D getCenter() {return this.center;}
	@Override
	public String toString() {
		return this.center.toString();
	}
}

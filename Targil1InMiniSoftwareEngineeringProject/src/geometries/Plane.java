package geometries;

import primitive.Point3D;
import primitive.Vector;

public class Plane implements Geometry {
	private Point3D q0;
	private Vector normal;
	/**
	 * Contractor whit point and vector
	 * @param p point on the Plane
	 * @param v normal horizontal to the Plane
	 */
	public Plane(Point3D p,Vector v) {
		this.q0=p;
		this.normal=v;
	}
	
	public Plane(Point3D p1,Point3D p2,Point3D p3) {
		
	}
	/**
	 * q0 is the on of the par who present plane(there is a normal too)
	 * @return point on the plane 
	 */
	public Point3D getq0() {return this.q0;}
	/**
	 * Normal form the q0
	 * @return the normal form the q0
	 */
	public Vector getNormal() {return this.normal;}
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}

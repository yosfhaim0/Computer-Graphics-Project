package geometries;

import primitive.Point3D;
import primitive.Vector;
/**
 * plane is Unlimited surface
 * The class contains a vector and point, 
 * the vector is perpendicular to the Plane,
 *  and the point is any point contained in the Plane
 * @author yosefHaim
 *
 */
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
	/**
	 * Constructor whit 3 point
	 * Creates two vectors from the points,
     *cross Product between them,
     *And in addition normalizes the result,
     *And selects one of the points as the q0
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1,Point3D p2,Point3D p3) {
		Vector v1=new Vector(p1.subtract(p2).getHead());
		Vector v2=new Vector(p1.subtract(p3).getHead());
		this.normal=new Vector(v1.crossProduct(v2).normalized().getHead());
		this.q0=p1;
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

package geometries;

import primitive.Point3D;
import primitive.Ray;
import primitive.Vector;

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
		// TODO Auto-generated method stub
		return null;
	}
	public Ray getAxisRay() {return this.axisRay;}
	public double getRadius() {return this.radius;}
	@Override
	public String toString() {
		return "AxisRay: "+this.axisRay.toString()+" Radius: "+this.radius+"\n";
	}
}

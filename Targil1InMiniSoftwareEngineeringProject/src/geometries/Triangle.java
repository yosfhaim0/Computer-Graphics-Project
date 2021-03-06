package geometries;

import primitive.Point3D;
import primitive.Vector;

public class Triangle extends Polygon implements Geometry {
	public Triangle(Point3D p1,Point3D p2,Point3D p3) {
		//i not sure this ligel....
		super( new Point3D[] {p1,p2,p3});
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane + "]";
	}

}

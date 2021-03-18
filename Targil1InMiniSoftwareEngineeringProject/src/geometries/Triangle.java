package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon implements Geometry {
	/**
	 * Builder with three vertices (Points3D)
	 * @param p1 Vertex of the triangle
	 * @param p2 Vertex of the triangle
	 * @param p3 Vertex of the triangle
	 */
	public Triangle(Point3D p1,Point3D p2,Point3D p3) {
		//i not sure this ligel....
		super(p1,p2,p3);
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		/**
		 * Triangle like polygon
		 * and polygon are in Plane
		 */
		return super.getNormal(p);
	}

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane + "]";
	}
	@Override
	public List<Point3D> findIntersections(Ray ray){
		return null;
	}

}

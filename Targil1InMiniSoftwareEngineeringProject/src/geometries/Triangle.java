package geometries;

import java.util.List;
import static primitives.Util.*;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import unittests.VectorTests;
/**
 * Triangle is a polygon whit only 3 vertices
 * @author yosefHaim
 *
 */
public class Triangle extends Polygon implements Geometry {
	/**
	 * ctor with three vertices (Points3D)
	 * 
	 * @param p1 Vertex of the triangle
	 * @param p2 Vertex of the triangle
	 * @param p3 Vertex of the triangle
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane + "]";
	}

}

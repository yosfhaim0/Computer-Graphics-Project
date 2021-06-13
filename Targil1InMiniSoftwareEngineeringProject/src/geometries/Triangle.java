package geometries;

import static primitives.Util.*;

import java.util.List;

import primitives.*;

/**
 * Triangle is a polygon whit only 3 vertices
 * 
 * @author yosefHaim
 *
 */
public class Triangle extends Polygon {
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

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> result = plane.findGeoIntersections(ray, maxDistance);
		if (result == null) {
			return null;
		}
		Point3D p = ray.getP0();
		Vector v = ray.getDir();
		// Create vector to the vertices
		Vector Subtract0 = vertices.get(0).subtract(p);
		Vector Subtract1 = vertices.get(1).subtract(p);
		Vector Subtract2 = vertices.get(2).subtract(p);

		// Create normal form Sides
		Vector crossProduct_normalize0 = Subtract0.crossProduct(Subtract1).normalized();
		Vector crossProduct_normalize1 = Subtract1.crossProduct(Subtract2).normalized();
		Vector crossProduct_normalize2 = Subtract2.crossProduct(Subtract0).normalized();

		// arr_NdotProductDir= normal * direction of ray
		double NdotProductDir0 = crossProduct_normalize0.dotProduct(v);
		double NdotProductDir1 = crossProduct_normalize1.dotProduct(v);
		double NdotProductDir2 = crossProduct_normalize2.dotProduct(v);
		if (isZero(NdotProductDir0) || isZero(NdotProductDir1) || isZero(NdotProductDir2))
			return null;

		// The point is inside if all
		if (!(checkSign(NdotProductDir0, NdotProductDir1)) || !(checkSign(NdotProductDir1, NdotProductDir2))
				|| !(checkSign(NdotProductDir2, NdotProductDir0)))
			return null;
		result.get(0).geometry = this;
		return result;
	}

}

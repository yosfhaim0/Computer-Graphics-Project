/**
 * 
 */
package geometries;

import java.util.List;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author yosefHaim
 *
 */
public class Strip extends Geometry {
	Polygon pol;

	public Strip(Point3D p1, Point3D p2) {
		pol = new Polygon(new Point3D(p1.getX() + 0.1, 0, p1.getY() + 0.1),
				new Point3D(p1.getX() - 0.1, 0, p1.getY() + 0.1), //
				new Point3D(p1.getX() - 0.1, 0, p1.getY() - 0.1), //
				new Point3D(p1.getX() + 0.1, 0, p1.getY() - 0.1));
	}

	@Override
	public Vector getNormal(Point3D p) {
		return pol.getNormal(p);
	}

	@Override
	List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return pol.findGeoIntersections(ray, maxDistance);
	}

}

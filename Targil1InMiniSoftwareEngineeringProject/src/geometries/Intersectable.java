/**
 * 
 */
package geometries;

import java.util.List;
import java.util.stream.Collectors;

import primitives.*;

/**
 * interface for shape are could intersect whit ray <br>
 * Including inner class geoPoint for help the emission calculate
 * 
 * @author yosefHaim
 *
 */
public interface Intersectable {
	/**
	 * PDS Class - we link a geometry body with a point on it<br>
	 * to adapt the color emission and get a more realistic render
	 * 
	 * @author yosefHaim
	 *
	 */
	public static class GeoPoint {
		/**
		 * geometry shape for connect to the point
		 */
		public Geometry geometry;
		/**
		 * point for connect to the geometry shape
		 */
		public Point3D point;

		/**
		 * Ctor for geoPoint
		 * 
		 * @param geometryBody the body associate
		 * @param point3d      point on the body
		 */
		public GeoPoint(Geometry geometryBody, Point3D point3d) {
			this.geometry = geometryBody;
			this.point = point3d;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return point.equals(other.point) && geometry.equals(other.geometry);
		}
	}

	/**
	 * 
	 * Default function for finding intersection points
	 * 
	 * @param ray Gets a Ray that is supposed to cut the shape
	 * @return Returns a list of points (not geo) if there is no intersect return
	 *         null
	 */
	default List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * The function returns a list of geoPoints that <br>
	 * are points of intersection of the shape with the ray
	 * 
	 * @param ray Gets a Ray that is supposed to cut the shape
	 * @return if there are Intersections: List<GeoPoint> Point3D <br>
	 *         else<br>
	 *         if there are no Intersections: null
	 */
	default List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * The function returns a list of geoPoints that <br>
	 * are points of intersection of the shape with the ray
	 * 
	 * @param ray         Gets a Ray that is supposed to cut the shape
	 * @param maxDistance optional for give a limit the collect of point<br>
	 *                    all the point in the list will be < maxDistance
	 * @return if there are Intersections: List<GeoPoint> Point3D all the point <br>
	 *         there distance form the begin of ray less maxDistance <br>
	 *         else<br>
	 *         if there are no Intersections: null
	 */
	List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

}

/**
 * 
 */
package geometries;

import java.util.List;
import java.util.stream.Collectors;

import primitives.*;

/**
 * interface for shape are could intersect whit ray <br>Including inner class
 * geoPoint for help the emission calculate
 * 
 * @author yosefHaim
 *
 */
public interface Intersectable {
	/**
	 * Helper Class for emission calculate
	 * 
	 * @author yosefHaim
	 *
	 */
	public static class GeoPoint {
		/**
		 * geometry shape
		 */
		public Geometry geometry;
		/**
		 * point 3D
		 */
		public Point3D point;

		/**
		 * Ctor for geoPoint
		 * 
		 * @param geometry1 the shape of the geoPoint
		 * @param point3d   point on the shape
		 */
		public GeoPoint(Geometry geometry1, Point3D point3d) {
			this.geometry = geometry1;
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
	 * @return if there is Intersections List<GeoPoint> Point3D <br>
	 *         list if there nothing Intersections return null
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray);

}

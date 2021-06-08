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
public abstract class Intersectable {
	/**
	 * minX, maxX, minY, maxY, minZ, maxZ<br>
	 * To create a box that corresponds to the axes we need 6 variables,<br>
	 * because there are 8 points of the box double 3 coordinates and each
	 * coordinate appears 4 times the same<br>
	 * so it is possible to divide 3 * 8/4 = 6
	 */
	protected double minX, maxX, minY, maxY, minZ, maxZ;
	/**
	 * middle Box Point, the inner middle point of the box
	 */
	protected Point3D middleBoxPoint;
	/**
	 * if the shape are finite shape like sphere or cylinder = true<br>
	 * other wise if shape are like plane or tube it mean infinity = false
	 */
	protected boolean finityShape = false;
	/**
	 * To give an option with or without acceleration of speed bounding volume
	 * Hierarchy<br>
	 * if we wont whit acceleration = true , whitout acceleration = false
	 */
	protected boolean BVHactivated = false;

	/**
	 * 
	 * Create box for the shape <br>
	 * set the miniX value to the minimum coordinate x of the shape or collection of
	 * shape <br>
	 * set the miniY value to the minimum coordinate y of the shape or collection of
	 * shape<br>
	 * set the miniZ value to the minimum coordinate z of the shape or collection of
	 * shape<br>
	 */
	protected abstract void CreateBoundingBox();

	/**
	 * creating boxes for all shapes in the geometries list and setting the bounding
	 * to be true
	 */
	public void createBox() {
		BVHactivated = true;
		CreateBoundingBox();
	}

	/**
	 * Function for finding the midpoint inside the box
	 * 
	 * @return the center inner point of the box
	 */
	public Point3D getMiddlePoint() {
		return new Point3D(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2), minZ + ((maxZ - minZ) / 2));
	}

	/**
	 * Extremely fast algorithms<br>
	 * for checking whether a ray cuts a box parallel to the axis
	 *
	 * @param A ray we want to test whether it is cutting or not the box
	 * @return true if intersect false if not
	 */
	public boolean isIntersectWithTheBox(Ray ray) {
		Point3D head = ray.getDir().getHead();
		Point3D p = ray.getP0();
		double temp;

		double dirX = head.getX(), dirY = head.getY(), dirZ = head.getZ();
		double origX = p.getX(), origY = p.getY(), origZ = p.getZ();

		// Min/Max starts with X
		double tMin = (minX - origX) / dirX, tMax = (maxX - origX) / dirX;
		if (tMin > tMax) {
			temp = tMin;
			tMin = tMax;
			tMax = temp;
		} // swap

		double tYMin = (minY - origY) / dirY, tYMax = (maxY - origY) / dirY;
		if (tYMin > tYMax) {
			temp = tYMin;
			tYMin = tYMax;
			tYMax = temp;
		} // swap
		if ((tMin > tYMax) || (tYMin > tMax))
			return false;
		if (tYMin > tMin)
			tMin = tYMin;
		if (tYMax < tMax)
			tMax = tYMax;

		double tZMin = (minZ - origZ) / dirZ, tZMax = (maxZ - origZ) / dirZ;
		if (tZMin > tZMax) {
			temp = tZMin;
			tZMin = tZMax;
			tZMax = temp;
		} // swap
		return tMin <= tZMax && tZMin <= tMax;
	}

	/**
	 * PDS Class - we link a geometry body with a point on it<br>
	 * to adapt the color emission and get a more realistic render
	 * 
	 * @author yosefHaim
	 *
	 */
	public static class GeoPoint {
		/**
		 * geometry are connect to point
		 */
		public Geometry geometry;
		/**
		 * point are connect to geometry
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
	}// end GeoPoint Class

	/**
	 * 
	 * Default function for finding intersection points
	 * 
	 * @param ray Gets a Ray that is supposed to cut the shape
	 * @return Returns a list of points (not geo) if there is no intersect return
	 *         null
	 */
	public List<Point3D> findIntersections(Ray ray) {
		var geoList = findIntsersectionsBound(ray);
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
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
		return findIntsersectionsBound(ray);
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
	public List<GeoPoint> findIntsersectionsBound(Ray ray) {
		return BVHactivated && !isIntersectWithTheBox(ray) ? null : findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * 
	 * @param ray
	 * @param maxDistanc
	 * @return
	 */
	public List<GeoPoint> findIntsersectionsBound(Ray ray, double maxDistanc) {
		return BVHactivated && !isIntersectWithTheBox(ray) ? null : findGeoIntersections(ray, maxDistanc);
	}

	abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

}

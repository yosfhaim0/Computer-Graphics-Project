/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * interface for shape are could intersect whit ray
 * 
 * @author yosefHaim
 *
 */
public interface Intersectable {
	/**
	 * The function returns a list of points that are points of intersection of the
	 * shape with the ray
	 * 
	 * @param ray The ray with which the shape is supposed cut
	 * @return if there is Intersections List<Point3D> Point3D list if there nothing
	 *         Intersections return null
	 */
	List<Point3D> findIntersections(Ray ray);
}

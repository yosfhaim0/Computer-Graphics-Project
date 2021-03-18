/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author yosefHaim
 *
 */
public interface Intersectable {
	/**
	 * The function returns a list of points 
	 * that are points of intersection of the shape with the ray
	 * @param primitive.Ray
	 * @return List<Point3D> Point3D list
	 */
List<Point3D> findIntersections(Ray ray);
}

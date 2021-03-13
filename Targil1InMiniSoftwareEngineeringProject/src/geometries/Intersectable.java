/**
 * 
 */
package geometries;

import java.util.List;

import primitive.*;

/**
 * @author yosefHaim
 *
 */
public interface Intersectable {
	/**
	 * The function returns a list of points 
	 * that are points of intersection of the shape with the ray
	 * @param primitive.Ray
	 * @return List<Point3D>
	 */
List<Point3D> findIntsersections(Ray ray);
}

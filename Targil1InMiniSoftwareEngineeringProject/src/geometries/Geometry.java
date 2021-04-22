package geometries;
/*
 * yosef haim amrsui 314897208
 * dvir keys 207668211
 * 
 * */

import primitives.*;

/**
 * interface for Geometry Shapes
 * 
 * @author yosefHaim
 *
 */
public interface Geometry extends Intersectable {
	/**
	 * The function is triggered by a geometric shape and returns normal from the
	 * point it receives (we assume that the point is indeed on the shape)
	 * 
	 * @param p point from which the normal came out
	 * @return orthogonal unit vector
	 */
	Vector getNormal(Point3D p);
}

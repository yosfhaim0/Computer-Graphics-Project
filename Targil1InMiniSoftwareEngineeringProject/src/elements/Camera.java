/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author yosefHaim
 *
 */
public class Camera {
	private Vector vTo, vUp, vRight;

	public Camera(Point3D point3d, Vector vectorTo, Vector vectorUp) {
		vRight = vectorTo.crossProduct(vectorUp).normalize();
		vUp = vectorUp.normalized();
		vTo = vectorTo.normalized();
	}

	/**
	 * Method set for the size of view plane
	 * 
	 * @param width  of view plane
	 * @param height of view plane
	 * @return this (Builder pattern)
	 */
	public Camera setViewPlaneSize(double width, double height) {
		return this;
	}
	/**
	 * Method set for the distance view plane form camera
	 * @param distance double number
	 * @return this (Builder pattern)
	 */
	public Camera setDistance(double distance) {
		return this;
	}
	public Ray constructRayThroughPixel(int nX,int nY,int j,int i) {
		return null;
	}

}

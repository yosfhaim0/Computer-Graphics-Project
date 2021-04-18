/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class Camera {
	/**
	 * location Point3d of Camera
	 */
	private Point3D locationPoint3d;
	/**
	 * vector Right Hand Coordinate System
	 */
	private Vector vTo, vUp, vRight;
	/**
	 * 
	 */
	private double distance = 0, width, height;

	/**
	 * ctor for camera the ctor get to vector up and to and generate the right
	 * vector
	 * 
	 * @param locatPoint point3d
	 * @param vectorTo   vector
	 * @param vectorUp   vector
	 */
	public Camera(Point3D locatPoint, Vector vectorTo, Vector vectorUp) {
		// if those vector are not orthogonal they throw exception form crossProduct
		// function
		vRight = vectorTo.crossProduct(vectorUp).normalize();
		vUp = vectorUp.normalized();
		vTo = vectorTo.normalized();
		locationPoint3d = locatPoint;
	}

	/**
	 * @return the locationPoint3d
	 */
	public Point3D getLocationPoint3d() {
		return locationPoint3d;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * Method set for the size of view plane
	 * 
	 * @param width  of view plane
	 * @param height of view plane
	 * @return this (Builder pattern)
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.height = height;
		this.width = width;
		return this;
	}

	/**
	 * Method set for the distance view plane form camera
	 * 
	 * @param distance double number
	 * @return this (Builder pattern)
	 */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * construct Ray Through Pixel form location of camera
	 * 
	 * @param nX depend hoe pixel we wont
	 * @param nY depend hoe pixel we wont
	 * @param j Rows
	 * @param i Columns
	 * @return Ray form location towards the center of pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D Pc;
		Pc = locationPoint3d;
		if (!primitives.Util.isZero(distance)) {

			Pc = locationPoint3d.add(vTo.scale(distance));

		}

		double Ry = height / nY, Rx = width / nX;
		double xj = (j - (nX - 1) / 2d) * Rx;
		double yi = -(i - (nY - 1) / 2d) * Ry;
		Point3D pij = Pc;
		if (!primitives.Util.isZero(xj)) {
			pij = pij.add(vRight.scale(xj));
		}
		if (!primitives.Util.isZero(yi)) {
			pij = pij.add(vUp.scale(yi));
		}
		Vector vij = pij.subtract(locationPoint3d);
		return new Ray(locationPoint3d, vij);
	}

}

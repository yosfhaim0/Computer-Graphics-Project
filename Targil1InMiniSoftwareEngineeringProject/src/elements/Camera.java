/**
 * 
 */
package elements;

import static primitives.Util.*;
import primitives.*;

/**
 * present camera, point of view in three diminution
 * 
 * @author yosefHaim <br>
 *         JavaDocs edited by Alexandre
 *
 */
public class Camera {
	/**
	 * location of the Camera
	 */
	private Point3D locationPoint3d;
	/**
	 * vector Right Hand Coordinate System
	 */
	private Vector vTo, vUp, vRight;
	/**
	 * distance between camera and view plane width of view plane height of view
	 * plane
	 */
	private double distance = 0, width, height;

	/**
	 * camera constructor: receive two orthogonal vectors and build a third one
	 * 
	 * @param locatPoint our point of view
	 * @param vectorTo   pointing where we look
	 * @param vectorUp   up to our looking direction
	 * 
	 * @throws IllegalArgumentException if vectorUp and vectorTo are not orthogonal
	 */
	public Camera(Point3D locatPoint, Vector vectorTo, Vector vectorUp) {
		// if vTo and vUp are not orthogonal
		if (vectorTo.dotProduct(vectorUp) != 0) {
			throw new IllegalArgumentException("Error !, vTo and vUp are not orthogonal");
		}
		vRight = vectorTo.crossProduct(vectorUp).normalize();
		vUp = vectorUp.normalized();
		vTo = vectorTo.normalized();
		locationPoint3d = locatPoint;
	}

	/**
	 * @return the center of the camera: our point of view
	 */
	public Point3D getLocationPoint3d() {
		return locationPoint3d;
	}

	/**
	 * @return the direction where we look
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
	 * Set distance between our camera and the view plane
	 * 
	 * @param distance double number
	 * @return this (Builder pattern)
	 */
	public Camera setVpDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * construct Ray Through Pixel form location of camera F
	 * 
	 * @param nX depend hoe pixel we wont row
	 * @param nY depend hoe pixel we wont column
	 * @param j  Rows
	 * @param i  Columns
	 * @return Ray form location towards the center of pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D Pc;
		Pc = locationPoint3d;
		if (!isZero(distance)) {
			Pc = locationPoint3d.add(vTo.scale(distance));
		}

		double Ry = height / nY, Rx = width / nX;
		double xj = (j - (nX - 1) / 2d) * Rx;
		double yi = -(i - (nY - 1) / 2d) * Ry;
		Point3D pij = Pc;
		if (!isZero(xj)) {
			pij = pij.add(vRight.scale(xj));
		}
		if (!isZero(yi)) {
			pij = pij.add(vUp.scale(yi));
		}
		Vector vij = pij.subtract(locationPoint3d);
		return new Ray(locationPoint3d, vij);
	}
}

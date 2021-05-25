/**
 * 
 */
package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * present camera, point of view in three dimension
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

	/**
	 * setter for location Point3d of camera Without<br>
	 * changing the state of the vectors
	 * 
	 * @param posi point to replace whit the current location point
	 * @return this (camera itself )
	 */
	public Camera setPosition(Point3D posi) {
		this.locationPoint3d = posi;
		return this;
	}

	/**
	 * Changing the direction of camera Head it mean the vto vector<br>
	 * it is taken into account that the floor of the three axes is the plain xy
	 * 
	 * @param target point to direct the vTo of camera<br>
	 *               In fact, this is the point you want the camera to take
	 * @return this the camera itself for builder design
	 */
	public Camera setCameraHead(Point3D target) {
		Vector v1 = target.subtract(locationPoint3d).normalize();
		try {
			vRight = v1.crossProduct(new Vector(0, 0, 1)).normalize();
		} catch (Exception e) {
			vRight = new Vector(0, -1, 0);
		}
		vUp = vRight.crossProduct(v1).normalize();
		vTo = v1;
		return this;
	}

	/**
	 * Rotate the camera Horizontally Builder pattern This <br>
	 * function change the angle of vRight & vUp along vTo direction the angle
	 * change Counterclockwise by the vTo axis!
	 * 
	 * @param angle the angle to change by Degrees,for Rotate the camera
	 *              Horizontally
	 * @return this the camera itself for builder design
	 */
	public Camera rotateHorizontally(double angle) {
		vRight = vRight.rotatingVectorOnAxis(vTo, angle);
		vUp = vUp.rotatingVectorOnAxis(vTo, angle);
		return this;
	}

}

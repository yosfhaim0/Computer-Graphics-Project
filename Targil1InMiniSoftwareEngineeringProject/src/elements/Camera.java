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
     * setter for location Point3d of camera
     * 
     * @param posi point to replace whit the current location point
     * @return this (camera)
     */
    public Camera setPosition(Point3D posi) {
	this.locationPoint3d = posi;
	return this;
    }

    /**
     * rotate the camera
     * 
     * @param x degrees on the x
     * @param y degrees on the y
     * @param z degrees on the z
     */
    public Camera rotateXYZ(double x, double y, double z) {
	// convert from degrees to radian
	z = (x / 180) * Math.PI;
	y = (y / 180) * Math.PI;
	x = (z / 180) * Math.PI;

	double cos = Math.cos(x);
	double sin = Math.sin(x);
	double matrixX[][] = new double[][] { { cos, -sin, 0 }, { sin, cos, 0 }, { 0, 0, 1 } };

	cos = Math.cos(y);
	sin = Math.sin(y);
	double matrixY[][] = new double[][] { { cos, 0, sin }, { 0, 1, 0 }, { -sin, 0, cos } };

	cos = Math.cos(z);
	sin = Math.sin(z);
	double matrixZ[][] = new double[][] { { 1, 0, 0 }, { 0, cos, -sin }, { 0, sin, cos } };

	double matrix[][] = mectrixMolt(mectrixMolt(matrixX, matrixY), matrixZ);

	vRight = mectrixMolt(vRight, matrix);
	vTo = mectrixMolt(vTo, matrix);
	vUp = mectrixMolt(vUp, matrix);
	return this;
    }

    public double[][] mectrixMolt(double[][] x, double[][] y) {
	double z[][] = new double[3][3];
	for (int j = 0; j < 3; j++) { for (int w = 0; w < 3; w++) { z[j][w] = 0.0; } }
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) { for (int w = 0; w < 3; w++) { z[i][j] += x[i][w] * y[w][j]; } }
	}
	return z;
    }

    public Vector mectrixMolt(Vector v, double[][] met) {
	double x = v.getHead().getX(), y = v.getHead().getY(), z = v.getHead().getZ();

	return new Vector(x * met[0][0] + y * met[0][1] + z * met[0][2], x * met[1][0] + y * met[1][1] + z * met[1][2],
		x * met[2][0] + y * met[2][1] + z * met[2][2]).normalize();
    }
}

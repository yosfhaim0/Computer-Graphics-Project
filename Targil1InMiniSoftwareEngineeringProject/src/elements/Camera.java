/**
 * 
 */
package elements;

import static primitives.Util.isZero;

import java.util.LinkedList;
import java.util.List;

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
	private Point3D location;
	/**
	 * vector Right Hand Coordinate System
	 */
	private Vector vTo, vUp, vRight;
	/**
	 * distance between camera and view plane width of view plane height of view
	 * plane
	 */
	private double VPdistance = 0, VPwidth, VPheight;

	/**
	 * size For Depth Of Field<br>
	 * the size of aperture window(rectangle)
	 */
	private double sizeForApertureWindow = 50;
	/**
	 * distance For Depth Of Filed <br>
	 * Distance from camera to Focal plane
	 */
	private double distanceToFocalPlane = 0;
	/**
	 * Also serves as a Boolean variable whether or not this image improvement<br>
	 * exists num Of Ray Form Aperture Window To Focal Point<br>
	 * Number of rays for focus
	 */
	private int numOfRayFormApertureWindowToFocalPoint = 0;
	/**
	 * Also serves as a Boolean variable whether or not this image improvement<br>
	 * num Of Ray For Anti Aliasing sharping the Edges
	 */
	private int numOfRayForAntiAliasing = 0;
	/**
	 * view plane center
	 */
	private Point3D viewPlaneCenter;

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
		vUp = vectorUp.normalized();
		vTo = vectorTo.normalized();
		vRight = vectorTo.crossProduct(vectorUp).normalize();
		location = locatPoint;
		if (!isZero(VPdistance))
			viewPlaneCenter = location.add(vTo.scale(VPdistance));
	}

	/**
	 * @return the center of the camera: our point of view
	 */
	public Point3D getLocationPoint3d() {
		return location;
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
	 * @return the distance
	 */
	public double getDistance() {
		return VPdistance;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return VPwidth;
	}

	/**
	 * @param numOfRayForAntiAlias the number of ray
	 */
	public Camera setNumOfRayForAntiAliasing(int numOfRayForAntiAlias) {
		if (numOfRayForAntiAliasing < 0)
			throw new IllegalArgumentException("num Of Ray For Anti Aliasing cant be less then 0!");
		this.numOfRayForAntiAliasing = numOfRayForAntiAlias;
		return this;

	}

	/**
	 * Method set for the size of view plane
	 * 
	 * @param width  of view plane
	 * @param height of view plane
	 * @return this (Builder pattern)
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.VPheight = height;
		this.VPwidth = width;
		return this;
	}

	/**
	 * Set distance between our camera and the view plane
	 * 
	 * @param distance double number
	 * @return this (Builder pattern)
	 */
	public Camera setVpDistance(double distance) {
		this.VPdistance = distance;
		if (!isZero(distance))
			viewPlaneCenter = location.add(vTo.scale(distance));
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
	public List<Ray> constructBeamRay(int nX, int nY, int j, int i) {
		Ray ray = constructRayThroughPixel(nX, nY, j, i);
		List<Ray> rays = new LinkedList<>();
		if (numOfRayForAntiAliasing > 0)
			rays.addAll(constructBeamRayForAntiAliesing(ray, nX, nY));
		if (numOfRayFormApertureWindowToFocalPoint > 0)
			rays.addAll(constructBeamRayThroughFocalPoint(ray, nX, nY));
		rays.add(ray);
		return rays;
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
		double Ry = VPheight / nY, Rx = VPwidth / nX;
		double xj = (j - (nX - 1) / 2d) * Rx;
		double yi = -(i - (nY - 1) / 2d) * Ry;
		Point3D pij = viewPlaneCenter;
		if (!isZero(xj)) {
			pij = pij.add(vRight.scale(xj));
		}
		if (!isZero(yi)) {
			pij = pij.add(vUp.scale(yi));
		}
		Vector vij = pij.subtract(location);
		return new Ray(location, vij);
	}

	/**
	 * setter for location Point3d of camera Without<br>
	 * changing the state of the vectors
	 * 
	 * @param posi point to replace whit the current location point
	 * @return this (camera itself )
	 */
	public Camera setPosition(Point3D posi) {
		this.location = posi;
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

		// 1) we calc the direction
		vTo = target.subtract(location).normalize();
		// 2) we calc the axisDir
		try {
			vRight = vTo.crossProduct(new Vector(0, 1, 0)).normalize();
			vUp = vRight.crossProduct(vTo).normalize();
		} catch (IllegalArgumentException e) {
			vUp = new Vector(1, 0, 0);
			vRight = vTo.crossProduct(vUp).normalize();
		}
		if (!isZero(VPdistance))
			viewPlaneCenter = location.add(vTo.scale(VPdistance));
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

	/**
	 * @param distancForDepthOfFilde the distancForDepthOfFilde to set
	 */
	public Camera setDistanceToFocalPlane(double distancForDepthOfFilde) {
		if (distancForDepthOfFilde < 0)
			throw new IllegalArgumentException("distanc For Depth Of Filde cant be less then 0!");
		this.distanceToFocalPlane = distancForDepthOfFilde;
		return this;
	}

	/**
	 * @param radius the radius to set
	 */
	public Camera setSizeForApertureWindow(double radius) {
		if (radius < 0)
			throw new IllegalArgumentException("radius For Depth Of Field cant be less then 0!");
		this.sizeForApertureWindow = radius;
		return this;

	}

	/**
	 * @param numOfRayForDepthOfFil the number of ray
	 */
	public Camera setNumOfRayFormApertureWindowToFocalPoint(int numOfRayForDepthOfFil) {
		if (numOfRayForDepthOfFil < 0)
			throw new IllegalArgumentException("num Of Ray For Depth Of Filde cant be less then 0!");
		this.numOfRayFormApertureWindowToFocalPoint = numOfRayForDepthOfFil;
		return this;

	}

	/**
	 * construct beam of Ray Through Pixel form location of camera F
	 * 
	 * @param ray for center ray
	 * @return List<Ray> form radius For Depth Of Field towards the center of
	 *         pixel<br>
	 * 
	 */
	public List<Ray> constructBeamRayThroughFocalPoint(Ray ray, int nX, int nY) {
		List<Ray> splittedRays = new LinkedList<>();
		double t = distanceToFocalPlane / (vTo.dotProduct(ray.getDir()));
		Point3D focalPoint = ray.getPoint(t);
		for (int i = 0; i < numOfRayFormApertureWindowToFocalPoint; i++) {
			Point3D point3d = location.randomPointOnRectangle(ray.getDir(), sizeForApertureWindow,
					sizeForApertureWindow);
			Vector v = focalPoint.subtract(point3d);
			splittedRays.add(new Ray(point3d, v));
		}
		return splittedRays;
	}

	/**
	 * construct beam of Ray Through Pixel form location of camera F
	 * 
	 * @param ray for center ray
	 * @param nX  depend hoe pixel we wont row
	 * @param nY  depend hoe pixel we wont column
	 * @return List<Ray> form radius For Anti Aliesing towards the center of
	 *         pixel<br>
	 * 
	 */
	public List<Ray> constructBeamRayForAntiAliesing(Ray ray, int nX, int nY) {
		List<Ray> splittedRays = new LinkedList<>();
		Point3D centerCirclePoint = null;
		double t = VPdistance / (vTo.dotProduct(ray.getDir()));
		double distance = location.distance(ray.getPoint(t));
		try {
			centerCirclePoint = ray.getPoint(distance);
		} catch (Exception e) {
			centerCirclePoint = location;
		}
		Point3D randomCirclePoint = null;
		Vector dir = ray.getDir();
		for (int i = 0; i < numOfRayForAntiAliasing; i++) {
			randomCirclePoint = centerCirclePoint.randomPointOnRectangle(dir, VPwidth / nX, VPheight / nY);
			Vector v = randomCirclePoint.subtract(location);
			splittedRays.add(new Ray(location, v));
		}
		return splittedRays;
	}

}

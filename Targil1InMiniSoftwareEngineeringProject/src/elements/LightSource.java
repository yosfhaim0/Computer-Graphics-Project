/**
 * 
 */
package elements;

import primitives.*;

/**
 * interface for Light Source
 * 
 * @author yosefHaim
 *
 */
public interface LightSource {
	/**
	 * find the color Intensity at a particular point
	 * 
	 * @param p Point you want to find the amount of <br>
	 *          light coming from the light source ()
	 * @return A color that is the amount of <br>
	 *         light that comes from the light source
	 */
	public Color getIntensity(Point3D p);

	/**
	 * Function for finding a vector from the <br>
	 * light source to the point
	 * 
	 * @param p Point you want to find the vector<br>
	 *          from the light source to the point
	 * @return Vector from the light source pointing to p
	 * 
	 *
	 * 
	 */
	public Vector getL(Point3D p);

	/**
	 * calc distance between the light source and the point
	 * 
	 * @param point for calc the distance form her
	 * @return distance between the light source and the point
	 */
	double getDistance(Point3D point);

	/**
	 * Radius of light is mainly used for soft shade getter for radius
	 * 
	 * @return radius of light for soft shadow
	 */
	double getRadius();

}

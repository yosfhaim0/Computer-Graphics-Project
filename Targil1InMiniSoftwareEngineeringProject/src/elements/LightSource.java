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
	 * Function for finding Intensity at a particular point
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
	 * @return Vector whose head is at the point <br>
	 *         param, and its beginning at the light source
	 */
	public Vector getL(Point3D p);

}

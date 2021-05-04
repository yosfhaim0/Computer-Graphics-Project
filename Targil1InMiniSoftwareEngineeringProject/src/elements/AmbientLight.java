/**
 * 
 */
package elements;

import primitives.*;

/**
 * Ambient Light= like sun in cloudy day
 * 
 * @author yosefHaim
 *
 */
public class AmbientLight extends Light {

	/**
	 * ctor for AmbientLight
	 * 
	 * @param intens intensity
	 * @param ka     Discount factor
	 */
	public AmbientLight(Color intens, double ka) {
		super(intens);
		this.intensity = intens.scale(ka);
	}

}

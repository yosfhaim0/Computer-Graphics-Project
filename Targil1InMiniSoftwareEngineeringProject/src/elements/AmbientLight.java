/**
 * 
 */
package elements;

import primitives.*;

/**
 * Ambient Light= like sun in cloudy day Lighting without<br>
 * specific direction or source
 * 
 * @author yosefHaim
 *
 */
public class AmbientLight extends Light {

	/**
	 * ctor for AmbientLight
	 * 
	 * @param intens Light intensity by components
	 * @param ka     Coefficient of attenuation of AmbientLight
	 */
	public AmbientLight(Color intens, double ka) {
		super(intens.scale(ka));
	}

	/**
	 * Default Ctor (black intensity)
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}

}
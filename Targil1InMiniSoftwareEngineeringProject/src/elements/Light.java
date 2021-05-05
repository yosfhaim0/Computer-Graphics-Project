/**
 * 
 */
package elements;

import primitives.*;

/**
 * present light
 * 
 * @author yosefHaim
 *
 */
abstract class Light {
	/**
	 * intensity = how much the light strong Light intensity by components
	 */
	protected Color intensity;
	/**
	 * ctor for light
	 * @param intens Light intensity by Color
	 */
	protected Light(Color intens) {
		this.intensity = intens;
	}

	/**
	 * getter for intensity
	 * 
	 * @return intensity Color of Ambient Light
	 */
	public Color getIntensity() {
		return this.intensity;
	}
}

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
	 * @param intensity1 Light intensity by Color
	 */
	protected Light(Color intensity1) {
		this.intensity = intensity1;
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

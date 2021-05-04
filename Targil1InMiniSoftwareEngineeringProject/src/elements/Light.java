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
	 * intensity = how much the light strong
	 */
	protected Color intensity;

	protected Light(Color intensity1) {
		this.intensity = intensity1;
	}
	/**
	 * getter for intensity
	 * @return intensity Color of Ambient Light
	 */
	public Color getIntensity() {return this.intensity;}
}

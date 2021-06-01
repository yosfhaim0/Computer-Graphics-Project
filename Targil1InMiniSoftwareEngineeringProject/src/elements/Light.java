/**
 * 
 */
package elements;

import primitives.*;

/**
 * abstract class for light Mutations ,Types of light
 * 
 * @author yosefHaim
 *
 */
public abstract class Light {
	/**
	 * intensity: the Light intensity of a light source
	 * 
	 */
	protected Color intensity;
	/**
	 * Radius of light is mainly used for soft shade
	 */
	protected double radius = 5;

	/**
	 * ctor for light
	 * 
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

	/**
	 * getRadius for light body
	 * 
	 * @param p
	 * @return
	 */
	public double getRadius() {
		return this.radius;
	}
}

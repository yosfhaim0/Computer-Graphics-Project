/**
 * 
 */
package elements;

import primitives.*;

/**
 * light source is far away - like sun
 * 
 * @author yosefHaim
 *
 */
public class DirectionalLight extends Light implements LightSource {
	/**
	 * direction of the light
	 */
	private Vector direction;

	/**
	 * ctor for DirectionalLight
	 * 
	 * @param intens intensity of the light
	 * @param dir    Directional of Light
	 */
	public DirectionalLight(Color intens, Vector dir) {
		super(intens);
		this.direction = dir;
	}

	@Override
	public Color getIntensity(Point3D p) {
		return this.intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return this.direction;
	}

}

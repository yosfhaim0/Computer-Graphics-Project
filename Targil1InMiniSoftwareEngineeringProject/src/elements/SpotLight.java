/**
 * 
 */
package elements;

import primitives.*;

/**
 * spot light are point light Without scattering
 * 
 * @author yosefHaim
 *
 */
public class SpotLight extends PointLight {
	/**
	 * direction of the spot light
	 */
	private Vector direction;

	/**
	 * ctor for spot light
	 * 
	 * @param intensity1 color of intensity of spot light
	 * @param direct     direction of spot light
	 */
	protected SpotLight(Color intensity1, Point3D positi, double kc, double kl, double kq, Vector direct) {
		super(intensity1, positi, kc, kl, kq);
		this.direction = direct;
		// TODO Auto-generated constructor stub
	}

}

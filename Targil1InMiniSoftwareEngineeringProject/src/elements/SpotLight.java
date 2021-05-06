/**
 * 
 */
package elements;

import static primitives.Util.*;
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
	public SpotLight(Color intensity1, Point3D positi, Vector direct, double kc, double kl, double kq) {
		super(intensity1, positi, kc, kl, kq);
		this.direction = direct.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		Color iL = super.getIntensity(p);
		double dirDotL = alignZero(direction.dotProduct(getL(p)));
		if (dirDotL < 0)
			return Color.BLACK;
		return iL.scale(dirDotL);

	}

}

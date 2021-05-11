/**
 * 
 */
package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * spot light are point light Without scattering <br>
 * With the possibility of becoming a "flashlight"
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
	 * For a narrower beam of light,<br>
	 * The larger it is, the narrower the beam of light
	 * 
	 */
	private int sharp = 1;

	/**
	 * 
	 * @param intens color of intensity of spot light
	 * @param positi Light body location
	 * @param direct Lighting direction
	 */
	public SpotLight(Color intens, Point3D positi, Vector direct) {
		super(intens, positi);
		this.direction = direct.normalized();
	}

	/**
	 * @param sharp the sharp to set
	 */
	public SpotLight setSharp(int sharp) {
		this.sharp = sharp;
		return this;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double dirDotL = alignZero(direction.dotProduct(getL(p)));
		if (dirDotL < 0)
			return Color.BLACK;

		if (sharp != 1)
			dirDotL = Math.pow(dirDotL, sharp);
		
		Color iL = super.getIntensity(p);
		return iL.scale(dirDotL);
	}

}

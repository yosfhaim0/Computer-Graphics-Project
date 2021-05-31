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
	 * ctor for spot
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
	 * ctor for spot whit target
	 * 
	 * @param intens color of intensity of spot light
	 * @param positi Light body location
	 * @param target target for lighting
	 */
	public SpotLight(Color intens, Point3D positi, Point3D target) {
		super(intens, positi);
		this.direction = target.subtract(positi).normalized();
	}

	/**
	 * @param kC the kC to set
	 */
	public SpotLight setkC(double kC) {
		return (SpotLight) super.setkC(kC);
	}

	/**
	 * @param kL the kL to set
	 */
	public SpotLight setkL(double kL) {
		return (SpotLight) super.setkL(kL);
	}

	/**
	 * @param kQ the kQ to set
	 */
	public SpotLight setkQ(double kQ) {
		return (SpotLight) super.setkQ(kQ);
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

	/**
	 * setter for radius of light
	 * 
	 * @param rad radius to set
	 * @return this for chaining
	 */
	public SpotLight setRadius(double rad) {
		this.radius = rad;
		return this;
	}

}

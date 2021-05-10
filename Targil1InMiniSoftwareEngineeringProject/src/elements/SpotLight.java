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
	 * @param positi
	 * @param direct direction of spot light
	 * @param kc
	 * @param kl
	 * @param kq
	 * @param sha
	 */
	public SpotLight(Color intens, Point3D positi, Vector direct, double kc, double kl, double kq, int sha) {
		super(intens, positi, kc, kl, kq);
		this.direction = direct.normalized();
		this.sharp = sha;
	}

	/**
	 * 
	 * @param intens color of intensity of spot light
	 * @param positi
	 * @param direct     direction of spot light
	 * @param kc
	 * @param kl
	 * @param kq
	 */
	public SpotLight(Color intens, Point3D positi, Vector direct, double kc, double kl, double kq) {
		super(intens, positi, kc, kl, kq);
		this.direction = direct.normalized();
	}
	/**
	 * 
	 * @param intens
	 * @param positi
	 * @param direct
	 */
	public SpotLight(Color intens, Point3D positi, Vector direct) {
		super(intens, positi, 1, 0, 0);
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
		Color iL = super.getIntensity(p);
		double dirDotL = alignZero(direction.dotProduct(getL(p)));
		if (dirDotL < 0)
			return Color.BLACK;
		dirDotL = Math.pow(dirDotL, sharp);
		return iL.scale(dirDotL);

	}

}

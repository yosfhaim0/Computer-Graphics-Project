package elements;

import primitives.*;

/**
 * point light represents light types like lamp or bonfire
 * 
 * @author yosefHaim
 *
 */
public class PointLight extends Light implements LightSource {
	/**
	 * position of point light
	 */
	private Point3D position;
	/**
	 * Discount coefficients <br>
	 * Fixed, linear, and square Respectively
	 */
	private double kC=1, kL=0, kQ=0;

	/**
	 * ctor for point light
	 * 
	 * @param intens intensity of light
	 * @param positi location
	 * @param kc     Discount coefficients Fixed
	 * @param kl     Discount coefficients linear
	 * @param kq     Discount coefficients square
	 */
	public PointLight(Color intens, Point3D positi, double kc, double kl, double kq) {
		super(intens);
		position = positi;
		kC = kc;
		kL = kl;
		kQ = kq;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getIntensity(Point3D p) {
		// TODO Auto-generated method stub
		double d = position.distance(p);
		Color iL = this.intensity.reduce(kC + kL * d + kQ * d * d);
		return iL;
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}

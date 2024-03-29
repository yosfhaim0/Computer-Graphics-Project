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
	protected Point3D position;
	/**
	 * Discount coefficients <br>
	 * Fixed, linear, and square Respectively
	 */
	private double kC = 1, kL = 0, kQ = 0;

	/**
	 * ctor for point light
	 * 
	 * @param intens intensity of light
	 * @param positi Light body position
	 */
	public PointLight(Color intens, Point3D positi) {
		super(intens);
		position = positi;
	}

	/**
	 * setter
	 * 
	 * @param kC the kC to set
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * setter
	 * 
	 * @param kL the kL to set
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * setter
	 * 
	 * @param kQ the kQ to set
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double d = position.distanceSquared(p);
		Color iL = this.intensity.reduce(kC + kL * Math.sqrt(d) + kQ * d);
		return iL;
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point3D point) {
		return position.distance(point);
	}

	/**
	 * setter for radius of light
	 * 
	 * @param rad radius to set
	 * @return this for chaining
	 */
	public PointLight setRadius(double rad) {
		this.radius = rad;
		return this;
	}

}

package primitives;

/**
 * PDS Class - hold attenuation and shininess factors<BR>
 * Role 1: Attention!!<BR>
 * for Real picture!!<BR>
 * Kd+max(Ks,Kr)+Kt<=1 <BR>
 * 
 * Role 2:<BR>
 * if(Kr>0) most be ->Ks>0<BR>
 * Role 3:<BR>
 * if shape like sphere kr=0 or kt=0<BR>
 * 
 * @author Alexandre
 *
 */
public class Material {

	/**
	 * kD - the diffusive coefficient
	 */
	public double kD = 0d;

	/**
	 * kS - the specular coefficient
	 */
	public double kS = 0d;

	/**
	 * kT - transparency coefficient
	 */
	public double kT = 0d;
	/**
	 * kR - reflection coefficient
	 */
	public double kR = 0d;

	/**
	 * The concentration of shine <br>
	 * value ~ 300
	 */
	public int nShininess = 0;
	/**
	 * radius For Glossy
	 */
	public double radiusForGlossy = 0;

	/**
	 * setter for radius
	 * 
	 * @param radiusForGlossy the radiusForGlossy to set
	 */
	public Material setRadiusForGlossy(double radiusForGlossy) {
		this.radiusForGlossy = radiusForGlossy;
		return this;
	}

	/**
	 * setter for radius
	 * 
	 * @param radiusForBlurry the radiusForBlurry to set
	 */
	public Material setRadiusForBlurry(double radiusForBlurry) {
		this.radiusForBlurry = radiusForBlurry;
		return this;

	}

	/**
	 * radius For Blurry
	 */
	public double radiusForBlurry = 0;

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

	/**
	 * diffusive
	 * set the kD coef.
	 * 
	 * @param kD diffusive coefficient
	 * @return this (Material) - for concatenation
	 */
	public Material setKd(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * specular
	 * set the kS coef.
	 * 
	 * @param kS the specular coefficient
	 * @return this - for concatenation
	 */
	public Material setKs(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * transparency
	 * set the kT coef.
	 * 
	 * @param kT the transparency coefficient
	 * @return this - Builder pattern
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * reflection
	 * set the kR coef.
	 * 
	 * @param kR the reflection coefficient
	 * @return this - Builder pattern
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}

}
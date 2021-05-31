package primitives;

/**
 * PDS Class - hold attenuation and shininess factors
 * 
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
     * @param nShininess the nShininess to set
     */
    public Material setShininess(int nShininess) {
	this.nShininess = nShininess;
	return this;
    }

    /**
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
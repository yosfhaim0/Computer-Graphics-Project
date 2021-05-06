package primitives;

/**
 * PDS Class - hold attenuation and shininess factors
 * 
 * @author Alexandre
 *
 */
public class Material {

    /**
     * kD - the diffusive coefficient<br>
     * kS - the specular coefficient
     */
    public double kD=0d, kS = 0d;
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
     * @return this (Material) - for concatenation
     */
    public Material setKs(double kS) {
	this.kS = kS;
	return this;
    }

}

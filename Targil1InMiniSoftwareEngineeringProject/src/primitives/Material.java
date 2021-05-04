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
    public double kD, kS = 0d;
    public int nShininess = 0;

    /**
     * set the kD coef.
     * 
     * @param kD diffusive coefficient
     * @return this (Material) - for concatenation
     */
    public Material setKD(double kD) {
	this.kD = kD;
	return this;
    }

    /**
     * set the kS coef.
     * 
     * @param kS the specular coefficient
     * @return this (Material) - for concatenation
     */
    public Material setKS(double kS) {
	this.kS = kS;
	return this;
    }

}

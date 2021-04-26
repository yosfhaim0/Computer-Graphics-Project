/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class AmbientLight {
	private Color intensity;

	public AmbientLight(Color intens, double ka) {
		this.intensity = intens.scale(ka);
	}

	public Color getIntensity() {
		return intensity;
	}

}

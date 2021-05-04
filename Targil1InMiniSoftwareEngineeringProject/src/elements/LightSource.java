/**
 * 
 */
package elements;

import primitives.*;

/**
 * interface for Light Source
 * 
 * @author yosefHaim
 *
 */
public interface LightSource {
	public Color getIntensity(Point3D p);
	public Vector getL(Point3D p);

}

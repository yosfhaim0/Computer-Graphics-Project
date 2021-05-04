/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class DirectionalLight extends Light implements LightSource{
	private Vector direction;

	protected DirectionalLight(Color intensity1) {
		super(intensity1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getIntensity(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}

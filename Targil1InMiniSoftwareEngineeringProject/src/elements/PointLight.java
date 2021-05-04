/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class PointLight extends Light implements LightSource {
	private Point3D position;
	private double kC, kL, kQ;

	protected PointLight(Color intensity1,Point3D positi,double kc,double kl,double kq) {
		super(intensity1);
		position=positi;
		kC=kc;
		kL=kl;
		kQ=kq;
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

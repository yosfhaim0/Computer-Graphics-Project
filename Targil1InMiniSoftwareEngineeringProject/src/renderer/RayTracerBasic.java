/**
 * 
 */
package renderer;

import java.util.List;
import elements.LightSource;
import elements.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

/**
 * basic ray tracer Follows a Ray for finding color
 * 
 * @author yosefHaim
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * ctor RayTracerBasic
	 * 
	 * @param scene for trace for her
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
		return calcColor(closestPoint,ray);
	}

	/**
	 * calculate color
	 * 
	 * @param point for calculate is color for her
	 * @return return the intensity of the ambientLight of scene
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		Color iP = scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
		for (LightSource l : scene.lights) {
			iP=iP.add(l.getIntensity(intersection.point));
		}
		return iP.add(calcLocalEffects(intersection, ray));
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (checkSign(nl, nv)) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r=l.subtract(n.scale(l.dotProduct(n)));
		double rDotV=alignZero(r.dotProduct(v.scale(-1)));
		if(rDotV>0)
			return Color.BLACK;
		rDotV=Math.pow(rDotV,nShininess);
		Color spcular=lightIntensity.scale(ks);
		spcular=spcular.scale(rDotV);
		return spcular;
		 
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double lDotN= l.dotProduct(n);
		if(lDotN<0)
			lDotN*=-1;
		lDotN*=kd;
		lightIntensity.scale(lDotN);
		return lightIntensity;
	}

}
;
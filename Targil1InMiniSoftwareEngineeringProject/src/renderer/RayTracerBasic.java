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
		return calcColor(closestPoint, ray);
	}

	/**
	 * calculate color
	 * 
	 * @param point for calculate is color for her
	 * @return return the intensity of the ambientLight of scene
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Color iP = scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point).normalize();
			double nl = alignZero(n.dotProduct(l));
			if (checkSign(nl, nv)) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, nl, lightIntensity),
						calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
			}
		}
		return iP.add(color);
	}

	private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess,
			Color lightIntensity) {
		Vector r = l.subtract(n.scale(nl * 2));
		double rDotV = -alignZero(r.dotProduct(v));
		if (rDotV < 0)
			return Color.BLACK;
		Color spcular = lightIntensity.scale(ks * Math.pow(rDotV, nShininess));
		return spcular;
	}

	private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
		if (nl < 0)
			nl = -nl;
		return lightIntensity.scale(kd * nl);
	}

};
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
 * @author yosefHaim & Alexandre
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * ctor RayTracerBasic
	 * 
	 * @param scene in which we will trace ray
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
		/*
		 * for every light source in our scene we are checking if it enlightens the body
		 * and computing the color at the intersection point; color = functionOf(the
		 * diffusive coefficient, the specular coefficient, the light intensity and the
		 * shininess)
		 */
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

	/**
	 * compute the specular effect
	 * 
	 * @param ks             specular coefficient
	 * @param l              direction of the light
	 * @param n              normal to the body
	 * @param nl             result of n DotProduct l
	 * @param v              direction of the camera
	 * @param nShininess     object shininess
	 * @param lightIntensity intensity of the light source
	 * @return color after applying specular effect
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess,
			Color lightIntensity) {
		// r : reflectance vector
		Vector r = l.subtract(n.scale(nl * 2));
		double rDotV = -alignZero(r.dotProduct(v));
		if (rDotV <= 0)
			return Color.BLACK;
		Color spcular = lightIntensity.scale(ks * Math.pow(rDotV, nShininess));
		return spcular;
	}

	/**
	 * compute the diffusive effect - thanks to which objects have colors
	 * 
	 * @param kd             diffusive coefficient
	 * @param nl             result of n DotProduct l - where n is the normal to the
	 *                       body and l the light direction
	 * @param lightIntensity intensity of the light source
	 * @return color after applying diffusive effect
	 */
	private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
		if (nl < 0)
			nl = -nl;
		return lightIntensity.scale(kd * nl);
	}

};
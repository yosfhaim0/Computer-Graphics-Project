package renderer;

import java.util.List;

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
     * const for move head ray shadow
     */
    private static final double DELTA = 0.1;

    /**
     * recursion stop condition - the maximum number of colors
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * recursion stop condition -
     */
    private static double MIN_CALC_COLOR_K = 0.001;

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
	if (intersections == null) return scene.background;
	GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
	return calcColor(closestPoint, ray);
    }

    /**
     * calcColor for point
     * 
     * @param intersection point for calc color for her
     * @param ray          the ray are intersect whit the point
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
	return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission())
		// add calculated light contribution from all light sources)
		.add(calcLocalEffects(intersection, ray));

    }

    /**
     * 
     * 
     * @param ray 
     * @return closest point from the geometry bodies and the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
	return (ray.getClosestGeoPoint(scene.geometries.findGeoIntersections(ray)));
    }

    /**
     * 
     * @param n
     * @param point
     * @param inRay
     * @return
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
	return null;
    }

    /**
     * 
     * @param n
     * @param point
     * @param inRay
     * @return
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
	return null;
    }

    /**
     * 
     * @param geopoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
	Color color = Color.BLACK;
	Material material = geopoint.geometry.getMaterial();
	double kr = material.kR, kkr = k * kr;
	
	if (kkr > MIN_CALC_COLOR_K) {
	    Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
	    GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
	    color = color.add(calcColor(reflectedPoint, reflectedRay, level – 1, kkr).scale(kr));
	}
	
	double kt = material.kt, kkt = k * kt;
	if (kkt > MIN_CALC_COLOR_K) {
	Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
	GeoPoint refractedPoint = findClosestIntersection(refractedRay);
	color = color.add(calcColor(refractedPoint, refractedRay, level – 1, kkt).scale(kt));
	}
	return color;
	}

    /**
     * 
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
	Color color = intersection.geometry.getEmission();
	color = color.add(calcLocalEffects(intersection, ray));

	return (1 == level) ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calc Local Effects like specular and diffusive effects
     * 
     * @param intersection point for calc color for her
     * @param ray          the ray are intersect whit the point
     * @return calc color form specular and diffusive effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
	Vector v = ray.getDir();
	Vector n = intersection.geometry.getNormal(intersection.point);
	double nv = alignZero(n.dotProduct(v));
	if (nv == 0) return Color.BLACK;
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
	    Vector l = lightSource.getL(intersection.point);
	    double nl = alignZero(n.dotProduct(l));
	    if (nl != 0 && checkSign(nl, nv)) { // sign(nl) == sing(nv)
		if (unshaded(lightSource, l, n, intersection)) {
		    Color lightIntensity = lightSource.getIntensity(intersection.point);
		    color = color.add(calcDiffusive(kd, nl, lightIntensity),
			    calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
		}
	    }
	}
	return color;
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
	if (rDotV <= 0) return Color.BLACK;
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
	if (nl < 0) nl = -nl;
	return lightIntensity.scale(kd * nl);
    }

    /**
     * 
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	Vector lightDirection = l.scale(-1); // from point to light source
	Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
	Point3D point = geopoint.point.add(delta);
	Ray lightRay = new Ray(point, lightDirection);
	double lightDistance = light.getDistance(geopoint.point);
	List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
	return intersections == null ? true : false;
    }

};
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
     * recursion stop condition - the maximum number of colors
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * recursion stop condition -
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * default coefficient
     */
    private static final double INITIAL_K = 1.0;

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
	GeoPoint closestPoint = findClosestIntersection(ray);
	return closestPoint == null ? Color.BLACK : calcColor(closestPoint, ray);
    }

    /**
     * calcColor for point<br>
     * wrapper function
     * 
     * @param intersection point
     * @param ray          the ray are intersect whit the point
     * @return return the intensity of the ambientLight of scene
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
	return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * compute the color of a given point
     * 
     * @param intersection point on the body
     * @param ray          direction of the light
     * @param level        stop condition
     * @param k
     * @return color of the point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
	Color color = intersection.geometry.getEmission();
	color = color.add(calcLocalEffects(intersection, ray, k));

	return (1 == level) ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * find the closest intersection point between the ray and the body
     * 
     * @param ray
     * @return closest point from the geometry bodies and the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
	return (ray.getClosestGeoPoint(scene.geometries.findGeoIntersections(ray)));
    }

    /**
     * construct the refracted ray
     * 
     * @param n     vector "on" the solid
     * @param point pointed by the vector n, on the solid to
     * @param inRay direction of the refracted vector
     * @return refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
	return new Ray(point, inRay.getDir(), n);
    }

    /**
     * construct the reflected ray
     * 
     * @param n     vector "on" the solid
     * @param point pointed by the vector n, on the solid to
     * @param inRay direction of the reflected vector
     * @return reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
	Vector v = inRay.getDir();
	double nv = n.dotProduct(v);
	if (nv == 0) return null;
	return new Ray(point, v.subtract(n.scale(2 * nv)), n);
    }

    /**
     * compute reflections and refractions effects in a point
     * 
     * @param geopoint point on the body
     * @param ray      direction of the light
     * @param level
     * @param k        coefficient
     * @return color of the point
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
	Color color = Color.BLACK;
	Material material = geopoint.geometry.getMaterial();

	double kr = material.kR, kkr = k * kr;
	if (kkr > MIN_CALC_COLOR_K) {
	    Ray reflectedRay = constructReflectedRay(geopoint.geometry.getNormal(geopoint.point), geopoint.point, ray);
	    GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
	    color = (reflectedPoint == null) ? color.add(Color.BLACK)
		    : color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
	}

	double kt = material.kT, kkt = k * kt;
	if (kkt > MIN_CALC_COLOR_K) {
	    Ray refractedRay = constructRefractedRay(geopoint.geometry.getNormal(geopoint.point), geopoint.point, ray);
	    GeoPoint refractedPoint = findClosestIntersection(refractedRay);
	    color = (refractedPoint == null) ? color.add(Color.BLACK)
		    : color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
	}
	return color;
    }

    /**
     * compute the transparency coefficient
     * 
     * @param light    the light source
     * @param l        direction of the light
     * @param n        normal to the body
     * @param geopoint intersection point
     * @return transparency coefficient
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	Vector lightDirection = l.scale(-1);// from point to light source
	Ray lightRay = new Ray(geopoint.point, lightDirection, n);
	double lightDistance = light.getDistance(geopoint.point);
	List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
	if (intersections == null) return 1.0;
	double ktr = 1.0;
	for (GeoPoint gp : intersections) {
	    if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
		ktr *= gp.geometry.getMaterial().kT;
		if (ktr < MIN_CALC_COLOR_K) return 0.0;
	    }
	}
	return ktr;
    }

    /**
     * calculate Local Effects specular and diffusive effects<br>
     * 
     * @param intersection point on the body
     * @param ray          intersect whit the point
     * @param k            coefficient
     * @return color of the intersection point
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
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
		double ktr = transparency(lightSource, l, n, intersection);
		if (ktr * k > MIN_CALC_COLOR_K) {
		    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
     * check if a point is unshaded
     * 
     * @param light    light source
     * @param l
     * @param n
     * @param geopoint
     * @return true if the point is enlighten
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	Vector lightDirection = l.scale(-1); // from point to light source
	Ray lightRay = new Ray(geopoint.point, lightDirection, n);
	double lightDistance = light.getDistance(geopoint.point);
	List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
	return intersections == null ? true : false;
    }

};
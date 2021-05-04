/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

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
		return calcColor(closestPoint);
	}

	/**
	 * calculate color
	 * 
	 * @param point for calculate is color for her
	 * @return return the intensity of the ambientLight of scene
	 */
	private Color calcColor(GeoPoint intersection) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
	}

}

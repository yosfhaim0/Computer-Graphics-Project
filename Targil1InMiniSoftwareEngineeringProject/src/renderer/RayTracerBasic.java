/**
 * 
 */
package renderer;

import java.util.List;

import primitives.*;
import scene.Scene;

/**
 * @author yosefHaim
 *
 */
public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);
		Point3D closestPoint = ray.getClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	private Color calcColor(Point3D point) {
		return scene.ambient.getIntensity();
	}

}

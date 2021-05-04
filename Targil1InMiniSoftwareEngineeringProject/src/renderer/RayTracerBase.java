/**
 * 
 */
package renderer;

import primitives.*;
import scene.*;

/**
 * interface for ray tracer
 * 
 * @author yosefHaim
 */
public abstract class RayTracerBase {
	/**
	 * scene
	 */
	protected Scene scene;

	/**
	 * ctor
	 * 
	 * @param scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * trace Ray and return the color of the intersection
	 * 
	 * @param ray to trace after in the scene
	 * @return color of the closest point, or <br>
	 *         if no intersect return the background color of scene
	 */
	public abstract Color traceRay(Ray ray);
}

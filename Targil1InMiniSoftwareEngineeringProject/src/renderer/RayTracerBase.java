/**
 * 
 */
package renderer;
import primitives.*;
import scene.*;
/**
 * @author yosefHaim
 *
 */
public abstract class RayTracerBase {
	/**
	 * scene
	 */
	protected Scene scene;

	/**
	 * @param scene
	 */
	public RayTracerBase(Scene scene) {
		super();
		this.scene = scene;
	}
	/**
	 * trace Ray and return the color
	 * @param ray
	 * @return color
	 */
	public abstract Color traceRay(Ray ray);
}

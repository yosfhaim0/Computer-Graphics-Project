package renderer;

import primitives.*;
import scene.*;

/**
 * RayTracerBase class is the base model for ray tracer
 * 
 * @author yosefHaim
 */
public abstract class RayTracerBase {
    /**
     * scene
     */
    protected Scene scene;

    /**
     * ctor for RayTracerBase
     * 
     * @param scene
     */
    public RayTracerBase(Scene scene) {
	this.scene = scene;
    }

    /**
     * trace a ray and return the color of the intersection point
     * 
     * @param ray to trace after in the scene
     * @return color of the closest point of the ray;<br>or 
     *         if there is no intersections return the background color
     */
    public abstract Color traceRay(Ray ray);
}

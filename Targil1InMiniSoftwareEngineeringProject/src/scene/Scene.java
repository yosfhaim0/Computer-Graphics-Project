/**
 * 
 */
package scene;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import elements.*;
import geometries.*;

/**
 * Scene is Contains all the components that <br>
 * exist in the space we want to photograph
 * 
 * @author yosefHaim
 */
public class Scene {
    /**
     * name of Scene
     */
    public String name;
    /**
     * background of the scene (all the ray how don't <br>
     * intersect whit body will get the color of background)<br>
     * Default=black color
     */
    public Color background = Color.BLACK;
    /**
     * ambient Light of scene <br>
     * Default=black color
     */
    public AmbientLight ambientLight = new AmbientLight();
    /**
     * collection of geometries shape
     */
    public Geometries geometries;
    /**
     * list of source light in scene <br>
     * Default= empty list
     */
    public List<LightSource> lights = new LinkedList<LightSource>();

    /**
     * ctor for scene
     * 
     * @param name of scene
     */
    public Scene(String name) {
	this.name = name;
	this.geometries = new Geometries();
    }

    /**
     * setter for background Color
     * 
     * @param background the background to set
     * 
     * @return this (scene)
     */
    public Scene setBackground(Color background) {
	this.background = background;
	return this;
    }

    /**
     * setter AmbientLight
     * 
     * @param ambientLight the ambientLight to set
     * 
     * @return this (scene)
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
	this.ambientLight = ambientLight;
	return this;
    }

    /**
     * setter for Geometries
     * 
     * @param geometr Geometries collection of 3d modulation
     * @return this (scene)
     */
    public Scene setGeometries(Geometries geometr) {
	this.geometries = geometr;
	return this;
    }

    /**
     * setter for LightSourceList
     * 
     * @param LightSourc List of light
     * @return this (scene)
     */
    public Scene setLightSourceList(List<LightSource> LightSourc) {
	this.lights = LightSourc;
	return this;
    }

}

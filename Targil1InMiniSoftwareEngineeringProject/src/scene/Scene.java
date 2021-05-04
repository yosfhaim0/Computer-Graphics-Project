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
 * @author yosefHaim Scene is collection present a snapshot/image
 */
public class Scene {
	/**
	 * name of Scene
	 */
	public String name;
	/**
	 * background of the scene (all the ray how dont <br>
	 * intersect whit body will get the color of background)
	 */
	public Color background = Color.BLACK;
	/**
	 * ambient Light of scene
	 */
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 1);
	/**
	 * collection of geometries shape
	 */
	public Geometries geometries;
	/**
	 * list of source light in scene
	 */
	public List<LightSource> LightSourceList = new LinkedList<LightSource>();

	/**
	 * ctur
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
	 * @return this (builder design)
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
	 * @return this (builder design)
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * setter for Geometries
	 * 
	 * @param geometr Geometries collection of 3d modulation
	 * @return this (builder design)
	 */
	public Scene setGeometries(Geometries geometr) {
		this.geometries = geometr;
		return this;
	}
	public Scene setLightSourceList(List<LightSource> LightSourc) {
	this.LightSourceList=LightSourc;
	return this;
	}

}

/**
 * 
 */
package scene;

import primitives.*;
import elements.*;
import geometries.*;

/**
 * @author yosefHaim
 *
 */
public class Scene {
	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;
	/**
	 * @param name of scene
	 */
	public Scene(String name) {
		super();
		this.name = name;
		this.geometries=new Geometries();
	}
	/**
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	/**
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}
	
	
}

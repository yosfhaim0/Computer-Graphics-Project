/**
 * 
 */
package renderer;
import java.util.MissingResourceException;

import elements.Camera;
import primitives.*;
import scene.*;
/**
 * @author yosefHaim
 *
 */
public class Render {
	ImageWriter imageWriter;
	Scene scene;
	Camera camera;
	RayTracerBasic rayTracerBasic;
	public void renderImage() {
		if(imageWriter==null)
			throw new MissingResourceException("Missing Resource", null, null);
		if(scene==null)
			throw new MissingResourceException("Missing Resource", null, null);
		if(camera==null)
			throw new MissingResourceException("Missing Resource", null, null);
		if(rayTracerBasic==null)
			throw new MissingResourceException("Missing Resource", null, null);
		throw new NotImplementedException();
	}
	public Render printGrid(int interval, Color color) {
		return null;
		
	}
	
}

/**
 * 
 */
package renderer;

import elements.*;
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
	RayTracerBasic rayTracerBase;

	public Render() {

	}

	public void renderImage() {

		imageWriter = new ImageWriter("ribua22", 100, 100);
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				Ray ray = camera.constructRayThroughPixel(100, 100, j, i);
				Color color = rayTracerBase.traceRay(ray);
				if (color != null)
					imageWriter.writePixel(j, i, color);
				else {
					imageWriter.writePixel(j, i, Color.BLACK);
				}
			}
		}

	}

	public Render printGrid(int interval, Color color) {
		return null;

	}

	public void writeToImage() {
		imageWriter.writeToImage();
	}

	public Render setImageWriter(ImageWriter imaWrit) {
		this.imageWriter = imaWrit;
		return this;
	}

	public Render setScene(Scene sce) {
		this.scene = sce;
		return this;
	}

	public Render setCamera(Camera cam) {
		this.camera = cam;
		return this;
	}

	public Render setRayTracer(RayTracerBasic basicRayTr) {
		this.rayTracerBase = basicRayTr;
		return this;
	}
}

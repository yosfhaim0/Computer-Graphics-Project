/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.*;
import primitives.*;
import scene.*;

/**
 * The render job is to create the color matrix of the image from the scene
 * 
 * @author yosefHaim
 *
 */
public class Render {
	/**
	 * Create the image
	 */
	ImageWriter imageWriter;
	/**
	 * scene for render
	 */
	Scene scene;
	/**
	 * point of view for render
	 */
	Camera camera;
	/**
	 * ray tracer for render
	 */
	RayTracerBasic rayTracerBase;

	/**
	 * void method for active the writePixel() <br>
	 * given for each pixel is color
	 */
	public void renderImage() {
		if (imageWriter == null)
			throw new MissingResourceException("one of the args of Render is null", ImageWriter.class.getName(),
					"imageWriter");
		if (scene == null)
			throw new MissingResourceException("one of the args of Render is null", Scene.class.getName(), "scene");
		if (camera == null)
			throw new MissingResourceException("one of the args of Render is null", Camera.class.getName(), "camera");
		if (rayTracerBase == null)
			throw new MissingResourceException("one of the args of Render is null", RayTracerBasic.class.getName(),
					"rayTracerBase");
		int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();
		Ray ray;
		Color color;
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				ray = camera.constructRayThroughPixel(Nx, Ny, j, i);
				color = rayTracerBase.traceRay(ray);
				imageWriter.writePixel(j, i, color);
			}
		}

	}

	/**
	 * Printing the grid with a fixed interval between lines
	 * 
	 * @param interval The interval between the lines.
	 * @param Color    the color of the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("one of the args of Render is null", ImageWriter.class.getName(),
					"imageWriter");
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				if (i % interval == 0 || j % interval == 0) {
					imageWriter.writePixel(j, i, color);
				}
			}
		}
	}

	/**
	 * active the writeToImage of the imageWriter <br>
	 * (we need that for keep know only your near friend)
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null, Illegal ", "imageWriter", "");
		imageWriter.writeToImage();
	}

	/**
	 * setter for ImageWriter
	 * 
	 * @param imaWrit for change in the Render
	 * @return this (Render)
	 */
	public Render setImageWriter(ImageWriter imaWrit) {
		this.imageWriter = imaWrit;
		return this;
	}

	/**
	 * setter for Scene
	 * 
	 * @param sce for change in the Render
	 * @return this (Render)
	 */
	public Render setScene(Scene sce) {
		this.scene = sce;
		return this;
	}

	/**
	 * setter for Camera
	 * 
	 * @param cam for change in the Render
	 * @return this (Render)
	 */
	public Render setCamera(Camera cam) {
		this.camera = cam;
		return this;
	}

	/**
	 * setter for RayTracerBasic
	 * 
	 * @param basicRayTr for change in the Render
	 * @return this (Render)
	 */
	public Render setRayTracer(RayTracerBasic basicRayTr) {
		this.rayTracerBase = basicRayTr;
		return this;
	}
}

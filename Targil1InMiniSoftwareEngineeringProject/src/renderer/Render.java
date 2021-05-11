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
	private ImageWriter imageWriter;
	/**
	 * point of view for render
	 */
	private Camera camera;
	/**
	 * ray tracer for render
	 */
	private RayTracerBase rayTracerBase;

	/**
	 * build a matrix - each case represent a pixel <br>
	 * for each pixel we compute his color
	 */
	public void renderImage() {
		if (imageWriter == null)
			throw new MissingResourceException("one of the args of Render is null", ImageWriter.class.getName(),
					"imageWriter");
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
	 * @param Color    the grid color
	 * @throws MissingResourceException if imageWriter is null
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("one of the args of Render is null", ImageWriter.class.getName(),
					"imageWriter");
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();

		// j is like the "x" component and i the "y component"
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
			throw new MissingResourceException("imageWriter is null, Illegal", "imageWriter", "");
		imageWriter.writeToImage();
	}

	/**
	 * setter for ImageWriter
	 * 
	 * @param imaWrit for change in the Render
	 * @return this (Render) - builder pattern
	 */
	public Render setImageWriter(ImageWriter imaWrit) {
		this.imageWriter = imaWrit;
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
	 * @return this (Render) - builder pattern
	 */
	public Render setRayTracer(RayTracerBase basicRayTr) {
		this.rayTracerBase = basicRayTr;
		return this;
	}
}

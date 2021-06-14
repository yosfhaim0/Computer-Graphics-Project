package renderer;

import java.util.List;
import java.util.MissingResourceException;

import elements.*;
import primitives.*;

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
	private static final String RESOURCE_ERROR = "Renderer resource not set";
	private static final String RENDER_CLASS = "Render";
	private static final String IMAGE_WRITER_COMPONENT = "Image writer";
	private static final String CAMERA_COMPONENT = "Camera";
	private static final String RAY_TRACER_COMPONENT = "Ray tracer";

	private int threadsCount = 0;
	private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
	private boolean print = false; // printing progress percentage

	/**
	 * Set multi-threading <br>
	 * - if the parameter is 0 - number of cores less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
		if (threads != 0)
			this.threadsCount = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			this.threadsCount = cores <= 2 ? 1 : cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		print = true;
		return this;
	}

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render
	 * object that they are generated in scope of. It is used for multithreading in
	 * the Renderer and for follow up its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each
	 * thread.
	 * 
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long maxRows = 0;
		private long maxCols = 0;
		private long pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long counter = 0;
		private int percents = 0;
		private long nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * 
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			this.maxRows = maxRows;
			this.maxCols = maxCols;
			this.pixels = (long) maxRows * maxCols;
			this.nextCounter = this.pixels / 100;
			if (Render.this.print)
				System.out.printf("\r %02d%%", this.percents);
		}

		/**
		 * Default constructor for secondary Pixel objects
		 */
		public Pixel() {
		}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object
		 * - this function is critical section for all the threads, and main Pixel
		 * object data is the shared data of this critical section.<br/>
		 * The function provides next pixel number each call.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return the progress percentage for follow up: if it is 0 - nothing to print,
		 *         if it is -1 - the task is finished, any other value - the progress
		 *         percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++this.counter;
			if (col < this.maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (Render.this.print && this.counter == this.nextCounter) {
					++this.percents;
					this.nextCounter = this.pixels * (this.percents + 1) / 100;
					return this.percents;
				}
				return 0;
			}
			++row;
			if (row < this.maxRows) {
				col = 0;
				target.row = this.row;
				target.col = this.col;
				if (Render.this.print && this.counter == this.nextCounter) {
					++this.percents;
					this.nextCounter = this.pixels * (this.percents + 1) / 100;
					return this.percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int finishPixels = nextP(target);
			if (Render.this.print && finishPixels > 0)
				synchronized (this) {
					notifyAll();
				}
			if (finishPixels >= 0)
				return true;
			if (Render.this.print)
				synchronized (this) {
					notifyAll();
				}
			return false;
		}

		/**
		 * Debug print of progress percentage - must be run from the main thread
		 */
		public void print() {
			if (Render.this.print)
				while (this.percents < 100)
					try {
						synchronized (this) {
							wait();
						}
						System.out.printf("\r %02d%%", this.percents);
						System.out.flush();
					} catch (Exception e) {
					}
		}
	}

	/**
	 * Produce a rendered image file active the writeToImage of the imageWriter <br>
	 * (we need that for keep know only your near friend)
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);

		imageWriter.writeToImage();
	}

	/**
	 * Cast ray from camera in order to color a pixel
	 * 
	 * @param nX  resolution on X axis (number of pixels in row)
	 * @param nY  resolution on Y axis (number of pixels in column)
	 * @param col pixel's column number (pixel index in row)
	 * @param row pixel's row number (pixel index in column)
	 */
	private void castRay(int nX, int nY, int col, int row) {
		List<Ray>  rays = camera.constructBeamRay(nX, nY, col, row);
		Color color;
		if (rays.size() == 1) {
			color = rayTracerBase.traceRay(rays.get(0));
		} else {
			color = Color.BLACK;
			for (Ray r : rays) {
				color = color.add(rayTracerBase.traceRay(r));
			}
			color = color.reduce(rays.size());
		}
		imageWriter.writePixel(col, row, color);
	}

	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object - with multi-threading
	 */
	private void renderImageThreaded() {
		final int nX = imageWriter.getNx();
		final int nY = imageWriter.getNy();
		final Pixel thePixel = new Pixel(nY, nX);
		// Generate threads
		Thread[] threads = new Thread[threadsCount];
		for (int i = threadsCount - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel))
					castRay(nX, nY, pixel.col, pixel.row);
			});
		}
		// Start threads
		for (Thread thread : threads)
			thread.start();

		// Print percents on the console
		thePixel.print();

		// Ensure all threads have finished
		for (Thread thread : threads)
			try {
				thread.join();
			} catch (Exception e) {
			}

		if (print)
			System.out.print("\r100%");
	}

	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object build a matrix - each case represent a pixel <br>
	 * for each pixel we compute his color
	 */
	public void renderImage() {
		if (imageWriter == null)
			throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, IMAGE_WRITER_COMPONENT);
		if (camera == null)
			throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, CAMERA_COMPONENT);
		if (rayTracerBase == null)
			throw new MissingResourceException(RESOURCE_ERROR, RENDER_CLASS, RAY_TRACER_COMPONENT);

		final int nX = imageWriter.getNx();
		final int nY = imageWriter.getNy();
		if (threadsCount == 0)
			for (int i = 0; i < nY; ++i)
				for (int j = 0; j < nX; ++j)
					castRay(nX, nY, j, i);
		else
			renderImageThreaded();

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

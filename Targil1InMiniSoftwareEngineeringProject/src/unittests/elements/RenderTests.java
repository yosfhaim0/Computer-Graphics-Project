package unittests.elements;

import org.junit.Test;


import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {
	private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)) //
			.setVpDistance(100) //
			.setViewPlaneSize(500, 500);
	private Camera camera1 = new Camera(new Point3D(0, 0, 9), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
			.setVpDistance(5) //
			.setViewPlaneSize(500, 500);

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {

		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere( new Point3D(0, 0, -100),50),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up right
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)), // down left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down right

		ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
		//TC02: smail face test
		Scene scene1 = new Scene("Test scene1")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
				.setBackground(new Color(75, 127, 90));
		scene1.geometries.add(new Sphere(new Point3D(2, 2, 0),1),new Sphere(new Point3D(-2, 2, 0), 1),
				new Polygon(new Point3D(-3,0,0),new Point3D(-2, 0, 0),new Point3D(0, -2, 0) ,new Point3D(0, -3, 0)),
				new Polygon(new Point3D(3,0,0),new Point3D(2, 0, 0),new Point3D(0, -2, 0) ,new Point3D(0, -3, 0)),
				new Sphere(new Point3D(0,0,-5), 5));
		
		ImageWriter imageWriter1 = new ImageWriter("base render test", 10, 10);
		Render render1 = new Render() //
				.setImageWriter(imageWriter1) //
				.setScene(scene1) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));

		render1.renderImage();
		render1.printGrid(100, new Color(java.awt.Color.YELLOW));
		render1.writeToImage();
		
		
		
	}
	
	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene("XML Test scene");
		// enter XML file name and parse from XML file into scene object
		// ...
		
		ImageWriter imageWriter = new ImageWriter("xml render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	
}

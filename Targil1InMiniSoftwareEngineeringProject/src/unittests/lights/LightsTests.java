package unittests.lights;

import java.util.List;

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
/**
 * @author yosefHaim
 *
 */
public class LightsTests {
	private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
	private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150) //
			.setVpDistance(1000);
	private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200) //
			.setVpDistance(1000);
	private Camera camera3 = new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
			.setVpDistance(1000).setViewPlaneSize(150, 150);

	private static Geometry triangle1 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
	private static Geometry triangle2 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
	private static Geometry sphere = new Sphere(new Point3D(0, 0, -50), 50) //
			.setEmission(new Color(java.awt.Color.BLUE)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1)));

		ImageWriter imageWriter = new ImageWriter("sphereDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights
				.add(new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50)).setkL(0.00001).setkQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("spherePoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2))
				.setkL(0.00001).setkQ(0.00000001));

		ImageWriter imageWriter = new ImageWriter("sphereSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)), //
				triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)), //
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
		scene2.lights
				.add(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130)).setkL(0.0005).setkQ(0.0005));

		ImageWriter imageWriter = new ImageWriter("trianglesPoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
		scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
				.setkL(0.0001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("trianglesSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a narrow spot light
	 */
	@Test
	public void sphereSpotSharp() {
		scene1.geometries.add(sphere);
		scene1.lights
				.add(((SpotLight) new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2))
						.setkL(0.000005).setkQ(0.00000025)).setSharp(5));

		ImageWriter imageWriter = new ImageWriter("sphereSpotSharp", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a narrow spot light
	 */
	@Test
	public void trianglesSpotSharp() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
		scene2.lights.add((new SpotLight(new Color(800, 400, 400), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
				.setkL(0.00005).setkQ(0.0000025)).setSharp(5));

		ImageWriter imageWriter = new ImageWriter("trianglesSpotSharp", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a point light and spot and direction
	 * light
	 */
	@Test
	public void spherePointMultiLights2() {
		scene1.geometries.add(sphere.setMaterial(new Material().setKd(0).setKs(0.5).setShininess(300)));
		scene1.lights.addAll(List.of(
				new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50)).setkL(0.00001).setkQ(0.000001),
				new DirectionalLight(new Color(500, 300, 0), new Vector(-50, -50, -50)),
				((SpotLight) new SpotLight(new Color(500, 300, 0), new Point3D(50, -50, 0), new Vector(-50, 50, -50))
						.setkL(0.0001).setkQ(0.000005)).setSharp(100),
				((SpotLight) new SpotLight(new Color(500, 300, 0), new Point3D(50, -50, 0), new Vector(-25, 75, -50))
						.setkL(0.0001).setkQ(0.000005)).setSharp(100),
				((SpotLight) new SpotLight(new Color(500, 300, 0), new Point3D(50, -50, 0), new Vector(-75, 25, -50))
						.setkL(0.0001).setkQ(0.000005)).setSharp(100)));

		ImageWriter imageWriter = new ImageWriter("spherePointMyTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a multiple lights
	 */
	@Test
	public void sphereMultiLights() {

		Scene scene = new Scene("Test scene");
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.geometries.add(new Sphere(new Point3D(0, 0, 50), 50)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
				.setEmission(new Color(java.awt.Color.BLUE)));

		scene.lights.addAll(List.of(
				new SpotLight(new Color(200, 300, 0), new Point3D(-100, 20, -50), new Vector(1, -1, 1)).setkC(1.2)
						.setkL(0.00001).setkQ(0.00000001),
				new DirectionalLight(new Color(0, 400, 300), new Vector(-1, 1, 1)),
				new PointLight(new Color(500, 50, 100), new Point3D(100, 100, -50)).setkC(1).setkL(0.0001)
						.setkQ(0.0000001)));

		ImageWriter imageWriter = new ImageWriter("sphereMultiLights", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera3)
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a multiple lights
	 */
	@Test
	public void triangleMultiLights() {

		Scene scene = new Scene("Test scene");
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.ORANGE), 0.15));

		scene.geometries.add(
				triangle1.setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				triangle2.setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

		scene.lights.addAll(List.of(
				new SpotLight(new Color(700, 20, 50), new Point3D(10, -10, -130), new Vector(-2, -2, -1)).setkL(0.0001)
						.setkQ(0.000005),
				new PointLight(new Color(0, 0, 300), new Point3D(10, -10, -130)).setkL(0.0005).setkQ(0.0002),
				new DirectionalLight(new Color(5, 100, 5), new Vector(0, 0, -1))));

		ImageWriter imageWriter = new ImageWriter("triangleMultiLights", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera2)
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	
}

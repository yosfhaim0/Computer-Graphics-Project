package unittests.lights;

import java.util.Iterator;
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

    /**
     * Newton's cradle
     */
    @Test
    public void body10pictuer() {
	Scene scene = new Scene("test case");
	Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
		.setVpDistance(13900.13562).setViewPlaneSize(2000, 2000);
	scene.setBackground(new Color(java.awt.Color.blue));
	scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));

	scene.geometries.add(
		new Cylinder(new Ray(new Point3D(500, 0, 0), new Vector(0, 0, 1)), 20, 500).setEmission(Color.BLACK)
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Cylinder(new Ray(new Point3D(-500, 0, 0), new Vector(0, 0, 1)), 20, 500)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Cylinder(new Ray(new Point3D(-500, 0, 500), new Vector(1, 0, 0)), 20, 1000)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),

		new Sphere(new Point3D(240, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)),
		new Sphere(new Point3D(120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)),
		new Sphere(new Point3D(0, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)),
		new Sphere(new Point3D(-120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)),
		new Sphere(new Point3D(-414.9996, 0, 226.567), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)),

		new Cylinder(new Ray(new Point3D(-240, 0, 500), new Vector(-Math.sqrt(3) / 3, 0, -1)), 5, 350)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Cylinder(new Ray(new Point3D(-120, 0, 150), new Vector(0, 0, 1)), 5, 350)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Cylinder(new Ray(new Point3D(0, 0, 150), new Vector(0, 0, 1)), 5, 350)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Cylinder(new Ray(new Point3D(120, 0, 150), new Vector(0, 0, 1)), 5, 350)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Cylinder(new Ray(new Point3D(240, 0, 150), new Vector(0, 0, 1)), 5, 350)
			.setEmission(new Color(java.awt.Color.black))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Plane(new Point3D(0, 0, -50), new Vector(0, 0, 1)).setEmission(new Color(100, 100, 100))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(500)),

		new Polygon(new Point3D(550, 50, 0), new Point3D(550, 50, -50), new Point3D(-550, 50, -50),
			new Point3D(-550, 50, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Polygon(new Point3D(550, 50, 0), new Point3D(550, 50, -50), new Point3D(550, -50, -50),
			new Point3D(550, -50, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Polygon(new Point3D(-550, -50, 0), new Point3D(-550, -50, -50), new Point3D(-550, 50, -50),
			new Point3D(-550, 50, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
		new Polygon(new Point3D(550, 50, 0), new Point3D(550, -50, 0), new Point3D(-550, -50, 0),
			new Point3D(-550, 50, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)));

	scene.lights.addAll(List.of(// new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
		((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(-400, 1000, 1000),
			new Vector(400, -1000, -1500)) //
				.setkL(1E-5).setkQ(1.5E-7)).setSharp(3)));

	ImageWriter imageWriter = new ImageWriter("Newton's cradle", 500, 500);
	Render render = new Render().setImageWriter(imageWriter).setCamera(cam).setRayTracer(new RayTracerBasic(scene));

	render.renderImage();
	// render.printGrid(50, new Color(java.awt.Color.green));
	render.writeToImage();

    }

    /**
     * Produce a picture of a some spheres with light
     */
    @Test
    public void OurPicture2() {
	Scene scene = new Scene("Test scene");
	Camera camera = new Camera(new Point3D(0, -620, -800), new Vector(0, 1, Math.sqrt(3)),
		new Vector(0, -1 * Math.sqrt(3), 1));
	camera.setVpDistance(1000);
	camera.setViewPlaneSize(200, 200);
	scene.setBackground(new Color(65, 105, 225));
	scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	Color c = new Color(0, 0, 0);
	int x = -150;
	int z = 100;
	for (int j = 0; j < 4; j++) {
	    x = -150;
	    for (int i = 0; i < 4; i++) {
		scene.geometries.add(new Sphere(new Point3D(x, 70, z), 50).setEmission(c)
			.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		x += 100;
	    }

	    x = -150;
	    for (int i = 0; i < 4; i++) {
		if (j == 2 && i == 2)
		    scene.geometries.add(new Sphere(new Point3D(0, 70, 400), 50).setEmission(new Color(32, 178, 170))
			    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		else
		    scene.geometries.add(new Sphere(new Point3D(x - 50, 70, z - 100), 50).setEmission(c)
			    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		x += 100;
	    }
	    z += 200;
	}
	scene.lights.addAll(List.of(
		new SpotLight(new Color(500, 300, 0), new Point3D(50, 0, 350), new Vector(-1, 1, 0.5)).setkL(0.0001)
			.setkQ(0.0001),
		new SpotLight(new Color(255, 255, 224), new Point3D(200, -200, 350), new Vector(-1, 1, 0.5)).setkL(2E-7)
			.setkQ(2E-10),
		new DirectionalLight(new Color(224, 255, 255), new Vector(1, 5, 0))));

	ImageWriter imageWriter = new ImageWriter("10 body bouns2", 1000, 1000);
	Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
		.setRayTracer(new RayTracerBasic(scene));
	// .setMultithreading(3);

	render.renderImage();
	render.writeToImage();
    }
}

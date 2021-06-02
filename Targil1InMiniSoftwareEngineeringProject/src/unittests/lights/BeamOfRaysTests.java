/**
 * 
 */
package unittests.lights;

import scene.*;

import java.util.List;
import org.junit.Test;
import elements.*;
import primitives.*;
import geometries.*;
import renderer.*;

/**
 * @author yosefHaim
 *
 */
public class BeamOfRaysTests {
    /**
     * glussy test
     */
    @Test
    public void GlassCube() {

	Scene scene = new Scene("Cube scene");
	Camera camera = (new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, 1, 0)))
		.setVpDistance(1000).setViewPlaneSize(150, 150);

	scene.setBackground(new Color(25, 25, 112));
	scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

	scene.geometries.add(new Polygon( // AEFD
		new Point3D(0, 0, 0), new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new Point3D(-50, 0, 50))
			.setEmission(new Color(105, 105, 105))
			.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0)),
		new Polygon( // TOP
			new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new Point3D(0, 70, 100),
			new Point3D(50, 70, 50)).setEmission(new Color(105, 105, 105))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0)),
		new Polygon( // DFHB
			new Point3D(-50, 0, 50), new Point3D(-50, 70, 50), new Point3D(0, 70, 100),
			new Point3D(0, 0, 100)).setEmission(new Color(105, 105, 105))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0)),
		new Polygon( // BHGC
			new Point3D(0, 0, 100), new Point3D(0, 70, 100), new Point3D(50, 70, 50),
			new Point3D(50, 0, 50)).setEmission(new Color(105, 105, 105))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0)),
		new Polygon( // CGEA
			new Point3D(50, 0, 50), new Point3D(50, 70, 50), new Point3D(0, 70, 0), new Point3D(0, 0, 0))
				.setEmission(new Color(105, 105, 105))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0)),
		new Polygon( // BOTTOM
			new Point3D(0, 0, 0), new Point3D(-50, 0, 50), new Point3D(0, 0, 100), new Point3D(50, 0, 50))
				.setEmission(new Color(105, 105, 105))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0))

		, new Sphere(new Point3D(0, 35, 50), 25).setEmission(new Color(java.awt.Color.red))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))

		, new Plane(new Point3D(0, -5, 0), new Vector(0, 1, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.25).setKs(0.3).setShininess(50).setkT(0).setkR(0.8))

		,
		new Sphere(new Point3D(-100, 35, 0), 30).setEmission(new Color(255, 210, 0))
			.setMaterial(new Material().setKd(0.3).setKs(0.4).setShininess(70).setkT(0).setkR(0.3)),
		new Sphere(new Point3D(100, 35, 0), 30).setEmission(new Color(255, 210, 0))
			.setMaterial(new Material().setKd(0.3).setKs(0.4).setShininess(70).setkT(0).setkR(0.3))

	);

	scene.lights.addAll(
		List.of(new SpotLight(new Color(1000, 600, 1000), new Point3D(-100, 100, 100), new Vector(1, -0.4, -1))
			.setkC(1).setkL(0.0001).setkQ(0.00005)

			, new DirectionalLight(new Color(255, 215, 0), new Vector(-1, -0.4, 1))));
	int p = 500;
	ImageWriter imageWriter = new ImageWriter("GlassCube", p, p);
	Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
		.setRayTracer(new RayTracerBasic(scene).setDistance(80).setNumOfRay(2).setRadius(0.08))
		.setMultithreading(3).setDebugPrint();

	render.renderImage();
	render.writeToImage();
	// whitin glussy
	ImageWriter imageWriter1 = new ImageWriter("GlassCube whitin glussy", p, p);
	Render render1 = new Render().setImageWriter(imageWriter1).setCamera(camera)
		.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();

	render1.renderImage();
	render1.writeToImage();
    }

    /**
     * soft shdow test
     */
    @Test
    public void SoftShadowTest() {
	Scene scene = new Scene("Test scene");
	Camera camera = new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
		.setVpDistance(1000).setViewPlaneSize(300, 300);
	scene.setBackground(primitives.Color.BLACK);
	scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), 0.15));

	scene.geometries.add( //
		new Polygon(new Point3D(-400, -400, 150), new Point3D(400, -400, 150), new Point3D(400, 400, 150),
			new Point3D(-400, 400, 150)).setEmission(new primitives.Color(0, 0, 0))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)), //
		new Sphere(new Point3D(0, 0, 130), 20).setEmission(new primitives.Color(java.awt.Color.green))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), // //
		// cube:
		new Polygon(new Point3D(-80, -80, 150), new Point3D(-50, -80, 150), new Point3D(-50, -80, 120),
			new Point3D(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
		new Polygon(new Point3D(-50, -80, 150), new Point3D(-50, -50, 150), new Point3D(-50, -50, 120),
			new Point3D(-50, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
		new Polygon(new Point3D(-80, -50, 150), new Point3D(-50, -50, 150), new Point3D(-50, -50, 120),
			new Point3D(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
		new Polygon(new Point3D(-80, -80, 150), new Point3D(-80, -50, 150), new Point3D(-80, -50, 120),
			new Point3D(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
		new Polygon(new Point3D(-80, -80, 120), new Point3D(-50, -80, 120), new Point3D(-50, -50, 120),
			new Point3D(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
		new Cylinder(new Ray(new Point3D(80, -65, 150), new Vector(0, 0, -1)), 20, 20)
			.setEmission(new primitives.Color(java.awt.Color.BLUE))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));

	scene.lights.addAll(List.of(new PointLight(new primitives.Color(700, 400, 400), //
		new Point3D(0, -80, 80)).setkC(1).setkL(4E-4).setkQ(2E-5).setRadius(25)));
	int p = 700;
	ImageWriter imageWriter = new ImageWriter("Soft Shadow Test", p, p);
	Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
		.setRayTracer(new RayTracerBasic(scene).setNumOfRay(100)).setMultithreading(3).setDebugPrint();

	render.renderImage();
	render.writeToImage();

	ImageWriter imageWriter2 = new ImageWriter("Soft Shadow Test whitout soft", p, p);
	Render render2 = new Render().setImageWriter(imageWriter2).setCamera(camera)
		.setRayTracer(new RayTracerBasic(scene).setNumOfRay(1)).setMultithreading(3).setDebugPrint();

	render2.renderImage();
	render2.writeToImage();
    }

    /**
     * tank picture (look like soryan tank)
     */
    @Test
    public void blurryTest() {
	Scene scene = new Scene("test case");
	Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
		.setVpDistance(13900.13562).setViewPlaneSize(5000, 5000);
	scene.setBackground(Color.BLACK);
	scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));
	scene.geometries.add(
		new Polygon(new Point3D(-500, -500, 500), new Point3D(500, -500, 500), new Point3D(500, -700, 250),
			new Point3D(-500, -700, 250)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // SIDE BACK
		new Polygon(new Point3D(-500, 500, 500), new Point3D(500, 500, 500), new Point3D(500, 700, 250),
			new Point3D(-500, 700, 250)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // side front
		new Polygon(new Point3D(500, -500, 500), new Point3D(500, 500, 500), new Point3D(500, 700, 250),
			new Point3D(500, -700, 250)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // side RIGHT
		new Polygon(new Point3D(-500, -500, 500), new Point3D(-500, -700, 250), new Point3D(-500, 700, 250),
			new Point3D(-500, 500, 500)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // side left
		new Polygon(new Point3D(-500, -500, 500), new Point3D(500, -500, 500), new Point3D(500, 500, 500),
			new Point3D(-500, 500, 500)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // up side

		new Polygon(new Point3D(-400, 500, 0), new Point3D(-400, 700, 250), new Point3D(-400, -700, 250),
			new Point3D(-400, -500, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // LEFT SIDE DOWN
		new Polygon(new Point3D(400, -700, 250), new Point3D(400, 700, 250), new Point3D(400, 500, 0),
			new Point3D(400, -500, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // RIGTH SIDE DOWN
		new Polygon(new Point3D(400, 700, 250), new Point3D(-400, 700, 250), new Point3D(-400, 500, 0),
			new Point3D(400, 500, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // front down
		new Polygon(new Point3D(-400, -700, 250), new Point3D(400, -700, 250), new Point3D(400, -500, 0),
			new Point3D(-400, -500, 0)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // back down

		new Cylinder(new Ray(new Point3D(490, -350, 150), new Vector(-1, 0, 0)), 150, 980)
			.setEmission(new Color(java.awt.Color.gray))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // lest while
		new Cylinder(new Ray(new Point3D(490, 0, 150), new Vector(-1, 0, 0)), 150, 980)
			.setEmission(new Color(java.awt.Color.gray))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // midel while
		new Cylinder(new Ray(new Point3D(490, 350, 150), new Vector(-1, 0, 0)), 150, 980)
			.setEmission(new Color(java.awt.Color.gray))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // first while

		new Sphere(new Point3D(0, 0, 500), 350).setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // the chriach
		new Cylinder(new Ray(new Point3D(0, 0, 700), new Vector(0, 1, 0)), 30, 1000)
			.setEmission(new Color(java.awt.Color.DARK_GRAY))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(5)), // BOMBER
		new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(100, 100, 100))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(500)), // flor
		new Polygon(new Point3D(300, 2000, 2000), new Point3D(300, 2000, 0), new Point3D(-300, 2000, 0),
			new Point3D(-300, 2000, 2000)).setEmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6).setkR(0))

	);

	scene.lights.addAll(List.of(new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
		((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(-500, -1000, 3000),
			new Point3D(0, 0, 700)) //
				.setkL(1E-5).setkQ(1.5E-7)).setSharp(3),
		new SpotLight(new Color(1000, 1000, 1000), new Point3D(0, 1020, 700), new Vector(0, -1, 0)) //
			.setkL(1E-5).setkQ(1.5E-7).setSharp(5)));
	int p = 1000;
	ImageWriter imageWriter = new ImageWriter("Syria tank position whitout blurry ", p, p);
	Render render = new Render().setImageWriter(imageWriter).setCamera(cam).setRayTracer(new RayTracerBasic(scene))
		.setMultithreading(3).setDebugPrint();

	render.renderImage();
	render.writeToImage();
	boolean flag = true;
	if (flag) {
	    render = render
		    // .setCamera(cam.setPosition(new Point3D(2000, 5000, 5000)).setCameraHead(new
		    // Point3D(0, 0, 500))
		    // .setVpDistance(Math.sqrt(10000 * 5000)))
		    .setImageWriter(new ImageWriter("Syria tank position whit blurry 2", p, p))
		    .setRayTracer(new RayTracerBasic(scene).setNumOfRay(2).setRadius(0.01)).setMultithreading(3)
		    .setDebugPrint();
	    render.renderImage();
	    render.writeToImage();

	    render = render.setImageWriter(new ImageWriter("Syria tank position blurryer  3", p, p))
		    .setRayTracer(new RayTracerBasic(scene).setNumOfRay(2).setRadius(0.02)).setMultithreading(3)
		    .setDebugPrint();
	    render.renderImage();
	    render.writeToImage();

	    render = render.setImageWriter(new ImageWriter("Syria tank position blurryest  4", p, p))
		    .setRayTracer(new RayTracerBasic(scene).setNumOfRay(2).setRadius(0.04)).setMultithreading(3)
		    .setDebugPrint();
	    render.renderImage();
	    render.writeToImage();
	}
    }

    @Test
    public void DepthOfField() {

	Scene scene = new Scene("Depth Of Field");

	Camera camera = (new Camera(new Point3D(150, 100, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)))
		.setVpDistance(1400).setViewPlaneSize(700, 700);

	scene.setBackground(new Color(25, 25, 112));
	scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

	scene.geometries.add(
		new Sphere(Point3D.ZERO, 50).setEmission(new Color(java.awt.Color.gray))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Sphere(new Point3D(100, 50, -100), 50).setEmission(new Color(java.awt.Color.RED))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Sphere(new Point3D(200, 100, -200), 50).setEmission(new Color(java.awt.Color.GREEN))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Sphere(new Point3D(300, 150, -300), 50).setEmission(new Color(java.awt.Color.BLUE))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
		new Sphere(new Point3D(400, 200, -400), 50).setEmission(new Color(java.awt.Color.darkGray))
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))

	);

	scene.lights
		.addAll(List.of(
			new SpotLight(new Color(1000, 600, 1000), new Point3D(0, 100, 100), Point3D.ZERO).setkC(1)
				.setkL(0.0001).setkQ(0.00005),
			new DirectionalLight(new Color(255, 215, 255), new Vector(0, -0.2, -1))));
	int p = 1000;
	ImageWriter imageWriter = new ImageWriter("Depth Of Field", p, p);
	Render render = new Render().setImageWriter(imageWriter)
		.setCamera(camera
			.setDistanceToFocalPlane(new Point3D(150, 100, 1000).distance(new Point3D(200, 100, -200)))
			.setSizeForApertureWindow(100).setNumOfRayFormApertureWindowToFocalPoint(50))
		.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3)//
		.setDebugPrint();

	render.renderImage();
	render.writeToImage();

    }

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void AntiAliasing() {
	Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
		.setVpDistance(9600) //
		.setViewPlaneSize(1000, 1000).setNumOfRayForAntiAliasing(150);
	Scene scene = new Scene("Anti Aliasing test")//
		.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
		.setBackground(new Color(75, 127, 90));

	scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 500)

	); // down
	   // right
	int p = 1000;
	ImageWriter imageWriter = new ImageWriter("base whit Anti Aliasing", p, p);
	Render render = new Render() //
		.setImageWriter(imageWriter) //
		.setCamera(camera) //
		.setRayTracer(new RayTracerBasic(scene)).setDebugPrint()//
		.setMultithreading(3)//
	//
	;

	render.renderImage();
	render.writeToImage();

	camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
		.setVpDistance(9600) //
		.setViewPlaneSize(1000, 1000);
	ImageWriter imageWriter1 = new ImageWriter("base whitout Anti Aliasing", p, p);
	Render render1 = new Render() //
		.setImageWriter(imageWriter1) //
		.setCamera(camera) //
		.setRayTracer(new RayTracerBasic(scene))//
		.setDebugPrint()//
		.setMultithreading(3);

	render1.renderImage();
	render1.writeToImage();
    }
}

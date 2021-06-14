package unittests.MiniProjectTests;

import java.util.List;

import org.junit.Test;
import elements.*;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

/**
 * Mini Project 2 Accelerate Tests Picture
 * 
 * @author yosefHaim
 *
 */
public class MP2AccelerateTests {
	/**
	 * Produce a picture of a some spheres with light
	 */
	@Test
	public void spheres32() {
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(0, -620, -800), new Vector(0, 1, Math.sqrt(3)),
				new Vector(0, -1 * Math.sqrt(3), 1)).setNumOfRayFormApertureWindowToFocalPoint(20)
						.setSizeForApertureWindow(100)
						.setDistanceToFocalPlane(new Point3D(0, -620, -800).distance(new Point3D(0, 70, 400)) - 50);
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
						.setkQ(0.0001).setRadius(20),
				new SpotLight(new Color(255, 255, 224), new Point3D(200, -200, 350), new Vector(-1, 1, 0.5)).setkL(2E-7)
						.setkQ(2E-10).setRadius(20),
				new DirectionalLight(new Color(224, 255, 255), new Vector(1, 5, 0))));
		int p = 700;

		ImageWriter imageWriter = new ImageWriter("MP2 31 sphere and pearl", p, p);
		Render render = new Render()//
				.setImageWriter(imageWriter)//
				.setCamera(camera).setRayTracer(new RayTracerBasic(scene.//
						setTreeOfGeomtir())//
				// .setNumOfRaySoftShadow(30)//
				)

				.setMultithreading(3)//
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * x and circel picture
	 */
	@Test
	public void picturForZoomBackground() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 0), new Vector(0, -1, 0), new Vector(0, 0, 1))
				.setVpDistance(10000).setViewPlaneSize(5000, 5000);
		scene.setBackground(Color.GOLD);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));
		scene.geometries.add(
				// the game
				new Tube(new Ray(new Point3D(0, 0, 834), new Vector(1, 0, 0)), 150)
						.setEmission(new Color(java.awt.Color.green))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Tube(new Ray(new Point3D(834, 0, 0), new Vector(0, 0, 1)), 150)
						.setEmission(new Color(java.awt.Color.pink))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Tube(new Ray(new Point3D(-834, 0, 0), new Vector(0, 0, 1)), 150).setEmission(Color.PERPERL)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Tube(new Ray(new Point3D(0, 0, -834), new Vector(1, 0, 0)), 150).setEmission(Color.BROWM)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				// eix
				new Cylinder(new Ray(new Point3D(2300, 0, 2300), new Vector(-1, 0, -1)), 150, 1500)
						.setEmission(new Color(java.awt.Color.blue))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // x
				new Cylinder(new Ray(new Point3D(2300, 0, 1200), new Vector(-1, 0, 1)), 150, 1500)
						.setEmission(new Color(java.awt.Color.blue))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // eix

				// circel
				new Cylinder(new Ray(new Point3D(1720, 0, 10), new Vector(0, -1, 0)), 650, 1)
						.setEmission(new Color(java.awt.Color.red))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point3D(1720, 0, 10), 500).setEmission(Color.GOLD)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // circel
				// circel
				new Cylinder(new Ray(new Point3D(10, 0, 1750), new Vector(0, -1, 0)), 650, 1)
						.setEmission(new Color(java.awt.Color.red))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point3D(10, 0, 1750), 500).setEmission(Color.GOLD)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // circel

				// PLANE
				new Plane(new Point3D(0, -2000, 0), new Vector(0, 1, 0))
						.setEmission(new Color(java.awt.Color.DARK_GRAY)).setMaterial(new Material().setKd(0.2)
								.setKs(0.2).setShininess(30).setKt(0.6).setKr(0).setRadiusForBlurry(2))

		);

		scene.addLights(new DirectionalLight(new Color(0, 0, 0), new Vector(0, -1, 0)),
				((SpotLight) new SpotLight(new Color(1500, 1500, 1500), new Point3D(3000, 5000, 3000),
						new Point3D(10, 0, 1750)) //
								.setkL(1E-5).setkQ(1.5E-7)).setRadius(100),
				new SpotLight(new Color(200, 200, 200), new Point3D(0, 8000, 0), new Point3D(1720, 0, 10)) //
						.setkL(1E-5).setkQ(1.5E-7).setSharp(5),
				new SpotLight(new Color(200, 200, 200), new Point3D(0, 8000, 0), new Point3D(2300, 0, 2300)) //
						.setkL(1E-5).setkQ(1.5E-7).setSharp(5));
		int p = 700;
		ImageWriter imageWriter = new ImageWriter("MP2 picturForZoomBackground", 480, 360);
		Render render = new Render().setImageWriter(imageWriter).setCamera(cam)
				.setRayTracer(new RayTracerBasic(scene).setNumOfRaySoftShadow(20)).setMultithreading(3)
				.setDebugPrint();
		scene.geometries.createBox();
		scene.geometries.createGeometriesTree();
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Cylinder and spheres
	 */
	@Test
	public void cylindersAndSpheres() {

		Scene scene = new Scene("spheres");

		Camera camera = (new Camera(new Point3D(1000, 500, 500), new Vector(0, -1, 0), new Vector(1, 0, 0)))
				.setVpDistance(4000).setViewPlaneSize(2000, 2000).setCameraHead(Point3D.ZERO)
				.setNumOfRayForAntiAliasing(2);

		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));
		Color color[] = { Color.BLACK, Color.BROWM, Color.GOLD, Color.PERPERL, Color.BLACK,
				new Color(java.awt.Color.green), new Color(java.awt.Color.blue), new Color(java.awt.Color.GRAY),
				Color.BLACK, new Color(java.awt.Color.orange), new Color(java.awt.Color.red),
				new Color(java.awt.Color.DARK_GRAY), Color.BLACK, new Color(java.awt.Color.cyan),
				new Color(java.awt.Color.magenta), new Color(java.awt.Color.pink), Color.BLACK,
				new Color(java.awt.Color.WHITE), new Color(java.awt.Color.yellow),
				new Color(java.awt.Color.lightGray) };
		int c = 0;
		int sum = 16;
		for (int i = -1000; i < 1000; i = i + 100) {
			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, 0), new Vector(0, 1, 0)), 25, 100)
					.setEmission(color[c++ % sum]).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		c = 0;
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, 150), 40).setEmission(color[c++ % sum])
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		c = 0;
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, -150), 40).setEmission(color[c++ % sum])
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		c = 0;
		for (int i = -1000; i < 1000; i = i + 100) {

			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, -300), new Vector(0, 1, 0)), 25, 100)
					.setEmission(color[c++ % sum]).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		c = 0;
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, -450), 40).setEmission(color[c++ % sum])
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		c = 0;
		for (int i = -1000; i < 1000; i = i + 100) {

			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, -600), new Vector(0, 1, 0)), 25, 100)
					.setEmission(color[c++ % sum]).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
//		

		scene.geometries.add(new Plane(Point3D.ZERO, new Vector(0, 1, 0)).setEmission(Color.BLACK)
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)));
		scene.addLights(
				new SpotLight(new Color(1000, 600, 1000), new Point3D(1000, 500, 500), Point3D.ZERO).setkC(1)
						.setkL(0.0001).setkQ(0.00005).setRadius(100),
				new PointLight(new Color(40000, 40000, 40000), new Point3D(1000, 1000, -1000)).setkC(1).setkL(4E-4)
						.setkQ(2E-5).setRadius(300));
		int p = 1000;
		scene.setTreeOfGeomtir();
		ImageWriter imageWriter = new ImageWriter("MP2 cliyndersAndSpheres", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene).setNumOfRaySoftShadow(30))//
				.setMultithreading(3)//
				.setDebugPrint()//
		;

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * cylinder and spheres whitout color, only black
	 */
	@Test
	public void cylindersAndSpheres2() {

		Scene scene = new Scene("spheres");

		Camera camera = (new Camera(new Point3D(1000, 500, 500), new Vector(0, -1, 0), new Vector(1, 0, 0)))
				.setVpDistance(4000).setViewPlaneSize(2000, 2000).setCameraHead(Point3D.ZERO)
				.setNumOfRayForAntiAliasing(2);

		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));
		for (int i = -1000; i < 1000; i = i + 100) {
			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, 0), new Vector(0, 1, 0)), 25, 100)
					.setEmission(Color.BLACK).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, 150), 40).setEmission(Color.BLACK)
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, -150), 40).setEmission(Color.BLACK)
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		for (int i = -1000; i < 1000; i = i + 100) {

			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, -300), new Vector(0, 1, 0)), 25, 100)
					.setEmission(Color.BLACK).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		for (int i = -1000; i < 1000; i = i + 150) {
			scene.geometries.add(new Sphere(new Point3D(i, 50, -450), 40).setEmission(Color.BLACK)
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}
		for (int i = -1000; i < 1000; i = i + 100) {

			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, -600), new Vector(0, 1, 0)), 25, 100)
					.setEmission(Color.BLACK).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100)));
		}

		scene.geometries.add(new Plane(Point3D.ZERO, new Vector(0, 1, 0)).setEmission(Color.BLACK)
				.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)));
		scene.addLights(
				new SpotLight(new Color(1000, 600, 1000), new Point3D(1000, 500, 500), Point3D.ZERO).setkC(1)
						.setkL(0.0001).setkQ(0.00005).setRadius(100),
				new PointLight(new Color(40000, 40000, 40000), new Point3D(1000, 1000, -1000)).setkC(1).setkL(4E-4)
						.setkQ(2E-5).setRadius(300));
		int p = 1000;
		scene.setTreeOfGeomtir();
		ImageWriter imageWriter = new ImageWriter("MP2 cliyndersAndSpheres OnlyBlack", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene).setNumOfRaySoftShadow(30))//
				.setMultithreading(3)//
				.setDebugPrint()//
		;

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * Produce a picture of a several spheres with beautiful effects
	 */
	@Test
	public void Spheres() {
		Scene scene = new Scene("Test scene");
		Camera camera = (new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		camera.setVpDistance(1000).setViewPlaneSize(200, 200);
		scene.setBackground(new Color(176, 196, 222));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add(
				new Plane(new Point3D(0, 100, 500), new Vector(new Point3D(0, -1, 0)))
						.setEmission(new Color(java.awt.Color.BLACK))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0).setKr(0.4).setShininess(60)
								.setRadiusForGlossy(0.8)),
				new Sphere(new Point3D(50, 50, 2000), 50).setEmission(new Color(64, 224, 208))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setKt(0.8).setKr(0).setShininess(10)),
				new Sphere(new Point3D(150, 0, 2100), 100).setEmission(new Color(0, 255, 0))
						.setMaterial(new Material().setKd(00.2).setKs(0.2).setKt(0.5).setKr(0).setShininess(100)),
				new Sphere(new Point3D(-250, -50, 2200), 150).setEmission(new Color(32, 178, 170))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setKt(0).setKr(0.0).setShininess(100)),
				new Sphere(new Point3D(-50, 25, 2100), 75).setEmission(new Color(220, 20, 60))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setKt(0.3).setKr(0.0).setShininess(20)));

		scene.addLights(
				new PointLight(new Color(255, 255, 224), new Point3D(0, -270, 2000)).setkC(1).setkL(0.00001)
						.setkQ(0.00000001),
				new SpotLight(new Color(500, 300, 0), new Point3D(250, -200, 1900), new Vector(-1, 1, 0.5)).setkC(1)
						.setkL(4E-7).setkQ(2E-10));
		scene.geometries.createBox();
		scene.geometries.createGeometriesTree();
		ImageWriter imageWriter = new ImageWriter("MP2 spheras", 600, 600);
		Render render = new Render().setCamera(camera).setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene).setNumOfRayGlossy(100).setDistanceForBlurryGlossy(80))//
				.setMultithreading(3)//
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Newton's cradle picture 10+ body bonus of targil 7 4 position of camera
	 */
	@Test
	public void newtonsCradle() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
				.setVpDistance(13900.13562).setViewPlaneSize(3000, 3000).setCameraHead(new Point3D(0, 0, 500))
				.rotateHorizontally(90);
		scene.setBackground(new Color(java.awt.Color.gray));
		scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.15));

		scene.geometries.add(
				// the Cradle stand up
				new Cylinder(new Ray(new Point3D(500, 0, 0), new Vector(0, 0, 1)), 20, 500).setEmission(Color.BLACK)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Cylinder(new Ray(new Point3D(-500, 0, 0), new Vector(0, 0, 1)), 20, 500)
						.setEmission(new Color(java.awt.Color.black))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Cylinder(new Ray(new Point3D(-500, 0, 500), new Vector(1, 0, 0)), 20, 1000)
						.setEmission(new Color(java.awt.Color.black))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				// bolls
				new Sphere(new Point3D(240, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setKr(0.5)),
				new Sphere(new Point3D(120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setKr(0.5)),
				new Sphere(new Point3D(0, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setKr(0.5)),
				new Sphere(new Point3D(-120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setKr(0.5)),
				new Sphere(new Point3D(-414.9996, 0, 226.567), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setKr(0.5)),
				// roots
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
						.setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(900).setKt(0.7)),
				// new Plane(new Point3D(0, 0, -50), new Vector(0, 0, 1)).setEmission(new
				// Color(100, 100, 100))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(500)),
				// stand down
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
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)),
				// blurry
				new Polygon(new Point3D(800, 150, 800), new Point3D(800, 150, 0), new Point3D(-800, 150, 0),
						new Point3D(-800, 150, 800)).setEmission(new Color(java.awt.Color.DARK_GRAY))
								.setMaterial(new Material().setKd(0).setKs(0).setShininess(100).setKr(0).setKt(1)
										.setRadiusForBlurry(1)));

		scene.lights.addAll(List.of(// new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				new SpotLight(new Color(255, 215, 0), new Point3D(-400, 1000, 1500), new Vector(400, -1000, -1500)) //
						.setkL(1E-5).setkQ(1.5E-7).setSharp(3)));
		scene.setTreeOfGeomtir();
		Render render = new Render()
				.setCamera(cam.setPosition(new Point3D(0, 100000, 100000)).setCameraHead(new Point3D(0, 0, 500))
						.setVpDistance(Math.sqrt(10000 * 10000) * 2 * 2 * 2 * 2 * 1.3).rotateHorizontally(180))
				.setImageWriter(new ImageWriter("MP2 Newton's cradle position 2", 500, 500)).setMultithreading(3)
				.setDebugPrint().setRayTracer(new RayTracerBasic(scene).setNumOfRayBlurry(100));

		render.renderImage();
		render.writeToImage();

	}
}

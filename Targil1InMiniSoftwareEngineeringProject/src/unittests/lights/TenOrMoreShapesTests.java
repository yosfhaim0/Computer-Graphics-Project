/**
 * 
 */
package unittests.lights;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Cylinder;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * Ten Or More Shapes Tests <br>
 * Multiple shapes and bodies and lighting
 * 
 * @author yosefHaim
 *
 */
public class TenOrMoreShapesTests {
	/**
	 * Newton's cradle picture 10+ body bonus of targil 7
	 * 4 position of camera
	 */
	@Test
	public void NewtonsCradle() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
				.setVpDistance(13900.13562).setViewPlaneSize(2000, 2000).setVto(new Point3D(0, 0, 500));
		scene.setBackground(new Color(java.awt.Color.blue));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));

		scene.geometries.add(
				// the Cradle
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
						.setMaterial(new Material().setKs(0.5).setShininess(100).setkR(0.5)),
				new Sphere(new Point3D(120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setkR(0.5)),
				new Sphere(new Point3D(0, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setkR(0.5)),
				new Sphere(new Point3D(-120, 0, 150), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setkR(0.5)),
				new Sphere(new Point3D(-414.9996, 0, 226.567), 60).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKs(0.5).setShininess(100).setkR(0.5)),
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
						.setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(900).setkT(0.7)),
				new Plane(new Point3D(0, 0, -50), new Vector(0, 0, 1)).setEmission(new Color(100, 100, 100))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(500)),
				// stand
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
				new Polygon(new Point3D(1000, -1000, 1000), new Point3D(1000, -1000, 0), new Point3D(-1000, -1000, 0),
						new Point3D(-1000, -1000, 1000)).setEmission(new Color(java.awt.Color.DARK_GRAY))
								.setMaterial(new Material().setKd(0).setKs(0).setShininess(100).setkR(1)));// miror

		scene.lights.addAll(List.of(// new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(-400, 1000, 1000),
						new Vector(400, -1000, -1500)) //
								.setkL(1E-5).setkQ(1.5E-7)).setSharp(3) //
		));

		ImageWriter imageWriter = new ImageWriter("Newton's cradle", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(cam).setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();

		render = render
				.setCamera(cam.setPosition(new Point3D(0, 100000, 100000)).setVto(new Point3D(0, 0, 0))
						.setVpDistance(Math.sqrt(10000 * 10000) * 2 * 2 * 2 * 2).rotateVrightAndVto(0))
				.setImageWriter(new ImageWriter("Newton's cradle position 2", 500, 500));
		render.renderImage();
		render.writeToImage();
		render = render
				.setCamera(cam.setPosition(new Point3D(-7000, 20000, 40000)).setVto(new Point3D(0, 0, 500))
						.setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2 * 2).rotateVrightAndVto(0))
				.setImageWriter(new ImageWriter("Newton's cradle position 3", 500, 500));
		render.renderImage();
		render.writeToImage();
		render = render
				.setCamera(cam.setPosition(new Point3D(-10000, -10000, 40000)).setVto(new Point3D(0, 0, 0))
						.setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2))
				.setImageWriter(new ImageWriter("Newton's cradle position 4", 500, 500));
		render.renderImage();
		render.writeToImage();
		render = render
				.setCamera(cam.setPosition(new Point3D(-10000, -10000, 40000)).setVto(new Point3D(0, 0, 0))
						.rotateVrightAndVto(-45).setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2))
				.setImageWriter(new ImageWriter("Newton's cradle position 4 + rotate -45 degree", 500, 500));
		render.renderImage();
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

		ImageWriter imageWriter = new ImageWriter("pearl", 1000, 1000);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));
		// .setMultithreading(3);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * tank picture (look like soryan tank)
	 */
	@Test
	public void tank() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
				.setVpDistance(13900.13562).setViewPlaneSize(2000, 2000);
		scene.setBackground(new Color(java.awt.Color.blue));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));
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
						.setEmission(new Color(java.awt.Color.blue))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // lest while
				new Cylinder(new Ray(new Point3D(490, 0, 150), new Vector(-1, 0, 0)), 150, 980)
						.setEmission(new Color(java.awt.Color.blue))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // midel while
				new Cylinder(new Ray(new Point3D(490, 350, 150), new Vector(-1, 0, 0)), 150, 980)
						.setEmission(new Color(java.awt.Color.blue))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // first while

				new Sphere(new Point3D(0, 0, 500), 350).setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // the chriach
				new Cylinder(new Ray(new Point3D(0, 0, 700), new Vector(0, 1, 0)), 30, 1000)
						.setEmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(900)), // BOMBER
				new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(100, 100, 100))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(500))// flor

		);
		scene.lights.addAll(List.of(// new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(-400, 1000, 1000),
						new Vector(400, -1000, -1500)) //
								.setkL(1E-5).setkQ(1.5E-7)).setSharp(3),
				((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(0, 1001, 700), new Vector(0, -1, 0)) //
						.setkL(1E-5).setkQ(1.5E-7)).setSharp(3)));

		ImageWriter imageWriter = new ImageWriter("tank", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(cam).setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
		render = render
				.setCamera(cam.setPosition(new Point3D(2000, 5000, 5000)).setVto(new Point3D(0, 0, 500))
						.setVpDistance(Math.sqrt(10000 * 5000)).rotateVrightAndVto(0))
				.setImageWriter(new ImageWriter("tank position 2", 500, 500));
		render.renderImage();
		render.writeToImage();
	}

}

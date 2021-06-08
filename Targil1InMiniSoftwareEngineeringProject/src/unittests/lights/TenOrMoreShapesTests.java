/**
 * 
 */
package unittests.lights;

import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
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
	 * Newton's cradle picture 10+ body bonus of targil 7 4 position of camera
	 */
	@Test
	public void newtonsCradle() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
				.setVpDistance(13900.13562).setViewPlaneSize(3000, 3000).setCameraHead(new Point3D(0, 0, 500))
				.rotateHorizontally(0);
		scene.setBackground(new Color(java.awt.Color.blue));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));

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
				new Polygon(new Point3D(1000, -1000, 1000), new Point3D(1000, -1000, 0), new Point3D(-1000, -1000, 0),
						new Point3D(-1000, -1000, 1000)).setEmission(new Color(java.awt.Color.DARK_GRAY))
								.setMaterial(new Material().setKd(0).setKs(0).setShininess(100).setkR(1))// miror

		);

		scene.lights.addAll(List.of(// new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				new SpotLight(new Color(255, 215, 0), new Point3D(-400, 1000, 1500), new Vector(400, -1000, -1500)) //
						.setkL(1E-5).setkQ(1.5E-7).setSharp(3)));

		ImageWriter imageWriter = new ImageWriter("Newton's cradle position 1", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(cam).setRayTracer(new RayTracerBasic(scene))
				.setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
		boolean flag = false;
		if (flag) {
			render = render
					.setCamera(cam.setPosition(new Point3D(0, 100000, 100000)).setCameraHead(new Point3D(0, 0, 500))
							.setVpDistance(Math.sqrt(10000 * 10000) * 2 * 2 * 2 * 2))
					.setImageWriter(new ImageWriter("Newton's cradle position 2", 500, 500)).setMultithreading(3)
					.setDebugPrint();
			render.renderImage();
			render.writeToImage();

			render = render
					.setCamera(cam.setPosition(new Point3D(-7000, 20000, 40000)).setCameraHead(new Point3D(0, 0, 500))
							.setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2 * 2))
					.setImageWriter(new ImageWriter("Newton's cradle position 3", 500, 500)).setMultithreading(3)
					.setDebugPrint();
			render.renderImage();
			render.writeToImage();
			render = render
					.setCamera(cam.setPosition(new Point3D(-10000, -10000, 40000)).setCameraHead(new Point3D(0, 0, 0))
							.setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2))
					.setImageWriter(new ImageWriter("Newton's cradle position 4", 500, 500)).setMultithreading(3)
					.setDebugPrint();
			render.renderImage();
			render.writeToImage();
			render = render
					.setCamera(cam.setPosition(new Point3D(-10000, -10000, 40000)).setCameraHead(new Point3D(0, 0, 0))
							.rotateHorizontally(-45).setVpDistance(Math.sqrt(10000 * 10000 + 2000 * 2000) * 2))
					.setImageWriter(new ImageWriter("Newton's cradle position 4 + rotate -45 degree", 500, 500))
					.setMultithreading(3).setDebugPrint();
			render.renderImage();
			render.writeToImage();
			render = render
					.setCamera(new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
							.setVpDistance(13900.13562).setViewPlaneSize(2000, 2000)
							.setCameraHead(new Point3D(0, 0, 500)).rotateHorizontally(90))
					.setImageWriter(new ImageWriter("Newton's cradle position 1 + rotate 90 degree", 500, 500))
					.setMultithreading(3).setDebugPrint();
			render.renderImage();
			render.writeToImage();
		}

	}

	
}

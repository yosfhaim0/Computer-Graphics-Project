package unittests.AccelerationRendererTests;

import java.util.List;

import org.junit.Test;

import elements.*;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

public class Accelerat {
	/**
	 * Produce a picture of a some spheres with light
	 */
	@Test
	public void spheres32() {
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(0, -620, -800), new Vector(0, 1, Math.sqrt(3)),
				new Vector(0, -1 * Math.sqrt(3), 1)).setNumOfRayFormApertureWindowToFocalPoint(100)
						.setSizeForApertureWindow(50)
						.setDistanceToFocalPlane(new Point3D(0, -620, -800).distance(new Point3D(0, 70, 400)));
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
		int p = 500;
		
		ImageWriter imageWriter = new ImageWriter("31 sphere and pearl Mini Project 2", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();
		// .setMultithreading(3);

		render.renderImage();
		render.writeToImage();
	}
}

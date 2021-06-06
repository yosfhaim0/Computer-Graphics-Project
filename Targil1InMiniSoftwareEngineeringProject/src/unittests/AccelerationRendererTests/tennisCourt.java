/**
 * 
 */
package unittests.AccelerationRendererTests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import primitives.*;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * @author yosefHaim
 *
 */
public class tennisCourt {

	@Test
	public void tennisCourtTest() {

		Scene scene = new Scene("tennis");

		Camera camera = (new Camera(new Point3D(0, 1000, 0), new Vector(0, -1, 0), new Vector(1, 0, 0)))
				.setVpDistance(1000).setViewPlaneSize(500, 500);

		scene.setBackground(new Color(25, 25, 112));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
		int x = 10;
		int y = 1;
		scene.geometries.add(
				// court
				new Plane(new Point3D(0, -1, 0), new Vector(0, 1, 0)).setEmission(new Color(java.awt.Color.gray))
		// stripes

		// net pillars
		// light pillars
		// net
		// sits
		// fences

		);

		scene.geometries.add();

		scene.lights
				.addAll(List.of(
						new SpotLight(new Color(1000, 600, 1000), new Point3D(0, 100, 100), Point3D.ZERO).setkC(1)
								.setkL(0.0001).setkQ(0.00005),
						new DirectionalLight(new Color(255, 215, 255), new Vector(0, -0.2, -1))));
		int p = 1000;
		ImageWriter imageWriter = new ImageWriter("tennis court", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3)//
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();

	}

	@Test
	public void cylinders() {

		Scene scene = new Scene("spheres");

		Camera camera = (new Camera(new Point3D(1000, 500, 500), new Vector(0, -1, 0), new Vector(1, 0, 0)))
				.setVpDistance(4000).setViewPlaneSize(2000, 2000).setCameraHead(Point3D.ZERO);

		scene.setBackground(new Color(25, 25, 112));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
		for (int i = -1000; i < 1000; i = i + 60) {
			scene.geometries.add(new Cylinder(new Ray(new Point3D(i, 0, 0), new Vector(0, 1, 0)), 10, 100)
					.setEmission(new Color(0, 255, 0))
					.setMaterial(new Material().setKd(00.2).setKs(0.2).setkT(0.5).setkR(0).setShininess(100)));
		}
		scene.geometries.add(new Plane(Point3D.ZERO, new Vector(0, 1, 0)).setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setkT(0).setkR(0.1).setShininess(60)));
		scene.lights.addAll(List.of(
				new SpotLight(new Color(1000, 600, 1000), new Point3D(1000, 500, 500), Point3D.ZERO).setkC(1)
						.setkL(0.0001).setkQ(0.00005),
				new DirectionalLight(new Color(500, 300, 0), new Vector(0, -1, 0.2))));
		for (int i = -1000; i < 1000; i = i + 60) {
			scene.addLights(new SpotLight(new Color(100, 100, 100), new Point3D(i, 500, 100), new Point3D(i, 0, 0))
					.setkC(1).setkL(0.0001).setkQ(0.00005));
		}
		int p = 500;
		scene.geometries.createBox();
		scene.geometries.callMakeTree();
		ImageWriter imageWriter = new ImageWriter("spheres", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene))//
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
	public void OurPicture1() {
		Scene scene = new Scene("Test scene");
		Camera camera = (new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		camera.setVpDistance(1000).setViewPlaneSize(200, 200);
		scene.setBackground(new Color(176, 196, 222));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add(
				new Plane(new Point3D(0, 100, 500), new Vector(new Point3D(0, -1, 0)))
						.setEmission(new Color(java.awt.Color.BLACK))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setkT(0).setkR(0.2).setShininess(60)),
				new Sphere(new Point3D(50, 50, 2000), 50).setEmission(new Color(64, 224, 208))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setkT(0.8).setkR(0).setShininess(10)),
				new Sphere(new Point3D(150, 0, 2100), 100).setEmission(new Color(0, 255, 0))
						.setMaterial(new Material().setKd(00.2).setKs(0.2).setkT(0.5).setkR(0).setShininess(100)),
				new Sphere(new Point3D(-250, -50, 2200), 150).setEmission(new Color(32, 178, 170))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setkT(0).setkR(0.2).setShininess(100)),
				new Sphere(new Point3D(-50, 25, 2100), 75).setEmission(new Color(220, 20, 60))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setkT(0.3).setkR(0.0).setShininess(20)));

		scene.addLights(
				new PointLight(new Color(255, 255, 224), new Point3D(0, -270, 2000)).setkC(1).setkL(0.00001)
						.setkQ(0.00000001),
				new SpotLight(new Color(500, 300, 0), new Point3D(250, -200, 1900), new Vector(-1, 1, 0.5)).setkC(1)
						.setkL(4E-7).setkQ(2E-10));

		ImageWriter imageWriter = new ImageWriter("OurPicture1", 600, 600);
		Render render = new Render().setCamera(camera).setImageWriter(imageWriter)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

}

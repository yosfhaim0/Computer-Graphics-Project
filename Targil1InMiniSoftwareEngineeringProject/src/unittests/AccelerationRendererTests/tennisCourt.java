/**
 * 
 */
package unittests.AccelerationRendererTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Strip;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;
import sun.jvm.hotspot.oops.java_lang_Class;

/**
 * @author yosefHaim
 *
 */
public class tennisCourt {

	@Test
	public void tennisCourtTest() {

		Scene scene = new Scene("tennis");

		Camera camera = (new Camera(new Point3D(0, 5000, 0), new Vector(0, -1, 0), new Vector(1, 0, 0)))
				.setVpDistance(4900).setViewPlaneSize(500, 500);

		scene.setBackground(new Color(25, 25, 112));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
		int x = 10;

		scene.geometries.add(
				// court
				new Plane(Point3D.ZERO, new Vector(0, 1, 0)).setEmission(new Color(java.awt.Color.red)),
				// stripes
				new Strip(new Point3D(-12 * x, 0.01, -5.5 * x), new Point3D(12 * x, 0.01, -5.5 * x))
						.setEmission(new Color(java.awt.Color.white)),
				new Strip(new Point3D(-12 * x, 0.01, 5.5 * x), new Point3D(-12 * x, 0.01, 5.5 * x))
						.setEmission(new Color(java.awt.Color.white)),
				new Strip(new Point3D(-12 * x, 0.01, 5.5 * x), new Point3D(12 * x, 0.01, 5.5 * x))
						.setEmission(new Color(java.awt.Color.white)),
				new Strip(new Point3D(12 * x, 0.01, 5.5 * x), new Point3D(12 * x, 0.01, -5.5 * x))
						.setEmission(new Color(java.awt.Color.white))

		// net pillars
		// light pillars
		// net
		// sits
		// fences

		);

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

}

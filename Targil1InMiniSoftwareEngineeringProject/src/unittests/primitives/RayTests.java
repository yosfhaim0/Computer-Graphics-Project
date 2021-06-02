/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * @author yosefHaim
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: closest in middle of list
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(null, new Point3D(0, 0, 9)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 3)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 5)));
		assertEquals("TC01: Wrong Point Closest", new Point3D(0, 0, 3), ray.getClosestGeoPoint(list).point);
		// =============== Boundary Values Tests ==================
		// TC02:List ==null
		list = null;
		assertNull("TC02: Wrong return", ray.getClosestGeoPoint(list));
		// TC03:Closest is first in list
		list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(null, new Point3D(0, 0, 3)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 9)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 5)));
		assertEquals("TC03: Wrong Point Closest", new Point3D(0, 0, 3), ray.getClosestGeoPoint(list).point);
		// TC04:Closest is last in list
		list.add(new GeoPoint(null, new Point3D(0, 0, 2)));
		assertEquals("TC04: Wrong Point Closest", new Point3D(0, 0, 2), ray.getClosestGeoPoint(list).point);
	}

	/**
	 * test for ray split, show the point of the split<br>
	 * Display the scattering of the rays randomly
	 */
	@Test
	public void splitRayTests() {
		List<Ray> list = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, 1)).raySplitter(new Vector(0, 0, 1), 400, 5,
				5);
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(0, 0, 50), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(50)
				.setViewPlaneSize(10, 10);
		scene.setBackground(primitives.Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), 0.15));
		List<Point3D> li = null;
		Cylinder cylinder = (Cylinder) new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 5, 0.000001)
				.setEmission(new Color(java.awt.Color.red));
		scene.geometries.add(cylinder,
				new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(java.awt.Color.green)));
		Sphere sphere = null;
		for (Ray ray : list) {
			li = scene.geometries.findIntersections(ray);

			sphere = new Sphere(li.get(0), 0.05);
			scene.geometries.add(sphere.setEmission(new Color(java.awt.Color.BLUE)));

		}
		scene.lights.addAll(List.of(new SpotLight(new Color(255, 255, 0), new Point3D(0, 2, 50), Point3D.ZERO)));
		ImageWriter imageWriter = new ImageWriter("View of the split rays random On circle", 200, 200);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();

	}

	/**
	 * test for ray split, show the point of the split<br>
	 * Display the scattering of the rays randomly
	 */
	@Test
	public void splitRayTests1() {
		List<Ray> list = new Ray(new Point3D(0, 2.5, -5), new Vector(0, 0, 1)).raySplitter(1000, 5, 10, 5);
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(0, 2.5, 50), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(50)
				.setViewPlaneSize(15, 10);
		scene.setBackground(primitives.Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), 0.15));
		List<Point3D> li = null;
		scene.geometries.add(
				new Polygon(new Point3D(-5, 5, 0), new Point3D(5, 5, 0), new Point3D(5, 0, 0), new Point3D(-5, 0, 0))
						.setEmission(new Color(java.awt.Color.red)),
				new Plane(new Point3D(0, 0, -0.0001), new Vector(0, 0, 1))
						.setEmission(new Color(java.awt.Color.green)));
		Sphere sphere = null;
		for (Ray ray : list) {
			li = scene.geometries.findIntersections(ray);

			sphere = new Sphere(li.get(0), 0.05);
			scene.geometries.add(sphere.setEmission(new Color(java.awt.Color.BLUE)));

		}
		scene.lights.addAll(List.of(new SpotLight(new Color(255, 255, 0), new Point3D(0, 2, 50), Point3D.ZERO)));
		ImageWriter imageWriter = new ImageWriter("View of the split rays random On Rectangle", 1000, 1000);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();

	}
}

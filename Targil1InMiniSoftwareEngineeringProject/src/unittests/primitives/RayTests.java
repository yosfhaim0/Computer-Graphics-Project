/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Cylinder;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
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
		System.out.println(Math.tan(5 / 360d * 2 * Math.PI));
	}

	/**
	 * test for ray split, show the point of the split<br>
	 * Display the scattering of the rays randomly
	 */
	@Test
	public void splitRayTests() {
		List<Ray> list = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, 1)).raySplitter(new Vector(0, 0, 1), 300, 5,
				5);
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(0, 0, 50), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(50)
				.setViewPlaneSize(10, 10);
		scene.setBackground(primitives.Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), 0.15));
		List<Point3D> li = null;
		Cylinder cylinder = (Cylinder) new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 5, 0.1)
				.setEmission(new Color(java.awt.Color.red));
		scene.geometries.add(cylinder);
		Sphere sphere = null;
		for (Ray ray : list) {
			li = cylinder.findIntersections(ray);
			
				sphere = new Sphere(li.get(0), 0.12);
				scene.geometries.add(sphere.setEmission(new Color(java.awt.Color.BLUE)));
			
		}
		scene.lights.addAll(List.of(new SpotLight(new Color(255, 255, 0), new Point3D(0, 2, 50), Point3D.ZERO)));
		ImageWriter imageWriter = new ImageWriter("View of the split rays", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();

	}
}

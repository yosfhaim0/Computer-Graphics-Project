/**
 * 
 */
package unittests.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;

/**
 * Integration Test for findIntersections and constructRayThroughPixel funcs
 * 
 * @author yosefHaim
 */
public class IntegrationTest {

	/**
	 * 
	 */
	@Test
	public void testIntegrationWithSphere() {
		// TC01 r=1 (1 point)
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)),
				new Vector(new Point3D(0, 1, 0))).setVpDistance(1).setViewPlaneSize(3, 3);
		Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);
		assertEquals("TC01: worng number", 2, ezerFunFor(camera, sphere));

		// TC02 r=2.5 (18 point)
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(new Point3D(0, 0, -1)), new Vector(new Point3D(0, 1, 0)))
				.setVpDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		assertEquals("TC02: worng number", 18, ezerFunFor(camera, sphere));

		// TC03 r=2 (10 point)
		sphere = new Sphere(new Point3D(0, 0, -2), 2);
		assertEquals("TC03: worng number", 10, ezerFunFor(camera, sphere));

		// TC04 r=4 (9 point)
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(new Point3D(0, -1, 0)), new Vector(new Point3D(0, 0, 1)))
				.setVpDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, -2), 4);
		assertEquals("TC04: worng number", 9, ezerFunFor(camera, sphere));

		// TC05: r=0.5 (0 point)
		camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)), new Vector(new Point3D(0, 1, 0)))
				.setVpDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, 1), 1);
		assertEquals("TC05: worng number", 0, ezerFunFor(camera, sphere));

	}

	@Test
	public void testIntegrationWithPlane() {
		// TC01: (9 point)
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)),
				new Vector(new Point3D(0, 1, 0))).setVpDistance(1).setViewPlaneSize(3, 3);
		Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, 0, -1)));
		assertEquals("TC01: worng number(plane inetgration test)", 9, ezerFunFor(camera, plane));

		// TC02: (9 point)
		plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -0.05, 1)));
		assertEquals("TC02: worng number(plane inetgration test)", 9, ezerFunFor(camera, plane));

		// TC03: (6 point)
		plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -1, 1)));
		assertEquals("TC03: worng number(plane inetgration test)", 6, ezerFunFor(camera, plane));

	}

	@Test
	public void testIntegrationWithTriangel() {
		// TC01: (1 point)
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(1)
				.setViewPlaneSize(3, 3);
		Triangle triangle = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals("TC01: wrong number(triangle integration test)", 1, ezerFunFor(camera, triangle));

		// TC02: (2 point)
		triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals("TC02: worng number(triangel inetgration test)", 2, ezerFunFor(camera, triangle));
	}

	/**
	 * fun for surtcut the code test
	 * 
	 * @param camera
	 * @param shape
	 * @return counter pointer
	 */
	public int ezerFunFor(Camera camera, Intersectable shape) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (shape.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += shape.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		return counter;
	}
}
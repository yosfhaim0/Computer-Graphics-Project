/**
 * 
 */
package unittests.elements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;

/**
 * @author yosefHaim
 *Integration Test for findIntersections and constructRayThroughPixel funcs
 */
public class IntegrationTest {

	@Test
	public void testIntegrationWithSphere() {
		// TC01 r=1 (1 point)
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)),
				new Vector(new Point3D(0, 1, 0))).setDistance(1).setViewPlaneSize(3, 3);
		Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);

		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}

		assertEquals("TC01: worng number", 2, counter);

		// TC02 r=2.5 (18 point)
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(new Point3D(0, 0, -1)), new Vector(new Point3D(0, 1, 0)))
				.setDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC02: worng number", 18, counter);

		// TC03 r=2 (10 point)
		sphere = new Sphere(new Point3D(0, 0, -2), 2);

		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC03: worng number", 10, counter);

		// TC04 r=2 (9 point)
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(new Point3D(0, -1, 0)), new Vector(new Point3D(0, 0, 1)))
				.setDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, -2), 4);

		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC04: worng number", 9, counter);

		// TC05 r=2 (9 point)
		camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)), new Vector(new Point3D(0, 1, 0)))
				.setDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, 1), 1);

		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += sphere.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC05: worng number", 0, counter);

	}

	@Test
	public void testIntegrationWithPlane() {
		// TC01 (9 point)
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, -1)),
				new Vector(new Point3D(0, 1, 0))).setDistance(1).setViewPlaneSize(3, 3);
		Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, 0, -1)));

		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC01: worng number(plane inetgration test)", 9, counter);

		// TC02 (9 point)
		plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -0.05, 1)));

		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC02: worng number(plane inetgration test)", 9, counter);

		// TC03: (6 point)

		plane = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -1, 1)));

		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += plane.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC03: worng number(plane inetgration test)", 6, counter);

	}

	@Test
	public void testIntegrationWithTriangel() {
		// TC01 (1 point)
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
				.setViewPlaneSize(3, 3);
		Triangle triangle = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));

		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (triangle.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += triangle.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC01: wrong number(triangle integration test)", 1, counter);
		// TC02 (2 point)
		triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (triangle.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null)
					counter += triangle.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size();
			}
		}
		assertEquals("TC02: worng number(triangel inetgration test)", 2, counter);
	}
}
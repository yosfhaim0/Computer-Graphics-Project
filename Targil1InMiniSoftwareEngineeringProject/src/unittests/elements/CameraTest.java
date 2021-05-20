package unittests.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
public class CameraTest {
	/**
	 * test for set vto camera vector (and vup)
	 */
	@Test
	public void setVtoTest() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.setVto(new Point3D(0, 0, -1));
		// simpel test for set Vup And Vto
		assertEquals("rotet not good", new Vector(1, 0, 0), camera.getvUp());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.setVto(new Point3D(1, 0, -1));
		// test for set Vup And Vto
		assertEquals("rotet not good", new Vector(Math.sqrt(2) / 2, 0, Math.sqrt(2) / 2), camera.getvUp());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.setVto(new Point3D(1, 1, 1));
		// test for set Vup And Vto
		assertEquals("rotet not good", new Vector(-1, -1, 2).normalize(), camera.getvUp());

	}

	/**
	 * Rotate camera test for camera.rotateVrightAndVto(double degree)
	 */
	@Test
	public void rotetCamera() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		assertEquals("rotet not good", new Vector(0, -1, 0), camera.getvRight());
		// ============ Equivalence Partitions Tests ==============

		camera.rotateVrightAndVto(180);
		// TC01:camera rotate 180 degree
		assertEquals("rotet not good", new Vector(0, 0, -1), camera.getvUp());
		assertEquals("rotet not good", new Vector(0, 1, 0), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.rotateVrightAndVto(-90);
		// test for set Vup And Vright -90 degree
		assertEquals("rotet not good", new Vector(0, 1, 0).normalize(), camera.getvUp());
		assertEquals("rotet not good", new Vector(0, 0, 1).normalize(), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.rotateVrightAndVto(90);
		// test for set Vup And Vright 90 degree
		assertEquals("rotet not good", new Vector(0, 0, -1).normalize(), camera.getvRight());
		assertEquals("rotet not good", new Vector(0, -1, 0).normalize(), camera.getvUp());
		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1));
		camera.rotateVrightAndVto(45);
		// test for set Vup And Vright 45 degree
		assertEquals("rotet not good", new Vector(0, -Math.sqrt(0.5), -Math.sqrt(0.5)).normalize(), camera.getvRight());
		assertEquals("rotet not good", new Vector(0, -Math.sqrt(0.5), Math.sqrt(0.5)).normalize(), camera.getvUp());

	}

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}.
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)).setVpDistance(10);

		// ============ Equivalence Partitions Tests ==============
		// TC01: 3X3 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 0));

		// TC02: 4X4 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 0, 0));

		// TC03: 4X4 Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 0));

		// TC04: 4X4 Inside (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
				camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 1));

		// =============== Boundary Values Tests ==================
		// TC11: 3X3 Center (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 1));

		// TC12: 3X3 Center of Upper Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 0));

		// TC13: 3X3 Center of Left Side (1,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 1));

	}

}

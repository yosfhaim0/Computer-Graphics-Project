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
	public void setCameraHeadTests() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		// ============ Equivalence Partitions Tests ==============
		// test for reset
		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		assertEquals("rotet not good", new Vector(0, 0, 1), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(0, 0, -1));
		// right down
		assertEquals("rotet not good", new Vector(1, 0, 0), camera.getvRight());
		assertEquals("rotet not good", new Vector(0, 1, 0), camera.getvUp());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(-1, 0, 0));
		// full ofsite
		assertEquals("rotet not good", new Vector(0, 1, 0).normalize(), camera.getvUp());
		assertEquals("rotet not good", new Vector(0, 0, 1).normalize(), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(0, -1, 0));
		// to 0,-1,0
		assertEquals("rotet not good", new Vector(0, 0, -1), camera.getvUp());
		assertEquals("rotet not good", new Vector(-1, 0, 0), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(0, 1, 0));
		// to 0,1,0
		assertEquals("rotet not good", new Vector(0, 0, 1), camera.getvUp());
		assertEquals("rotet not good", new Vector(-1, 0, 0), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(0, 0, 1));
		// to 0,0,1
		assertEquals("rotet not good", new Vector(0, 1, 0), camera.getvUp());
		assertEquals("rotet not good", new Vector(1, 0, 0), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(1, 0, 0));
		// to 1,0,0
		assertEquals("rotet not good", new Vector(0, 1, 0), camera.getvUp());
		assertEquals("rotet not good", new Vector(0, 0, -1), camera.getvRight());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(1, 1, 1));
		// set to 1,1,1
		assertEquals("rotet not good", new Vector(-1, 2, -1).normalize(), camera.getvUp());

		camera = new Camera(Point3D.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0));
		camera.setCameraHead(new Point3D(1, 0, -1));
		// to 1,0,-1
		assertEquals("rotet not good", new Vector(0, 1, 0), camera.getvUp());

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

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}.
	 */
	@Test
	public void testConstructRayThroughPixel1() {
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

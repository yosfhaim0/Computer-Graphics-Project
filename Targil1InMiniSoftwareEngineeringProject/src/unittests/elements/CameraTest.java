package unittests.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
public class CameraTest {

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}.
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0)).setViewPlaneSize(2,
				5);

		// ============ Equivalence Partitions Tests ==============
		// TC01: 3X3 Corner (0,0)
		assertEquals("Bad ray1", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
				camera.setViewPlaneSize(6, 6).setDistance(10).constructRayThroughPixel(3, 3, 0, 0));

		// TC02: 4X4 Corner (0,0)
		assertEquals("Bad ray2", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
				camera.setViewPlaneSize(8, 8).setDistance(10).constructRayThroughPixel(4, 4, 0, 0));

		// TC03: 4X4 Side (0,1)
		assertEquals("Bad ray3", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
				camera.setViewPlaneSize(8, 8).setDistance(10).constructRayThroughPixel(4, 4, 1, 0));

		// TC04: 4X4 Inside (1,1)
		assertEquals("Bad ray4", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
				camera.setViewPlaneSize(8, 8).setDistance(10).constructRayThroughPixel(4, 4, 1, 1));

		// =============== Boundary Values Tests ==================
		// TC11: 3X3 Center (1,1)
		assertEquals("Bad ray5", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
				camera.setViewPlaneSize(6, 6).setDistance(10).constructRayThroughPixel(3, 3, 1, 1));

		// TC12: 3X3 Center of Upper Side (0,1)
		assertEquals("Bad ray6", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
				camera.setViewPlaneSize(6, 6).setDistance(10).constructRayThroughPixel(3, 3, 1, 0));

		// TC13: 3X3 Center of Left Side (1,0)
		assertEquals("Bad ray7", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
				camera.setViewPlaneSize(6, 6).setDistance(10).constructRayThroughPixel(3, 3, 0, 1));

	}

}

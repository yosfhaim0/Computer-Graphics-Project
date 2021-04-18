/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import java.util.List;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author yosefHaim
 *
 */
public class PlaneTests {
	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D,primitives.Point3D,primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		// =============== Boundary Values Tests ==================
		// assertThrows("failure text", IllegalArgumentException.class, new Plane(new
		// Point3D(0, 2, 0),new Point3D(0, 3, 0),new Point3D(0, 4, 0)));
		// TC01:All The point on the same line
		try {
			new Plane(new Point3D(0, 2, 0), new Point3D(0, 3, 0), new Point3D(0, 4, 0));
			fail("Failed constructing a Illegal -All The point on the same line- Plane");
		} catch (IllegalArgumentException e) {
		}
		// TC02: tow point are metlacdot(hebrw)
		try {
			new Plane(new Point3D(0, 2, 0), new Point3D(0, 2, 0), new Point3D(0, 4, 0));
			fail("Failed constructing a Illegal -tow point are same- Plane");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane p = new Plane(new Point3D(0, 2, 0), new Point3D(2, 0, 0), new Point3D(0, 0, 2));
		Vector v = p.getNormal(new Point3D(0, 2, 0));
		/**
		 * It is expected that the dot product between the normal and the vector
		 * contained within the plane will be equal to 0
		 */
		assertTrue("The normal is not really perpendicular to the Plane", isZero(v.dotProduct(new Vector(-2, 0, 2))));
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane p = new Plane(new Point3D(1, 0, 0), new Vector(new Point3D(0, 0, 1)));
		// ============ Equivalence Partitions Tests ==============
		// EP:The Ray must be neither orthogonal nor parallel to the plane
		// TC01: Ray intersects the plane (1 point)
		Ray ray1 = new Ray(new Point3D(-1, 0, 1.5), new Vector(new Point3D(2, 0, -0.5)));
		List<Point3D> resuelt2 = p.findIntersections(ray1);
		Point3D p2 = new Point3D(5, 0, 0);
		assertEquals("one intersection", List.of(p2), resuelt2);
		// TC02: Ray does not intersect the plane
		Ray ray2 = new Ray(new Point3D(-1, 0, 1.5), new Vector(new Point3D(2, 0, 0.5)));
		assertEquals("plane and the ray are parallel", null, p.findIntersections(ray2));
		// =============== Boundary Values Tests ==================

		// **********plane and the ray are parallel:*******
		// TC03: Ray not including the plane (0 point)
		Ray ray3 = new Ray(new Point3D(0, 0, 0), new Vector(new Point3D(1, 0, 1)));
		assertEquals("plane and the ray are parallel", null, p.findIntersections(ray3));
		// TC04: the ray in the plane infinite point
		Ray ray5 = new Ray(new Point3D(0, 1, 0), new Vector(new Point3D(1, 0, 0)));
		assertEquals("the ray in the plane infinite point", null, p.findIntersections(ray5));

		// ******Ray is orthogonal to the plane******
		// TC05: before(1 Point)
		Ray ray6 = new Ray(new Point3D(0, 1, -1), new Vector(new Point3D(0, 0, 1)));
		List<Point3D> resule1 = p.findIntersections(ray6);
		Point3D p1 = new Point3D(0, 1, 0);
		assertEquals("Ray is orthogonal to the plane and before", List.of(p1), resule1);
		// TC06: in(0 Point)
		Ray ray4 = new Ray(new Point3D(0, 1, 0), new Vector(new Point3D(0, 0, 1)));
		assertEquals("Ray is orthogonal to the plane and in", null, p.findIntersections(ray4));
		// TC07: after (0 Point)
		Ray ray7 = new Ray(new Point3D(0, 0, 1), new Vector(new Point3D(0, 0, 1)));
		assertEquals("the ray Direction Away from the plane", null, p.findIntersections(ray7));

		// *******Special cases*******
		// TC08: Ray is neither orthogonal nor parallel to and begins at the plane
		// (נ�‘ƒ0 is in the plane, but not the ray) (0 Point) 
		Ray ray8 = new Ray(new Point3D(0, 1, 0), new Vector(new Point3D(1, 3, -2)));
		assertEquals("Ray is neither orthogonal nor parallel to and begins at the plane", null,
				p.findIntersections(ray8));
		// TC09:Ray is neither orthogonal nor parallel to the plane and begins in
		// the same point which appears as reference point in the plane (Q)
		Ray ray9 = new Ray(new Point3D(1, 0, 0), new Vector(new Point3D(0, 1, 1)));
		assertEquals(
				"Ray is neither orthogonal nor parallel to the plane and begins in\r\n"
						+ "		//the same point which appears as reference point in the plane (Q)",
				null, p.findIntersections(ray9));
	}

}

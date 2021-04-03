
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

	/**
	 * Test method for
	 * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
			fail("Constructed a polygon with wrong order of vertices");
		} catch (IllegalArgumentException e) {
		}

		// TC03: Not in the same plane
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 2, 2));
			fail("Constructed a polygon with vertices that are not in the same plane");
		} catch (IllegalArgumentException e) {
		}

		// TC04: Concave quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
			fail("Constructed a concave polygon");
		} catch (IllegalArgumentException e) {
		}

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
			fail("Constructed a polygon with vertix on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC11: Last point = first point
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC12: Colocated points
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 1, 0));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
				new Point3D(-1, 1, 1));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
	 */

	@Test
	public void testFindIntersections() {
		Polygon pl = new Polygon(new Point3D(0, 0, 0), new Point3D(0, 3, 0), new Point3D(2, 2, 0),
				new Point3D(2, -1, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Inside polygon(1 Point)
		Ray ray1 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(1, 1, 1)));
		List<Point3D> resulet = pl.findIntersections(ray1);
		Point3D p1 = new Point3D(1, 1, 0);
		assertEquals("Inside polygon(1 Point)", List.of(p1), resulet);
		// TC02: Outside against edge(0 Point)
		Ray ray2 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(3, 1, 1)));
		assertEquals("Outside against edge", null, pl.findIntersections(ray2));
		// TC03: Outside against vertex(0 Point)
		Ray ray3 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(-0.5, 4, 1)));
		assertEquals("Outside against vertex", null, pl.findIntersections(ray3));
		// =============== Boundary Values Tests ==================
		// *********Three cases (the ray begins "before" the polygon)*****
		// TC04: On edge(0 Point)
		Ray ray4 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(2, 0, 1)));
		assertEquals("On edge", null, pl.findIntersections(ray4));
		// TC05: In vertex(0 Point)
		Ray ray5 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 3, 1)));
		assertEquals("In vertex", null, pl.findIntersections(ray5));
		// TC06: On edge's continuation(0 Point)
		Ray ray6 = new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 4, 1)));
		assertEquals("On edge's continuation", null, pl.findIntersections(ray6));
	}

}

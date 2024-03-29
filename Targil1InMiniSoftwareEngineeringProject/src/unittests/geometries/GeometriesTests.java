/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class GeometriesTests {

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(3, 0, 0), new Point3D(0, 2, 0), new Point3D(0, 0, 0));
		Polygon polygon = new Polygon(new Point3D(-1, -1, 2), new Point3D(-2, 0, 0), new Point3D(0, -2, 0),
				new Point3D(0, -2, 1));
		Plane plane = new Plane(new Point3D(-1, 0, 0), new Point3D(0, -1, 0), new Point3D(0, 0, 1));
		Geometries g = new Geometries(triangle, polygon, plane);
		// ============ Equivalence Partitions Tests ==============
		// TC04: few are cut but not all (0<point)
		List<Point3D> resultList1 = g
				.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(new Point3D(-2, -2, 0))));
		assertEquals("one shape are intersection : Wrong number of points ", 2, resultList1.size());
		// =============== Boundary Values Tests ==================
		// TC01: Geometries it empty list (0 point)
		Geometries g1 = new Geometries();
		List<Point3D> resultList2 = g1
				.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(new Point3D(-2, -2, 0))));
		assertNull("Geometries it empty list : Wrong number of points ", resultList2);
		// TC02: no shape are cutting(0 point)
		List<Point3D> resultList3 = g
				.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(new Point3D(2, 2, 0))));
		assertNull("no shape are cutting : Wrong number of points ", resultList3);
		// TC03 one shape are intersection(0<point)
		List<Point3D> resultList0 = g
				.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(new Point3D(-1, -3, 0))));
		assertEquals("one shape are intersection : Wrong number of points ", 1, resultList0.size());
		// TC05: all the shape are intersection(1<point)
		Geometries g2 = new Geometries(polygon, plane);
		List<Point3D> resultList5 = g2
				.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(new Point3D(-2, -2, 0))));
		assertEquals("all the shape are intersection : Wrong number of points ", 2, resultList5.size());

	}

	/**
	 * test isIntersectWithTheBox method
	 */
	@Test
	public void testIsIntersectBox() {
		Sphere sphere = new Sphere(new Point3D(5, 5, 5), 3);
		sphere.createBox();

		// ============ Equivalence Partitions Tests ==============
		// TC01: ray intersect with the box
		Ray ray1 = new Ray(new Point3D(0, 0, 0), new Vector(5, 5, 5));
		assertTrue("the ray must intersect with the box !", sphere.isIntersectWithTheBox(ray1));

		// TC02: ray not intersect with the box
		Ray ray2 = new Ray(new Point3D(0, 0, 0), new Vector(5, 2, 9));
		assertFalse("the ray must not intersect with the box !", sphere.isIntersectWithTheBox(ray2));

	}

	/**
	 * test isIntersectWithTheBox method
	 */
	@Test
	public void treeGeometriesTest() {
		Cylinder c = new Cylinder(new Ray(new Point3D(2, 0, 0), new Vector(new Point3D(0, 0, 1))), 2, 1);
		Sphere s = new Sphere(new Point3D(1, 0, 1), 2);
		Geometries geometries = new Geometries(c, s);
		geometries.createBox();
		geometries.createGeometriesTree();
	}
}
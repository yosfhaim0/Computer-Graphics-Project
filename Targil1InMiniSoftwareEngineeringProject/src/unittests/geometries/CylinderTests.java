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
 * 
 * @author yosefHaim
 *
 */
public class CylinderTests {
	/**
	 * Test method for {@link geometries.cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {

		Cylinder c = new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 1, 1);

		// ============ Equivalence Partitions Tests ==============
		// TC01:the point Contained low base
		Point3D p1 = new Point3D(0.5, 0.5, 0);
		Vector v1 = c.getNormal(p1);
		assertEquals("Bad normal to the low base of cylinder1", new Vector(0, 0, 1), v1);
		// TC02:the point Contained the up base
		Point3D p2 = new Point3D(0.5, 0.5, 1);
		Vector v2 = c.getNormal(p2);
		assertEquals("Bad normal to the up base of cylinder1", new Vector(0, 0, 1), v2);
		// TC03:the point Contained the sides
		Point3D p3 = new Point3D(1, 0, 0.5);
		Vector v3 = c.getNormal(p3);
		assertEquals("Bad normal to the sides of cylinder1", v3, new Vector(1, 0, 0));

		// =============== Boundary Values Tests ==================
		// TC04:the point between the cylinder base(down) and the "cylinder"
		Point3D p4 = new Point3D(1, 0, 0);
		Vector v4 = c.getNormal(p4);
		assertEquals("Bad normal Boundary Values1", new Vector(0, 0, 1), v4);
		// TC05:the point between the cylinder base(up) and the "cylinder"
		Point3D p5 = new Point3D(1, 0, 1);
		Vector v5 = c.getNormal(p5);
		assertEquals("Bad normal Boundary Values2", new Vector(0, 0, 1), v5);
	}

	/**
	 * Test method for
	 * {@link geometries.cylinder#findIntersections(primitives.Ray)}.
	 */

	@Test
	public void testFindIntersections() {
		Cylinder cylinder = new Cylinder(new Ray(new Point3D(2, 0, 0), new Vector(new Point3D(0, 0, 1))), 2, 1);
		List<Point3D> result;
		// ============ Equivalence Partitions Tests==============
		// **** Group: ray start are same level q0(start of cylinder)
		// TC01: Ray's line is outside the cylinder (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(6, 0, 0), new Vector(-6, 5, 0)));
		assertEquals("TC01: Ray's line is outside the cylinder", null, result);
		// TC02: Ray starts before and crosses the cylinder (2 points)
		result = cylinder.findIntersections(new Ray(new Point3D(0, 4, 0), new Vector(2, -2, 0)));
		Point3D p1 = new Point3D(2, 2, 0);
		Point3D p2 = new Point3D(4, 0, 0);
		assertNull("TC02:empty list", result);
		// assertEquals("TC02: Wrong number of points", 2, result.size());
		// if (result.get(0).getX() > result.get(1).getX())
		// result = List.of(result.get(0), result.get(1));
		// assertEquals("TC02: Ray crosses cylinder", List.of(p1, p2), result);

		// TC03: Ray starts inside the cylinder (1 point)
		result = cylinder.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0)));
		assertNull("TC02:empty list", result);
		// assertEquals("TC03: Ray starts inside the cylinder", List.of(new Point3D(2,
		// 2, 0)), result);

		// TC04: Ray starts after the cylinder (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("TC04: Ray starts after the cylinder", null, result);
		// **** Group: the same like the four up but ray start are level up form
		// q0(start of cylinder)
		Cylinder cylinder1 = new Cylinder(new Ray(new Point3D(2, 0, -1), new Vector(new Point3D(0, 0, 1))), 2, 2);
		// TC17: Ray's line is outside the cylinder (0 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(6, 0, 0), new Vector(-6, 5, 0)));
		assertEquals("TC17: Ray's line is outside the cylinder (0 points)", null, result);
		// TC18: Ray starts before and crosses the cylinder (2 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(0, 4, 0), new Vector(2, -2, 0)));
		Point3D p3 = new Point3D(2, 2, 0);
		Point3D p4 = new Point3D(4, 0, 0);
		assertEquals("TC18: Ray starts before and crosses the cylinder (2 points): Wrong number of points", 2,
				result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("TC18: Ray starts before and crosses the cylinder (2 points)", List.of(p3, p4), result);
		// TC19: Ray starts inside the cylinder (1 point)
		result = cylinder1.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0)));
		assertEquals("TC19: Ray starts inside the cylinder (1 point)", List.of(new Point3D(2, 2, 0)), result);
		// TC20: Ray starts after the cylinder (0 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("TC20: Ray starts after the cylinder (0 points)", null, result);
		// =============== Boundary Values Tests ==================
		// **** Group: Ray's line crosses the cylinder (ray start are same level
		// q0(start of cylinder))(but not the center)
		// TC05:Ray starts at cylinder and goes inside (1 points)
		result = cylinder.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(-2, 2, 0)));
		assertNull("TC02:empty list", result);
		// assertEquals(" TC05:Ray starts at cylinder and goes inside", List.of(new
		// Point3D(2, 2, 0)), result);
		// TC06: Ray starts at cylinder and goes outside(0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(1, 0, 0)));
		assertEquals(" TC06: Ray starts at cylinder and goes outside(0 points)", null, result);

		// **** Group: Ray's line goes through the center (ray start are same level
		// q0(start of cylinder)(spaciel for
		// cylinder ) *******
		// TC07: Ray starts before the cylinder (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(0, 6, 0)));
		assertNull("TC07:empty list", result);
		// TC08: Ray starts at cylinder and goes inside (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(0, 6, 0)));
		assertNull("TC08:empty list", result);
		// TC09: Ray starts inside (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 6, 0)));
		assertNull("TC09:empty list", result);
		// TC10: Ray starts at the center (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 6, 0)));
		assertNull("TC10:empty list", result);
		// TC11: Ray starts at cylinder and goes outside(0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, 2, 0), new Vector(0, 6, 0)));
		assertNull("TC11:empty list", result);
		// TC12: Ray starts after cylinder (0 points)
		result = cylinder.findIntersections(new Ray(new Point3D(2, 3, 0), new Vector(0, 6, 0)));
		assertNull("TC12:empty list", result);
		// **** Group: Ray's line is tangent to the cylinder (ray start are same level
		// q0(start of cylinder)) (all tests 0 points)
		// (spaciel for cylinder ) *******
		// TC13: Ray starts before the tangent point
		result = cylinder.findIntersections(new Ray(new Point3D(4, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC13: Ray starts before the tangent point", null, result);
		// TC14: Ray starts at the tangent point
		result = cylinder.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("  TC14: Ray starts at the tangent point", null, result);
		// TC15: Ray starts after the tangent point
		result = cylinder.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC15: Ray starts after the tangent point", null, result);
		// TC16: Ray's line is outside, ray is orthogonal to ray start to cylinder's
		// center
		// line
		result = cylinder.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(-4, 0, 0)));
		assertEquals("TC16: Ray's line is outside, ray is orthogonal to ray start to cylinder's center line", null,
				result);

		// **** Group: Ray's line crosses the cylinder (ray start are level up form
		// q0(start of cylinder)) (but not the center)
		// TC21:Ray starts at cylinder and goes inside (1 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(-2, 2, 0)));
		assertEquals(" TC21:Ray starts at cylinder and goes inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC22: Ray starts at cylinder and goes outside(0 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(1, 0, 0)));
		assertEquals(" TC22: Ray starts at cylinder and goes outside(0 points)", null, result);
		// **** Group: Ray's line goes through the center*****
		// TC23: Ray starts before the cylinder (2 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(0, 6, 0)));
		assertEquals(" TC23: Ray starts before the cylinder (2 points)",
				List.of(new Point3D(2, -2, 0), new Point3D(2, 2, 0)), result);
		// TC24: Ray starts at cylinder and goes inside (1 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC24: Ray starts at cylinder and goes inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC25: Ray starts inside (1 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 6, 0)));
		assertEquals(" TC25: Ray starts inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC26: Ray starts at the center (1 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 6, 0)));
		assertEquals(" TC26: Ray starts at the center (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC27: Ray starts at cylinder and goes outside(0 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, 2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC27: Ray starts at cylinder and goes outside(0 points)", null, result);
		// TC28: Ray starts after cylinder (0 points)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, 3, 0), new Vector(0, 6, 0)));
		assertEquals("  TC28: Ray starts after cylinder (0 points)", null, result);

		// **** Group: Ray's line is tangent to the cylinder ray start are level up form
		// q0(start of cylinder) (all tests 0 points)
		// TC29: Ray starts before the tangent point
		result = cylinder1.findIntersections(new Ray(new Point3D(4, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("TC29: Ray starts before the tangent point", null, result);
		// TC30: Ray starts at the tangent point
		result = cylinder1.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC30: Ray starts at the tangent point", null, result);
		// TC31: Ray starts after the tangent point
		result = cylinder1.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("TC31: Ray starts after the tangent point", null, result);
		// TC32: Ray's line is outside, ray is orthogonal to ray start to cylinder's
		// center line ray start are level up form q0(start of cylinder)
		result = cylinder1.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(-4, 0, 0)));
		assertEquals("  TC32: Ray's line is outside, ray is orthogonal to ray start to cylinder's center line", null,
				result);

		// new data
		Cylinder cylinder2 = new Cylinder(new Ray(new Point3D(2, 0, 0), new Vector(new Point3D(0, 0, 1))), 2, 3);

		// **** Group: Ray NOT perapell and not ortgonal to dir of cylinder (spaciel for
		// cylinder ) *******
		// TC33: Ray starts befor the cylinder intersect whit the center down and
		// Bounder up(2 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(0, 0, -3), new Vector(2, 0, 3)));
		assertEquals("TC33: Ray starts inside the tube", List.of(new Point3D(2, 0, 0), new Point3D(4, 0, 3)), result);
		// TC34: Ray starts up the cylinder intersect whit the base up and
		// base down(2 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(2, 2, 4.5), new Vector(0, -2, -3)));
		assertEquals("TC34: Ray starts inside the tube", List.of(new Point3D(2, -1, 0), new Point3D(2, 1, 3)), result);
		// TC39:ray intersect the sides and the base up (not in the center)(2 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(2.5, 3.5, 0), new Vector(-0.5, -1.5, 1)));
		assertEquals("TC39: Ray starts inside the tube", List.of(new Point3D(1, -1, 3), new Point3D(2, 2, 1)), result);
		// TC40:ray intersect the sides (trohw in the center(not the start ray))(2
		// point)
		result = cylinder2.findIntersections(new Ray(new Point3D(8, 0, 0), new Vector(-4, 0, 1)));
		assertEquals("TC40: Ray starts inside the tube", List.of(new Point3D(4, 0, 1), new Point3D(0, 0, 2)), result);

		// **** Group: Ray perapell to dir of cylinder *******
		// TC35: Ray perapell to dir of cylinder outside the cylinder(0 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(0, 5, 0), new Vector(0, 0, 1)));
		assertEquals("TC35: Ray starts inside the tube", null, result);
		// TC36: ray start in low base and parepel to the axis(1 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("TC36: Ray starts inside the tube", List.of(new Point3D(1, 1, 3)), result);
		// TC37: ray start in cylinder base and parepel to the axis(1 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 1)));
		assertEquals("TC37: Ray starts inside the tube", List.of(new Point3D(1, 1, 3)), result);
		// TC38: ray start out cylinder(befor) base and parepel to the axis(1 point)
		result = cylinder2.findIntersections(new Ray(new Point3D(1, 1, -1), new Vector(0, 0, 1)));
		assertEquals("TC38: Ray starts inside the tube", List.of(new Point3D(1, 1, 0), new Point3D(1, 1, 3)), result);

	}

}

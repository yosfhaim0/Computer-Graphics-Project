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
public class TubeTests {
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube t = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 1);
		Tube t1 = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 2);
		// ============ Equivalence Partitions Tests ==============
		// TC01:the intersection whit (3,2,0)vector came out form the center AXIS
		// Intersection whit the vector(1.41,1.41,0) came out for z=1
		Vector v2 = t1.getNormal(new Point3D(1.41, 1.41, 1));
		double y = 0.7071067811865475;
		assertEquals("Bad normal to Tube1", new Vector(y, y, 0), v2);
		// =============== Boundary Values Tests ==================
		// TC02:the point in the same level of the p0 of the rayTube
		Vector v3 = t.getNormal(new Point3D(1, 0, 0));
		assertEquals("Bad normal to Tube2", new Vector(1, 0, 0), v3);
	}

	/**
	 * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
	 */

	@Test
	public void testFindIntersections() {
		Tube tube = new Tube(new Ray(new Point3D(2, 0, 0), new Vector(new Point3D(0, 0, 1))), 2);
		Tube tube1 = new Tube(new Ray(new Point3D(2, 0, -1), new Vector(new Point3D(0, 0, 1))), 2);
		List<Point3D> result;
		// =============== Boundary Values Tests ==================
		// **** Group: ray in the level of axisray
		// TC01: Ray's line is outside the tube (0 points)
		result = tube.findIntersections(new Ray(new Point3D(6, 0, 0), new Vector(-6, 5, 0)));
		assertEquals("Ray's line is outside the tube", null, result);
		// TC02: Ray starts before and crosses the tube (2 points)
		result = tube.findIntersections(new Ray(new Point3D(0, 4, 0), new Vector(2, -2, 0)));
		Point3D p1 = new Point3D(2, 2, 0);
		Point3D p2 = new Point3D(4, 0, 0);
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(0), result.get(1));
		assertEquals("Ray crosses tube", List.of(p1, p2), result);
		// TC03: Ray starts inside the tube (1 point)
		result = tube.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0)));
		assertEquals("Ray starts inside the tube", List.of(new Point3D(2, 2, 0)), result);
		// TC04: Ray starts after the tube (0 points)
		result = tube.findIntersections(new Ray(new Point3D(5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Ray starts after the tube", null, result);
		// **** Group: Ray's line crosses the tube (ray in the level of axisray)(but not
		// the center)
		// TC05:Ray starts at tube and goes inside (1 points)
		result = tube.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(-2, 2, 0)));
		assertEquals(" TC05:Ray starts at tube and goes inside", List.of(new Point3D(2, 2, 0)), result);
		// TC06: Ray starts at tube and goes outside(0 points)
		result = tube.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(1, 0, 0)));
		assertEquals(" TC06: Ray starts at tube and goes outside(0 points)", null, result);

		// **** Group: Ray's line goes through the center (ray in the level of
		// axisray)*****
		// TC07: Ray starts before the tube (2 points)
		result = tube.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(0, 6, 0)));
		assertEquals(" TC07: Ray starts before the tube (2 points)",
				List.of(new Point3D(2, -2, 0), new Point3D(2, 2, 0)), result);
		// TC08: Ray starts at tube and goes inside (1 points)
		result = tube.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC08: Ray starts at tube and goes inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC09: Ray starts inside (1 points)
		result = tube.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 6, 0)));
		assertEquals(" TC09: Ray starts inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC10: Ray starts at the center (1 points)
		result = tube.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 6, 0)));
		assertEquals(" TC10: Ray starts at the center (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC11: Ray starts at tube and goes outside(0 points)
		result = tube.findIntersections(new Ray(new Point3D(2, 2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC11: Ray starts at tube and goes outside(0 points)", null, result);
		// TC12: Ray starts after tube (0 points)
		result = tube.findIntersections(new Ray(new Point3D(2, 3, 0), new Vector(0, 6, 0)));
		assertEquals(" TC11: Ray starts at tube and goes outside(0 points)", null, result);

		// **** Group: Ray's line is tangent to the tube (ray in the level of axisray)
		// (all tests 0 points)
		// TC13: Ray starts before the tangent point
		result = tube.findIntersections(new Ray(new Point3D(4, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC13: Ray starts before the tangent point", null, result);
		// TC14: Ray starts at the tangent point
		result = tube.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("  TC14: Ray starts at the tangent point", null, result);
		// TC15: Ray starts after the tangent point
		result = tube.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC15: Ray starts after the tangent point", null, result);

		// TC16: Ray's line is outside, ray is orthogonal to ray start to tube's center
		// line (ray in the level of axisray)
		result = tube.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(-4, 0, 0)));
		assertEquals("TC16: Ray's line is outside, ray is orthogonal to ray start to tube's center line", null, result);
		// **** Group: ray not in the level of axisray
		// TC17: Ray's line is outside the tube (0 points)
		result = tube1.findIntersections(new Ray(new Point3D(6, 0, 0), new Vector(-6, 5, 0)));
		assertEquals("TC17: Ray's line is outside the tube (0 points)", null, result);
		// TC18: Ray starts before and crosses the tube (2 points)
		result = tube1.findIntersections(new Ray(new Point3D(0, 4, 0), new Vector(2, -2, 0)));
		Point3D p3 = new Point3D(2, 2, 0);
		Point3D p4 = new Point3D(4, 0, 0);
		assertEquals("TC18: Ray starts before and crosses the tube (2 points): Wrong number of points", 2,
				result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("TC18: Ray starts before and crosses the tube (2 points)", List.of(p3, p4), result);
		// TC19: Ray starts inside the tube (1 point)
		result = tube1.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0)));
		assertEquals("TC19: Ray starts inside the tube (1 point)", List.of(new Point3D(2, 2, 0)), result);
		// TC20: Ray starts after the tube (0 points)
		result = tube1.findIntersections(new Ray(new Point3D(5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("TC20: Ray starts after the tube (0 points)", null, result);
		// **** Group: Ray's line crosses the (tube ray are orthogonal to the tube)(ray
		// not in the level of axisray) (but not the center)
		// TC21:Ray starts at tube and goes inside (1 points)
		result = tube1.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(-2, 2, 0)));
		assertEquals(" TC21:Ray starts at tube and goes inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC22: Ray starts at tube and goes outside(0 points)
		result = tube1.findIntersections(new Ray(new Point3D(4, 0, 0), new Vector(1, 0, 0)));
		assertEquals(" TC22: Ray starts at tube and goes outside(0 points)", null, result);
		// **** Group: Ray's line goes through the center(tube ray are orthogonal to the
		// tube)(ray not in the level of axisray)*****
		// TC23: Ray starts before the tube (2 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(0, 6, 0)));
		assertEquals(" TC23: Ray starts before the tube (2 points)",
				List.of(new Point3D(2, -2, 0), new Point3D(2, 2, 0)), result);
		// TC24: Ray starts at tube and goes inside (1 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC24: Ray starts at tube and goes inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC25: Ray starts inside (1 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 6, 0)));
		assertEquals(" TC25: Ray starts inside (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC26: Ray starts at the center (1 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 6, 0)));
		assertEquals(" TC26: Ray starts at the center (1 points)", List.of(new Point3D(2, 2, 0)), result);
		// TC27: Ray starts at tube and goes outside(0 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, 2, 0), new Vector(0, 6, 0)));
		assertEquals(" TC27: Ray starts at tube and goes outside(0 points)", null, result);
		// TC28: Ray starts after tube (0 points)
		result = tube1.findIntersections(new Ray(new Point3D(2, 3, 0), new Vector(0, 6, 0)));
		assertEquals("  TC28: Ray starts after tube (0 points)", null, result);

		// **** Group: Ray's line is tangent to the tube (tube ray are orthogonal to the
		// tube)(ray not in the level of axisray)(all tests 0 points)
		// TC29: Ray starts before the tangent point
		result = tube1.findIntersections(new Ray(new Point3D(4, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("TC29: Ray starts before the tangent point", null, result);
		// TC30: Ray starts at the tangent point
		result = tube1.findIntersections(new Ray(new Point3D(2, -2, 0), new Vector(-4, 0, 0)));
		assertEquals(" TC30: Ray starts at the tangent point", null, result);
		// TC31: Ray starts after the tangent point
		result = tube1.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(-4, 0, 0)));
		assertEquals("TC31: Ray starts after the tangent point", null, result);
		// TC32: Ray's line is outside, ray is orthogonal to ray start to tube's center
		// line
		result = tube1.findIntersections(new Ray(new Point3D(2, -4, 0), new Vector(-4, 0, 0)));
		assertEquals("  TC32: Ray's line is outside, ray is orthogonal to ray start to tube's center line", null,
				result);

		// new data for the next tests
		Vector vAxis = new Vector(0, 0, 1);
		Tube tube2 = new Tube(new Ray(new Point3D(1, 1, 1), vAxis), 1d);
		Ray ray;
		// **** Group: Ray's line is parallel to the axis (0 points)
		// TC33: Ray is inside the tube (0 points)
		ray = new Ray(new Point3D(0.5, 0.5, 0.5), vAxis);
		assertNull(" TC33: must not be intersections", tube2.findIntersections(ray));
		// TC34: Ray is outside the tube
		ray = new Ray(new Point3D(0.5, -0.5, 0.5), vAxis);
		assertNull("TC34: must not be intersections", tube2.findIntersections(ray));
		// TC35: Ray is at the tube surface
		ray = new Ray(new Point3D(2, 1, 0.5), vAxis);
		assertNull("TC35: must not be intersections", tube2.findIntersections(ray));
		// TC36: Ray is inside the tube and starts against axis head
		ray = new Ray(new Point3D(0.5, 0.5, 1), vAxis);
		assertNull("TC36: must not be intersections", tube2.findIntersections(ray));
		// TC37: Ray is outside the tube and starts against axis head
		ray = new Ray(new Point3D(0.5, -0.5, 1), vAxis);
		assertNull("TC37: must not be intersections", tube2.findIntersections(ray));
		// TC38: Ray is at the tube surface and starts against axis head
		ray = new Ray(new Point3D(2, 1, 1), vAxis);
		assertNull("TC38: must not be intersections", tube2.findIntersections(ray));
		// TC39: Ray is inside the tube and starts at axis head
		ray = new Ray(new Point3D(1, 1, 1), vAxis);
		assertNull("TC39: must not be intersections", tube2.findIntersections(ray));

		// **** Group: Ray's line is neither parallel nor orthogonal to the axis and
		// begins against axis head
		Point3D p0 = new Point3D(0, 2, 1);
		// TC40: Ray's line is outside the tube
		ray = new Ray(p0, new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC40: Ray's line is outside the tube: Bad intersections", result);
		// TC41: Ray's line crosses the tube and begins before
		ray = new Ray(p0, new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC41: Ray's line crosses the tube and begins before: must be intersections", result);
		assertEquals("TC41: Ray's line crosses the tube and begins before: must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("TC41: Ray's line crosses the tube and begins before: Bad intersections",
				List.of(new Point3D(2, 1, 2), new Point3D(0.4, 1.8, 1.2)), result);
		// TC42: Ray's line crosses the tube and begins at surface and goes inside
		ray = new Ray(new Point3D(0.4, 1.8, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC42: must be intersections", result);
		assertEquals("TC42: must be 1 intersections", 1, result.size());
		assertEquals("TC42: Bad intersections", List.of(new Point3D(2, 1, 1.8)), result);
		// TC43: Ray's line crosses the tube and begins inside
		ray = new Ray(new Point3D(1, 1.5, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC43: must be intersections", result);
		assertEquals("TC43: must be 1 intersections", 1, result.size());
		assertEquals("TC43: Bad intersections", List.of(new Point3D(2, 1, 1.5)), result);
		// TC44: Ray's line crosses the tube and begins at the axis head
		ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC44: must be intersections", result);
		assertEquals("TC44: must be 1 intersections", 1, result.size());
		assertEquals("TC44: Bad intersections", List.of(new Point3D(1, 2, 2)), result);
		// TC45: Ray's line crosses the tube and begins at surface and goes outside
		ray = new Ray(new Point3D(2, 1, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC45: Bad intersections", result);
		// TC46: Ray's line is tangent and begins before
		ray = new Ray(p0, new Vector(0, 2, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC46: Bad intersections", result);
		// TC47: Ray's line is tangent and begins at the tube surface
		ray = new Ray(new Point3D(1, 2, 1), new Vector(1, 0, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC47: Bad intersections", result);
		// TC48: Ray's line is tangent and begins after
		ray = new Ray(new Point3D(2, 2, 1), new Vector(1, 0, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC48: Bad intersections", result);

		// **** Group: Ray's line is neither parallel nor orthogonal to the axis and
		// does not begin against axis head
		double sqrt2 = Math.sqrt(2);
		double denomSqrt2 = 1 / sqrt2;
		double value1 = 1 - denomSqrt2;
		double value2 = 1 + denomSqrt2;
		// TC49: Ray's crosses the tube and the axis
		ray = new Ray(new Point3D(0, 0, 2), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC49: must be intersections", result);
		assertEquals("TC49: must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("TC49: Bad intersections",
				List.of(new Point3D(value1, value1, 2 + value1), new Point3D(value2, value2, 2 + value2)), result);
		// TC50: Ray's crosses the tube and the axis head
		ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC50: must be intersections", result);
		assertEquals("TC50: must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("TC50: Bad intersections",
				List.of(new Point3D(value1, value1, value1), new Point3D(value2, value2, value2)), result);
		// TC52: Ray's begins at the surface and goes inside crossing the axis
		ray = new Ray(new Point3D(value1, value1, 2 + value1), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC52: must be intersections", result);
		assertEquals("TC52: must be 1 intersections", 1, result.size());
		assertEquals("TC52: Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
		// TC53: Ray's begins at the surface and goes inside crossing the axis head
		ray = new Ray(new Point3D(value1, value1, value1), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC53: must be intersections", result);
		assertEquals("TC53: must be 1 intersections", 1, result.size());
		assertEquals("TC53: Bad intersections", List.of(new Point3D(value2, value2, value2)), result);
		// TC54: Ray's begins inside and the line crosses the axis
		ray = new Ray(new Point3D(0.5, 0.5, 2.5), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC54: must be intersections", result);
		assertEquals("TC54: must be 1 intersections", 1, result.size());
		assertEquals("TC54: Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
		// TC55: Ray's begins inside and the line crosses the axis head
		ray = new Ray(new Point3D(0.5, 0.5, 0.5), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC55: must be intersections", result);
		assertEquals("TC55: must be 1 intersections", 1, result.size());
		assertEquals("TC55: Bad intersections", List.of(new Point3D(value2, value2, value2)), result);
		// TC56: Ray's begins at the axis
		ray = new Ray(new Point3D(1, 1, 3), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("TC56: must be intersections", result);
		assertEquals("TC56: must be 1 intersections", 1, result.size());
		assertEquals("TC56: Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
		// TC57: Ray's begins at the surface and goes outside
		ray = new Ray(new Point3D(2, 1, 2), new Vector(2, 1, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC57: Bad intersections", result);
		// TC58: Ray's begins at the surface and goes outside and the line crosses the
		// axis
		ray = new Ray(new Point3D(value2, value2, 2 + value2), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC58: Bad intersections", result);
		// TC59: Ray's begins at the surface and goes outside and the line crosses the
		// axis head
		ray = new Ray(new Point3D(value2, value2, value2), new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNull("TC59: Bad intersections", result);
	}
}

/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: closest in middle of list
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		List<Point3D> list = new ArrayList<Point3D>();
		list.add(new Point3D(0, 0, 9));
		list.add(new Point3D(0, 0, 3));
		list.add(new Point3D(0, 0, 5));
		assertEquals("TC01: Wrong Point Closest", new Point3D(0, 0, 3), ray.findClosestPoint(list));
		// =============== Boundary Values Tests ==================
		// TC02:List ==null
		list = new ArrayList<Point3D>();
		assertEquals("TC02: Wrong return", null, ray.findClosestPoint(list));
		// TC03:Closest is first in list
		list.add(new Point3D(0, 0, 3));
		list.add(new Point3D(0, 0, 9));
		list.add(new Point3D(0, 0, 5));
		assertEquals("TC03: Wrong Point Closest", new Point3D(0, 0, 3), ray.findClosestPoint(list));
		// TC04:Closest is last in list
		list.add(new Point3D(0, 0, 2));
		assertEquals("TC04: Wrong Point Closest", new Point3D(0, 0, 2), ray.findClosestPoint(list));
	}

}

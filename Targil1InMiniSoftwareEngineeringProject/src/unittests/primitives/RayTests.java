/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: closest in middle of list
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(null, new Point3D(0, 0, 9)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 3)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 5)));
		assertEquals("TC01: Wrong Point Closest", new Point3D(0, 0, 3), ray.getClosestGeoPoint(list).point);
		// =============== Boundary Values Tests ==================
		// TC02:List ==null
		list = null;
		assertNull("TC02: Wrong return", ray.getClosestGeoPoint(list));
		// TC03:Closest is first in list
		list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(null, new Point3D(0, 0, 3)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 9)));
		list.add(new GeoPoint(null, new Point3D(0, 0, 5)));
		assertEquals("TC03: Wrong Point Closest", new Point3D(0, 0, 3), ray.getClosestGeoPoint(list).point);
		// TC04:Closest is last in list
		list.add(new GeoPoint(null, new Point3D(0, 0, 2)));
		assertEquals("TC04: Wrong Point Closest", new Point3D(0, 0, 2), ray.getClosestGeoPoint(list).point);
	}

	
}

/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author yosefHaim
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#chackIfPointInRay()}.
	 */
	@Test
	public void testChackIfPointInRay() {
		Ray r= new Ray(new Point3D(1, 0, 0), new Vector(new Point3D(1, 0, 0)));
		// ============ Equivalence Partitions Tests ==============
        assertFalse("bad chack", r.chackIfPointInRay(new Point3D(-1, 0, 0)));
		// ============ Equivalence Partitions Tests ==============
        assertTrue("bad chack", r.chackIfPointInRay(new Point3D(5, 0, 0)));

	}

}

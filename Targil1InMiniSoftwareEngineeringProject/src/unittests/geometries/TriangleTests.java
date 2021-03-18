/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class TriangleTests {
	/**
     * Test method for
     * {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle t = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), t.getNormal(new Point3D(0, 0, 1)));
    }

}

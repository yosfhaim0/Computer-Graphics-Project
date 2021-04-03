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
	/**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
	@Test
	public void testFindIntersections() {
		Triangle t=new Triangle(new Point3D(2, 0, 0),new Point3D(0, 3, 0), new Point3D(0, 0, 0));
        // ============ Equivalence Partitions Tests ==============
		//TC01: Inside polygon/triangle(1 Point)
		Ray ray1=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(1, 1, 1)));
		List<Point3D> resulet=t.findIntersections(ray1);
		Point3D p1=new Point3D(1,1,0);
		assertEquals("Inside polygon/triangle(1 Point)",List.of(p1),resulet);
		//TC02: Outside against edge(0 Point)
		Ray ray2=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(2, 1, 1)));
		assertEquals("Outside against edge",null,t.findIntersections(ray2));
		//TC03: Outside against vertex(0 Point)
		Ray ray3=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(3, -0.5, 1)));
		assertEquals("Outside against vertex",null,t.findIntersections(ray3));
        // =============== Boundary Values Tests ==================
		//*********Three cases (the ray begins "before" the Triangle)*****
		//TC04: On edge(0 Point)
		Ray ray4=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 1, 1)));
		assertEquals("On edge",null,t.findIntersections(ray4));
		//TC05: In vertex(0 Point)
		Ray ray5=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 3, 1)));
		assertEquals("In vertex",null,t.findIntersections(ray5));
		//TC06: On edge's continuation(0 Point)
		Ray ray6=new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 4, 1)));
		assertEquals("On edge's continuation",null,t.findIntersections(ray6));
	}
}

/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;


import geometries.Plane;
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
     * {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
	@Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
		Plane p=new Plane(new Point3D(0,2,0),new Point3D(2,0,0),new Point3D(0,0,2));
		Vector v= p.getNormal(new Point3D(0,2,0));
		/**
		 * It is expected that the dot product between the normal 
		 * and the vector contained within the plane will be equal to 0
		 */
        assertTrue("The normal is not really perpendicular to the Plane", isZero(v.dotProduct(new Vector(-2,0,2))));
	}
	/**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
	@Test
	public void testFindIntersections() {
		Plane p=new Plane(new Point3D(2, 0, 0), new Vector(new Point3D(1, 1, 1)));
        // ============ Equivalence Partitions Tests ==============
		//There is one intersection between the plane and the ray (1 point)
		Ray ray1=new Ray(new Point3D(0, 4, 0), new Vector(new Point3D(-1, -3, 2)));
		assertEquals("one intersection",0,p.findIntersections(ray1).indexOf(new Point3D(-1, 1, 2)));
        
		// ============ Equivalence Partitions Tests ==============
		//plane and the ray are parallel (0 point)
		Ray ray2=new Ray(new Point3D(0, 4, 0), new Vector(new Point3D(1, 1, 1)));
		assertEquals("plane and the ray are parallel",null,p.findIntersections(ray2));
		
        // =============== Boundary Values Tests ==================
		//the ray in the plane infinite point
		Ray ray3=new Ray(new Point3D(0, 2, 0), new Vector(new Point3D(-2, 0, 2)));
		assertEquals("the ray in the plane infinite point",null,p.findIntersections(ray3));
		
		// ============ Equivalence Partitions Tests ==============
		//the ray Direction Away from the plane
		Ray ray4=new Ray(new Point3D(0, 4, 0), new Vector(new Point3D(1, 3, -2)));
		assertEquals("the ray Direction Away from the plane",null,p.findIntersections(ray4));

	}
	

}

/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;
import primitives.*;
/**
 * @author yosefHaim
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(5,5,3);
        // ============ Equivalence Partitions Tests ==============
		//test the subtract vector are correct 
        assertEquals("subtract() wrong value",new Vector(-4,-3,0),p1.subtract(p2));
        try {
            p1.subtract(p1);
            fail("subtract() for Point3D Exactly himself does not throw an exception(You tried to build vector 0 it is invalid!)");
        } catch (Exception e) {}
        }

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(5,5,3);
        // ============ Equivalence Partitions Tests ==============
		//test the add point are correct 
        assertEquals("Add() wrong value",new Point3D(6,7,6),p1.add(new Vector(p2)) );
        }

	/**
	 * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(5,5,3);   
		// ============ Equivalence Partitions Tests ==============
		// test DistanceSquared..
        assertTrue("distanceSquared() wrong value",isZero(p1.distanceSquared(p2)-25));
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(5,5,3);
        // ============ Equivalence Partitions Tests ==============
        assertTrue("distance() wrong value", isZero(p1.distance(p2) - 5));

	}
	

}

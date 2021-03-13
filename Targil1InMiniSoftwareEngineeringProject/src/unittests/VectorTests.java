/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitive.Util.isZero;

import org.junit.Test;

import primitive.*;

/**
 * Unit tests for primitive.Vector class
 * @author yosefHaim
 *
 */
public class VectorTests{
	
   
	/**
	 * Test method for {@link primitive.Vector#subtract(primitive.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(2, 4, 5);
		Vector v2 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("your secced to subtract",v1.subtract(v2).length(),3,0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.subtract(v1);
            fail("subtract() for Vector Exactly himself does not throw an exception(You tried to build vector 0 it is invalid!)");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitive.Vector#add(primitive.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 2, 4);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3=new Vector(-1,-2,-4);
		// ============ Equivalence Partitions Tests ==============
		Vector v4=v1.add(v2);
		assertEquals("your secced to subtract",3,v4.length(),0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.add(v3);
            fail("add() for Vector with Just the opposite coordinates does not throw an exception");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitive.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(0.5, 1, 1);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("your secced to subtract",3,(v1.scale(2)).length(),0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.scale(0);
            fail("scale() for number 0 does not throw an exception");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitive.Vector#dotProduct(primitive.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector(0, 3, -2);
			// ============ Equivalence Partitions Tests ==============
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitive.Vector#crossProduct(primitive.Vector)}.
	 */
    @Test
    public void testCrossProduct() {
    	Vector v1 = new Vector(1, 2, 3);
    	Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
    	Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


	/**
	 * Test method for {@link primitive.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
        // test lengthSquared..
        assertTrue("lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitive.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
        // test length..
        assertTrue("length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}

	/**
	 * Test method for {@link primitive.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v=new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		v.normalize();
        assertTrue("Normaliz() Does not normalize", isZero(v.length() - 1));
	}

	/**
	 * Test method for {@link primitive.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v3 = new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		Vector v=v3.normalized();
        assertTrue("Normaliz() Does not normalize", isZero(v.length() - 1));
	}

}

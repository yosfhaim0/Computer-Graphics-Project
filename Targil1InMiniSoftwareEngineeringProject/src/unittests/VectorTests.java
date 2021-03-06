/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.*;

/**
 * Unit tests for primitive.Vector class
 * @author yosefHaim
 *
 */
public class VectorTests{
	
   
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(2, 4, 5);
		Vector v2 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		//test the subtract vector are correct 
		assertEquals("bad subtract",3,v1.subtract(v2).length(),0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.subtract(v1);
            fail("subtract() for Vector Exactly himself does not throw an exception(You tried to build vector 0 it is invalid!)");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 2, 4);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3=new Vector(-1,-2,-4);
		Vector v4=v1.add(v2);
		// ============ Equivalence Partitions Tests ==============
		//test the add vector are correct 
		assertEquals("add worng result",3,v4.length(),0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.add(v3);
            fail("add() for Vector with Just the opposite coordinates does not throw an exception(vector 0)");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(0.5, 1, 1);
		// ============ Equivalence Partitions Tests ==============
		//test the scale vector give propriety result
		assertEquals("scale() wrong result",3,(v1.scale(2)).length(),0.0001);
		// =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {
            v1.scale(0);
            fail("scale() for number 0 does not throw an exception");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector(0, 3, -2);
			// ============ Equivalence Partitions Tests ==============
	     //test the orthogonal vector dot product really give 0
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        //test the double number given form dot product is propriety  
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
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
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
        // test lengthSquared..
        assertTrue("lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
        // test length..
        assertTrue("length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v=new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		//test whether the length of a normal vector is really 1
		v.normalize();
        assertTrue("Normaliz() Does not normalize", isZero(v.length() - 1));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v3 = new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		//test whether the length of a normal vector is really 1
		Vector v=v3.normalized();
        assertTrue("Normalizd() Does not normalize", isZero(v.length() - 1));
	}
	/**
	 * Test method for {@link primitives.Vector#angleBetweenTowVector()}.
	 */
	@Test
	public void testAngalBetweenTowVector() {
		Vector v1 =new Vector(new Point3D(1, 0, 0));
		Vector v2 =new Vector(new Point3D(-1, 0, 0));
		Vector v4 =new Vector(new Point3D(0, 1, 0));
		Vector v3 =new Vector(new Point3D(3, 0, 0));
		// ============ Equivalence Partitions Tests ==============
		//TC01:				The vectors in opposite directions
		assertEquals("Angal Between Tow Vector opposite directions bad",Math.PI,v1.angleBetweenTowVector(v2),0.0001);
		// ============ Equivalence Partitions Tests ==============
		//TC01:		The vectors are in exactly the same direction
		assertEquals("Angal Between Tow Vector same direction bad",0,v1.angleBetweenTowVector(v3),0.0001);
		// ============ Equivalence Partitions Tests ==============
		//TC01: 		The vectors are perpendicular
		assertEquals("Angal Between Tow Vector perpendicular bad",Math.PI/2,v1.angleBetweenTowVector(v4),0.0001);
	}
}

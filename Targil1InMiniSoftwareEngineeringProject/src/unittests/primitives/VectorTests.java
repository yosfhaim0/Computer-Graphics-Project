/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;
import org.junit.Test;
import primitives.*;

/**
 * Unit tests for primitive.Vector class
 * 
 * @author yosefHaim
 *
 */
public class VectorTests {

	/**
	 * tests for rotating Vector On Axis function Test method for
	 * {@link primitives.Vector#rotatingVectorOnAxis(primitives.Vector,double)}.
	 */
	@Test
	public void rotatingVectorOnAxis() {
		Vector vector = new Vector(1, 0, 0);
		Vector vector2 = new Vector(0, 0, 1);
		// TC01:rotet 90 degree
		assertEquals("bad rotating Vector On Axis", new Vector(0, -1, 0), vector2.rotatingVectorOnAxis(vector, 90));
		// TC02:rotet 180 degree
		assertEquals("bad rotating Vector On Axis", new Vector(0, 0, -1), vector2.rotatingVectorOnAxis(vector, 180));
		// TC03:rotet 270 degree
		assertEquals("bad rotating Vector On Axis", new Vector(0, 1, 0), vector2.rotatingVectorOnAxis(vector, 270));
		// TC04:rotet 360 degree
		assertEquals("bad rotating Vector On Axis", new Vector(0, 0, 1), vector2.rotatingVectorOnAxis(vector, 360));
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(2, 4, 5);
		Vector v2 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// test the subtract vector are correct
		assertEquals("bad subtract", 3, v1.subtract(v2).length(), 0.0001);
		// =============== Boundary Values Tests ==================
		// test zero vector from scale vectors
		try {
			v1.subtract(v1);
			fail("subtract() for Vector Exactly himself does not throw an exception(You tried to build vector 0 it is invalid!)");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 2, 4);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(-1, -2, -4);
		Vector v4 = v1.add(v2);
		// ============ Equivalence Partitions Tests ==============
		// test the add vector are correct
		assertEquals("add worng result", 3, v4.length(), 0.0001);
		// =============== Boundary Values Tests ==================
		// test zero vector from scale vectors
		try {
			v1.add(v3);
			fail("add() for Vector with Just the opposite coordinates does not throw an exception(vector 0)");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(0.5, 1, 1);
		// ============ Equivalence Partitions Tests ==============
		// test the scale vector give propriety result
		assertEquals("scale() wrong result", 3, (v1.scale(2)).length(), 0.0001);
		// =============== Boundary Values Tests ==================
		// test zero vector from scale vectors
		try {
			v1.scale(0);
			fail("scale() for number 0 does not throw an exception");
		} catch (Exception e) {
		}
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
		// test the orthogonal vector dot product really give 0
		assertEquals("dotProduct() for orthogonal vectors is not zero", 0, v1.dotProduct(v3), 0.000000001);
		// test the double number given form dot product is propriety
		assertEquals("dotProduct() wrong value", -28, v1.dotProduct(v2), 0.0000000001);
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

		// Test that length of cross-product is proper (orthogonal vectors taken for
		// simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

		// Test cross-product result orthogonality to its operands
		assertEquals("crossProduct() result is not orthogonal to 1st operand", 0, vr.dotProduct(v1), 0.000000001);
		assertEquals("crossProduct() result is not orthogonal to 2nd operand", 0, vr.dotProduct(v3), 0.000000001);

		// =============== Boundary Values Tests ==================
		// test zero vector from cross-productof co-lined vectors
		try {
			v1.crossProduct(v2);
			fail("crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
		// test lengthSquared..
		assertEquals("lengthSquared() wrong value", 14, v1.lengthSquared(), 0.0000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
		// test length..
		assertEquals("length() wrong value", 5, new Vector(0, 3, 4).length(), 0.0000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v = new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		// test whether the length of a normal vector is really 1
		v.normalize();
		assertEquals("Normaliz() Does not normalize", 1, v.length(), 0.000000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v3 = new Vector(1, 5, -2);
		// ============ Equivalence Partitions Tests ==============
		// test whether the length of a normal vector is really 1
		Vector v = v3.normalized();
		assertEquals("Normalizd() Does not normalize", 1, v.length(), 0.000000000001);
	}

	/**
	 * test for check if the method return vertical vector
	 * Test method for {@link primitives.Vector#CreateVerticalVector()}.
	 */
	@Test
	public void testCreateVerticalVector() {
		Vector v = new Vector(1, 1, 1);
		double d = v.dotProduct(v.createVerticalVector());
		// TC01: vector 1,1,1 all the same
		assertEquals("CreateVerticalVector() Does not Create Vertical Vector", 0d, d, 0.00000000000001);
		
		v = new Vector(-2, 8, -10).normalize();
		d = v.dotProduct(v.createVerticalVector());
		// TC02: component x is smallest
		assertEquals("CreateVerticalVector() Does not Create Vertical Vector", 0d, d, 0.00000000000001);
		
		v = new Vector(-10, 8, -5).normalize();
		d = v.dotProduct(v.createVerticalVector());
		// TC03: component z is smallest
		assertEquals("CreateVerticalVector() Does not Create Vertical Vector", 0d, d, 0.00000000000001);
		
		v = new Vector(-2, 8, -10).normalize();
		d = v.dotProduct(v.createVerticalVector());
		// TC04: component y is smallest
		assertEquals("CreateVerticalVector() Does not Create Vertical Vector", 0d, d, 0.00000000000001);
		
		v = new Vector(-7127, 5000, -9000).normalize();
		d = v.dotProduct(v.createVerticalVector());
		// TC01: vector (-7127, 5000, -9000).normalize()
		assertEquals("CreateVerticalVector() Does not Create Vertical Vector", 0d, d, 0.00000000000001);

	}

}

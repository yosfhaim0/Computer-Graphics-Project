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
public class TubeTests {
	/**
     * Test method for
     * {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
	@Test
	public void testGetNormal() {
		Tube t=new Tube(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1);
		Tube t1=new Tube(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),2);
        // ============ Equivalence Partitions Tests ==============
		//TC01:base case
		assertEquals("Bad normal to Tube1", new Vector(1, 0, 0), t.getNormal(new Point3D(1,0,1)));
		//TC02:the intersection whit (3,2,0)vector came out form the center AXIS
		//Intersection whit the vector(1.41,1.41,0) came out for z=1
		Vector v2=t1.getNormal(new Point3D(1.41,1.41,1));
		double y=0.7071067811865475;
		assertEquals("Bad normal to Tube2",new Vector(y, y, 0),v2);
        // =============== Boundary Values Tests ==================
		//the point in the same level of the p0 of the rayTube
		assertEquals("Bad normal to Tube1", new Vector(1, 0, 0), t.getNormal(new Point3D(1,0,0)));
	}
}

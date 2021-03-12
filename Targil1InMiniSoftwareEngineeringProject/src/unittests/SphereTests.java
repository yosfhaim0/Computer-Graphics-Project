/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitive.*;

/**
 * @author yosefHaim
 *
 */
public class SphereTests {

	@Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		//the base calculation
		Sphere s=new Sphere(new Point3D(0,0,1),1);
		Vector v1 = s.getNormal(new Point3D(0,0,0));
		//The expected normal is (0,0,1)
		assertEquals("The normal calculation is incorrect",v1,new Vector(0,0,-1));
		
		//more complicity this a test whit the vector (0,4,-1) come out form the center of the Sphere
		Vector v2 = s.getNormal(new Point3D(0,0.96,0.76));
		double x= Math.sqrt(17);
		assertEquals("The normal calculation is incorrect",v2,new Vector(0,4d/x,-1d/x));
	}

}

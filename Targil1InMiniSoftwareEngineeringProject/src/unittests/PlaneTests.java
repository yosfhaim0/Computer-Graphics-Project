/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitive.Util.isZero;

import org.junit.Test;

import geometries.Plane;
import primitive.Point3D;
import primitive.Vector;

/**
 * @author yosefHaim
 *
 */
public class PlaneTests {

	@Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		Plane p=new Plane(new Point3D(0,2,0),new Point3D(2,0,0),new Point3D(0,0,2));
		Vector v= p.getNormal(new Point3D(0,2,0));
		/**
		 * It is expected that the scalar product between the normal 
		 * and the vector contained within the plane will be equal to 0
		 */
        assertTrue("The normal is not really perpendicular to the Plane", isZero(v.dotProduct(new Vector(-2,0,2))));
	}
	

}

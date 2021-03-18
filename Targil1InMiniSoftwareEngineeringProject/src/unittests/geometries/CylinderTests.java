/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;


import geometries.*;
import primitives.*;

/**
 * 
 * @author yosefHaim
 *
 */
public class CylinderTests {
	/**
     * Test method for
     * {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
	@Test
    public void testGetNormal() {
		
		Cylinder c=new Cylinder(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1,1);
		Cylinder c2=new Cylinder(new Ray(new Point3D(0, 0, -1), new Vector(new Point3D(0, 0, 1))), 3,5);

        // ============ Equivalence Partitions Tests ==============
		//TC01:the point Contained low base
		Point3D p1=new Point3D(0.5,0.5,0);
		Vector v1= c.getNormal(p1);
		assertEquals("Bad normal to the low base of Cylinder1",new Vector(0,0,1),v1);
		//TC01.1
		Point3D p9=new Point3D(0.02,-1.26,-1);
		Vector v9= c2.getNormal(p9);
		assertEquals("Bad normal to the low base of Cylinder2",new Vector(0,0,1),v9);
		//TC01.2
		Point3D p7=new Point3D(0.24,-0.69,0);
		Vector v7= c.getNormal(p7);
		assertEquals("Bad normal to the low base of Cylinder2",new Vector(0,0,1),v7);
        // ============ Equivalence Partitions Tests ==============
		//TC02:the point Contained the up base
		Point3D p2=new Point3D(0.5,0.5,1);
		Vector v2= c.getNormal(p2);
		assertEquals("Bad normal to the up base of Cylinder1",new Vector(0,0,1),v2);
		//TC02.2
		Point3D p8=new Point3D(-1.68, -0.53, 4);
		assertEquals("Bad normal to the up base of Cylinder2",new Vector(0,0,1),c2.getNormal(p8));
		//TC02.3
		Point3D p6=new Point3D(0.44,0.3,1);
		Vector v6= c.getNormal(p6);
		assertEquals("Bad normal to the up base of Cylinder3",new Vector(0,0,1),v6);
        // ============ Equivalence Partitions Tests ==============
		//TC03:the point Contained the sides
		Point3D p3=new Point3D(1,0,0.5);
		Vector v3= c.getNormal(p3);
		assertEquals("Bad normal to the sides of Cylinder1",new Vector(1,0,0),v3);
		
        // =============== Boundary Values Tests ==================
		//TC04:the point between the cylinder base(down) and the "tube"
		Point3D p4=new Point3D(1,0,0);
		Vector v4= c.getNormal(p4);
		assertEquals("Bad normal Boundary Values1",new Vector(0,0,1),v4);
        // =============== Boundary Values Tests ==================
		//TC05:the point between the cylinder base(up) and the "tube"
		Point3D p5=new Point3D(1,0,1);
		Vector v5= c.getNormal(p5);
		assertEquals("Bad normal Boundary Values2",new Vector(0,0,1),v5);
	}
	

}

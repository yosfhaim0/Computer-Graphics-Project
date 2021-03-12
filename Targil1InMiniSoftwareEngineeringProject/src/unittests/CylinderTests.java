/**
 * 
 */
package unittests;

import static org.junit.Assert.*;


import org.junit.Test;

import geometries.*;
import primitive.*;

/**
 * 
 * @author yosefHaim
 *
 */
public class CylinderTests {

	@Test
    public void testGetNormal() {
		
		Cylinder c=new Cylinder(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1,1);
        // ============ Equivalence Partitions Tests ==============
		//the point Contained low base
		Point3D p1=new Point3D(0.5,0.5,0);
		Vector v1= c.getNormal(p1);
		assertEquals("Bad normal to the low base of Cylinder",new Vector(0,0,1),v1);
        // ============ Equivalence Partitions Tests ==============
		//the point Contained the up base
		Point3D p2=new Point3D(0.5,0.5,1);
		Vector v2= c.getNormal(p2);
		assertEquals("Bad normal to the up base of Cylinder",new Vector(0,0,1),v2);
        // ============ Equivalence Partitions Tests ==============
		//the point Contained the sides
		Point3D p3=new Point3D(1,0,0.5);
		Vector v3= c.getNormal(p3);
		assertEquals("Bad normal to the sides of Cylinder",new Vector(1,0,0),v3);
        // =============== Boundary Values Tests ==================
		//in case of point on Boundary the vector vertical the base of the cylinder
		Point3D p4=new Point3D(1,0,0);
		Vector v4= c.getNormal(p4);
		assertEquals("Bad normal Boundary Values1",new Vector(0,0,1),v4);
        // =============== Boundary Values Tests ==================
		//in case of point on Boundary the vector vertical the base of the cylinder
		Point3D p5=new Point3D(1,0,1);
		Vector v5= c.getNormal(p5);
		assertEquals("Bad normal Boundary Values2",new Vector(0,0,1),v5);
		
		//Point3D p6=new Point3D(0,0.5,1);
		//Vector v6= c.getNormal(p6);
		//assertEquals("Bad normal Boundary Values2",new Vector(0,0,1),v6);
		
//		Cylinder c1=new Cylinder(new Ray(new Point3D(-6.59431,0.90272,-2.22848),new Vector(9.01,-3.59,6)),1,11.404744);
//		Point3D p6=new Point3D(-1.32,-0.48,2);
//		Vector v6= c1.getNormal(p6);
//		assertEquals("Bad normal Boundary Values3",new Vector(0.1158903146,0.7677733345,0.6301535859),v6);
	}
	

}
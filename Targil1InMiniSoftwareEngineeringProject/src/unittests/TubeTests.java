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
public class TubeTests {

	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		
		Tube t=new Tube(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1);
		//base case
		assertEquals("Bad normal to Tube", new Vector(1, 0, 0), t.getNormal(new Point3D(1,0,0)));
		//more complicated
		//the intersection whit (3,2,0)vector came out form the center AXIS
		double x=Math.sqrt(13);
		Vector v=t.getNormal(new Point3D(0.83,0.55,0));
		assertEquals("Bad normal to Tube",new Vector(3d/x, 2d/x, 0),v);
		//coplicatest
		//interscetion whit the vector(1.41,1.41,0) came out for z=1
		Tube t1=new Tube(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),2);
		Vector v1=t1.getNormal(new Point3D(1.66,1.11,0));
		assertEquals("Bad normal to Tube",new Vector(3d/x, 2d/x, 0),v1);
		/*
//		//MORE
//		//interscetion whit the vector(1.41,1.41,0) came out for z=1
//		Vector v2=t1.getNormal(new Point3D(1.41,1.41,1));
//		double y=0.7071067811;
//		chackIfEcual(new Vector(y,y,0),v2);
//	}
//	public static void chackIfEcual(Vector v1,Vector v2) {
//		double x1= v1.getHead().getX();
//		double y1= v1.getHead().getY();
//		double z1= v1.getHead().getZ();
//		double x2= v2.getHead().getX();
//		double y2= v2.getHead().getY();
//		double z2= v2.getHead().getZ();
//		
//		assertEquals("Bad normal to Tube", x1,x2,0.01);
//		assertEquals("Bad normal to Tube", y1,y2,0.01);
//		assertEquals("Bad normal to Tube", z1,z1,0.01);
//	*/}

}

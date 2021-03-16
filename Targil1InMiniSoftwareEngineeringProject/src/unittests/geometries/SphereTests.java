/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import geometries.*;
import primitives.*;

/**
 * @author yosefHaim
 *
 */
public class SphereTests {
	/**
     * Test method for
     * {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
	@Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test 
		Sphere s=new Sphere(new Point3D(0,0,1),1);
		Vector v1 = s.getNormal(new Point3D(0,0,0));
		//The expected normal is (0,0,-1)
		assertEquals("The Sphere normal calculation is incorrect1",new Vector(0,0,-1),v1);
		//TC02: more complicity this a test whit the vector (0,4,-1) come out form the center of the Sphere
		Vector v2 = s.getNormal(new Point3D(0,0.96,0.76));
		double x= Math.sqrt(17);
		assertEquals("The Sphere normal calculation is incorrect2",new Vector(0,4d/x,-1d/x),v2);

	}
	
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
	/*
    @Test
    public void testFindIntersections() {
    	
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
        Sphere sphere1=new Sphere(new Point3D(2,0,0), 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                        sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);
        // TC03: Ray starts inside the sphere (1 point)
        List<Point3D> result0=sphere1.findIntersections(new Ray(new Point3D(1.5, 0, 0.5),new Vector(-1,0,-1)));
        assertEquals("Wrong number of points", 1, result0.size());
        assertEquals("Ray starts inside sphere", List.of(new Point3D(1,0,0)), result0);
        // TC04: Ray starts after the sphere (0 points)
        List<Point3D> result1=sphere.findIntersections(new Ray(new Point3D(-2, 0, 0),new Vector(-1,0,0)));
        assertEquals("Ray starts after sphere", null, result1);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        List<Point3D> result2=sphere.findIntersections(new Ray(new Point3D(1, 0, 1),new Vector(-1,0,-1)));
        assertEquals("Ray starts after sphere", List.of(new Point3D(0, 0, 0)), result2);
        // TC12: Ray starts at sphere and goes outside (0 points)
        List<Point3D> result3=sphere.findIntersections(new Ray(new Point3D(1, 0, 1),new Vector(-1,0,0.5)));
        assertEquals("Ray starts after sphere", null, result3);
        
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        List<Point3D> result4=sphere1.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(new Point3D(3, 0, 0))));
        assertEquals("Wrong number of points", 2, result4.size());
        if (result.get(0).getX() > result.get(1).getX())
            result4 = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(new Point3D(1,0,0) ,new Point3D(3, 0, 0)), result4);
        // TC14: Ray starts at sphere and goes inside (1 points)
        List<Point3D> result5=sphere1.findIntersections(new Ray(new Point3D(1, 0, 0),new Vector(3,0,0)));
        assertEquals("Ray starts after sphere", List.of(new Point3D(3, 0, 0)), result5);
        // TC15: Ray starts inside (1 points)
        List<Point3D> result6=sphere1.findIntersections(new Ray(new Point3D(1.5, 0, 0),new Vector(3,0,0)));
        assertEquals("Ray starts at the center", List.of(new Point3D(3, 0, 0)), result6);
        // TC16: Ray starts at the center (1 points)
        List<Point3D> result7=sphere1.findIntersections(new Ray(new Point3D(2, 0, 0),new Vector(3,0,0)));
        assertEquals("Ray starts at the center", List.of(new Point3D(3, 0, 0)), result7);
        // TC17: Ray starts at sphere and goes outside (0 points)
        List<Point3D> result8=sphere1.findIntersections(new Ray(new Point3D(3, 0, 0),new Vector(1,0,0)));
        assertEquals("Ray starts at the center", null, result8);
        // TC18: Ray starts after sphere (0 points)
        List<Point3D> result9=sphere1.findIntersections(new Ray(new Point3D(3.5, 0, 0),new Vector(1,0,0)));
        assertEquals("Ray starts at the center", null, result9);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        List<Point3D> result10=sphere.findIntersections(new Ray(new Point3D(0, 0, 0.5),new Vector(0,0,1)));
        assertEquals("Ray starts at the center", null, result10);
        // TC20: Ray starts at the tangent point
        List<Point3D> result11=sphere.findIntersections(new Ray(new Point3D(0, 0, 0),new Vector(1,0,0)));
        assertEquals("Ray starts at the center", null, result11);
        // TC21: Ray starts after the tangent point
        List<Point3D> result12=sphere.findIntersections(new Ray(new Point3D(1, 0, 0),new Vector(1,0,0)));
        assertEquals("Ray starts at the center", null, result12);
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        List<Point3D> result13=sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(1,0,0)));
        assertEquals("Ray starts at the center", null, result13);
    }

	*/

}

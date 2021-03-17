package geometries;

import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Sphere = ball
 * 
 * @author yosefHaim
 *
 */
public class Sphere implements Geometry {
	private Point3D center;
	private double radius;

	/**
	 * ctor of Sphere The center = Point3D center
	 * 
	 * @param cen Point3D of center of Sphere
	 * @param red double number
	 */
	public Sphere(Point3D cen, double red) {
		this.center = cen;
		this.radius = red;
	}

	@Override
	public Vector getNormal(Point3D p) {
		/**
		 * n=normalize(P-Center)
		 */
		return p.subtract(center).normalize();
	}

	/**
	 * getter
	 * 
	 * @return Point3D the center of the Sphere
	 */
	public Point3D getCenter() {
		return this.center;
	}

	/**
	 * getter
	 * 
	 * @return double Radius of the Sphere
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return this.center.toString();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		double tm,d;
		Vector u;
		try{
			u = center.subtract(ray.getP0());
			tm = ray.getDir().dotProduct(u);
			d = alignZero(Math.sqrt(u.length() * u.length() - (tm * tm)));
		}catch (Exception e) {
			tm=0;
			d = alignZero(Math.sqrt(tm * tm));
		}
		if (d >= radius)
			return null;
		double th = Math.sqrt(radius * radius - d * d);
		if (alignZero(tm - th) <= 0 && alignZero(tm + th) <= 0) {
			return null;
		}
		//i konw ther are point  i craet list
		List<Point3D> arr= new LinkedList<>();
		
		if(alignZero(tm - th) > 0)
			arr.add(ray.getP0().add(ray.getDir().scale(tm - th)));
		if(alignZero(tm + th) > 0)
			arr.add(ray.getP0().add(ray.getDir().scale(tm + th)));

		return arr;
		

	}

}
/*
 * Vector u; if(ray.chackIfPointInRay(center))
 * 
 * if(center.equals(ray.getP0())) returnS
 * List.of(ray.getDir().scale(radius).getHead());
 * u=center.subtract(ray.getP0()); double tm=ray.getDir().dotProduct(u);
 * if(u.length()<radius&&radius!=u.scale(tm).length()) {} double
 * d=Math.sqrt((u.length()*u.length())-(tm*tm)); if(d>=radius) return null;
 * double th=Math.sqrt((radius*radius)-(d*d)); double t1=tm+th; double t2=tm-th;
 * if(t1<=0&&t2<=0) return null; List<Point3D> arr = new ArrayList<Point3D>();
 * if(t1>0) arr.add(ray.getP0().add(ray.getDir().scale(t1))); if(t2>0)
 * arr.add(ray.getP0().add(ray.getDir().scale(t2))); return arr;
 */

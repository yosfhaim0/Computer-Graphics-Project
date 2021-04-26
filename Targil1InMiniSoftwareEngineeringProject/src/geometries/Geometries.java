/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * Geometries represent collection of Geometries shape
 * 
 * @author yosefHaim amrusi
 *
 */
public class Geometries implements Intersectable {
	/**
	 * list of geometries like sphere, triangle, tube, plane,polygon, cylinder
	 */
	private List<Intersectable> geometries = new LinkedList<>();

	/**
	 * Default ctor
	 * 
	 */
	public Geometries() {
	}

	/**
	 * ctor with list
	 * 
	 * @param geometries shape are implement interface Intersectable
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * adding list
	 * 
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable i : geometries)
			this.geometries.add(i);
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// linked because you don't now how much der is...
		if(ray==null)return null;
		List<Point3D> resultList = null;
		for (Intersectable i : geometries) {
			List<Point3D> arr = i.findIntersections(ray);
			if (arr != null)
				if (resultList == null)
					resultList = new LinkedList<>(arr);
				else
					resultList.addAll(arr);
		}
		return resultList;
	}

}

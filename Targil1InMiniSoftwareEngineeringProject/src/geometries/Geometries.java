/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * Geometries represent collection of Geometries shape
 * 
 * @author yosefHaim
 *
 */
public class Geometries implements Intersectable {
	/**
	 * list of geometries like sphere, triangle, tube, plane,polygon, cylinder
	 */
	private List<Intersectable> geometries;

	/**
	 * Default ctor
	 * 
	 */
	public Geometries() {
		super();
		/**
		 * Initializing the list to array list i wont the search to be O(1)...
		 */
		this.geometries = new ArrayList<>();
	}

	/**
	 * ctor whit list
	 * 
	 * @param geometries shape are implement interface Intersectable
	 */
	public Geometries(Intersectable... geometries) {
		super();
		this.geometries = new ArrayList<>();// i dont sure about this line
		for (Intersectable i : geometries) {
			this.geometries.add(i);
		}
	}

	/**
	 * adding list
	 * 
	 * @param geometries
	 */
	public void Add(Intersectable... geometries) {
		for (Intersectable i : geometries) {
			// i chack if i are exsist
			if (!this.geometries.contains(i))
				this.geometries.add(i);
		}
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// linked because you don't now how much der is...
		List<Point3D> resultList = new LinkedList<>();
		for (Intersectable i : geometries) {
			List<Point3D> arr = i.findIntersections(ray);
			if (arr != null)
				resultList.addAll(arr);
		}
		return resultList;
	}

}

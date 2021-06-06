/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * Geometries represent collection of Geometries shape
 * 
 * @author yosefHaim
 *
 */
public class Geometries extends Intersectable {
	/**
	 * list of geometries like sphere, triangle, tube, plane,polygon, cylinder
	 */
	private List<Intersectable> geometries = new LinkedList<Intersectable>();

	/**
	 * Default ctor for geometries
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
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		// linked because you don't now how much der is...
		if (ray == null)
			return null;
		List<GeoPoint> resultList = null;
		for (Intersectable i : geometries) {
			List<GeoPoint> arr = i.findIntsersectionsBound(ray, maxDistance);
			if (arr != null)
				if (resultList == null)
					resultList = new LinkedList<>(arr);
				else
					resultList.addAll(arr);
		}
		return resultList;
	}

	@Override
	protected void setBox() {
		minX = Double.MAX_VALUE;
		minY = Double.MAX_VALUE;
		minZ = Double.MAX_VALUE;
		maxX = Double.MIN_VALUE;
		maxY = Double.MIN_VALUE;
		maxZ = Double.MIN_VALUE;
		for (Intersectable geo : geometries) {
			geo.createBox();
			if (geo.minX < minX)
				minX = geo.minX;
			if (geo.maxX > maxX)
				maxX = geo.maxX;
			if (geo.minY < minY)
				minY = geo.minY;
			if (geo.maxY > maxY)
				maxY = geo.maxY;
			if (geo.minZ < minZ)
				minZ = geo.minZ;
			if (geo.maxZ > maxZ)
				maxZ = geo.maxZ;

		}
		middlePoint = getMiddlePoint();
	}

	/**
	 * the function is calling the makeTree function after taking out all infinity
	 * shapes to a separate list after the function makeTree was called and return
	 * the binary tree list we add the infinity shape list to the head of the binary
	 * list so _shapes as all geometries in one list.
	 */
	public void callMakeTree() {
		LinkedList<Intersectable> shapesWhitOutBox = null;
		for (int i = 0; i < geometries.size(); ++i) {
			if (!geometries.get(i).finity) {
				if (shapesWhitOutBox == null)
					shapesWhitOutBox = new LinkedList<Intersectable>();
				shapesWhitOutBox.add(geometries.remove(i));
			}
		}
		geometries = makeTree(geometries);
		if (shapesWhitOutBox != null)
			geometries.addAll(0, shapesWhitOutBox);
	}

	/**
	 * update box size after every new geometry we add to geometries list.
	 */
	protected void updateBoxSize(Intersectable a, Intersectable b) {
		finity = true;
		minX = Double.MAX_VALUE;
		minY = Double.MAX_VALUE;
		minZ = Double.MAX_VALUE;
		maxX = Double.MIN_VALUE;
		maxY = Double.MIN_VALUE;
		maxZ = Double.MIN_VALUE;
		minX = Math.min(a.minX, b.minX);
		minY = Math.min(a.minY, b.minY);
		minZ = Math.min(a.minZ, b.minZ);
		maxX = Math.max(a.maxX, b.maxX);
		maxY = Math.max(a.maxY, b.maxY);
		maxZ = Math.max(a.maxZ, b.maxZ);
		middlePoint = getMiddlePoint();
	}

	/**
	 * the function is making pears of two closes geometries until the list is empty
	 * the function calls itself until the list contains one geometry node
	 *
	 * @param geometries2 the list of finite shapes
	 * @return a list of shapes in a binary way
	 */
	List<Intersectable> makeTree(List<Intersectable> geometries2) {
		if (geometries2.size() == 1)
			return geometries2;
		LinkedList<Intersectable> _newShapes = null;
		while (!geometries2.isEmpty()) {
			Intersectable first = geometries2.remove(0), nextTo = geometries2.get(0);
			double minD = first.middlePoint.distance(nextTo.middlePoint);
			int min = 0;
			for (int i = 1; i < geometries2.size(); ++i) {
				if (minD == 0)
					break;
				double temp = first.middlePoint.distance(geometries2.get(i).middlePoint);
				if (temp < minD) {
					minD = temp;
					nextTo = geometries2.get(i);
					min = i;
				}
			}
			if (_newShapes == null)
				_newShapes = new LinkedList<Intersectable>();
			geometries2.remove(min);
			Geometries newGeo = new Geometries(first, nextTo);
			newGeo.updateBoxSize(first, nextTo);
			_newShapes.add(newGeo);
			if (geometries2.size() == 1)
				_newShapes.add(geometries2.remove(0));
		}
		return makeTree(_newShapes);
	}
}
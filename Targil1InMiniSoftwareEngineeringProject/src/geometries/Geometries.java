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
	protected void CreateBoundingBox() {
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
		middleBoxPoint = getMiddlePoint();
	}

	/**
	 * the function is calling the makeTree function after taking out all infinity
	 * shapes to a separate list.<br>
	 * after the function createGeometriesTreeRecursion was called and return the
	 * binary tree list we add the infinity shape list to the head of the binary
	 * tree.<br>
	 * so geometries as all geometries in one binary tree.
	 */
	public void createGeometriesTree() {
		List<Intersectable> shapesWithoutBox = null;
		List<Intersectable> shapesWithBox = null;
		for (var i : geometries) {
			if (i.finityShape) {
				if (shapesWithBox == null)
					shapesWithBox = new LinkedList<Intersectable>();
				shapesWithBox.add(i);
			} else {
				if (shapesWithoutBox == null)
					shapesWithoutBox = new LinkedList<Intersectable>();
				shapesWithoutBox.add(i);
			}
		}
		if (shapesWithBox == null)
			geometries = shapesWithoutBox;
		else {
			geometries = createGeometriesTreeRecursion(shapesWithBox);
			if (shapesWithoutBox != null)
				geometries.addAll(0, shapesWithoutBox);
		}
	}

	/**
	 * update box size after every new geometry we add to geometries list. create
	 * the box that include both bodies
	 */
	protected void updateBoxSize(Intersectable a, Intersectable b) {
		finityShape = true;
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
		middleBoxPoint = getMiddlePoint();
	}

	/**
	 * The function calculates the list that represents a binary tree of boxes
	 * within boxes...<br>
	 * inside each box there are two closely related shapes,<br>
	 * used to accelerate the ray tracer,<br>
	 * in case a ray does not cut the box all the boxes inside it are probably not
	 * cut.
	 *
	 * @param finiteShapes the list of finite Shapes
	 * @return a list of shapes present tree of geometries
	 */
	List<Intersectable> createGeometriesTreeRecursion(List<Intersectable> finiteShapes) {
		if (finiteShapes.size() == 1)
			return finiteShapes;
		LinkedList<Intersectable> _newShapes = null;
		while (!finiteShapes.isEmpty()) {
			Intersectable first = finiteShapes.remove(0), nextTo = finiteShapes.get(0);
			double minD = first.middleBoxPoint.distance(nextTo.middleBoxPoint);
			int min = 0;
			for (int i = 1; i < finiteShapes.size(); ++i) {
				if (minD == 0)
					break;
				double temp = first.middleBoxPoint.distance(finiteShapes.get(i).middleBoxPoint);
				if (temp < minD) {
					minD = temp;
					nextTo = finiteShapes.get(i);
					min = i;
				}
			}
			if (_newShapes == null)
				_newShapes = new LinkedList<Intersectable>();
			finiteShapes.remove(min);
			Geometries newGeo = new Geometries(first, nextTo);
			newGeo.updateBoxSize(first, nextTo);
			_newShapes.add(newGeo);
			if (finiteShapes.size() == 1)
				_newShapes.add(finiteShapes.remove(0));
		}
		return createGeometriesTreeRecursion(_newShapes);
	}
}
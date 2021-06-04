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
    private List<Intersectable> geometries = new LinkedList<>();

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
	if (ray == null) return null;
	List<GeoPoint> resultList = null;
	for (Intersectable i : geometries) {
	    List<GeoPoint> arr = i.findGeoIntersections(ray, maxDistance);
	    if (arr != null) if (resultList == null)
		resultList = new LinkedList<>(arr);
	    else
		resultList.addAll(arr);
	}
	return resultList;
    }

    @Override
    protected void setBox() {
	double tmpMinX = Double.MAX_VALUE;
	double tmpMinY = Double.MAX_VALUE;
	double tmpMinZ = Double.MAX_VALUE;

	double tmpMaxX = Double.MIN_VALUE;
	double tmpMaxY = Double.MIN_VALUE;
	double tmpMaxZ = Double.MIN_VALUE;

	for (Intersectable bodies : geometries) {
	    if (tmpMinX > bodies.minX) tmpMinX = bodies.minX; //
	    if (tmpMinY > bodies.minY) tmpMinY = bodies.minY; //
	    if (tmpMinZ > bodies.minZ) tmpMinZ = bodies.minZ; //

	    if (tmpMaxX < bodies.maxX) tmpMaxX = bodies.maxX; //
	    if (tmpMaxY < bodies.maxY) tmpMaxY = bodies.maxY; //
	    if (tmpMaxZ < bodies.maxZ) tmpMaxZ = bodies.maxZ; //
	}

	maxX = tmpMaxX;
	maxY = tmpMaxY;
	maxZ = tmpMaxZ;
	minX = tmpMinX;
	minY = tmpMinY;
	minZ = tmpMinZ;

	middlePoint = getMiddlePoint();
    }
}
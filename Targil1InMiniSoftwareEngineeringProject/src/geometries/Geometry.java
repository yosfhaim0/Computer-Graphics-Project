package geometries;

import primitives.*;

/**
 * interface for Geometry Shapes
 * 
 * @author yosefHaim
 *
 */
public abstract class Geometry extends Intersectable {

	// ********** fields *********//
	/**
	 * default color of geometry shape is black
	 */
	protected Color emission = Color.BLACK;
	/**
	 * default material coefficients are 0
	 */
	private Material material = new Material();
	// ********** end fields *********//

	/**
	 * getter for material
	 * 
	 * @return the material of the body
	 */
	public Material getMaterial() {
		return this.material;
	}

	/**
	 * setter for material component of Geometry
	 * 
	 * @param materialToSet
	 * @return this (Geometry) - builder pattern
	 */
	public Geometry setMaterial(Material materialToSet) {
		this.material = materialToSet;
		return this;
	}

	/**
	 * getter for emission color
	 * 
	 * @return the emission color of the geometry
	 */
	public Color getEmission() {
		return this.emission;
	}

	/**
	 * setter for emission color
	 * 
	 * @param _emission color for replace the current emission
	 * @return this (geometry)
	 */
	public Geometry setEmission(Color _emission) {
		this.emission = _emission;
		return this;
	}

	/**
	 * The function is triggered by a geometric shape and returns normal from the
	 * point it receives (we assume that the point is on the shape)
	 * 
	 * @param p point from which the normal came out
	 * @return orthogonal unit vector
	 */
	public abstract Vector getNormal(Point3D p);
}

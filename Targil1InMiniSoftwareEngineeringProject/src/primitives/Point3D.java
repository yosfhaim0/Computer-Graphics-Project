package primitives;
/**
 * A point in three-dimensional space of this shape: (x,y,z)
 * @author yosefHaim
 *
 */
public class Point3D {
	final private Coordinate x,y,z;
	/**
	 * ZERO=(0,0,0)
	 */
	public static Point3D ZERO=new Point3D(0,0,0);
	/**
	 * point Represented bay three coordinate
	 * in 3D world
	 * @param x Axis
	 * @param y Axis 
	 * @param z Axis
	 */
	public Point3D(Coordinate x,Coordinate y,Coordinate z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	/**
	 * point contractor whit 3 double number
	 * this number are Are replaced to coordinate
	 * 3D world
	 * @param x Axis
	 * @param y Axis
	 * @param z Axis
	 */
	public Point3D(double x,double y,double z){
		this.x=new Coordinate(x);
		this.y=new Coordinate(y);
		this.z=new Coordinate(z);
	}
	/**
	 * 
	 * @return double number*
	 */
	public double getX() {return this.x.coord;}
	/**
	 * 
	 * @return double number*
	 */
	public double getY() {return this.y.coord;}
	/**
	 * 
	 * @return double number*
	 */
	public double getZ() {return this.z.coord;}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (!(obj instanceof Point3D)) return false;
	    Point3D other = (Point3D)obj;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
	 }
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	/**
	 *  subtract((x1,y1,z1),(x2,y2,z2))=(x1-x2,y1-y2,z1-z2)
	 * @param p the head of the vector for subtract
	 * @return vector (this point less given point)
	 */
	public Vector subtract(Point3D p) {
		return new Vector(this.x.coord-p.x.coord,
						  this.y.coord-p.y.coord,
						  this.z.coord-p.z.coord);
	}
	/**
	 * add((x1,y1,z1),(x2,y2,z2))=(x1+x2,y1+y2,z1+z2)
	 * @param v vector new Add to existing vector
	 * @return new point the sum of the two point 3D 
	 */
	public Point3D add(Vector v) {
		return new Point3D(this.x.coord+v.getHead().x.coord,
				this.y.coord+v.getHead().y.coord,
				this.z.coord+v.getHead().z.coord);
		
	}
	/**
	 * The distance between two points squares
	 * distanceSquared((x1,y1,z1)(x2,y2,z2))=(x1-x2)^2+(y1-y2)^2+(z1-z2)^2
	 * @param other 
	 * @return double
	 */
	public double distanceSquared(Point3D other) {
		return (this.x.coord-other.x.coord)*(this.x.coord-other.x.coord)+
				(this.y.coord-other.y.coord)*(this.y.coord-other.y.coord)+
				(this.z.coord-other.z.coord)*(this.z.coord-other.z.coord);			
	}
	/**
	 * Distance between 2 points
	 * Uses the previous function named: double distanceSquared(Point3D)
	 * distance(x,y,z)=sqrt(x^2+y^2+z^2)
	 * @param other Point3D
	 * @return double
	 */
	public double distance(Point3D other) {
		return Math.sqrt(this.distanceSquared(other));
	}
	 
}

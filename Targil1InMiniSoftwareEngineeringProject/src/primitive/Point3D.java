package primitive;

public class Point3D {
	final private Coordinate x,y,z;
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
	/*
	 * return double number*
	 */
	public double getX() {return this.x.coord;}
	/*
	 * return double number*
	 */
	public double getY() {return this.y.coord;}
	/*
	 * return double number*
	 */
	public double getZ() {return this.z.coord;}

	@Override
	 public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        Point3D that = (Point3D) o;
        return x.equals(that.x) && y.equals(that.y) && z.equals(that.z);
    }
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	/**
	 * 
	 * @param p the head of the vector for subtract
	 * @return vector (this point less given point)
	 */
	public Vector subtract(Point3D p) {
		return new Vector(this.x.coord-p.x.coord,
						  this.y.coord-p.y.coord,
						  this.z.coord-p.z.coord);
	}
	/**
	 * 
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
	 * @param other 
	 * @return double
	 */
	public double distanceSquared(Point3D other) {
		return (this.x.coord-other.x.coord)*(this.x.coord-other.x.coord)+
				(this.y.coord-other.y.coord)*(this.y.coord-other.y.coord)+
				(this.z.coord-other.y.coord)*(this.z.coord-other.y.coord);			
	}
	/**
	 * Distance between 2 points
	 * Uses the previous function named: double distanceSquared(Point3D)
	 * @param other Point3D
	 * @return distance
	 */
	public double distance(Point3D other) {
		return Math.sqrt(this.distanceSquared(other));
	}
	 
}

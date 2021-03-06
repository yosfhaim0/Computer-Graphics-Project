package primitive;
/**
 * Vector from the beginning of the axes to a certain point
 * Builders : a) three coordinates, b) three double-digit numbers, c) a Point3D
.  Copy builder not exist!!!
 * @author yosefHaim
 *
 */
public class Vector {
Point3D head;
/**
 * Vector can be represented by three coordinate 
 * If you try to build a vector 0 then it throws an exception
 * @param x 
 * @param y
 * @param z
 */
public Vector(Coordinate x,Coordinate y,Coordinate z){
	if(x.equals(y)&&y.equals(z)&&z.equals(new Coordinate(0)))//need factory!!!!!!
		throw new RuntimeException("You tried to build vector 0 it is invalid!");
	this.head=new Point3D(x,y,z);
}
/**
 * Builder of three numbers
Uses the constructor of this point 
public Point3D(Coordinate x,Coordinate y,Coordinate z),
If you try to build a vector 0 then it throws an exception
 * @param x
 * @param y
 * @param z
 */
public Vector(double x,double y,double z){
	if((x==y)&&(y==z)&&(z==0))//need factory!!!!!!
		throw new RuntimeException("You tried to build vector 0 it is invalid!");
	this.head=new Point3D(x,y,z);
}
/**
 * Constructor of vector by Point3D,
 * If you try to build a vector 0 then it throws an exception
 * @param p
 */
public Vector(Point3D p){
	if(p==Point3D.ZERO)
		throw new RuntimeException("You tried to build vector 0 it is invalid!");
	this.head=new Point3D(p.x,p.y,p.z);
}
/**
 * 
 * @return the head of the vector
 */
public Point3D getHead() {return this.head;}
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    //if (!super.equals(o)) return false;
    Vector that = (Vector) o;
    return head.equals(that.head) ;
}
@Override
public String toString() {
	return head.toString();
}
/**
 * Subtraction of vectors

Vector multiplication
 * @param v
 * @return
 */
public Vector subtract(Vector v) {
	return new Vector(head.subtract(v.head).head);
}
/**
 * Connecting vectors
 * @param v
 * @return
 */
public Vector add(Vector v) {
	return new Vector(head.add(v));
}
/**
 * Scalar multiplication
 * @param scale
 * @return
 */
public Vector scale(double scale) {
	return new Vector(this.head.x.coord*scale,
					  this.head.y.coord*scale,
					  this.head.z.coord*scale);	
}
/**
 * Returns a Scalar that is a combination of the following operation:
Multiplies the first component of the first vector by the first component of the second vector,
 multiplies the second component by the first vector by the second component by the second vector, 
 multiplies the third component by the first vector by the third component by the second vector
 * @param v
 * @return
 */
public double dotProduct(Vector v) {
	return  this.head.x.coord*v.head.x.coord+
			this.head.y.coord*v.head.y.coord+
			this.head.z.coord*v.head.z.coord;
}
/**
 * Vector returns, which 
 * are the result of a vector product,
 * @param v
 * @return
 */
public Vector crossProduct(Vector v) {
	double x1,x2,y1,y2,z1,z2;
	x1=this.head.x.coord;
	y1=this.head.y.coord;
	z1=this.head.z.coord;
	x2=v.head.x.coord;
	y2=v.head.y.coord;
	z2=v.head.z.coord;
	return new Vector(y1*z2-z1*y2,
					  z1*x2-x1*z2,
					  x1*y2-y1*x2);
}
/**
 * The distance between two points(the to head of the vector) squares
 * Uses the Point3D function named:double distanceSquared(Point3D)
 * @return
 */
public double lengthSquared() {
	return this.head.distanceSquared(Point3D.ZERO);
}
/**
 * he distance between two points(the to head of the vector)
 * Uses the Point3D function named:double distance(Point3D)
 * @return length of the vector
 */
public double length() {
	return this.head.distance(Point3D.ZERO);
}
/**
 * Each component of the vector is divided by its length
 * This action is the only one that changes the vector itself !!!
 * @return it returns the object itself (this)
 */
public Vector normalize() {
	double length=this.length();
	this.head=new Point3D(this.head.x.coord/length,
						  this.head.y.coord/length,
						  this.head.z.coord/length);
	return this;
	}
/**
 * the same like normalize()
 * but return a new vector
 * @return new vector
 */
public Vector normalized() {
	return new Vector(this.normalize().getHead());
}


}

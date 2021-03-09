package primitive;
/**
 * Vector from the beginning of the axes to a certain point
 * Builders : a) three coordinates, b) three double-digit numbers, c) a Point3D
.  Copy builder not exist!!!
 * @author yosefHaim
 *
 */
public class Vector {
	private Point3D head;
/**
 * Vector can be represented by three coordinate 
 * If you try to build a vector 0 then it throws an exception
 * @param x 
 * @param y
 * @param z
 * @throws When trying to build vector 0
 */
public Vector(Coordinate x,Coordinate y,Coordinate z){
	if(Point3D.ZERO.equals(new Point3D(x,y,z)))//mayby need factory!!!!!!
		throw new IllegalArgumentException("You tried to build vector 0 it is invalid!");
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
 * @throws When trying to build vector 0
 */
public Vector(double x,double y,double z){
	if(new Point3D(x,y,z).equals(Point3D.ZERO))//need factory!!!!!!
		throw new IllegalArgumentException("You tried to build vector 0 it is invalid!");
	this.head=new Point3D(x,y,z);
}
/**
 * Constructor of vector by Point3D,
 * If you try to build a vector 0 then it throws an exception
 * @param p
 */
public Vector(Point3D p){
	if(p.equals(Point3D.ZERO))//aloghet isnt worte in section 
		throw new IllegalArgumentException("You tried to build vector 0 it is invalid!");
	this.head=new Point3D(p.getX(),p.getY(),p.getZ());
}
/**
 * 
 * @return the head of the vector
 */
public Point3D getHead() {return this.head;}
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Vector)) return false;
    Vector other = (Vector)obj;
    return head.equals(other.head);
 }

@Override
public String toString() {
	return head.toString();
}
/**
 * Subtraction of vectors
 * Subtraction between two vectors, i.e. subtracting their heads, 
 * i.e. returning a new vector which is the subtraction between their two heads
 * @param v vector to subtract
 * @return new vector
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
 * scale(a(x,y,z))=(a*x,a*y,a*z)
 * @param scale
 * @return
 */
public Vector scale(double scale) {
	return new Vector(this.head.getX()*scale,
					  this.head.getY()*scale,
					  this.head.getZ()*scale);	
}
/**
 * Returns a Scalar that is a combination of the following operation:
 * Multiplies the first component of the first vector by the first component of the second vector,
 * multiplies the second component by the first vector by the second component by the second vector, 
 * multiplies the third component by the first vector by the third component by the second vector
 * dotProduct((x1,y1,z1),(x2,y2,z2))=x1*x2+y1*y2+z1*z2
 * @param v Vector
 * @return double
 */
public double dotProduct(Vector v) {
	return  this.head.getX()*v.head.getX()+
			this.head.getY()*v.head.getY()+
			this.head.getZ()*v.head.getZ();
}
/**
 * returns Vector , which 
 * are the result of a vector product
 * crossProduct((x1,y1,z1),(x2,y2,z2))=
 *    y1*z2-z1*y2
 *	-[x1*z2-z1*x2]
 *    x1*y2-y1*x2
 * @param v
 * @return
 */
public Vector crossProduct(Vector v) {
	double x1,x2,y1,y2,z1,z2;
	x1=this.head.getX();
	y1=this.head.getY();
	z1=this.head.getZ();
	x2=v.head.getX();
	y2=v.head.getY();
	z2=v.head.getZ();
	
	return new Vector(y1*z2-z1*y2,
					  z1*x2-x1*z2,
					  x1*y2-y1*x2);
}
/**
 * The distance between two points(the to head of the vector) squares
 * Uses the Point3D function named:double distanceSquared(Point3D)
 * @return double
 */
public double lengthSquared() {
	return this.head.distanceSquared(Point3D.ZERO);
}
/**
 * he distance between two points(the to head of the vector)
 * Uses the Point3D function named:double distance(Point3D)
 * @return double length of the vector
 */
public double length() {
	return this.head.distance(Point3D.ZERO);
}
/**
 * Each component of the vector is divided by its length
 * This action is the only one that changes the vector itself !!!
 * normalize(Size(x,y,z))=(a/Size,a/Size,a/Size)
 * @return it returns the object itself (this)
 */
public Vector normalize() {
	double length=this.length();
	this.head=new Point3D(this.head.getX()/length,
						  this.head.getY()/length,
						  this.head.getZ()/length);
	return this;
	}
/**
 * the same like normalize()
 * but return a new vector
 * @return Vector(new)
 */
public Vector normalized() {
	return new Vector(this.normalize().getHead());
}


}

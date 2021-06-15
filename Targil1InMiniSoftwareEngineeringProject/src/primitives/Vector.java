package primitives;


/**
 * A vector is an object with size and direction Vector from the beginning of
 * the axes to a certain point Builders : a) three coordinates, b) three
 * double-digit numbers, c) a Point3D .* Copy builder not exist!!!
 * 
 * @author yosefHaim
 *
 */
public class Vector {
	Point3D head;

	/**
	 * Builder of three numbers Uses the constructor of this point public
	 * Point3D(Coordinate x,Coordinate y,Coordinate z), If you try to build a vector
	 * 0 then it throws an exception
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @throws When trying to build vector 0
	 */
	public Vector(double x, double y, double z) {
		this(new Point3D(x, y, z));
	}

	/**
	 * Constructor of vector by Point3D, If you try to build a vector 0 then it
	 * throws an exception
	 * 
	 * @param p
	 */
	public Vector(Point3D p) {
		if (p.equals(Point3D.ZERO))// aloghet isnt worte in section
			throw new IllegalArgumentException("You tried to build vector 0 it is invalid!");
		this.head = p;
	}

	/**
	 * Head point getter
	 * 
	 * @return the head of the vector
	 */
	public Point3D getHead() {
		return this.head;
	}
	
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return head.equals(other.head);
	}

	@Override
	public String toString() {
		return "o->" + head.toString();
	}

	/**
	 * Subtraction of vectors Subtraction between two vectors, i.e. subtracting
	 * their heads, i.e. returning a new vector which is the subtraction between
	 * their two heads subtract((x1,y1,z1),(x2,y2,z2))=(x1-x2,y1-y2,z1-z2)
	 * 
	 * @param v vector to subtract
	 * @return new vector
	 */
	public Vector subtract(Vector v) {
		return head.subtract(v.head);
	}

	/**
	 * Connecting vectors add((x1,y1,z1),(x2,y2,z2))=(x1+x2,y1+y2,z1+z2)
	 * 
	 * @param v vector
	 * @return new Vector
	 */
	public Vector add(Vector v) {
		return new Vector(head.add(v));
	}

	/**
	 * Scalar multiplication scale(a(x,y,z))=(a*x,a*y,a*z)
	 * 
	 * @param scale
	 * @return Vector
	 */
	public Vector scale(double scale) {
		return new Vector(this.head.x.coord * scale, //
				this.head.y.coord * scale, //
				this.head.z.coord * scale);
	}

	/**
	 * Returns a Scalar that is a combination of the following operation: Multiplies
	 * the first component of the first vector by the first component of the second
	 * vector, multiplies the second component by the first vector by the second
	 * component by the second vector, multiplies the third component by the first
	 * vector by the third component by the second vector
	 * dotProduct((x1,y1,z1),(x2,y2,z2))=x1*x2+y1*y2+z1*z2
	 * 
	 * @param v Vector
	 * @return double
	 */
	public double dotProduct(Vector v) {
		return this.head.x.coord * v.head.x.coord //
				+ this.head.y.coord * v.head.y.coord //
				+ this.head.z.coord * v.head.z.coord;
	}

	/**
	 * returns Vector , which are the result of a vector product
	 * crossProduct((x1,y1,z1),(x2,y2,z2))= y1*z2-z1*y2 -[x1*z2-z1*x2] x1*y2-y1*x2
	 * 
	 * @param v Vector
	 * @return new Vector
	 */
	public Vector crossProduct(Vector v) {
		double x1 = this.head.x.coord;
		double y1 = this.head.y.coord;
		double z1 = this.head.z.coord;
		double x2 = v.head.x.coord;
		double y2 = v.head.y.coord;
		double z2 = v.head.z.coord;

		return new Vector(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2);
	}

	/**
	 * The distance between two points(the to head of the vector) squares Uses the
	 * Point3D function named:double distanceSquared(Point3D)
	 * lengthSquared(x,y,z)=x^2+y^2+z^2
	 * 
	 * @return double
	 */
	public double lengthSquared() {
		return head.x.coord * head.x.coord + //
				head.y.coord * head.y.coord + //
				head.z.coord * head.z.coord;
	}

	/**
	 * he distance between two points(the to head of the vector) Uses the Point3D
	 * function named:double distance(Point3D) distance(x,y,z)=sqrt(x^2+y^2+z^2)
	 * 
	 * @return double length of the vector
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * Each component of the vector is divided by its length This action is the only
	 * one that changes the vector itself !!!
	 * normalize(x,y,z)=(a/SizeVec,a/SizeVec,a/SizeVec)
	 * 
	 * @return it returns the object itself (this)
	 */
	public Vector normalize() {
		double length = this.length();
		this.head = new Point3D(this.head.x.coord / length, //
				this.head.y.coord / length, //
				this.head.z.coord / length);
		return this;
	}

	/**
	 * the same like normalize() but return a new vector
	 * 
	 * @return Vector(new)
	 */
	public Vector normalized() {
		return new Vector(this.getHead()).normalize();
	}

	/**
	 * Rotating vector on axis
	 * 
	 * @param axis  vector axis rotated on him
	 * @param angle the angle to change by Degrees,for Rotate the vector
	 * @return vector rotated by the degree on the v(axis)<br>
	 *         unit vector
	 */
	public Vector rotatingVectorOnAxis(Vector axis, double angle) {
		// change to degrees
		double cosAngle = Math.cos((angle * Math.PI) / 180);
		double sinAngle = Math.sin((angle * Math.PI) / 180);
		// formula :
		// Vfinal = V * cos(angle) + (K x V) * sin(angle) + K * (K dot V) * (1 -
		// cos(angle))
		// vfinal = the vector we want to turn by tta degree
		// K= the vector Axis of rotation(vector how dont change)
		// V= vector are supusd to rotate and finely will changed
		boolean cosZero = Util.isZero(cosAngle);
		boolean sinZero = Util.isZero(sinAngle);
		Vector vFinal;
		if (cosZero) {
			try {
				vFinal = axis.crossProduct(this).scale(sinAngle);
			} catch (Exception e) {
				vFinal = this.scale(sinAngle);
			}

		} else {
			vFinal = this.scale(cosAngle);
			if (!sinZero) {
				vFinal = vFinal.add(axis.crossProduct(this).scale(sinAngle));
			}
		}
		return vFinal.normalize();
	}

	/**
	 * This function return a Vertical Vector to "this" vector <br>
	 * (this) most be normalized!!!
	 * 
	 * @return normal vector Vertical to this
	 */
	public Vector createOrthogonalVector() {
		double x = head.x.coord, y = head.y.coord, z = head.z.coord;
		switch (head.findAbsoluteMinimumCoordinate()) {
		case 'x': {
			return new Vector(0, -z, y).normalize();
		}
		case 'y': {
			return new Vector(-z, 0, x).normalize();
		}
		case 'z': {
			return new Vector(y, -x, 0).normalize();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + head.findAbsoluteMinimumCoordinate());
		}
	}

}
package primitive;

import static primitive.Util.isZero;

public class Ray {
Point3D p0;
Vector dir;
/**
 * (ray) - A fundamental object in geometry - 
 * the group of points on a straight line that are on one relative side
 *        To a given point on the line called the beginning of the ray. 
 *        Defined by point and direction (unit vector) ...
 * @param p point of the begin
 * @param v direction of the ray
 */
Ray(Point3D p,Vector v){
    if (isZero(v.length() - 1)) 
    	v.normalize();
	this.p0=new Point3D(p.x,p.y,p.z);
	this.dir=new Vector(v.normalized().getHead());
}
@Override
public String toString() {
	return "p0: "+this.p0.toString()+" dir: "+this.dir.toString()+"\n";
}

}

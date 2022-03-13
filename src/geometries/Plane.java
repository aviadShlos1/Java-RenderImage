/**
 * @author: Aviad Shlosberg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;


/**
 * Class Plane is 2D basic object in geometry which represented by two vectors which come from the same point and
 *  have different directions (together reflecting the basic 2D object.)
 *  contains a point in the plane and normal to the plane.
 */
public class Plane implements Geometry
{
    final Point q0;
    final Vector normal;

    /**
     *
     * * constructor for plane. receives 3 points.
     *
     * @param p1 - first point
     * @param p2 - second point
     * @param p3 - third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        normal = U.crossProduct(V).normalize();
    }

//c-tor which gets point and vector
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
        normal = normal.normalize();
    }
// getter
    public Vector getNormal() {return normal;}

// getNormal
    @Override
    public Vector getNormal(Point myPoint) {
        return normal;
    }
}

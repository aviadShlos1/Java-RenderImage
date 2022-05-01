/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * Class Plane is 2D basic object in geometry which represented by two vectors which come from the same point and
 *  have different directions (together reflecting the basic 2D object.)
 *  contains a point in the plane and normal to the plane.
 */
public class Plane extends Geometry {
    final Point q0;
    final Vector normal;

    /**
     * * constructor for plane. receives 3 points.
     *
     * @param p1 - first point
     * @param p2 - second point
     * @param p3 - third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;//reference point
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
    public Vector getNormal() {
        return normal;
    }

    // getNormal
    @Override
    public Vector getNormal(Point myPoint) {
        return normal;
    }

    /**
     * @param ray - ray that cross the geometry
     * @return list of intersection points that were found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Point Q0 = q0;
        Vector N = normal;

        double enumerator=N.dotProduct(Q0.subtract(P0));
        double denominator=N.dotProduct(v);
        double t = alignZero(enumerator/denominator);
        List<Point> intersectPoints=new LinkedList<>();
        if (t > 0){
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        return null;
    }

    /**
     * perform full comparison between a given object and this
     *
     * @param o - object
     * @return - whether the object equals to this or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return q0.equals(plane.q0) && normal.equals(plane.normal);
    }
}

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

    /**
     * c-tor
     * @param q0 the point
     * @param normal the vector
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
        normal = normal.normalize();
    }

    /**
     * Getter of normal
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Getter of normal
     * @param myPoint
     * @return the normal
     */
    @Override
    public Vector getNormal(Point myPoint) {
        return normal;
    }

    /**
     * @param ray - ray that cross the geometry
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     * @return list of intersection points that were found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance) {
        if (box != null && !box.IsRayHitBox(ray))
            return null;
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Point Q0 = q0;
        Vector N = normal;

        double enumerator=N.dotProduct(Q0.subtract(P0));
        double denominator=N.dotProduct(v);
        double t = alignZero(enumerator/denominator);
        List<Point> intersectPoints = new LinkedList<>();
        if ((t > 0) && alignZero(t- maxDistance) <=0 ) {  // ensure the points are inside the bound
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
    /**
     * create box for a plan
     */
    @Override
    public void setBox() {
        double pInfinite = Double.POSITIVE_INFINITY;
        double nInfinite = Double.NEGATIVE_INFINITY;
        //Initializing axis vectors
        Vector nX = new Vector(1, 0, 0);
        Vector nY = new Vector(0, 1, 0);
        Vector nZ = new Vector(0, 0, 1);

        //In case on of the axis is vertical to the plan,
        //so the max and min point of this axis is the q0 in the point
        if (nX.equals(normal) || nX.scale(-1).equals(normal)) {
            box = new Box(q0.getX(), pInfinite, pInfinite, q0.getX(), nInfinite, nInfinite);
        }
        else if (nY.equals(normal) || nY.scale(-1).equals(normal)) {
            box = new Box(pInfinite, q0.getY(), pInfinite, nInfinite, q0.getY(), nInfinite);
        }

        else if (nZ.equals(normal) || nZ.scale(-1).equals(normal)) {
            box = new Box(pInfinite, pInfinite, q0.getZ(), nInfinite, nInfinite, q0.getZ());
        }
        else
            box = new Box(pInfinite, pInfinite, pInfinite, nInfinite, nInfinite, nInfinite);
    }

}

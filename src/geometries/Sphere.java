/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class represents a geometrical object that is a three-dimensional analogue to a two-dimensional circle based on main point anda radius
 */
public class Sphere implements Geometry {
    Point centerPoint;
    double radius;

    /**
     *  Main Constructor for sphere, by entrance of center point and radius
     *
     * @param centerPoint - center of sphere
     * @param radius - radius of sphere
     */
    public Sphere(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    /**
     * Getter
     * @return the point
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * Getter
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * function to calculate the normal of the sphere
     *
     * @param myPoint pointing in the direction of the normal
     * @return the normal vector
     */
    @Override
    public Vector getNormal(Point myPoint) {
       return (myPoint.subtract(centerPoint)).normalize();

    }
    /**
     * @param ray         ray that cross the geometry
     * @return list of intersection points that were found
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point O = centerPoint;
        Point p0 = ray.getP0();
        double r = radius;
        Vector v = ray.getDir();
        Vector u = O.subtract(p0);

        double t_m = alignZero(v.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - (t_m*t_m)) );
        double t_h = alignZero(Math.sqrt( (radius*radius) - (d*d) ));

        // if d is equal to or bigger than r, there will be no intersections at all
        if (d >= r) {
            return null;
        }
        double t1 = alignZero(t_m + t_h);
        double t2 = alignZero(t_m - t_h);

        List <Point> intersectPoints = new LinkedList<>();

        // t must be positive
        if (t1 > 0) {
            intersectPoints.add(new Point(this, ray.getP0(t1) ));
        }
    }
}

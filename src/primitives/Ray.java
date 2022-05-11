/**
 * @author: Aviad Shlosberg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class presents the primitive "Ray" -
 * part of a line that has a fixed starting point but no end point. It can extend infinitely in one direction.
 */

public class Ray
{
    /**
     * @member _dir - the point the Ray points to from p0
     * @member _p0 - starting point of Ray
     * DELTA - Constant value defining by how much we need to move the ray's starting point
     */
    final private Point p0;
    final private Vector dir;
    private static final double DELTA = 0.1;

    /**
     * Constructor to initialize Ray based object with its point and vector
     *
     * @param p0 the point
     * @param dir the vector which will be normalized
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**
     * constructor for ray.
     * creates a new ray and moves its head in the
     * normal direction by the normal scaled by DELTA
     *
     * @param p0     - starting point
     * @param dir    - direction vector
     * @param normal - the normal defining the plane
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        this(p0, dir); // activate the current instance constructor

        // make sure the normal and the direction are not orthogonal
        double nv = alignZero(normal.dotProduct(dir));

        // if not orthogonal
        if (nv!=0) {
            // create new vector to help move the head of
            // the vector to the correct position
            Vector fixVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            // move the head of the vector in the right direction
            p0 = p0.add(fixVector);
        }
    }
    /**
     * Getters
     */
    public Point getP0() {
        return p0;
    }
    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) { // Function calculate - P = P0 + v * t
        return p0.add(dir.scale(t));
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray) obj;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * find the closest Point to Ray
     *
     * @param pointsList List of intersections point
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> pointsList) {
        double distance = Double.POSITIVE_INFINITY;
        Point nearPoint = null;

        if (pointsList == null) {
            return null;
        }

        for (Point p : pointsList) {
            double dis = p.distance(p0); // distance from the starting point of the ray
            if (dis < distance) {
                distance = dis;
                nearPoint = p;
            }
        }

        return nearPoint;
    }

    /**
     * find the closest GeoPoint to Ray
     *
     * @param intersections List of intersections point
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {

        double distance = Double.POSITIVE_INFINITY;

        GeoPoint nearPoint = null;

        if (intersections == null) {
            return null;
        }

        // distance => distanceSquared
        // no need to activate the Math.sqrt function
        // distance is always a positive value,

        for (GeoPoint p : intersections) {
            double dis = p.point.distanceSquared(p0); // distance from the starting point of the ray
            if (dis < distance) {
                distance = dis;
                nearPoint = p;
            }
        }
        return nearPoint;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }



}

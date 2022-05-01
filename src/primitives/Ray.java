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
/**
 * This class presents the primitive "Ray" -
 * part of a line that has a fixed starting point but no end point. It can extend infinitely in one direction.
 */

public class Ray
{
    final private Point p0;
    final private Vector dir;

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
     * Getters
     * @return
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
     * @param points3DList List of intersections point
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> points3DList) {
        double distance = Double.POSITIVE_INFINITY;
        Point nearPoint = null;

        if (points3DList == null) {
            return null;
        }

        for (Point p : points3DList) {
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
     * @param GeoPointList List of intersections point
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> GeoPointList) {

        double distance = Double.POSITIVE_INFINITY;

        GeoPoint nearPoint = null;

        if (GeoPointList == null) {
            return null;
        }

        // distance => distanceSquared
        // no need to activate the Math.sqrt function
        // distance is always a positive value,
        for (GeoPoint p : GeoPointList) {
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

/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;
import primitives.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Interface for Composite Design Pattern The Composite Class - Geometries The
 * Basic Classes - all the specific geometries
 */
public abstract class Intersectable {

    /**
     * PDS
     * in order to find the correct color in a point, we need to
     * take into consideration the shape which the light is bouncing from
     */

    /** Helper class representing a point on a geometry surface
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /** Constructor to initialize both fields of this helper class
         *
         * @param geo geometry (basic)
         * @param p   point on the surface of the geometry
         */
        public GeoPoint(Geometry geo, Point p) {
            geometry = geo;
            point = p;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (this == obj)
                return true;
            if (!(obj instanceof GeoPoint))
                return false;
            GeoPoint other = (GeoPoint) obj;
            return this.geometry == other.geometry && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return "" + geometry + ": " + point;
        }

    }

    public Box box;

    /**
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    public List<Point> findIntersections(Ray ray) {
            List<GeoPoint> geoList = findGeoIntersections(ray);
            return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    /**
     * @param ray ray that cross the geometry
     * @param maxDistance - the upper bound of distance, any point which
     *                      its distance is greater than this bound will not be returned
     * @return list of intersection points that were found and have valid distance value
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }
    /**
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    protected abstract List<GeoPoint>findGeoIntersectionsHelper(Ray ray, double maxDistance);
    /**
     * Abstract method for creating box for each geometry
     */
    public abstract void setBox();
}

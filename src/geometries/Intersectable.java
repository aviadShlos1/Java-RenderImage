/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;
import primitives.*;
import java.util.List;

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




    /**
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    public abstract List<Point> findIntersections(Ray ray);


    /**
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}

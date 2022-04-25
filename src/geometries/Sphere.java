/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;
import primitives.*;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.alignZero;

/**
 * Sphere class represents a geometrical object that is a three-dimensional analogue to a two-dimensional circle based on main point anda radius
 */
public class Sphere extends Geometry {
    Point centerPoint;
    double radius;

    /**
     * Main Constructor for sphere, by entrance of center point and radius
     *
     * @param centerPoint - center of sphere
     * @param radius      - radius of sphere
     */
    public Sphere(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    /**
     * Getter
     *
     * @return the point
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * Getter
     *
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
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if (p0.equals(this.centerPoint))         // p0 = centerPoint only one intersect
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        Vector u = this.centerPoint.subtract(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        if (d >= this.radius)
            return null;
        double th = Math.sqrt(this.radius * this.radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;

        if (t1 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if (t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        return null;
    }

}

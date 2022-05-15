/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * Cylinder class represents which has two parallel circular bases at a distance and a height
 */
public class Cylinder extends Tube {

    final double height;

    /**
     * Constructor to initialize Tube
     *
     * @param radius
     * @param axisRay
     */
    public Cylinder(double radius, Ray axisRay,double height) {
        super(radius, axisRay);
        if (height <=0)
        throw new IllegalArgumentException("The height must to be bigger than 0");

        this.height = height;
    }

    /**
     * getter
     * @return height of cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        Point P0 = axisRay.getP0(); //middle of starting base
        Vector v = axisRay.getDir();
        Point P1 = P0.add(v.scale(height)); //middle of far base

        //if point is on the starting base - if distance from p0 is radius, and orthogonal to ray (so it is not on ray itself)
        if ((myPoint.distance(P0) <= radius) && (myPoint.subtract(P0).dotProduct(v) == 0)) {
            return getAxisRay().getDir().scale(-1);
        }
        //if point is on the far base - if distance from p1 is radius, and orthogonal to ray (so it is not on ray itself)
        else if ((myPoint.distance(P1) <= radius) && (myPoint.subtract(P1).dotProduct(v) == 0)) {
            return getAxisRay().getDir();
        }

        // if point is on round surfaces - not based:
        else {
            Vector PMinusP0 = myPoint.subtract(P0);
            double t = v.dotProduct(PMinusP0);
            Point O = P0.add(v.scale(t));
            return (myPoint.subtract(O)).normalize();
        }
    }

    /**
     * this function finds the intersection points of a given ray with the cylinder
     *
     * @param ray         - which could intersect the cylinder
     * @param maxDistance - is the maximum distance to find intersections in
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Step 1: find intersections between the ray and the tube which the cylinder is a part of
        List<GeoPoint> intersectionsTube = super.findGeoIntersections(ray, maxDistance);

        // Step 2: intersect is between caps
        Vector dir = axisRay.getDir();
        Point bottomCapCenter = axisRay.getP0();
        Point upperCupCenter = axisRay.getPoint(height);

        double loweBound;
        double upperBound;
        List<GeoPoint> intersectionsCylinder = new ArrayList<>();

        // validate each intersection (make sure it is in the cylinder itself and not on its continual)
        if (intersectionsTube != null) {
            for (GeoPoint geoPoint : intersectionsTube) {
                loweBound = dir.dotProduct(geoPoint.point.subtract(bottomCapCenter));
                upperBound = dir.dotProduct(geoPoint.point.subtract(upperCupCenter));
                if (loweBound > 0 && upperBound < 0) {
                    // the check for distance, if the intersection point is beyond the distance
                    if (alignZero(geoPoint.point.distance(ray.getP0()) - maxDistance) <= 0) {
                        intersectionsCylinder.add(geoPoint);
                    }
                }
            }
        }

        // Step 3: intersect with each plane which belongs to the caps
        Plane bottomCap = new Plane(bottomCapCenter, dir);
        Plane upperCap = new Plane(upperCupCenter, dir);
        List<GeoPoint> intersectionsBottomCup = bottomCap.findGeoIntersections(ray, maxDistance);
        List<GeoPoint> intersectionsUpperCup = upperCap.findGeoIntersections(ray, maxDistance);

        // if no intersections were found with the caps, return the ones we have already found
        if (intersectionsBottomCup == null && intersectionsUpperCup == null) {
            if (intersectionsCylinder.isEmpty()) {
                return null;
            }
            return intersectionsCylinder;
        }

        // Step 4: intersections inside caps
        Point bottomCapIntersectionPoint;
        Point upperCapIntersectionPoint;

        // bottom cup
        if (intersectionsBottomCup != null) {
            bottomCapIntersectionPoint = intersectionsBottomCup.get(0).point;
            if (bottomCapIntersectionPoint.subtract(bottomCapCenter).lengthSquared() < radius * radius) {
                intersectionsCylinder.add(intersectionsBottomCup.get(0));
            }
        }

        // upper cup
        if (intersectionsUpperCup != null) {
            upperCapIntersectionPoint = intersectionsUpperCup.get(0).point;
            if (upperCapIntersectionPoint.subtract(upperCupCenter).lengthSquared() < radius * radius) {
                intersectionsCylinder.add(intersectionsUpperCup.get(0));
            }
        }

        if (intersectionsCylinder.isEmpty()) {
            return null;
        }

        return intersectionsCylinder;
    }
}

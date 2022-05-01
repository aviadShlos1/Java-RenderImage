/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package unittests.geometries;

import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane testPlane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sqrt3 = Math.sqrt(1 / 3d);

        // Test that result of getNormal is proper
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), testPlane.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }


    /**
     * Test method for {@link geometries.Plane#findGeoIntersectionsHelper(primitives.Ray)}.'
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1));


// ============ Equivalence Partitions Tests ==============
        Point intersectionPoint;

        // TC01: Ray intersects the plane (1 points)
        Ray ray1 = new Ray(new Point(0, -1, 0), new Vector(1, 3, 1));
        List<Point> TC01result = plane.findIntersections(ray1);

        assertEquals(1, TC01result.size(), "Wrong number of intersection points");

        intersectionPoint = new Point(0.4, 0.2, 0.4);
        assertEquals(intersectionPoint, TC01result.get(0), "not the correct intersection point");

        // TC02: Ray doesn't intersect the plane(0 points)
        Ray ray2 = new Ray(new Point(3, 4, 2), new Vector(1, 2, 1));
        List<Point> TC02result = plane.findIntersections(ray2);

        assertEquals(0,TC02result.size(), "Wrong number of intersection points");


// =============== Boundary Values Tests ==================

        // TC03 :Ray is parallel to the plane and is included in the plane (0 points)

        Ray ray3 = new Ray(new Point(0.5, 0.5, 0), new Vector(1, -2, 0));
        List<Point> TC03result = plane.findIntersections(ray3);

        assertEquals(0, TC03result.size(), "Wrong number of intersection points");

        // TC04 :Ray is parallel to the plane and is not included in the plane (0 points)
        Ray ray4 = new Ray(new Point(0.5, 0, 0), new Vector(1, -2, 0));
        List<Point> TC04result = plane.findIntersections(ray4);

        assertEquals(0, TC04result.size(), "Wrong number of intersection points");

        // TC05 :Ray is orthogonal to the plane and the point is located before the plane (1 points)
        Ray ray5 = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));
        List<Point> TC05result = plane.findIntersections(ray5);

        assertEquals(1, TC05result.size(), "Wrong number of intersection points");

        intersectionPoint = new Point(1 / 3d, 1 / 3d, 1 / 3d);
        assertEquals(intersectionPoint, TC05result.get(0), "not the correct intersection point");

        // TC06 :Ray is orthogonal to the plane and its starting point is in the plane (0 points)
        Ray ray6 = new Ray(new Point(0.5, 0.25, 0.25), new Vector(-1, -1, -1));
        List<Point> TC06result = plane.findIntersections(ray6);

        assertEquals(0, TC06result.size(), "Wrong number of intersection points");

        // TC07 :Ray is orthogonal to the plane and the point is located after the plane (0 points)
        Ray ray7 = new Ray(new Point(-1, -1, -1), new Vector(-1, -1, -1));
        List<Point> TC07result = plane.findIntersections(ray7);

        assertEquals(0, TC07result.size(), "Wrong number of intersection points");

        // TC08 :Ray is neither orthogonal nor parallel  and begins at the plane(the point is in the plane) (0 points)
        Ray ray8 = new Ray(new Point(0.5, 0.25, 0.25), new Vector(-4, 1, 0));
        List<Point> TC08result = plane.findIntersections(ray8);

        assertEquals(0, TC08result.size(), "Wrong number of intersection points");

        // TC09 :Ray is neither orthogonal nor parallel  and  begins in
        //the same point which appears as reference point in the plane (0 points)
        Ray ray9 = new Ray(new Point(-2, -2, 5), new Vector(2, 10, -5));
        List<Point> TC09result = plane.findIntersections(ray9);

        assertEquals(0, TC09result.size(), "Wrong number of intersection points"+
                "the same point which appears as reference point in the plane");
    }
}

/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package unittests.geometries;
import geometries.Intersectable;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Sphere;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}./
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Sphere sp = new Sphere(new Point(0, 0, 0), 1);
        double sq = Math.sqrt(1 / 3d);
        Vector N = sp.getNormal(new Point(sq, sq, sq));

        // Test that result of getNormal is proper
        assertEquals(N, new Vector(sq, sq, sq));
    }


    /**
    * Test method for {@link geometries.Sphere#findGeoIntersectionsHelper(primitives.Ray)}.'
     * */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point( 0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point p_inside = new Point(1.5, 0, 0);
        List<Point> TC03results = sphere.findIntersections(new Ray(p_inside, new Vector(1, 1, 1)));
        assertEquals(1, TC03results.size(), "Wrong number of points");

        Point TC03intersection = new Point(1.8603796100280632, 0.36037961002806324, 0.36037961002806324);
        assertEquals(List.of(TC03intersection), TC03results, "Wrong intersection point");

        // TC04: Ray starts after the sphere (0 points)
        Point p_after = new Point(-0.5, 0, 0);
        assertNull(sphere.findGeoIntersectionsHelper(new Ray(p_after, new Vector(-0.5, 0, 0))), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center).
        // TC11: Ray starts at sphere and goes inside (1 points).
        Point p_onSphere = new Point(2, 0, 0);
        Point TC11intersection = new Point(0.4, 0, 0.8);
        List<Point> TC11results = sphere.findIntersections(new Ray(p_onSphere, new Vector(-2, 0, 1)));
        assertEquals(1, TC11results.size(), "Wrong number of points");

        assertEquals(TC11results, List.of(TC11intersection), "Wrong intersection point");

        // TC12: Ray starts at sphere and goes outside (0 points)
        List<Point> TC12results = sphere.findIntersections(new Ray(p_onSphere, new Vector(2, 0, 2)));
        assertNull(TC12results, "Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        List<Point> TC13results = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals(2, TC13results.size(), "Wrong number of points");

        if (TC13results.get(0).getX() > TC13results.get(1).getX()) {
            TC13results = List.of(TC13results.get(1), TC13results.get(0));
        }

        assertEquals(List.of(new Point(1, 1, 0), new Point(1, -1, 0)), TC13results, "Ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        p_onSphere = new Point(1, -1, 0);
        List<Point> TC14results = sphere.findIntersections(new Ray(p_onSphere, new Vector(0, 1, 0)));
        assertEquals(1, TC14results.size(), "Wrong number of points");

        Point TC14intersection = new Point(1, 1, 0);
        assertEquals(TC14results, List.of(TC14intersection), "Wrong intersection point");

        // TC15: Ray starts inside (1 points)
        p_onSphere = new Point(1, 0.5, 0);
        List<Point> TC15results = sphere.findIntersections(new Ray(p_onSphere, new Vector(0, 1, 0)));
        assertEquals(1, TC15results.size(), "Wrong number of points");

        Point TC15intersection = new Point(1, 1, 0);
        assertEquals(TC15results, List.of(TC15intersection), "Wrong intersection point");

        // TC16: Ray starts at the center (1 points)
        p_onSphere = new Point(1, 0.5, 0);
        List<Point> TC16results = sphere.findIntersections(new Ray(p_onSphere, new Vector(0, 1, 0)));
        assertEquals(1, TC16results.size(), "Wrong number of points");

        Point TC16intersection = new Point(1, 1, 0);
        assertEquals(TC16results, List.of(TC16intersection), "Wrong intersection point");

        // TC17: Ray starts at sphere and goes outside (0 points)
        p_onSphere = new Point(1, 1, 0);
        List<Point> TC17results = sphere.findIntersections(new Ray(p_onSphere, new Vector(0, 1, 0)));

        assertNull(TC17results, "Wrong number of points");

        // TC18: Ray starts after sphere (0 points)
        p_onSphere = new Point(1, 2, 0);
        List<Point> TC18results = sphere.findIntersections(new Ray(p_onSphere, new Vector(0, 1, 0)));

        assertNull(TC18results, "Wrong number of points");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        Point p_tan = new Point(0, 1, 0);
        List<Point> TC19results = sphere.findIntersections(new Ray(p_tan, new Vector(1, 0, 0)));

        assertNull(TC19results, "Wrong number of points");

        // TC20: Ray starts at the tangent point
        p_tan = new Point(1, 1, 0);
        List<Point> TC20results = sphere.findIntersections(new Ray(p_tan, new Vector(1, 0, 0)));

        assertNull(TC20results, "Wrong number of points");

        // TC21: Ray starts after the tangent point
        p_tan = new Point(2, 1, 0);
        List<Point> TC21results = sphere.findIntersections(new Ray(p_tan, new Vector(1, 0, 0)));

        assertNull(TC21results, "Wrong number of points");

        // **** Group: Special cases
        // TC19B: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        Point p = new Point(-1, 0, 0);
        List<Point> TC19B_results = sphere.findIntersections(new Ray(p, new Vector(0, 0, 1)));

        assertNull(TC19B_results, "Wrong number of points");

    }


}
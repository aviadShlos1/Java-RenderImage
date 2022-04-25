package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 */

public class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Triangle testT = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );
        double sq = Math.sqrt(1 / 3d);

        // Test that result of getNormal is proper
        assertEquals(testT.getNormal(new Point(1, 0, 0)), new Vector(sq, sq, sq));

    }
    /**
     * Test method for {@link geometries.Triangle#findGeoIntersectionsHelper(primitives.Ray)}.'
     * */
    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============
        Triangle t = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1)
        );

        Point intersectionPoint;

        // TC01: Ray intersects the triangle (1 points)
        Ray ray1 = new Ray(new Point(0, -1, 0), new Vector(1, 3, 1));
        List<Point> TC01result = t.findGeoIntersectionsHelper(ray1);

        assertEquals(1, TC01result.size(), "Wrong number of intersection points");

        intersectionPoint = new Point(0.4, 0.2, 0.4);
        assertEquals(TC01result.get(0), intersectionPoint, "not the correct intersection point");

        // TC02: Ray doesn't intersect the triangle-against the edge (0 points)
        Ray ray2 = new Ray(new Point(0, -1, 0), new Vector(2, 2, 1));
        List<Point> TC02result = t.findGeoIntersectionsHelper(ray2);

        assertNull(TC02result, "Wrong number of intersection points");

        // TC03: Ray doesn't intersect the triangle-against the vertices (0 points)
        Ray ray3 = new Ray(new Point(0, -1, 0), new Vector(2, 1, 0));
        List<Point> TC03result = t.findGeoIntersectionsHelper(ray3);

        assertNull(TC03result, "Wrong number of intersection points");

        // =============== Boundary Values Tests ==================

        // TC04: Ray  intersects the triangle on the edge (0 points)
        Ray ray4 = new Ray(new Point(0, -1, 0), new Vector(1, 2, 1));
        List<Point> TC04result = t.findGeoIntersectionsHelper(ray4);

        assertNull(TC04result, "Wrong number of intersection points");

        // TC05: Ray  intersects the triangle on the vertices (0 points)
        Ray ray5 = new Ray(new Point(0, -1, 0), new Vector(1, 1, 0));
        List<Point> TC05result = t.findGeoIntersectionsHelper(ray5);

        assertNull(TC05result, "Wrong number of intersection points");

        // TC06: Ray intersects on edge's continuation (0 points)
        Ray ray6 = new Ray(new Point(0, -1, 0), new Vector(1, 0, 0));
        List<Point> TC06result = t.findGeoIntersectionsHelper(ray6);

        assertNull(TC06result, "Wrong number of intersection points");
    }
}


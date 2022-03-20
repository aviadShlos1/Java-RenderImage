/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR02
 * Brief: Creating unit tests according to TDD principle
 */
package unittests.geometries;

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
}

//    @Test
//    void testFindIntersections() {
//        Ray ray = new Ray(1,);
//        Plane plane = new Plane(
//                new Point(1,0,0),
//                new Point(0, 1, 0),
//                new Point(0, 0, 1));
//        assertNull(plane.findIntersections(ray),"");
//
////        assertEquals(List.of (new Point(1,1,1), plane.findIntersections(ray)) );




//    }


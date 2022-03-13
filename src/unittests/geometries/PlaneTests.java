package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane testPlane = new Plane(
                new Point(1,0,0),
                new Point(0,1,0),
                new Point(0,0,1)
        );
        double sqrt3 = Math.sqrt(1 / 3d);

        // Test that result of getNormal is proper
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), testPlane.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }
}
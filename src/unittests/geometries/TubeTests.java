package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");
    }
}
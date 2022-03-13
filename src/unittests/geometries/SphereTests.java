package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import geometries.Sphere;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
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
}
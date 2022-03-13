package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Plane testPlane = new Plane(
                new Point(1,0,0),
                new Point(0,1,0),
                new Point(0,0,1)
        );
    }
}
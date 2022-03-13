package unittests.primitives;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for primitives.Point class
 *  @author: Aviad Shloserg 314960881
 *           Evyatar Levi   318753993
 */

class PointTests {
    Point a = new Point(1,2,3);
    Point b = new Point(1,2,3);

    Vector vec=b.subtract(a);
    /**
     * Test method for {@link primitives.Point# testSubtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point testPoint = new Point(0,0,0);
        assertEquals(testPoint,a.subtract(b),"ERROR: subtract() wrong value");
    }

    /**
     * Test method for {@link primitives.Point# testAdd(primitives.Point)}.
     */
    @Test
    void testAdd(){
        Point testPoint = new Point(1,2,3);
        assertEquals(testPoint,a.add(vec),"ERROR: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Point# testDistanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        fail("Not yet implemented");
    }


    /**
     * Test method for {@link primitives.Point# testDistance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        fail("Not yet implemented");
    }
}
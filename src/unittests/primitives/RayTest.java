package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Test method for {@link Ray#findClosestPoint(List <Point>)}.
     */
    @Test
    void testFindClosestPoint() {

        Ray ray = new Ray(Point.ZERO, new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01 - the closest point is in the middle of the list

        List<Point> list = new LinkedList<>() {{
            add(new Point(-1000, 90, 100));
            add(new Point(50, 48, 1000));
            add(new Point(0, 5, 1));
            add(new Point(-20, 60, 50));
            add(new Point(0, 0, -90));
        }};

        assertEquals(list.get(2), ray.findClosestPoint(list), "wrong point!");

        // =============== Boundary Values Tests ==================
        //TC11 - no points
        assertNull(ray.findClosestPoint(null), "supposed to be null!");

        //TC21 - the closest point is at the end of the list
        list.add(new Point(0, 0, 3));
        assertEquals(list.get(list.size() - 1), ray.findClosestPoint(list), "wrong point!");

        //TC22 - the closest point is at the beginning of the list
        list.add(0, Point.ZERO);
        assertEquals(list.get(0), ray.findClosestPoint(list), "wrong point!");

    }
}
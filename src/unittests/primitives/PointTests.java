/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR02
 * Brief: Creating unit tests according to TDD principle
 */
package unittests.primitives;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PointTests {
    Point a = new Point(1,2,3);
    Point b = new Point(3,5,5);
    /**
     * Test method for {@link primitives.Point# testSubtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Vector testVector = new Vector(2,3,2);
        assertEquals(testVector, b.subtract(a), "ERROR: subtract() wrong value");
    }

    /**
     * Test method for {@link primitives.Point# testAdd(primitives.Point)}.
     */
    @Test
    void testAdd(){
        Point testPoint = new Point(1,2,3);
        Vector myVector = new Vector(-2,-3,-2);
        assertEquals(testPoint,b.add(myVector),"ERROR: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Point# testDistanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {

        int sqrtDistance = 17;
        Point testPoint = new Point(1,2,3);
        assertEquals(sqrtDistance,b.distanceSquared(a),"ERROR: DistanceSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Point# testDistance(primitives.Point)}.
     */
    @Test
    void testDistance() {
       Double distance = Math.sqrt(17);
        assertEquals(distance,b.distance(a),"ERROR: DistanceSquared() wrong value");
    }
}
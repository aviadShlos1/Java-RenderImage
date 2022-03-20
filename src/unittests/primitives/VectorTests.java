/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR02
 * Brief: Creating unit tests according to TDD principle
 */
package unittests.primitives;
import org.junit.jupiter.api.Test;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    /**
     * Test method for {@link primitives.Vector# testAdd(primitives.Point)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Vector testVec = new Vector(-1, -2, -3);

        // Test that sum of add is proper
        assertEquals(testVec, v1.add(v2), "ERROR: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#  testScale(primitives.Point)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector testVec = new Vector(3, 6, 9);

        // Test that result of scale is proper
        assertEquals(testVec, v1.scale(3), "ERROR: scale() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#  testDotProduct(primitives.Point)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        int dotProd = -28; //-2-8-18;

        // Test that result of dotProduct is proper
        assertEquals(dotProd, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#  testLengthSquared(primitives.Point)}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        int length1 = 14;
        int length2 = 56;

        // Test that length squared is proper
        assertEquals(length1, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
        assertEquals(length2, v2.lengthSquared(), "ERROR: lengthSquared() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#  testLength(primitives.Point)}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        int length2 = 5;

        // Test that length is proper
        assertEquals(length2, new Vector(0, 3, 4).length(), "ERROR: length() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#  testNormalize(primitives.Point)}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(0,3,4);
        Vector n = v.normalize();
        // ========================= Equivalence Partitions Tests ===========
        // TC01: Simple test
        assertEquals(1d,n.lengthSquared() , 0.00001, "wrong normalized vector Length");
        assertThrows(IllegalArgumentException.class, ()-> v.crossProduct(n),
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0,0.6,0.8),n,"wrong normalized vector");
    }
}
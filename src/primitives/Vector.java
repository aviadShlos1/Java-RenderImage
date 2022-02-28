/**
 * @author: Aviad Shloserg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package primitives;

/**
 * This class will serve all primitive classes by using basic vector methods
 */
public class Vector extends Point
{
    /**
     * @param x first number value
     * @param y second number value
     * @param z third number value
     * @throws IllegalArgumentException an exception will be thrown in case the vector is a zero vector
     */
    public Vector(double x, double y, double z) throws IllegalArgumentException {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: cannot get vector of zero");
    }

    /**
     * @param dbl three-dimensional point
     * @throws IllegalArgumentException an exception will be thrown in case the vector is a zero vector
     */
    public Vector(Double3 dbl) throws IllegalArgumentException{
        super(dbl);
        if (dbl.equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: cannot get vector of zero");
    }

    /**
     * Sum two vectors into a new vector where each coordinate is summarized
     * @param myVec the vector to be added
     * @return result of add
     */
    public Vector add(Vector myVec) {
        return new Vector(this.xyz.add(myVec.xyz));
    }

    /**
     * Scale (multiply) floating point triad by a number into a new triad where each
     * number is multiplied by the number
     * @param scalar right handle side operand for scaling
     * @return vector which is multiplied by the scalar
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Product two floating point triads into a new triad where each couple of
     * numbers is multiplied
     * @param myVec right handle side operand for product
     * @return result of product
     */
    public double dotProduct(Vector myVec) {
        return this.xyz.d1*myVec.xyz.d1 +this.xyz.d2*myVec.xyz.d2 +this.xyz.d3*myVec.xyz.d3;
    }

    /**
     * Binary operation on two vectors in three-dimensional space. It results in a vector that is perpendicular to both vectors
     * @param myVec the second vector of the multiplying
     * @return the perpendicular vector
     */
    public Vector crossProduct(Vector myVec) {
       return new Vector(this.xyz.d2*myVec.xyz.d3-this.xyz.d3*myVec.xyz.d2,
                        this.xyz.d3*myVec.xyz.d1-this.xyz.d1*myVec.xyz.d3,
                        this.xyz.d1*myVec.xyz.d2-this.xyz.d2*myVec.xyz.d1);
    }

    /**
     * Calculate the squared length of the vector
     * @return result of squared length
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2* xyz.d2 + xyz.d3* xyz.d3;
    }

    /**
     * Calculate the length of the vector using the method above
     * @return result of squared length
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Convert the given vector to be a unit vector by dividing each of its coordinate by its length
     * @return the normalized vector
     */
    public Vector normalize() {
        return new Vector(this.xyz.d1/length(),this.xyz.d2/length(),this.xyz.d3/length());
    }

    @Override
    public String toString() {
        return "Vector{}";
    }

}

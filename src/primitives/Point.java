/**
 * @author: Aviad Shloserg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package primitives;
import primitives.Double3;
import java.util.Objects;

/**
 * This class will serve all primitive classes by basic point methods
 */

public class Point
{
    /**
     * Immutable field which holds three-dimensional point
     */
    final Double3 xyz;

    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz= new Double3(x,y,z);
    }

    /**
     * Constructor to initialize Double3 based object with its three number values
     * @param dbl three-dimensional point
     */
    public Point(Double3 dbl) {
        xyz= new Double3(dbl.d1,dbl.d2,dbl.d3);
    }

    /**
     * Subtract two points into a new vector where each couple of coordinate is subtracted
     * @param p1 the Point
     * @return result of subtract
     */
    public Vector subtract(Point p1) {
        return new Vector(this.xyz.subtract(p1.xyz));
//        return new Vector(this.xyz.d1-p1.xyz.d1,this.xyz.d2-p1.xyz.d2,this.xyz.d3-p1.xyz.d3 );
    }

    /**
     * Sum two vectors into a new vector where each coordinate is summarized
     * @param v the vector to be added
     * @return result of add
     */
    public Point add(Vector v) {
        return  new Point(this.xyz.add(v.xyz));
    }

    /**
     * Calculate the squared distance between two points
     * @param p1 the point
     * @return result of distance before the "sqrt" action
     */
    public double distanceSquared(Point p1) {
        return (this.xyz.d1-p1.xyz.d1)*(this.xyz.d1-p1.xyz.d1) +
                         (this.xyz.d2-p1.xyz.d2)*(this.xyz.d2-p1.xyz.d2) +
                         (this.xyz.d3-p1.xyz.d3)*(this.xyz.d3-p1.xyz.d3) ;
    }

    /**
     * Calculate the distance between two points, using the method above
     * @param p1 the point
     * @return result of distance
     */
    public double distance(Point p1) {
      return Math.sqrt(distanceSquared(p1));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}

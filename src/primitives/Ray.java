/**
 * @author: Aviad Shloserg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Defining the Primitives and the Geometries entities
 */
package primitives;

import java.util.Objects;
/**
 * This class presents the primitive "Ray" -
 * part of a line that has a fixed starting point but no end point. It can extend infinitely in one direction.
 */

public class Ray
{
    final private Point p0;
    final private Vector dir;

    /**
     * Constructor to initialize Ray based object with its point and vector
     *
     * @param p0 the point
     * @param dir the vector which will be normalized
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir;
        this.dir.normalize();
    }

    /**
     * Getters
     * @return
     */
    public Point getP0() {
        return p0;
    }
    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray) obj;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}

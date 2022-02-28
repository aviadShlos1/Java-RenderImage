/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.*;

/**
 * Sphere class represents a geometrical object that is a three-dimensional analogue to a two-dimensional circle based on main point anda radius
 */
public class Sphere implements Geometry {
    Point point;
    double radius;

    /**
     * Constructor to initialize Sphere
     * @param point
     * @param radius
     */
    public Sphere(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    /**
     * Getter
     * @return the point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Getter
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
 public Vector getNormal(Point myPoint) {
  return null;
 }
}

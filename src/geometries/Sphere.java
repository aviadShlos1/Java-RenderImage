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
    Point centerPoint;
    double radius;

    /**
     *  Main Constructor for sphere, by entrance of center point and radius
     *
     * @param centerPoint - center of sphere
     * @param radius - radius of sphere
     */
    public Sphere(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    /**
     * Getter
     * @return the point
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * Getter
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * function to calculate the normal of the sphere
     *
     * @param myPoint pointing in the direction of the normal
     * @return the normal vector
     */
    @Override
    public Vector getNormal(Point myPoint) {
       return (myPoint.subtract(centerPoint)).normalize();

    }
}

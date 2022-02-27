/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Definding the Primitives and the Gemoteries entities
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents any of various usually cylindrical structures or devices based on radius and a ray
 */
public class Tube implements Geometry {
    double radius;
    Ray ray;

    /**
     * Constructor to initialize Tube
     * @param radius
     * @param ray
     */
    public Tube(double radius, Ray ray) {
        this.radius = radius;
        this.ray = ray;
    }

    /**
     * Getter
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Getter
     * @return ray
     */
    public Ray getRay() {
        return ray;
    }

    @Override
    public Vector getNormal(Point myPoint) {return null;}
}

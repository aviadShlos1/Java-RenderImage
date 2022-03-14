/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents any of various usually cylindrical structures or devices based on radius and a ray
 */
public class Tube implements Geometry {
    final double radius;
    final Ray axisRay;

    /**
     * Constructor to initialize Tube
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        this.radius = radius;
        this.axisRay = axisRay;
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
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * function that receive a point in a body and return a normal in this point to the body
     *
     * @param myPoint pointing in the direction of the normal
     * @return normal vector to the Geometry
     */
    @Override
    public Vector getNormal(Point myPoint) {
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        double t= myPoint.subtract(p0).dotProduct(v);
        // getting the center point
        Point center = p0.add(v.scale(t));
        return (myPoint.subtract(center)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}

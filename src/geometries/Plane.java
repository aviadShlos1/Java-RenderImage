/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Definding the Primitives and the Gemoteries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a flat, two-dimensional surface that extends indefinitely which based on a vector and a point
 */
public class Plane implements Geometry
{
    private Point q0;
    private Vector normal;

    /**
     * constructor for plane using three points
     *
     * @param vertex
     * @param vertex1
     * @param vertex2
     */
    public Plane(Point vertex, Point vertex1, Point vertex2) {
        normal = null;
        q0 = vertex;
    }

    /**
     * c-tor which gets point and vector
     * @param q0 is the point
     * @param normal is the vector
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
        this.normal.normalize();
    }

    /**
     * Getter
     * @return the Vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        return null;
    }
}

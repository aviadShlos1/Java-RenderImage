/**
 * @author: Aviad Shloserg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;

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

//c-tor which gets point and vector
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
        this.normal.normalize();
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        return null;
    }
}

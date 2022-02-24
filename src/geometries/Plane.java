package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    private Point q0;
    private Vector normal;

//c-tor which gets three points
    public Plane(Point... vertices)
    {

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

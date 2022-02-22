package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    private Point q0;
    private Vector normal;

    public Plane(Point... vertices)
    {
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}

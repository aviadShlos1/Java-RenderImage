package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon{
    //c-tor which gets three points
    public Triangle(Point... vertices)
    {
        super(vertices);
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}

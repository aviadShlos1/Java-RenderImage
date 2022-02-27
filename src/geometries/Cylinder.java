package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder implements Geometry {
    double height;

    public Cylinder(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        return null;
    }
}

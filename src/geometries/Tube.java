package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {
    double radius;

    public double getRadius() {
        return radius;
    }

    public Ray getRay() {
        return ray;
    }

    Ray ray;

    public Tube(double radius, Ray ray) {
        this.radius = radius;
        this.ray = ray;
    }

    @Override
    public Vector getNormal(Point myPoint) {return null;}
}

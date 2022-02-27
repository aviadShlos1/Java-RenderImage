package geometries;

import primitives.*;

public class Sphere implements Geometry {
    Point point;
    double radius;

    public Sphere(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

 @Override
 public Vector getNormal(Point myPoint) {
  return null;
 }
}

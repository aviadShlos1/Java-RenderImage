/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

/**
 * Cylinder class represents which has two parallel circular bases at a distance and a height
 */
public class Cylinder extends Tube {

    final double height;

    /**
     * Constructor to initialize Tube
     *
     * @param radius
     * @param axisRay
     */
    public Cylinder(double radius, Ray axisRay,double height) {
        super(radius, axisRay);
        if (height <=0)
        throw new IllegalArgumentException("The height must to be bigger than 0");

        this.height = height;
    }

    /**
     * getter
     * @return height of cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        Point P0 = axisRay.getP0(); //middle of starting base
        Vector v = axisRay.getDir();
        Point P1 = P0.add(v.scale(height)); //middle of far base

        //if point is on the starting base - if distance from p0 is radius, and orthogonal to ray (so it is not on ray itself)
        if ((myPoint.distance(P0) <= radius) && (myPoint.subtract(P0).dotProduct(v) == 0)) {
            return getAxisRay().getDir().scale(-1);
        }
        //if point is on the far base - if distance from p1 is radius, and orthogonal to ray (so it is not on ray itself)
        else if ((myPoint.distance(P1) <= radius) && (myPoint.subtract(P1).dotProduct(v) == 0)) {
            return getAxisRay().getDir();
        }

        // if point is on round surfaces - not based:
        else {
            Vector PMinusP0 = myPoint.subtract(P0);
            double t = v.dotProduct(PMinusP0);
            Point O = P0.add(v.scale(t));
            return (myPoint.subtract(O)).normalize();
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}

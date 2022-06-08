/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.*;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Tube class represents any of various usually cylindrical structures or devices based on radius and a ray
 */
public class Tube extends Geometry {
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
    /**
     * @param ray - ray that cross the geometry
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     * @return list of intersection points that were found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray ,  double maxDistance) {
  /*
        The equation for a tube of radius r oriented along a line pa + vat:
        (q - pa - (va,q - pa)va)2 - r2 = 0
        get intersections using formula : (p - pa + vt - (va,p - pa + vt)va)^2 - r^2 = 0
        reduces to at^2 + bt + c = 0
        with a = (v - (v,va)va)^2
             b = 2 * (v - (v,va)va,∆p - (∆p,va)va)
             c = (∆p - (∆p,va)va)^2 - r^2
        where  ∆p = p - pa
        */

        Vector v = ray.getDir();
        Vector va = this.getAxisRay().getDir();

        //if vectors are parallel then there is no intersections possible
        if (v.normalize().equals(va.normalize()))
            return null;

        //use of calculated variables to avoid vector ZERO
        double vva;
        double pva;
        double a;
        double b;
        double c;

        //check every variables to avoid ZERO vector
        if (ray.getP0().equals(this.axisRay.getP0())) {
            vva = v.dotProduct(va);
            if (vva == 0) {
                a = v.dotProduct(v);
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
            }
            b = 0;
            c = -radius * radius;
        } else {
            Vector deltaP = ray.getP0().subtract(this.axisRay.getP0());
            vva = v.dotProduct(va);
            pva = deltaP.dotProduct(va);

            if (vva == 0 && pva == 0) {
                a = v.dotProduct(v);
                b = 2 * v.dotProduct(deltaP);
                c = deltaP.dotProduct(deltaP) - radius * radius;
            } else if (vva == 0) {
                a = v.dotProduct(v);
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -radius * radius;
                } else {
                    b = 2 * v.dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - this.radius * this.radius;
                }
            } else if (pva == 0) {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP);
                c = (deltaP.dotProduct(deltaP)) - this.radius * this.radius;
            } else {
                a = (v.subtract(va.scale(vva))).dotProduct(v.subtract(va.scale(vva)));
                if (deltaP.equals(va.scale(deltaP.dotProduct(va)))) {
                    b = 0;
                    c = -radius * radius;
                } else {
                    b = 2 * v.subtract(va.scale(vva)).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))));
                    c = (deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(deltaP.subtract(va.scale(deltaP.dotProduct(va))))) - this.radius * this.radius;
                }
            }
        }

        //calculate delta for result of equation
        double delta = b * b - 4 * a * c;

        if (delta <= 0) {
            return null; // no intersections
        } else {
            //calculate points taking only those with t > 0
            double t1 = alignZero((-b - Math.sqrt(delta)) / (2 * a));
            double t2 = alignZero((-b + Math.sqrt(delta)) / (2 * a));
            if (t1 > 0 && t2 > 0) {
                Point p1 =ray.getPoint(t1);
                double distance1 = ray.getP0().distance(p1);
                Point p2 = ray.getPoint(t2);
                double distance2 = ray.getP0().distance(p2);
                if (distance1 <= maxDistance && distance2 <= maxDistance) {
                    return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
                } else if (distance1 <= maxDistance) {
                    return List.of(new GeoPoint(this, p1));
                } else if (distance2 <= maxDistance) {
                    return List.of(new GeoPoint(this, p2));
                } else {
                    return null;
                }
            } else if (t1 > 0) {
                Point p1 = ray.getPoint(t1);
                double distance1 = ray.getP0().distance(p1);
                if (distance1 <= maxDistance) {
                    return List.of(new GeoPoint(this, p1));
                }
            } else if (t2 > 0) {
                Point p2 = ray.getPoint(t2);
                double distance2 = ray.getP0().distance(p2);
                if (distance2 <= maxDistance) {
                    return List.of(new GeoPoint(this, p2));
                }
            }
        }
        return null;
    }

    @Override
    public void setBox() {
    }
}

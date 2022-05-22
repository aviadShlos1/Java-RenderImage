/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package lighting;

import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * class represents a light source with a known position, emits light to all directions (e.g. light bulb)
 * Point light is defined by the location of the lighting, and the intensity of light, (and attenuation coefficients).
 * It transmits energy in all directions on an equal level.
 * The attenuation constants give better control over the lighting model to the extent that the distance affects the intensity.
 */

public class PointLight extends Light implements LightSource{

    /**
     * location of the light source
     */
    protected Point position;
    /**
     * kL - fixed attenuation dependent on distance,
     * kC - fixed attenuation regardless of distance,
     * kQ - fixed attenuation depending on square distance
     */
    private double kC = 1,kL = 0 ,kQ = 0;

    protected double radius;



    /**
     * constructor
     *
     * @param color    - the color of the light source
     * @param position - the point which the light is being emitted from
     * @param radius - the radius of the light source
     */
    public PointLight(Color color, Point position, double radius) {
        super(color);
        this.position = position;
        this.radius=radius;
    }

    /**
     * Calculates the color of the light in a given point in the 3D space ,
     * taking into consideration the attenuation factors
     *
     * @param p - the point which we want to know what the color is in
     * @return the light color in p
     */
    @Override
    public Color getIntensity(Point p) {
        double dist = p.distance(position);
        double distSquared = p.distanceSquared(position);
        return getIntensity().scale(1 / (kC + kL * dist + kQ * distSquared));
    }

    /**
     * Get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * function to calculate the distance between
     * light source and a point to make sure no object.
     * behind the light source is casting a shadow on the tested point
     *
     * @param point - the tested point
     * @return the distance between the given point and the light source
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
    /**
     * setter - chaining method design pattern
     *
     * @param kC - attenuation factor
     * @return the light source after the change
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;

    }

    /**
     * setter - chaining method design pattern
     *
     * @param kL - attenuation factor
     * @return the light source after the change
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter - chaining method design pattern
     *
     * @param kQ - attenuation factor
     * @return the light source after the change
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
    /**
     *
     * @param lightDirection - the vector of the light source
     * @param minPoints - minimum points required to distribute
     * @return list of points distributed on the surface of the light source.
     * Min size of the list is as the minimum points required
     */
    @Override
    public List<Point> lightPoints(Vector lightDirection, int minPoints) {
        Plane targetArea = new Plane(position, lightDirection);
        return pointsOnTarget(position,radius,targetArea,minPoints);
    }

    /**
     * gets a 2D circle in the 3D model which represents the light source shape
     * and produces multiple points distributed on the surface
     * @param center center of the light source
     * @param radius radius of the lights source
     * @param plane the plane containing the circle
     * @param minPoints min points to produce
     * @return list of points distributed on the surface,at least as the minimum points required
     */
    public List<Point> pointsOnTarget(Point center, double radius, Plane plane, int minPoints) {
        List<Point> pointsInCircle = new LinkedList<Point>();
        Vector normal = plane.getNormal(position);

        //defining the plane by two orthogonal vectors included in it

        //multiple scalar of orthogonal vectors is 0. therefore, we create vector x so x*normal =0
        Vector x = new Vector(normal.getY() * -1,normal.getX(),0).normalize();
        Vector y = x.crossProduct(normal);

        Point xPoint;     // movement in the x-axis
        Point yPoint;     // movement in the y-axis
        //number of moves from the center to each side in the direction of vector X(or it opposite direction)
        //if the area was a square then the formula was :square (mn Points) / 2
        //since the area is a circle (area is about 80 percent) and our coverage is about 80-90
        // total we get about 2/3 of the original wanted amount
        // then we multiply the above formula by 1.25 times (square is 1.5 bigger.
        // after 2/3 reduce we will get the wanted amount)
        int partition = (int) (Math.sqrt(minPoints) / 2 * 1.25);
        double distance = radius / partition;
        for (int i = -partition; i <= partition; i++) {
            if (Util.alignZero(i * distance) != 0) {
                xPoint = position.add(x.scale(i * distance));
            } else {
                xPoint = position;
            }
            double maxY = Math.sqrt((radius * radius) - (i * distance) * (i * distance));
            int moves = (int) (maxY / distance);
            for (int j = -moves; j <= moves; j++) {
                if (Util.alignZero(j * distance) != 0) {
                    pointsInCircle.add(xPoint.add(y.scale(j * distance)));
                }
            }

        }
        return pointsInCircle;
    }
}


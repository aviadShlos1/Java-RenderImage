/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

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
    private Point position;
    /**
     * kL - fixed attenuation dependent on distance,
     * kC - fixed attenuation regardless of distance,
     * kQ - fixed attenuation depending on square distance
     */
    private double kC = 1,kL = 0 ,kQ = 0;

    /**
     * constructor
     *
     * @param color    - the color of the light source
     * @param position - the point which the light is being emitted from
     */
    public PointLight(Color color, Point position) {
        super(color);
        this.position = position;
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

}

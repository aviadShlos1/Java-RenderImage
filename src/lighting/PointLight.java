package lighting;

import primitives.Color;
import primitives.Point;

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

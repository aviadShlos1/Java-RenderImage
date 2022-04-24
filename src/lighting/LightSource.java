package lighting;

import primitives.*;
/**
 *  An interface defining crucial methods for every kind of light source
 */
public interface LightSource {
    /**
     * function calculates the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know what the color is in
     * @return the light color in p
     */
    public Color getIntensity(Point p);
    /**
     * function to get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    public Vector getL(Point p);
}

package lighting;

import primitives.*;


/**
 *
 * This class represents a light source with a known  direction, no position! (e.g. the sun)
 * Directional Light defined by the intensity of light emitted from an undefined, and vector direction.
 * Because the energy propagation is uniform in its direction,
 * It will always go from surface to surface in the same way that energy is stored, no attention to distance
 * and the whole scene receives the intensity of light in the same way.
 *
 * */

public class DirectionalLight extends Light implements LightSource {
    /**
     * The direction of the directional light
     */
    private Vector direction;

    /**
     * Constructor of Directional Light defined by the direction and intensity of light
     *
     * @param color     - the color of the light
     * @param direction - the direction of the light beam
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction;
    }

    /**
     * function calculates the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know what the color is in
     * @return the light color in p
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity(); // No attenuation with distance
    }

    /**
     * function to get the ray from the light source to the given point
     *
     * @param p - the ray's destination point
     * @return the ray - the normalized(p - pL)
     */
    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

}

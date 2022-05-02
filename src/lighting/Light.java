/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package lighting;

import primitives.Color;

/**
 * An Abstract Class represents a basic element which is defined by the intensity of colors of the lighting,
 * Refers to the light propagation model.
 * represents all types of light sources in the scene e.g. spotlight, point light etc.
 * its function is to illuminate the objects with colors in different ways.
 */
abstract class Light {

    /**
     * the color of the light, the intensity of RGB colors
     */
    private Color intensity;

    /**
     * Light constructor accepting the intensity of colors value
     * creates the light's intensity
     *
     * @param color - the color of the light
     */
    protected Light(Color color) {
        this.intensity = color;
    }

    /**
     * getter for Light intensity
     *
     * @return the color of this instance
     */
    public Color getIntensity() {
        return intensity;
    }
}

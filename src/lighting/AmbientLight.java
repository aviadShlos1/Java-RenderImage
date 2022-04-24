
/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR05
 * Brief: Support color, add scheme and building image with ambient light
 */
package lighting;
import primitives.Color;

import primitives.Double3;
/**
 * Ambient Light Color
 * Ambient light class represents a fixed-intensity and fixed color light source
 * that affects all objects in the scene equally.
 * the Ambient light intensity in point is Ip = Ka ∙ Ia
 *
 * Upon rendering, all objects in the scene are brightened with the specified intensity and color.
 * Mainly used to provide the scene with a basic view of the different objects in it.
 * The simplest type of lighting to implement and models how light can be
 */
public class AmbientLight extends Light {
    /**
     * empty constructor - return neutral color
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
    /**
     * Ambient Light constructor accepting the intensity's value and the color light source's value
     * creates the fixed ambient light's intensity
     *
     * @param Ia intensity color
     * @param Ka constant for intensity
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }


    @Override
    public String toString() {
        return "AmbientLight{" +
                "_intensity=" + getIntensity() +
                '}';
    }

}

package lightning;

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
public class AmbientLight {
    private Color intensity;
    /**
     * empty constructor - return neutral color
     */
    public AmbientLight() {
        this.intensity=Color.BLACK;
    }

    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity= Ia.scale(Ka);
    }
    public Color getIntensity() {
        return intensity;
    }
}

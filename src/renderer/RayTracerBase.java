package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
/**
 * An abstract class used to trace rays for the rendering engine
 */
public abstract class RayTracerBase {
    /**
     * scene to be rendered
     */
    protected Scene scene;

    /**
     * constructor for the ray tracer
     * @param scene to be intersected
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    /**
     * abstract function to determine the color of a pixel
     * @param ray - ray to intersect
     * @return the color of the pixel intersects the given ray
     */
    public Color traceRay(Ray ray){
        return null;
    }
}

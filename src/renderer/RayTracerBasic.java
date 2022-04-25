/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR05
 * Brief: Support color, add scheme and building image with ambient light
 */
package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * abstract function to determine the color of a pixel
     *
     * @param ray - the ray sent from the light source to the scene
     * @return the color of the pixel intersects the given ray
     */
    @Override
    public Color traceRay(Ray ray) {
       List<Point> intersections= scene.geometries.findGeoIntersectionsHelper(ray);
       if(intersections==null)
           return scene.background;
        Point closetPoint= ray.findClosestPoint(intersections);
        return calcColor(closetPoint);
    }

    /**
     * function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param closetPoint - the point on the 3D model
     * @return the color in the point
     */
    private Color calcColor(Point closetPoint)
    {
        return scene.ambientLight.getIntensity();
    }
}

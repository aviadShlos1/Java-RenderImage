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

    @Override
    public Color traceRay(Ray ray) {
       List<Point> intersections= scene.geometries.findIntersections(ray);
       if(intersections==null)
           return scene.background;
        Point closetPoint= ray.findClosestPoint(intersections);
        return calcColor(closetPoint);
    }

    private Color calcColor(Point closetPoint)
    {
        return scene.ambientLight.getIntensity();
    }
}
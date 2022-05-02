/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

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
       List<GeoPoint> intersections= scene.geometries.findGeoIntersections(ray);
       if(intersections==null)
           return scene.background;
        GeoPoint closetPoint= ray.findClosestGeoPoint(intersections);
        return calcColor(closetPoint,ray);
    }

    /**
     * function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param closetPoint - the point on the 3D model
     * @return the color in the point
     */
    private Color calcColor(GeoPoint closetPoint, Ray ray){
        return scene.ambientLight.getIntensity()
                .add(closetPoint.geometry.getEmission())
                .add(calcLocalEffects(closetPoint, ray));
    }
    /**
     * function to calculate transparency and reflections
     *
     * @param gp - the tested point
     * @param ray      - the ray going through the point
     * @return the Color of the returned light after calculating all the required effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point).normalize();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        double nShininess = gp.geometry.getMaterial().nShininess;
        Material material = gp.geometry.getMaterial();
        Double3 ks = gp.geometry.getMaterial().kS;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color lightIntensity = lightSource.getIntensity(gp.point);
                color = color.add(lightIntensity.scale(calcDiffusive(material,nl)),
                        lightIntensity.scale(calcSpecular(material, n, l ,nl, v)));
            }
        }
        return color;
    }

    /**
     * function to calculate the diffusive component of the light
     * @param nl             - the cosine of the angle between l and n vectors
     * @param material       - the material of the body
     * @return the light after the calculated effects
     */
    private Double3 calcDiffusive(Material material, double nl){
//        double ln = l.normalize().dotProduct(n.normalize());
        return material.kD.scale(Math.abs(nl));
    }
    /**
     * function to calculate the specular component of the light
     *
     * @param material       - the material of the body
     * @param n              - the normal of l with the body
     * @param l              - the vector between the light source and the point
     * @param v              - the camera vector from the point of view
     * @param nl             - the cosine of the angle between l and n vectors
     * @param v              - the vector from the point of view (the viewer)
     * @return the light after the calculated effects
     */
    private Double3 calcSpecular(Material material,Vector n,Vector l ,double nl,Vector v){

        Vector r = l.add(n.scale(-2 * nl));
        double vr = v.scale(-1).dotProduct(r);
        double maximal = Math.max(0,vr);
        var p = Math.pow(maximal, material.nShininess);
        return material.kS.scale(p);
    }
}

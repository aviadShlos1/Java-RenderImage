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

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracerBase {
    /**
     * Constant value defining by how much we need to move the ray's starting point
     */
    // How many reflections we want to calculate,
    // pay attention, higher values may lead to decrease the performance rapidly
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    // Minimal value which is not considered at fully transparent or other effects
    private static final double MIN_CALC_COLOR_K = 0.001;
    //  0 leads to no reflections at all, but no significant difference with any other value
    private static final double INITIAL_K = 1;
    //A number of minimum points required to distribute to the surface
    int MIN_SHADOW_SAMPLES = 0;


    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * setter to the number of ray tracing
     * @param MIN_SHADOW_SAMPLES - number of minimum points required to distribute to the surface
     * @return this - builder pattern
     */
    public RayTracerBasic setMIN_SHADOW_SAMPLES(int MIN_SHADOW_SAMPLES) {
        this.MIN_SHADOW_SAMPLES = MIN_SHADOW_SAMPLES;
        return this;
    }

    /**
     * abstract function to determine the color of a pixel
     *
     * @param ray - the ray sent from the light source to the scene
     * @return the color of the pixel intersects the given ray
     */
    @Override
    public Color traceRay(Ray ray) {
       GeoPoint closetPoint= findClosestIntersection(ray);
       return closetPoint == null ? scene.background : calcColor(closetPoint, ray);
    }

    /**
     * function to find geo point closest to starting point of the ray
     * from all the intersections with an object
     *
     * @param ray - the tested ray
     * @return the point closest to the ray's starting point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null || intersections.size() == 0) {
            return null;
        } else {
            return ray.findClosestGeoPoint(intersections);
        }
    }



    /**
     * function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param intersection - closet intersection point on the object
     * @param ray          - ray to the point
     * @param level        - recursion iterations upper limit
     * @param k            - TODO lines 26 - 27
     * @return the color in the point with all the effects
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = calcLocalEffects(intersection, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }
    /**
     * function which returns the color of the object the ray is intersecting
     * if no intersection was found, returns the ambient light's color
     *
     * @param gp - the point on the 3D model
     * @param ray   - ray to the point
     * @return the color in the point
     */
    private Color calcColor(GeoPoint gp, Ray ray){

        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * function to calculate transparency and reflections
     *
     * @param gp       - the tested point
     * @param v        - the direction vector
     * @param level    - recursion iterations upper limit
     * @param k        - TODO lines 26 - 27
     * @return the Color of the returned light after calculating all the required effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.scale(k);
        if (!kkr.lowerThan( MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point,v, n), level, material.kR, kkr);
        Double3 kkt = material.kT.scale(k);
        if (!kkt.lowerThan( MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point,v, n), level, material.kT, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx.hashCode())).scale(kx);
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
                Double3 ktr = transparency(gp,lightSource,l,n);
                if ( !ktr.product(ks).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material,nl)),
                            lightIntensity.scale(calcSpecular(material, n, l ,nl, v)));
                }
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
       /**
    * boolean function to test whether a given point is shaded or not
     *
    * @param light    - the light source which we check if the point is shaded from
    * @param l        - the vector between the light source and the point
    * @param n        - the normal of l with the body
    * @param gp       - the tested point
    * @return true if the point is shaded, false otherwise
    */

    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray (gp.point, lightDirection,n);
        double distance = light.getDistance(gp.point);
        List<GeoPoint> intersections= scene.geometries.findGeoIntersections(lightRay);
        if (intersections != null) {
            for (GeoPoint geo : intersections){
                if (alignZero(geo.point.distance(gp.point) - distance) <= 0
                        && geo.geometry.getMaterial().kT.lowerThan(0)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * function to determine the point transparency
     *
     * @param ls       - the light source which we measure the distance from
     * @param l        - the vector between the light source and the point
     * @param n        - the normal in the point
     * @param geoPoint - the tested point
     * @return the transparency value
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        List<Point> lightPoints = new LinkedList<>();
        if (MIN_SHADOW_SAMPLES != 0){                 //activate soft shadow
            lightPoints = ls.lightPoints(l, MIN_SHADOW_SAMPLES);
        }
        List<Ray> lightRays = new LinkedList<>();
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        //directional light - no position,  or no need to active soft shadow (lightPoints size =0)
        if (lightPoints == null || MIN_SHADOW_SAMPLES == 0){
            lightRays.add(lightRay);
        }
        else{
            for (Point lightPoint: lightPoints){
                lightRays.add(new Ray(geoPoint.point, lightPoint.subtract(geoPoint.point).normalize(),n));
            }
        }

        Double3 ktrTotal=new Double3(0.0);
        Double3 ktr = new Double3(1.0);
        for(Ray ray:lightRays){
            var intersections = scene.geometries.findGeoIntersections(ray);
            if (intersections == null)
                ktrTotal= ktrTotal.add(ktr);
            else{
                double lightDistance = ls.getDistance(geoPoint.point);
                for (GeoPoint gp : intersections) {
                    if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                        ktr = ktr.product(gp.geometry.getMaterial().kT);
                        if (ktr.lowerThan(MIN_CALC_COLOR_K)) break;
                    }
                }
                ktrTotal = ktrTotal.add(ktr);
            }
        }
       return (ktrTotal.scale((double) 1/lightRays.size()));
    }
    /**
     * function to construct the new ray reflected
     * from a point where another ray hits
     *

     * @param point - the reflection point
     * @param v     - the direction vector
     * @param n     - the normal in point
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        Vector vnn = n.scale(-2 * vn);
        Vector r = v.add(vnn);
        // use the 3 arguments constructor to move the ray head if it is needed
        return new Ray(point, r , n);
    }


    /**
     * function to construct the new ray reflected
     * from a point where another ray hits
     *
     * @param point - the reflection point
     * @param v     - the direction vector
     * @param n     - the normal in point
     * @return the reflected ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point,v, n);
    }
}

/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package lighting;
import geometries.Plane;
import primitives.*;

import java.util.List;

/**
 * class represents a light source in a shape  of spotlight
 * in order to get the same light intensity, we need to increase the initial intensity of the light source
 * class Spot Light defined by position, direction and light intensity.
 * it's a kind of point light so we choose to extend it from pointLight.
 */

public class SpotLight extends PointLight {
    /**
     *  direction - the direction of the light source
     */
    private Vector direction ;

    /**
     * constructor
     *
     * @param color    - the color of the light source
     * @param position - the point which the light is being emitted from
     * @param direction direction of light
     */
    public SpotLight(Color color, Point position, Vector direction, double radius) {
        super(color, position,radius);
        this.direction = direction.normalize();
    }
    /**
     * default constructor. light source is a point and has no dimensions or shape
     * @param color    - the color of the light source
     * @param position - the point which the light is being emitted from
     * @param direction direction of light
     */
    public SpotLight(Color color, Point position, Vector direction) {
        this(color, position, direction, 0);
    }


    /**
     * function calculates the color of the light in a given point in the 3D space
     *
     * @param p - the point which we want to know what the color is in
     * @return the light color in p
     */
    @Override
    public Color getIntensity(Point p) {
        double projection = direction.dotProduct(getL(p));
        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double maxAngle=Math.max(0,projection);
        return super.getIntensity(p).scale(maxAngle);
    }

    /**
     * Getter
     * @param p - the ray's destination point
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    /**
     * Getter
     * @param point - the tested point
     * @return
     */
    @Override
    public double getDistance(Point point) {
        return super.getDistance(point);
    }

    /**
     *
     * @param lightDirection - the vector of the light
     * @param minPoints - minimum points required to distribute
     * @return list of points distributed on the surface of the light source.
     * Min size of the list is as the minimum points required
     */
    @Override
    public List<Point> lightPoints(Vector lightDirection, int minPoints) {
        Plane targetArea = new Plane(position, direction);
        return super.pointsOnTarget(position, radius, targetArea, minPoints);
    }
}

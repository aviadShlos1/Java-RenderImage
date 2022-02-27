/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Definding the Primitives and the Gemoteries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Cylinder class represents which has two parallel circular bases at a distance and a height
 */
public class Cylinder implements Geometry {
    double height;

    /**
     * Constructor to initialize Cylinder based the height
     * @param height
     */
    public Cylinder(double height) {
        this.height = height;
    }

    /**
     * Getter
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        return null;
    }
}

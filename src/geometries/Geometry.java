/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This interface will serve all geometries classes which will implement the getNormal method
 */
public abstract class Geometry implements Intersectable{
     public abstract Vector getNormal(Point myPoint);
     protected Color emission=Color.BLACK;

     /**
      * Getter
      * @return
      */
     public Color getEmission() {
          return emission;
     }
}


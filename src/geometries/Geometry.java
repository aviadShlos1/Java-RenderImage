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
public abstract class Geometry extends Intersectable{
     public abstract Vector getNormal(Point myPoint);
     /**
      * emission light
      */
     protected Color emission = Color.BLACK;
     /**
      * get self color of the shape
      *
      * @return emission of geometry
      */
     public Color getEmission() {
          return emission;
     }

     /**
      * setter of emission light, chaining method design pattern
      *
      * @param emission color of shape
      * @return this instance
      */
     public Geometry setEmission(Color emission) {
          this.emission = emission;
          return this;
     }
}


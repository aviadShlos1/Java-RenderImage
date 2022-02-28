/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will serve all geometries classes which will implement the getNormal method
 */
public interface Geometry
{
    Vector getNormal(Point myPoint);
}

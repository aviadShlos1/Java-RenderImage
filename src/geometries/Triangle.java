/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Triangle class represents a polygon with three edges and three vertices
 */
public class Triangle extends Polygon{
    /**
     * c-tor which gets three points
     * @param vertices which include a list of three vertices
     */
    public Triangle(Point... vertices)
    {
        super(vertices);
    }


}

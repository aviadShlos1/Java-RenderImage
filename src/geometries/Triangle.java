/**
 * @author: Aviad Shloserg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon{
    //c-tor which gets three points
    public Triangle(Point... vertices)
    {
        super(vertices);
    }

    @Override
    public Vector getNormal(Point point) {return null;}
}

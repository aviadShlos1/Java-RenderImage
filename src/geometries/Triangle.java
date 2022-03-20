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

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

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

    @Override
    public Vector getNormal(Point p){
        return super.getNormal(p);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> resultPoint = plane.findIntersections(ray);
        if (resultPoint == null) // In case there is no intersection with the plane return null
            return null;
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        double vn1 = alignZero(n1.dotProduct(ray.getDir()));
        double vn2 = alignZero(n2.dotProduct(ray.getDir()));
        double vn3 = alignZero(n3.dotProduct(ray.getDir()));

        if (vn1 == 0 || vn2 == 0 || vn3 == 0) // In case one or more of the scalars equals zero
            return null; // that mean the point is not inside the triangle

        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) { // In case the all scalars are in the same sign, the point is in the triangle
            LinkedList<Point> result = new LinkedList<>();
            result.add(resultPoint.get(0));
            return result;
        } else
            return null;	//If the scalars are in a different sign
    }
}

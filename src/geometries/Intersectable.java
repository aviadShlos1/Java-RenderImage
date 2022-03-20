/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;
import primitives.*;
import java.util.List;

/**
 * An Interface for Composite Design Pattern The Composite Class - Geometries The
 * Basic Classes - all the specific geometries
 */
public interface Intersectable {
    public List<Point> findIntersections(Ray ray);
}

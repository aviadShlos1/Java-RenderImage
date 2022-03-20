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

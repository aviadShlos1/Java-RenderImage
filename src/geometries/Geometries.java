package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * this class represents a group of shapes in the space that represent a picture.
 * Composite class which includes components and composite geometries
 */
public class Geometries implements Intersectable {
    /**
     * geometries - list of all components in the scene
     */
    private List<Intersectable> geometriesList;

    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows adding and deleting easily (running time).
     */
    public Geometries() {
        geometriesList = new LinkedList<>();
    }

    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows adding and deleting easily (running time).
     * after initializing, it adds shapes to the list, by using the add method.
     * @param geometriesList - shapes to be added to the constructed instance
     */
    public Geometries(Intersectable... geometriesList) {
        add(geometriesList);
    }

    /**
     * a method that receives one or more shape and adds to this list.
     * @param geometries - shapes to be added to this instance
     */
    private void add(Intersectable... geometries) {
        this.geometriesList.addAll(Arrays.asList(geometries));
    }

    /**
     * a method that receive a ray and find all intersections of this ray with the shapes in this class
     *
     * @param ray         - the ray to be checked with the shapes
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<>();
        for (Intersectable geometry : geometriesList) {
            // declare list as null
            List<Point> geoIntersections = null;

            if (geometriesList != null && geometriesList.size() > 0) {
                intersections.addAll(geoIntersections);
            }
        }
        if (geometriesList.size() > 0) {
            return intersections;
        }
        return null;
    }
}


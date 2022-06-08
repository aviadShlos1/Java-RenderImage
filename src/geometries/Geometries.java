/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * this class represents a group of shapes in the space that represent a picture.
 * Composite class which includes components and composite geometries
 */
public class Geometries extends Intersectable {
    /**
     * geometries - list of all components in the scene
     */
    private List<Intersectable> geometriesBodies = new LinkedList<>();

    /**
     * constructor of class, creates the list and for now it is empty.
     * implements as a linked list that allows adding and deleting easily (running time).
     */
    public Geometries() {
        geometriesBodies = new LinkedList<>();
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
    public void add(Intersectable... geometries) {
        this.geometriesBodies.addAll(List.of(geometries));
    }

    /**
     * a method that receive a ray and find all intersections of this ray with the shapes in this class
     *
     * @param ray         - the ray to be checked with the shapes
     * @param maxDistance - the upper bound of distance, any point which
     *                    its distance is greater than this bound will not be returned
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray ,  double maxDistance ) {
        if (box != null && !box.IsRayHitBox(ray))
            return null;
        List<GeoPoint> intersections = new LinkedList<>();
        for (var geometry : geometriesBodies) {
            // declare list as null
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray , maxDistance) ;

            if (geoIntersections != null) {
                intersections.addAll(geoIntersections);
            }
        }
        if (intersections.size() > 0) {
            return intersections;
        }
        return null;
    }
    /**
     * Function that create box for each geometry
     */
    public void setGeometriesBoxes() {
        for(Intersectable geo : geometriesBodies) {
            geo.setBox();
        }
    }
    /**
     * Create big box that will contain all of the geometries
     */
    @Override
    public void setBox() {

        setGeometriesBoxes(); //Create box for each geometry

        Intersectable intersecI = geometriesBodies.get(0);
        double maxX = intersecI.box.maxX;
        double maxY = intersecI.box.maxY;
        double maxZ = intersecI.box.maxZ;
        double minX = maxX;
        double minY = maxY;
        double minZ = maxZ;

        for(Intersectable geo : geometriesBodies) {	//For each geometry find the max and min of is box,
            //and create the geometries box

            if (maxX < geo.box.maxX)
                maxX = geo.box.maxX;

            if (maxY < geo.box.maxY)
                maxY = geo.box.maxY;

            if (maxZ < geo.box.maxZ)
                maxZ = geo.box.maxZ;

            if (minX > geo.box.minX)
                minX = geo.box.minX;

            if (minY > geo.box.minY)
                minY = geo.box.minY;

            if (minZ > geo.box.minZ)
                minZ = geo.box.minZ;
        }
        box = new Box(maxX, maxY, maxZ, minX, minY, minZ);
    }

}


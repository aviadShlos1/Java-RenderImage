package geometries;

import geometries.Intersectable;
import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    private void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}


/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR03
 * Brief: Creates the finding intersection method and implement the tests
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;
	private int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	/**
	 * @param ray ray that cross the geometry
	 * @param maxDistance - the upper bound of distance, any point which
	 *                    its distance is greater than this bound will not be returned
	 * @return list of intersection points that were found
	 */
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance){
		if (box != null && !box.IsRayHitBox(ray))
			return null;
		// First, check if there is a point of intersection with the plane
		if (plane.findGeoIntersections(ray, maxDistance) == null)
			return null;

		Point p0 = ray.getP0();
		Vector v = ray.getDir();

		List<Vector> vectors = new LinkedList<>();
		List<Vector> normalVectors = new LinkedList<>();


		for (Point pt : vertices) {
			Vector Vi = pt.subtract(p0);
			vectors.add(Vi);
		}

		int n = vectors.size() - 1;

		for (int i = 0; i < n; i++) {

			Vector Vi = vectors.get(i);
			Vector Vii = vectors.get(i + 1);

			Vector Ni = (Vi.crossProduct(Vii)).normalize();

			normalVectors.add(Ni);
		}

		Vector Vn = vectors.get(n);
		Vector V1 = vectors.get(0);

		Vector Nn = (Vn.crossProduct(V1)).normalize();

		normalVectors.add(Nn);

		List<Double> Vns = new LinkedList<>();

		for (Vector N : normalVectors) {
			double Vni = alignZero(N.dotProduct(v));
			// one Vni equals to zero is enough to determine that we have no intersection points
			if (isZero(Vni)) {
				return null;
			} else Vns.add(Vni);
		}

		// check that all the elements
		if (!Vns.stream().allMatch(i -> i > 0) && !Vns.stream().allMatch(i -> i < 0)) {
			return null;
		} else {
			Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
			return List.of(new GeoPoint(this, plane.findGeoIntersections(ray , maxDistance).get(0).point));
		}
	}

	/**
	 * Set box for polygon
	 */
	@Override
	public void setBox() {
		Point pointI;
		Point point = vertices.get(0);

		//initialize max and min in the first element of vertices list
		double maxX = point.getX();
		double maxY = point.getY();
		double maxZ = point.getZ();
		double minX = maxX;
		double minY = maxY;
		double minZ = maxZ;
		for (int i = 1; i < vertices.size(); i++) {	//loop for that find the max and min in all of the
			pointI = vertices.get(i);										//coordinate of the polygon
			if (maxX < pointI.getX())
				maxX = pointI.getX();

			if (maxY < pointI.getY())
				maxY = pointI.getY();

			if (maxZ < pointI.getZ())
				maxZ = pointI.getZ();

			if (minX > pointI.getX())
				minX = pointI.getX();

			if (minY > pointI.getY())
				minY = pointI.getY();

			if (minZ > pointI.getZ())
				minZ = pointI.getZ();
		}
		box = new Box(maxX, maxY, maxZ, minX, minY, minZ);
	}
}

/**
 * @author: Aviad Shlosberg 314960881
 *          Evyatar Levi   318753993
 * Exercise: PR01
 * Brief: Define the Primitives and the Geometries entities
 */
package primitives;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * This class presents the primitive "Ray" -
 * part of a line that has a fixed starting point but no end point. It can extend infinitely in one direction.
 */

public class Ray
{
    /**
     * @member dir - the point the Ray points to from p0
     * @member p0 - starting point of Ray
     * DELTA - Constant value defining by how much we need to move the ray's starting point
     */
    private Point p0;
    final private Vector dir;
    private static final double DELTA = 0.1;

    /**
     * Constructor to initialize Ray based object with its point and vector
     *
     * @param p0 the point
     * @param dir the vector which will be normalized
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * constructor for ray.
     * creates a new ray and moves its head in the
     * normal direction by the normal scaled by DELTA
     *
     * @param p0     - starting point
     * @param dir    - direction vector
     * @param normal - the normal defining the plane
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        this(p0, dir); // activate the current instance constructor

        // make sure the normal and the direction are not orthogonal
        double nv = alignZero(normal.dotProduct(dir));

        // if not orthogonal
        if (nv!=0) {
            // create new vector to help move the head of
            // the vector to the correct position
            Vector fixVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            // move the head of the vector in the right direction
            this.p0 = p0.add(fixVector);
        }
    }

    /**
     * Getters
     */
    public Point getP0() {
        return p0;
    }
    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) { // Function calculate - P = P0 + v * t
        return p0.add(dir.scale(t));
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray) obj;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * find the closest Point to Ray
     *
     * @param pointsList List of intersections point
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> pointsList) {
        double distance = Double.POSITIVE_INFINITY;
        Point nearPoint = null;

        if (pointsList == null) {
            return null;
        }

        for (Point p : pointsList) {
            double dis = p.distance(p0); // distance from the starting point of the ray
            if (dis < distance) {
                distance = dis;
                nearPoint = p;
            }
        }

        return nearPoint;
    }

    /**
     * find the closest GeoPoint to Ray
     *
     * @param intersections List of intersections point
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {

        double distance = Double.POSITIVE_INFINITY;

        GeoPoint nearPoint = null;

        if (intersections == null) {
            return null;
        }

        // distance => distanceSquared
        // no need to activate the Math.sqrt function
        // distance is always a positive value,

        for (GeoPoint p : intersections) {
            double dis = p.point.distanceSquared(p0); // distance from the starting point of the ray
            if (dis < distance) {
                distance = dis;
                nearPoint = p;
            }
        }
        return nearPoint;
    }

    /**
     * auxiliary function to randomly scatter points within a rectangular surface.
     * returns a list of rays which relates to the surface.
     *
     * @param numRays     - number of rays we create in the rectangular surface.
     * @param vUp         - upper vector of rectangular surface.
     * @param vRight      - right vector of rectangular surface.
     * @param dist        - of the camera from the view plane
     * @param pixelWidth  - the width of a single pixel in view plane
     * @param pixelHeight - the height of a single pixel in view plane
     * @return list of rays from the area of the pixel to the scene
     */
    public List<Ray> randomRaysInGrid(Vector vUp, Vector vRight, int numRays, double dist, double pixelWidth, double pixelHeight) {
        List<Ray> rays = new LinkedList<>();

        // the starting point of the original vector
        Point point = p0;

        // the center of the pixel
        Point pixelCenter = p0.add(dir.scale(dist));

        // calculate the maximal number of rays which we can compress in one dimension
        // if we don't make this calculation, we might add too many rays, which will decrease performance
        // if the sqrt is not an integer, it will be the floor value
        // (mostly, unless the number is x.99...99 with 15 9s or more, which will probably never happen)
        int raysInDimension = (int) Math.sqrt(numRays);

        // the size of one step from one ray to another in each dimension
        double xMove = pixelWidth / raysInDimension;
        double yMove = pixelHeight / raysInDimension;

        // the starting point is the right bottom corner of the pixel
        Point cornerOfPixel = pixelCenter
                .add(vRight.scale(pixelWidth / 2d))
                .add(vUp.scale(pixelHeight / 2d));

        Point newRayStartPoint;

        int sign = 1;
        Random rand = new Random();

        for (int i = 0; i < raysInDimension; ++i) {
            for (int j = 0; j < raysInDimension; ++j, sign *= -1) {

                newRayStartPoint = cornerOfPixel;

                // determine by how much we move the point in each iteration
                double randomMoveX = xMove / 2d + xMove * rand.nextDouble();
                double randomMoveY = yMove / 2d + yMove * rand.nextDouble();

                // move in the x dimension
                if (!isZero(i * randomMoveX)) {
                    newRayStartPoint = newRayStartPoint.add(vRight.scale((i) * randomMoveX));
                }

                // move in the y dimension
                if (!isZero(j * randomMoveY)) {
                    newRayStartPoint = newRayStartPoint.add(vUp.scale((j) * randomMoveY));
                }

                // make sure we do not add the center ray more than once
                if (!newRayStartPoint.equals(pixelCenter)) {
                    rays.add(new Ray(p0, (newRayStartPoint.subtract(p0)))); // normalized inside Ray constructor
                }

            }
        }
        return rays;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }



}

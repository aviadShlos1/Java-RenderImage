/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR04
 * Brief: Create the camera and camera test and tests camera integration between some geometries
 */
package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


/**
 *
 * Class Camera represent view of the geometric world through the view plane (which represent the picture),
 * it produces graphic views of the objects using the view plane and rays and object intersections.
 * The rays converge according to the location of the pixel centers in the view plane.
 * directions of the camera to the right, up and front  (compared to the original x,y,z axis),
 * all vectors orthogonal to each other
 */
public class Camera {
    /**
     * p0 - the camera location
     */
    private Point P0;

    /**
     * Vto - X axis vector
     */
    private Vector Vto;
    /**
     * _Vup - Y axis vector
     */
    private Vector Vup;

    /**
     * Vright - Z axis vector
     */
    private Vector Vright;

    /**
     * object's actual distance from the camera center
     */
    private double distance;
    private double width;
    private double height;

    /**
     * simple Camera constructor which get as input location point and two orthogonal vectors represent the direction
     *
     * @param p0  - the camera location
     * @param Vto - Y axis vector
     * @param Vup - X axis vector
     */
    public Camera(Point p0, Vector Vto, Vector Vup) {
        if(!isZero(Vto.dotProduct(Vup)))
            throw new IllegalArgumentException("The vectors are not orthogonal");
        this.P0 = p0;
        this.Vto = Vto.normalize();
        this.Vup = Vup.normalize();
        this.Vright = Vto.crossProduct(Vup);
    }


    /**
     * this function gets the view plane size and a selected pixel,
     * and return the ray from the camera which intersects this pixel
     *
     * @param nX - amount of rows in view plane (number of pixels)
     * @param nY - amount of columns in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the ray which goes through the pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point Pc = P0.add(Vto.scale(distance)); // image center

        // ratio (pixel width and height)
        double Rx = width / nX;
        double Ry = height / nY;

        Point Pij = Pc;
        double Yi = -Ry * (i - (nY - 1) / 2d);
        double Xj = Rx * (j - (nX - 1) / 2d);

        if (!isZero(Xj)) {
            Pij = Pij.add(Vright.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(Vup.scale(Yi));
        }

        Vector Vij = Pij.subtract(P0); // vector to pixel center

        return new Ray(P0, Vij);
    }

////////////// getters & setters

    /**
     * get the location point of camera
     *
     * @return p0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * get the forward vector of the camera (represent the z axis of camera, forward direction of the view)
     *
     * @return Vto
     */
    public Vector getVto() {
        return Vto;
    }
    /**
     * get the upper vector of the camera (represent the y axis of camera, position of the view)
     *
     * @return Vup
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * get the right vector of the camera (represent the x axis of camera, position of the view)
     *
     * @return Vright
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * get distance of camera from scene
     *
     * @return the object's actual distance from the camera center
     */
    public double getDistance() {
        return distance;
    }
    /**
     * get width of camera
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }
    /**
     * get height of camera
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }



    /**
     * setter - chaining method
     *
     * @param width   - the width of the view plane
     * @param height- the height of the view plane
     * @return the camera with the configured view plane
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * setter - chaining method
     *
     * @param distance - the object's actual distance from the camera center
     * @return the camera with the configured distance
     */
    public Camera setViewPlaneDistance(double distance) {
        this.distance = distance;
        return this;
    }



}

/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR05
 * Brief: Support color, add scheme and building image with ambient light
 */
package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.stream.*;

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
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBasic;
    /**
     * number of rays in a single pixel for active super sampling
     */
    private int numberOfRaysInPixel;
    /**
     * bolean value to determine anti aliasing
     */
    private boolean AntiAliasing;


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
    public Ray constructRay(int nX, int nY, int j, int i) {
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
    /**
     * this function gets the view plane size and a selected pixel,
     * and return the rays from the view plane
     *
     * @param nX - amount of columns in view plane (number of pixels)
     * @param nY - amount of rows in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the list of rays which goes from the pixel
     */
    public List<Ray>  constructRays(int nX, int nY, int j, int i) {

        // the returned list of rays
        List<Ray> rays = new ArrayList<>();

        // add the center ray to the list
        Ray centerRay = constructRay(nX, nY, j, i);
        rays.add(centerRay);

        // calculate the actual size of a pixel
        // pixel height is the division of the view plane height in the number of rows of pixels
        double pixelHeight = alignZero(height / nY);   //  Ry = h/Ny
        // pixel width is the division of the view plane width in the number of columns of pixels
        double pixelWidth = alignZero(width / nX);   //  Rx = w/Nx

        if (numberOfRaysInPixel != 1) {
            rays.addAll(centerRay.randomRaysInGrid(
                    Vup,
                    Vright,
                    numberOfRaysInPixel,
                    distance,
                    pixelWidth,
                    pixelHeight)
            );
        }
        return rays;
    }
    /**
     * Cast ray from camera in order to color a pixel
     * @param col - pixel's column number (pixel index in row)
     * @param row - pixel's row number (pixel index in column)
     */
    private Color castRay(int col,int row)
    {
        Ray rayForCast= constructRay(imageWriter.getNx(), imageWriter.getNy(), col,row);
        return rayTracerBasic.traceRay(rayForCast);
    }
    /**
     * Cast beam of rays from the pixel in the view plane to the focal point in the focal plane
     *
     * @param nX  - resolution on X axis (number of pixels in row)
     * @param nY  - resolution on Y axis (number of pixels in column)
     * @param col - pixel's column number (pixel index in row)
     * @param row - pixel's row number (pixel index in column)
     */
    private Color castBeam(int nX, int nY, int col, int row) {
        List<Ray> rays = constructRays(nX, nY, col, row);

        List<Color> colors = new LinkedList<>();
        for (Ray ray : rays) {
            colors.add(rayTracerBasic.traceRay(ray));
        }
        return Color.avgColor(colors);
    }

    public void renderImage() {
        final int nX= imageWriter.getNx();
        final int nY= imageWriter.getNy();
        double INTERVAL=100;
        if (imageWriter == null)
            throw new MissingResourceException("Error: you missed", "Camera", "imageWriter");
        if (rayTracerBasic == null)
            throw new MissingResourceException("Error: you missed", "Camera", "rayTracerBasic");

        Pixel.initialize(nY, nX, INTERVAL);
        IntStream.range(0, nY).parallel().forEach(i -> {
            IntStream.range(0, nX).parallel().forEach(j -> {
                castBeam(nX, nY, j, i);
                Pixel.pixelDone();
                Pixel.printPixel();
            });
        });
    }
    /**
     * Create a grid [over the picture] in the pixel color map. given the grid's
     * interval and color. Colors only the grid lines
     *
     * @param interval  grid's step
     * @param color grid's color
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Error: you missed", "Camera", "imageWriter");

        for (int i=0; i< imageWriter.getNx();i++){
            for (int j=0; j< imageWriter.getNy();j++){
                if(i%interval==0  || j%interval==0 )
                    imageWriter.writePixel(i,j,color);
            }
        }
    }
    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Error: you missed", "Camera", "imageWriter");
        imageWriter.writeToImage();
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
     * get the right vector of the camera (represent the x axis of camera, position of the view).
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

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter=imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic=rayTracerBasic;
        return this;
    }

    /**
     * setter - chaining method
     *
     * @param numberOfRaysInPixel - number of rays in a pixel
     * @return the camera with the configured number of rays
     */
    public Camera setNumberOfRaysInPixel(int numberOfRaysInPixel) {
        this.numberOfRaysInPixel = numberOfRaysInPixel;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param AntiAliasing - does the picture has Anti Aliasing
     * @return the camera with the configured AA
     */
    public Camera setAntiAliasing(boolean AntiAliasing) {
        this.AntiAliasing = AntiAliasing;
        return this;
    }
//
//    /** /////////////////////// turn camera functions for bonus ///////////////////
//     *
//     */
//    /**
//     * function to set new camera location
//     *
//     * @param x - added amount to the x coordinates
//     * @param y - added amount to the y coordinates
//     * @param z - added amount to the z coordinates
//     * @return the camera in its new position
//     */
//
//    public Camera moveCamera(double x, double y, double z) {
//
//        this.P0 = new Point(P0.getX() + x, P0.getY() + y, P0.getZ() + )z;
//
//        return this;
//    }
//
//
//    /**
//     * function to set new direction vectors to the camera
//     *
//     * @param Vup - new Vup vector
//     * @param Vto - n
//     * @return the camera after the rotation
//     */
//    public Camera turnCamera(Vector Vto, Vector Vup) {
//        Camera newcam = new Camera(this.P0, Vto, Vup);
//        return newcam;
//    }
//
//    /**
//     * function to set new direction vectors to the
//     * camera according to a rotating axis
//     *
//     * @param axis  - turning axis
//     * @param theta - angle to turn the camera
//     * @return this instance
//     */
//    public Camera rotateCamera(Vector axis, double theta) {
//        if (theta == 0) return this;
//        this.Vup.rotateVector(axis, theta);
//        this.Vright.rotateVector(axis, theta);
//        this.Vto.rotateVector(axis, theta);
//        return this;
//    }
}

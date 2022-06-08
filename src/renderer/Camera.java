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
    private final Point P0;

    /**
     * Vto - X axis vector
     */
    private final Vector Vto;
    /**
     * _Vup - Y axis vector
     */
    private final Vector Vup;

    /**
     * Vright - Z axis vector
     */
    private final Vector Vright;

    /**
     * object's actual distance from the camera center
     */
    private double distance;
    private double width;
    private double height;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBasic;
    /**
     * boolean value to determine adaptive super sampling
     */
    boolean multiSampling = false;
    /**
     * number of rays in a single pixel for active super sampling
     */
    private int numberOfRaysInPixel;
    /**
     * boolean value to determine anti aliasing
     */
    private boolean antiAliasing = false;

    /**
     *  the depth level of the recursion
     */
    private int MAX_LEVEL = 4;
    /**
     * number of threads for the rendering method
     */
    private int threadsCount = 0;
    /**
     * number of spare threads for the rendering method
     * Spare threads if trying to use all the cores
     */
    private static final int SPARE_THREADS = 2;


    //region Multithreading
    /**
     * Set multi-threading <br>
     * - if the parameter is 0 - number of cores less 2 is taken
     *
     * @param threads number of threads
     * @return the Camera object itself
     */
    public Camera setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads != 0)
            this.threadsCount = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsCount = cores <= 2 ? 1 : cores;
        }
        return this;
    }
//endregion

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
    public List<Ray> constructRays(int nX, int nY, int j, int i) {

        // the returned list of rays
        List<Ray> rays = new ArrayList<>();

        // add the center ray to the list
        Ray centerRay = constructRay(nX, nY, j, i);
        rays.add(centerRay);

        // calculate the actual size of a pixel height
        // is the division of the view plane height in the number of rows of pixels
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
    private void castRay(int col,int row)
    {
        Ray rayForCast= constructRay(imageWriter.getNx(), imageWriter.getNy(), col,row);
        Color color= rayTracerBasic.traceRay(rayForCast);
        imageWriter.writePixel(col,row, color);
    }
    /**
     * Cast beam of rays from the pixel in the view plane to the focal point in the focal plane
     *
     * @param nX  - resolution on X axis (number of pixels in row)
     * @param nY  - resolution on Y axis (number of pixels in column)
     * @param col - pixel's column number (pixel index in row)
     * @param row - pixel's row number (pixel index in column)
     */
    private void castBeam(int nX, int nY, int col, int row) {
        List<Ray> rays = constructRays(nX, nY, col, row);

        List<Color> colors = new LinkedList<>();
        for (Ray ray : rays) {
            colors.add(rayTracerBasic.traceRay(ray));
        }
        Color colorAvg= Color.avgColor(colors);
        imageWriter.writePixel(col,row, colorAvg);
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Error: you missed", "Camera", "imageWriter");
        if (rayTracerBasic == null)
            throw new MissingResourceException("Error: you missed", "Camera", "rayTracerBasic");

        final int nX= imageWriter.getNx();
        final int nY= imageWriter.getNy();

        if (threadsCount == 0)
        {
            // check whether the camera has AA effect turned on,
            // if it is not, proceed as usual (cast a single ray)
            // if it is on, cast a beam, instead of a single ray
            if (antiAliasing)
            {
                for (int i = 0; i < nY; ++i) {
                    for (int j = 0; j < nX; ++j) {
                        castBeam(nX, nY, j, i);
                    }
                }
            }
            else
            {
                for (int i = 0; i < nY; ++i) {
                    for (int j = 0; j < nX; ++j) {
                        castRay(nX, nY);
                    }
                }
            }
        }
        else
        {
           renderImageWithTreads();
        }
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object - with multi-threading
     */
    public void renderImageWithTreads() {
        // In case that not all the fields are filled
        if (imageWriter == null || rayTracerBasic == null)
            throw new MissingResourceException("Missing", "resource", "exception");

        // The nested loop finds and creates a ray for each pixel, finds its color and
        // writes it to the image pixels
        int nY = this.imageWriter.getNy();
        int nX = this.imageWriter.getNx();

        double printInterval = 0.01;
        Pixel.initialize(nY, nX, printInterval);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    if(antiAliasing)
                        castBeam(nX, nY, pixel.col, pixel.row);
                    else if(multiSampling) {
                        adaptive(getCenterPixel(nX,nY,pixel.col, pixel.row),nX,nY);
                    }
                    else{
                        castRay(nX,nY);
                    }
            }).start();
        }
        Pixel.waitToFinish();
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
     * @param antiAliasing - does the picture has Anti Aliasing
     * @return the camera with the configured AA
     */
    public Camera setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }

    /**
     * setter for adaptive super sampling improvement activation
     * @return this ray tracer (chaining method)
     */
    public Camera setMultiSampling(boolean multiSampling) {
        this.multiSampling = multiSampling;
        return this;
    }

//region Adaptive Super Sampling
    /**
     * setter for maxAdaptiveLevel
     *
     * @param MAX_LEVEL- The depth of the recursion
     */
    public Camera setMAX_LEVEL(int MAX_LEVEL) {
        this.MAX_LEVEL = MAX_LEVEL;
        return this;
    }

    /**
     * get the point and return the color of the ray to this point
     *
     * @param p- point on the view plane
     * @return color of this point
     */
    private Color calcPointColor(Point p) {
        return rayTracerBasic.traceRay(new Ray(P0, p.subtract(P0)));
    }
    /**
     * calculate the point the ray intersect on the view plan - the center of pixel
     *
     * @param nX- Resolution of view plane x-axis
     * @param nY- Resolution of view plane y-axis
     * @param j-  number of columns of the pixel
     * @param i-  number of rows of the pixel
     * @return the point the ray intersect on the view plan
     */
    private Point getCenterPixel(int nX, int nY, int j, int i) {
        double heightPix = height / nY;
        double widthPix = width / nX;

        Point centerPix = P0.add(Vto.scale(distance)); // The center of the View Plane

        double yI = -(i - (nY - 1d) / 2) * heightPix;
        double xJ = (j - (nX - 1d) / 2) * widthPix;
        if (yI != 0) centerPix = centerPix.add(Vup.scale(yI));
        if (xJ != 0) centerPix = centerPix.add(Vright.scale(xJ));
        return centerPix;
    }

    /**
     * calculate average color of the pixel by using adaptive Super-sampling
     * @param center- the center of the pixel
     * @param nY- number of pixels to width
     * @param nX- number of pixels to length
     * @return  the average color of the pixel
     */
    private Color adaptive(Point center, double nX, double nY) {
        double rY = height / nY / 2;
        double rX = width / nX / 2;
        Color upRight = calcPointColor(center.add(Vup.scale(rY)).add(Vright.scale(rX)));
        Color upLeft = calcPointColor(center.add(Vup.scale(rY)).add(Vright.scale(-rX)));
        Color downRight = calcPointColor(center.add(Vup.scale(-rY)).add(Vright.scale(rX)));
        Color downLeft = calcPointColor(center.add(Vup.scale(-rY)).add(Vright.scale(-rX)));

        return adaptiveHelper(1, center, rX, rY, upLeft, upRight, downLeft, downRight);
    }

    /**
     * recursive method that return the average color of the pixel- by checking the color of the four corners
     * @param max- the depth of the recursion
     * @param center- the center of the pixel
     * @param rX- the width of the pixel
     * @param rY- the height of the pixel
     * @param upLeftCol- the color of the up left corner
     * @param upRightCol- the color of the up right corner
     * @param downLeftCol- the color of the down left corner
     * @param downRightCol - the color of the down right corner
     * @return the average color of the pixel
     */
    private Color adaptiveHelper(int max, Point center, double rX, double rY,
                                 Color upLeftCol, Color upRightCol, Color downLeftCol, Color downRightCol) {

        if (max == MAX_LEVEL) {
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
        if (upRightCol.equals(upLeftCol) && downRightCol.equals(downLeftCol) && downLeftCol.equals(upLeftCol))
            return upRightCol;
        else {
            Color rightPCol = calcPointColor(center.add(Vright.scale(rX)));
            Color leftPCol = calcPointColor(center.add(Vright.scale(-rX)));
            Color upPCol = calcPointColor(center.add(Vup.scale(rY)));
            Color downPCol = calcPointColor(center.add(Vup.scale(-rY)));
            Color centerCol = calcPointColor(center);

            rX = rX / 2;
            rY = rY / 2;
            upLeftCol = adaptiveHelper(max + 1, center.add(Vup.scale(rY / 2))
                    .add(Vright.scale(-rX / 2)), rX, rY, upLeftCol, upPCol, leftPCol, centerCol);
            upRightCol = adaptiveHelper(max + 1, center.add(Vup.scale(rY / 2))
                    .add(Vright.scale(rX / 2)), rX, rY, upPCol, upRightCol, centerCol, leftPCol);
            downLeftCol = adaptiveHelper(max + 1, center.add(Vup.scale(-rY / 2))
                    .add(Vright.scale(-rX / 2)), rX, rY, leftPCol, centerCol, downLeftCol, downPCol);
            downRightCol = adaptiveHelper(max + 1, center.add(Vup.scale(-rY / 2))
                    .add(Vright.scale(rX / 2)), rX, rY, centerCol, rightPCol, downPCol, downRightCol);
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
    }
//endregion Adaptive Super Sampling



















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

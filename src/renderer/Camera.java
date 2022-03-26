package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    /**
     * zeroPoint - the camera location
     */
    private Point p0;

    /**
     * X axis vector
     */
    private Vector Vto;
    /**
     * _Vup - Y axis vector
     */
    private Vector Vup;

    /**
     * Z axis vector
     */
    private Vector Vright;

    /**
     * object's actual distance from the camera center
     */
    private double distance;
    private double width;
    private double height;

    /**
     * C-tor
     * @param p0
     * @param Vto
     * @param Vup
     */
    public Camera(Point p0, Vector Vto, Vector Vup) {
        this.p0 = p0;
        this.Vto = Vto.normalize();
        this.Vup = Vup.normalize();
        if(Vto.dotProduct(Vup)!=0)
            throw new IllegalArgumentException("The vectors are not orthogonal");
        this.Vright = Vto.crossProduct(Vup);
    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        return  null;
    }

    public Vector getVto() {
        return Vto;
    }

    public Vector getVup() {
        return Vup;
    }

    public Vector getVright() {
        return Vright;
    }

    public Point getP0() {
        return p0;
    }

    public double getDistance() {
        return distance;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


}

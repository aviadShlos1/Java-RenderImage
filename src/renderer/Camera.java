package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;
    private Point zeroPoint;
    private double distance;
    private double width;
    private double height;

    /**
     * C-tor
     * @param zeroPoint
     * @param Vto
     * @param Vup
     */
    public Camera(Point zeroPoint, Vector Vto, Vector Vup) {
        this.zeroPoint =zeroPoint;
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

    public Ray constructRay(int nX, int nY, int j, int i) {
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

    public Point getZeroPoint() {
        return zeroPoint;
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

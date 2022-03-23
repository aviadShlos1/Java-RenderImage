package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;
    private Point location;
    private double distance;
    private double width;
    private double height;

    public Camera(Point zeroPoint, Vector Vto, Vector Vup) {
        this.Vto = Vto.normalize();
        //TODO: implement
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;

    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
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

    public Point getLocation() {
        return location;
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

    public Ray constructRay(int nx, int i1, int i2, int i3) {
        return  null;
    }
}

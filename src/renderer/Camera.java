package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private final Vector vT0;

    private double distance;
    private double width;
    private double height;

    public Camera(Point zeroPoint, Vector vTo, Vector vUp) {
        this.vT0 = vTo.normalize();
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

    public Ray constructRay(int nx, int i1, int i2, int i3) {
        return  null;
    }
}

/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR04
 * Brief: Creating the camera and the camera integration between some geometries, and testing the cases.
 */
package unittests.renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import geometries.Intersectable.GeoPoint;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for different integrations with camera
 */
public class CameraIntegrationsTest {
    // Magic Numbers
    private static final int ROW = 3;
    private static final int COL = 3;

    private void testPoints(Geometry geo, Camera cam, int points) {

        List<GeoPoint> allPoints = null;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Ray ray = cam.constructRay(ROW, COL, j, i);
                List<GeoPoint> intersections = geo.findGeoIntersections(ray);

                if (intersections != null) {
                    if (allPoints == null) {
                        allPoints = new LinkedList<>();
                    }
                    allPoints.addAll(intersections);
                }
            }
        }

        // check if the amount of intersection points is correct
        if (points == 0) {
            assertNull(allPoints);
        } else {
            assertEquals(points ,allPoints.size() , "wrong number of intersections");
        }
    }

    static final Point ZERO_POINT = new Point(0, 0, 0);
    Camera cam1 = new Camera(
            Point.ZERO,                   // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, 1, 0))    // Vto
            .setViewPlaneDistance(1)
            .setViewPlaneSize(3, 3);

    Camera cam2 = new Camera(
            new Point(0, 0, 0.5), // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, 1, 0))    // Vto
            .setViewPlaneDistance(1)
            .setViewPlaneSize(3, 3);

    Camera cam3 = new Camera(
            Point.ZERO,                   // camera center
            new Vector(0, 0, -1),   // Vup
            new Vector(0, -1, 0))   // Vto
            .setViewPlaneDistance(1)
            .setViewPlaneSize(3, 3);

    /**
     * Test method for spheres
     */
    @Test
    void testSphereWithCam() {

        // =================================================================================
        // Sphere 1 test (slide 14)
        Sphere sphere1 = new Sphere(new Point(0, 0, -3), 1);
        testPoints(sphere1, cam1, 2);
        // =================================================================================


        // =================================================================================
        // Sphere 2 test (slide 15)
        Sphere sphere2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        testPoints(sphere2, cam2, 18);
        // =================================================================================

        // =================================================================================
        // Sphere 3 test (slide 16)
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 2);
        testPoints(sphere3, cam2, 10);
        // =================================================================================

        // =================================================================================
        // Sphere 4 test (slide 17)
        Sphere sphere4 = new Sphere(new Point(0, 0, -1), 4);
        testPoints(sphere4, cam1, 9);
        // =================================================================================

        // =================================================================================
        // Sphere 5 test (slide 18)
        Sphere sphere5 = new Sphere(new Point(0, 0, 1), 0.5);
        testPoints(sphere5, cam1, 0);
        // =================================================================================
    }

    /**
     * Test method for planes
     */
    @Test
    void testPlaneWithCam() {

        // =================================================================================
        // Plane 1 test (slide 19)
        Plane plane1 = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1));
        testPoints(plane1, cam3, 9);
        // =================================================================================

        // =================================================================================
        // Plane 2 test (slide 20)
        Plane plane2 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 2));
        testPoints(plane2, cam3, 9);
        // =================================================================================

        // =================================================================================
        // Plane 3 test (slide 21)
        Plane plane3 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        testPoints(plane3, cam3, 6);
        // =================================================================================
    }

    /**
     * Test method for triangles
     */
    @Test
    void testTriangleWithCam() {

        // =================================================================================
        // Triangle 1 test (slide 22)
        Triangle triangle1 = new Triangle(
                new Point(0, 1, -2),
                new Point(-1, -1, -2),
                new Point(1, -1, -2));
        testPoints(triangle1, cam1, 1);
        // =================================================================================

        // =================================================================================
        // Triangle 2 test (slide 23)
        Triangle triangle2 = new Triangle(
                new Point(0, 20, -2),
                new Point(-1, -1, -2),
                new Point(1, -1, -2));
        testPoints(triangle2, cam1, 2);
        // =================================================================================

    }

}

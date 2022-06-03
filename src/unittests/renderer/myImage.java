/**
 *
 */
package unittests.renderer;
import org.junit.jupiter.api.Test;
import static java.awt.Color.*;
import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;


public class myImage {
    Scene myScene = new Scene("firstImage");

    /**
     * Creates a basic Billiard image with a couple of elements using lights, shadows and refraction techniques.
     */
    @Test
    public void MP1() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setViewPlaneSize(200, 200)
                .setViewPlaneDistance(1000)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(10)
                .setMultithreading(3);

        myScene.setAmbientLight(new AmbientLight
                        (new Color(MAGENTA), new Double3(0.2))).
                setBackground(new Color(BLACK));

        myScene.geometries.add(//
                // The flat table
                new Polygon(
                        new Point(100, 0, -100),
                        new Point(0, 100, -100),
                        new Point(-100, 0, -100),
                        new Point(0, -100, -100))
                        .setEmission(new Color(0, 128, 0))//
                        .setMaterial(new Material()
                                .setKd(0.5)
                                .setKs(0.5)
                                .setShininess(60)), ///

                new Sphere(new Point(0, 56, -50), 7d)
                        .setEmission(new Color(BLACK)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(7, 44, -50), 7d)
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))
                        ),

                new Sphere(new Point(-7, 44, -50), 7d)
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(-14, 31, -50), 7d)
                        .setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(-0.5, 31, -50), 7d)
                        .setEmission(new Color(0, 153, 230)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(14, 31, -50), 7d)
                        .setEmission(new Color(102, 0, 102)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(-0.5, -10, -25), 7d)
                        .setEmission(new Color(WHITE)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),
                //The starter
                new Cylinder(3, new Ray(new Point(-0.5, -100, -25), new Vector(0, 4, 1)), 60)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(77, 40, 0)),
                //edge right
                new Cylinder(1.5, new Ray(new Point(-0.5, 69, 0), new Vector(0.55, -0.88, 0)), 56)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51, 26, 0).reduce(2)),
                //edge left
                new Cylinder(1.5, new Ray(new Point(-27, 23, 2), new Vector(3, 5, 2)), 55)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51, 26, 0).reduce(2)),
                //edge bottom
                new Cylinder(1.5, new Ray(new Point(-29, 22, -25), new Vector(10, 0, 1)), 60)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51, 26, 0).reduce(2)),


                //region moon

                new Sphere(new Point(-75, 80, 80), 10)
                        .setMaterial(new Material()
                                .setKd(0.005)
                                .setKs(0.00005)
                                .setShininess(50)
                                .setKt(0.9))
                        .setEmission(Color.YELLOW.add(Color.WHITE.reduce(6))),


                //endregion

                //region stars
                new Sphere(new Point(-75, 60, 80), 2)
                        .setMaterial(new Material()
                                .setKd(0.005)
                                .setKs(0.00005)
                                .setShininess(100)
                                .setKt(0.9))
                        .setEmission(Color.YELLOW.reduce(4)));


        //moon
        myScene.lights.add(
                new PointLight(
                        Color.YELLOW.reduce(6),
                        new Point(-75, 80, 80)));

        myScene.lights.add( ///
                new SpotLight(new Color(YELLOW), new Point(10, 20, -10), new Vector(1, 1, 0), 5) //
                        .setkL(0.0004)
                        .setkQ(0.000006));

        myScene.lights.add(
                new PointLight(new Color(WHITE).reduce(3.2), new Point(10, 0, 15), 4)
                        .setkL(0.00005)
                        .setkQ(0.0000012));

        myScene.lights.add(
                new DirectionalLight(new Color(GRAY).reduce(10), new Vector(1, 1, -10)));


        camera.setImageWriter(new ImageWriter("BilliardWithAA_SH", 500, 500))
                .setRayTracer(
                        new RayTracerBasic(myScene)
                                .setMIN_SHADOW_POINTS(30)); //
        camera.renderImageWithTreads(); //
        camera.writeToImage();
    }

    @Test
    public void MP2()
    {
        Camera camera2 = new Camera(new Point(0,30,0), new Vector(1,0,0), new Vector(0,1,0))
                .setViewPlaneDistance(50)
                .setViewPlaneSize(200, 200)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(10)
                .setMultithreading(3);
        Geometries floor;
        /**
         * create the floor for the scene
         */
        floor = new Geometries();
        double z = 80;
        for(int i = 0, k = 0; i < 20; i++, k++){
            double x = 10;
            for(int j = 0; j < 10; j++, k++){
                floor.add(new Polygon(
                        new Point(x, 0, z + 10),
                        new Point(x + 10,0, z + 10),
                        new Point(x + 10, 0, z),
                        new Point(x, 0, z))
                        .setEmission(k % 2 == 0 ? new Color(195, 148, 171) : new Color(163, 163, 117))
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.6)
                                .setKs(1)));
                x += 10;
            }
            z -= 10;
        }

        myScene.geometries.add(floor);
        myScene.lights.add(
                new SpotLight(new Color(244, 248, 176),
                        new Point(70, 105, 0),
                        new Vector(0,-1,0) , 2)
                        .setkQ(0.00000001)
                        .setkL(0.0000001));

        myScene.lights.add(new PointLight(
                        new Color(226, 218, 198),
                        new Point(100, 1000, 0) , 2)
                        .setkL(0.000005)
                        .setkQ(0.000001));

//        myScene.setAmbientLight(new AmbientLight
//                        (new Color(MAGENTA), new Double3(0.2)));

        camera2.setImageWriter(new ImageWriter("Room", 500, 500))
                .setRayTracer(
                        new RayTracerBasic(myScene)
                                .setMIN_SHADOW_POINTS(30)); //
        camera2.renderImageWithTreads(); //
        camera2.writeToImage();
    }
}

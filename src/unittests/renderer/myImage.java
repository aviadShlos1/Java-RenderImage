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

import java.util.Random;


public class myImage {
    Scene myScene = new Scene("firstImage");

    /**
     * Creates a basic Billiard image with a couple of elements using lights, shadows and refraction techniques.
     */
    @Test
    public void createFirstImage() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0 , 1, 0))
                .setViewPlaneSize(200, 200)
                .setViewPlaneDistance(1000)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(2);
        myScene.setAmbientLight(new AmbientLight
                (new Color(MAGENTA),new Double3(0.2))).
                setBackground(new Color(BLACK));

        myScene.geometries.add(//
                // The flat table
                new Polygon(
                        new Point(100, 0, -100),
                        new Point(0, 100, -100),
                        new Point(-100, 0, -100),
                        new Point(0,-100,-100))
                        .setEmission(new Color(0,128,0))//
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
                new Cylinder(3, new Ray(new Point(-0.5, -100, -25), new Vector(0, 4, 1)),60)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(77, 40, 0)),
                //edge right
                new Cylinder(1.5, new Ray(new Point(-0.5, 69, 0), new Vector(0.55, -0.88, 0)),56)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51,26,0).reduce(2)),
                //edge left
                new Cylinder(1.5, new Ray(new Point(-27, 23, 2), new Vector(3, 5, 2)),55)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51,26,0).reduce(2)),
                //edge bottom
                new Cylinder(1.5, new Ray(new Point(-29 ,22, -25), new Vector(10, 0, 1)),60)
                        .setMaterial(new Material()
                                .setShininess(100)
                                .setKd(0.7)
                                .setKs(0.5)
                                .setKt(0.4))
                        .setEmission(new Color(51,26,0).reduce(2)),


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
                        .setEmission(Color.YELLOW.reduce(4) ));


//                // cube:
//                new Polygon(new Point(-80, -80, 150), new Point(-50, -80, 150), new Point(-50, -80, 120),
//                        new Point(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
//                new Polygon(new Point(-50, -80, 150), new Point(-50, -50, 150), new Point(-50, -50, 120),
//                        new Point(-50, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
//                new Polygon(new Point(-80, -50, 150), new Point(-50, -50, 150), new Point(-50, -50, 120),
//                        new Point(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
//                new Polygon(new Point(-80, -80, 150), new Point(-80, -50, 150), new Point(-80, -50, 120),
//                        new Point(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
//                new Polygon(new Point(-80, -80, 120), new Point(-50, -80, 120), new Point(-50, -50, 120),
//                        new Point(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Cylinder(20,new Ray(new Point(80, -65, 150), new Vector(0, 0, -1)) , 20)
//                        .setEmission(new primitives.Color(java.awt.Color.BLUE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
            //moon
        myScene.lights.add(
                new PointLight(
                Color.YELLOW.reduce(6),
                 new Point(-75, 80, 80)));

        myScene.lights.add( ///
                new SpotLight(new Color(YELLOW), new Point(10, 20, -10), new Vector(1, 1, 0),5) //
                        .setkL(0.0004)
                        .setkQ(0.000006));

        myScene.lights.add(
                new PointLight(new Color(WHITE).reduce(3.2), new Point(10, 0, 15),4)
                        .setkL(0.00005)
                        .setkQ(0.0000012));

        myScene.lights.add(
                new DirectionalLight(new Color(GRAY).reduce(10), new Vector(1, 1, -10)));


        camera.setImageWriter(new ImageWriter(  "BilliardWithAA_SH", 500, 500))
                .setRayTracer(
                        new RayTracerBasic(myScene)
                                .setMIN_SHADOW_POINTS(2)); //
        camera.renderImage(); //
        camera.writeToImage();
    }








    /**
     * soft shdow test
     */
    @Test
    public void SoftShadowTest() {
        Scene scene = new Scene("Test scene");
        Camera camera2 = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
                .setViewPlaneDistance(1000).setViewPlaneSize(300, 300);
        scene.setBackground(primitives.Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Polygon(new Point(-400, -400, 150), new Point(400, -400, 150), new Point(400, 400, 150),
                        new Point(-400, 400, 150)).setEmission(new primitives.Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)), //
                new Sphere(new Point(0, 0, 130), 20).setEmission(new primitives.Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), // //
                // cube:
                new Polygon(new Point(-80, -80, 150), new Point(-50, -80, 150), new Point(-50, -80, 120),
                        new Point(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point(-50, -80, 150), new Point(-50, -50, 150), new Point(-50, -50, 120),
                        new Point(-50, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point(-80, -50, 150), new Point(-50, -50, 150), new Point(-50, -50, 120),
                        new Point(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point(-80, -80, 150), new Point(-80, -50, 150), new Point(-80, -50, 120),
                        new Point(-80, -80, 120)).setEmission(new primitives.Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point(-80, -80, 120), new Point(-50, -80, 120), new Point(-50, -50, 120),
                        new Point(-80, -50, 120)).setEmission(new primitives.Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Cylinder(20,new Ray(new Point(80, -65, 150), new Vector(0, 0, -1)) , 20)
                        .setEmission(new primitives.Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));

        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400), //
                new Point(0, -80, 80)).setkC(1).setkL(4E-4).setkQ(2E-5).setRadius(25));
        int p = 700;

        camera2.setImageWriter(new ImageWriter(  "tank", 700, 700))
                .setRayTracer(
                        new RayTracerBasic(myScene)
                                .setMIN_SHADOW_POINTS(50)); //
        camera2.renderImage(); //
        camera2.writeToImage();
    }

}
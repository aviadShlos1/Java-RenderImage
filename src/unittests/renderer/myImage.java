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
        Camera camera3 = new Camera(new Point(0, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200) //
                .setViewPlaneDistance(110)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(10)
                .setMultithreading(3);


            Geometry door1 = new Polygon(new Point(0,31,0),  new Point(0,61,0),
                    new Point(15,61,0),  new Point(15,31,0))
                    .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(400));

            Geometry middle1 = new Polygon(new Point(0,0,0),  new Point(-1,0,0),
                    new Point(-1,61,0),  new Point(0,61,0))
                    .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

            Geometry door2 = new Polygon(new Point(-1,31,0),  new Point(-1,61,0),
                    new Point(-16,61,0),  new Point(-16,31,0))
                    .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(400));
            Geometry middle2 = new Polygon(new Point(-16,0,0),  new Point(-17,0,0),
                    new Point(-17,61,0),  new Point(-16,61,0))
                    .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            /////
            Geometry middle3 = new Polygon(new Point(15,31,0),  new Point(15,29,0),
                    new Point(-16,29,0),  new Point(-16,31,0))
                    .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry middle4 = new Polygon(new Point(-32,0,0),  new Point(-33,0,0),
                    new Point(-33,61,0),  new Point(-32,61,0))
                    .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry door3 = new Polygon(new Point(0,30,0),  new Point(0,0,0),
                    new Point(15,0,0),  new Point(15,30,0))
                    .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(400));
            Geometry door4 = new Polygon(new Point(-1,30,0),  new Point(-1,0,0),
                    new Point(-16,0,0),  new Point(-16,30,0))
                    .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(400));

            Geometry door5 = new Polygon(new Point(-17,61,0),  new Point(-17,0,0),
                    new Point(-32,0,0),  new Point(-32,61,0))
                    .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(300).setKr(1));
            Geometry door6 = new Polygon(new Point(-33,61,0),  new Point(-33,0,0),
                    new Point(-48,0,0),  new Point(-48,61,0))
                    .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(300).setKr(1));
            Geometry floor= new Plane(new Point(0,0,0),new Vector(0,1,0))
                    .setEmission(new Color(java.awt.Color.GRAY))
                    .setMaterial(new Material().setKd(0.4).setKs(0.05).setShininess(100));
            Geometry roof= new Plane(new Point(0,80,0),new Vector(0,1,0))
                    .setEmission(new Color(java.awt.Color.WHITE).scale(0.3))
                    .setMaterial(new Material().setKd(0.7));
            Geometry wallRight= new Plane(new Point(35,0,0),new Vector(1,0,0))
                    .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                    .setMaterial(new Material().setKd(0.5));
            Geometry wallLeft= new Plane(new Point(-100,0,0),new Vector(1,0,0))
                    .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry wallBehind= new Plane(new Point(0,0,200),new Vector(0,0,1))
                    .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                    .setMaterial(new Material().setKd(0.5));
            Geometry wallFront= new Plane(new Point(0,0,-20),new Vector(0,0,1))
                    .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                    .setMaterial(new Material().setKd(0.5));

            Geometry handle1 = new Sphere(new Point(2,33,1),1)
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry handle2 = new Sphere(new Point(2,28,1),1)
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry handle3 = new Sphere(new Point(-14,33,1),1)
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry handle4 = new Sphere(new Point(-14,28,1),1)
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));


            //foot1
            Geometry foot11 = new Polygon(new Point(-40,0,60),new Point(-40,0,62),
                    new Point(-40,20,62),new Point(-40,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot12 = new Polygon(new Point(-40,0,62),new Point(-42,0,62),
                    new Point(-42,20,62),new Point(-40,20,62))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot13 = new Polygon(new Point(-42,0,60),new Point(-42,0,62),
                    new Point(-42,20,62),new Point(-42,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot14 = new Polygon(new Point(-40,0,60),new Point(-42,0,60),
                    new Point(-42,20,60),new Point(-40,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));

            //foot2

            Geometry foot21 = new Polygon(new Point(-40,0,80),new Point(-40,0,82),
                    new Point(-40,20,82),new Point(-40,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot22 = new Polygon(new Point(-40,0,82),new Point(-42,0,82),
                    new Point(-42,20,82),new Point(-40,20,82))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot23 = new Polygon(new Point(-42,0,80),new Point(-42,0,82),
                    new Point(-42,20,82),new Point(-42,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot24 = new Polygon(new Point(-40,0,80),new Point(-42,0,80),
                    new Point(-42,20,80),new Point(-40,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            //foot3
            Geometry foot31 = new Polygon(new Point(-70,0,60),new Point(-70,0,62),
                    new Point(-70,20,62),new Point(-70,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot32 = new Polygon(new Point(-70,0,62),new Point(-72,0,62),
                    new Point(-72,20,62),new Point(-70,20,62))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot33 = new Polygon(new Point(-72,0,60),new Point(-72,0,62),
                    new Point(-72,20,62),new Point(-72,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot34 = new Polygon(new Point(-70,0,60),new Point(-72,0,60),
                    new Point(-72,20,60),new Point(-70,20,60))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            //foot4
            Geometry foot41 = new Polygon(new Point(-70,0,80),new Point(-70,0,82),
                    new Point(-70,20,82),new Point(-70,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot42 = new Polygon(new Point(-70,0,82),new Point(-72,0,82),
                    new Point(-72,20,82),new Point(-70,20,82))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot43 = new Polygon(new Point(-72,0,80),new Point(-72,0,82),
                    new Point(-72,20,82),new Point(-72,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry foot44 = new Polygon(new Point(-70,0,80),new Point(-72,0,80),
                    new Point(-72,20,80),new Point(-70,20,80))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));

            Geometry plateDown = new Polygon(new Point(-37,20,58),new Point(-37,20,84),
                    new Point(-75,20,84),new Point(-75,20,58))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry plateUp = new Polygon(new Point(-37,21,58),new Point(-37,21,84),
                    new Point(-75,21,84),new Point(-75,21,58))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry plateSide1 = new Polygon(new Point(-37,20,58),new Point(-37,21,58),
                    new Point(-75,21,58),new Point(-75,20,58))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry plateSide2 = new Polygon(new Point(-37,20,58),new Point(-37,21,58),
                    new Point(-37,21,84),new Point(-37,20,84))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry plateSide3 = new Polygon(new Point(-37,20,84),new Point(-37,21,84),
                    new Point(-75,21,84),new Point(-75,20,84))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
            Geometry plateSide4 = new Polygon(new Point(-75,20,58),new Point(-75,21,58),
                    new Point(-75,21,84),new Point(-75,20,84))
                    .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));

            //decor outer
            Geometry triangle1 = new Triangle((new Point(-56,25,71)),new Point(-53,21,69),
                    new Point(-53,21,73))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100));
            Geometry triangle2 = new Triangle((new Point(-56,25,71)),new Point(-53,21,73),
                    new Point(-59,21,73))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100));
            Geometry triangle3 = new Triangle((new Point(-56,25,71)),new Point(-59,21,73),
                    new Point(-59,21,69))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100));
            Geometry triangle4 = new Triangle((new Point(-56,25,71)),new Point(-59,21,69),
                    new Point(-53,21,69))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100));
            //decor inner
            Geometry triangle5 = new Triangle((new Point(-56,30,71)),new Point(-50,21,66),
                    new Point(-50,21,76))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.8));
            Geometry triangle6 = new Triangle((new Point(-56,30,71)),new Point(-50,21,76),
                    new Point(-62,21,76))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.8));
            Geometry triangle7 = new Triangle((new Point(-56,30,71)),new Point(-62,21,76),
                    new Point(-62,21,66))
                    .setEmission(new Color(java.awt.Color.pink).scale(0.1))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.8));
            Geometry triangle8 = new Triangle((new Point(-56,30,71)),new Point(-62,21,66),
                    new Point(-50,21,66))
                    .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.8));

//wallSpheres.
            Geometry sphere1 = new Sphere(new Point(35, 30, 50), 4)
                    .setEmission(new Color(java.awt.Color.RED).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));
            Geometry sphere2 = new Sphere(new Point(35, 30, 75), 4)
                    .setEmission(new Color(java.awt.Color.ORANGE).scale(0.5))
                    .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));
            Geometry sphere3 = new Sphere(new Point(35, 30, 100), 4)
                    .setEmission(new Color(java.awt.Color.GREEN).scale(0.3))
                    .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));


            myScene.geometries.add(door1,middle1,middle2,door2,door3,door4,door5,middle3,floor,wallBehind,handle1,handle2,handle3,handle4,roof
                    ,wallRight,wallLeft,foot11,foot12,foot13,foot14,foot21,foot22,foot23,foot24,foot31,foot32,foot33,foot34,foot41,foot42,foot43,foot44,
                    plateUp,plateDown,plateSide1,plateSide2,plateSide3,plateSide4,middle4,door6,triangle1,triangle2,triangle3,triangle4,
                    triangle5,triangle6,triangle7,triangle8,wallFront,sphere1,sphere2,sphere3);
        myScene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW)
                    .add(new Color(java.awt.Color.YELLOW)).scale(0.2), new Point(0, 50, 40),3));
        myScene.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0),3));
        myScene.lights.add(new SpotLight(new Color(java.awt.Color.CYAN).scale(0.8), new Point(10, 55, 75),new Vector(1.5,-1,0),3));

        camera3.setImageWriter(new ImageWriter("Room", 1000, 1000))
                    .setRayTracer(new RayTracerBasic(myScene)
                            .setMIN_SHADOW_POINTS(10));
            camera3.renderImageWithTreads();
            camera3.writeToImage();
        }
    }
//        myScene.geometries.add(//
//                // The flat table
//                new Polygon(
//                        new Point(100, 0, -100),
//                        new Point(0, 100, -100),
//                        new Point(-100, 0, -100),
//                        new Point(0, -100, -100))
//                        .setEmission(new Color(0, 128, 0))//
//                        .setMaterial(new Material()
//                                .setKd(0.5)
//                                .setKs(0.5)
//                                .setShininess(60)), ///
//
//                new Sphere(new Point(0, 56, -50), 7d)
//                        .setEmission(new Color(BLACK)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//
//                new Sphere(new Point(7, 44, -50), 7d)
//                        .setEmission(new Color(BLUE)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))
//                        ),
//
//                new Sphere(new Point(-7, 44, -50), 7d)
//                        .setEmission(new Color(RED)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//
//                new Sphere(new Point(-14, 31, -50), 7d)
//                        .setEmission(new Color(YELLOW)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//
//                new Sphere(new Point(-0.5, 31, -50), 7d)
//                        .setEmission(new Color(0, 153, 230)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//
//                new Sphere(new Point(14, 31, -50), 7d)
//                        .setEmission(new Color(102, 0, 102)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//
//                new Sphere(new Point(-0.5, -10, -25), 7d)
//                        .setEmission(new Color(WHITE)) //
//                        .setMaterial(new Material()
//                                .setKd(0.2)
//                                .setKs(0.8)
//                                .setShininess(10)
//                                .setKt(new Double3(0.6))),
//                //The starter
//                new Cylinder(3, new Ray(new Point(-0.5, -100, -25), new Vector(0, 4, 1)), 60)
//                        .setMaterial(new Material()
//                                .setShininess(100)
//                                .setKd(0.7)
//                                .setKs(0.5)
//                                .setKt(0.4))
//                        .setEmission(new Color(77, 40, 0)),
//                //edge right
//                new Cylinder(1.5, new Ray(new Point(-0.5, 69, 0), new Vector(0.55, -0.88, 0)), 56)
//                        .setMaterial(new Material()
//                                .setShininess(100)
//                                .setKd(0.7)
//                                .setKs(0.5)
//                                .setKt(0.4))
//                        .setEmission(new Color(51, 26, 0).reduce(2)),
//                //edge left
//                new Cylinder(1.5, new Ray(new Point(-27, 23, 2), new Vector(3, 5, 2)), 55)
//                        .setMaterial(new Material()
//                                .setShininess(100)
//                                .setKd(0.7)
//                                .setKs(0.5)
//                                .setKt(0.4))
//                        .setEmission(new Color(51, 26, 0).reduce(2)),
//                //edge bottom
//                new Cylinder(1.5, new Ray(new Point(-29, 22, -25), new Vector(10, 0, 1)), 60)
//                        .setMaterial(new Material()
//                                .setShininess(100)
//                                .setKd(0.7)
//                                .setKs(0.5)
//                                .setKt(0.4))
//                        .setEmission(new Color(51, 26, 0).reduce(2)),
//
//
//                //region moon
//
//                new Sphere(new Point(-75, 80, 80), 10)
//                        .setMaterial(new Material()
//                                .setKd(0.005)
//                                .setKs(0.00005)
//                                .setShininess(50)
//                                .setKt(0.9))
//                        .setEmission(Color.YELLOW.add(Color.WHITE.reduce(6))),
//
//
//                //endregion
//
//                //region stars
//                new Sphere(new Point(-75, 60, 80), 2)
//                        .setMaterial(new Material()
//                                .setKd(0.005)
//                                .setKs(0.00005)
//                                .setShininess(100)
//                                .setKt(0.9))
//                        .setEmission(Color.YELLOW.reduce(4)));
//
//
//        //moon
//        myScene.lights.add(
//                new PointLight(
//                        Color.YELLOW.reduce(6),
//                        new Point(-75, 80, 80)));
//
//        myScene.lights.add( ///
//                new SpotLight(new Color(YELLOW), new Point(10, 20, -10), new Vector(1, 1, 0), 5) //
//                        .setkL(0.0004)
//                        .setkQ(0.000006));
//
//        myScene.lights.add(
//                new PointLight(new Color(WHITE).reduce(3.2), new Point(10, 0, 15), 4)
//                        .setkL(0.00005)
//                        .setkQ(0.0000012));
//
//        myScene.lights.add(
//                new DirectionalLight(new Color(GRAY).reduce(10), new Vector(1, 1, -10)));
//
//
//        camera.setImageWriter(new ImageWriter("BilliardWithAA_SH", 500, 500))
//                .setRayTracer(
//                        new RayTracerBasic(myScene)
//                                .setMIN_SHADOW_POINTS(30)); //
//        camera.renderImageWithTreads(); //
//        camera.writeToImage();
//    }



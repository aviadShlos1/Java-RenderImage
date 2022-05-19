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
    public void createFirstImage() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1 ),
                new Vector(0 , 1, 0))
                .setViewPlaneSize(200, 200)
                .setViewPlaneDistance(1000);
        myScene.setAmbientLight(new AmbientLight(new Color(MAGENTA),new Double3(0.2))).setBackground(new Color(BLACK));

        myScene.geometries.add(//
                new Polygon(
                        new Point(100, 0, -100),
                        new Point(0, 100, -100),
                        new Point(-100, 0, -100),
                        new Point(0,-100,-100))
                        .setEmission(new Color(GREEN))//
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //

//                new Polygon(new Point(-65, 0, -100), new Point(-65, 0, 100), new Point(-65, 0, -100), new Point(-65,0,100)) //
//                        .setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(-0.5, 56, -50), 7d).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(7, 44, -50), 7d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))
                        ),

                new Sphere(new Point(-8, 44, -50), 7d).setEmission(new Color(RED)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Sphere(new Point(-14, 31, -50), 7d).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),
                new Sphere(new Point(-0.5, 31, -50), 7d).setEmission(new Color(0, 153, 230)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),
                new Sphere(new Point(14, 31, -50), 7d).setEmission(new Color(102, 0, 102)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),



                new Sphere(new Point(-0.5, -10, -25), 7d).setEmission(new Color(WHITE)) //
                        .setMaterial(new Material()
                                .setKd(0.2)
                                .setKs(0.8)
                                .setShininess(10)
                                .setKt(new Double3(0.6))),

                new Cylinder(3, new Ray(new Point(-0.5, -100, -25), new Vector(0, 4, 1)),60)
                .setMaterial(new Material().setShininess(100).setKd(0.7).setKs(0.5).setKt(0.4))
                        .setEmission(new Color(77, 40, 0)));

//        myScene.lights.add( //
//                new SpotLight(new Color(WHITE), new Point(100, 100, 200), new Vector(-1, -1, 0)) //
//                        .setkL(0.0004).setkQ(0.000006));

        myScene.lights.add(
                new PointLight(new Color(WHITE).reduce(2.5), new Point(100, 0, -10))
                        .setkL(0.00005).setkQ(0.0000012));

        myScene.lights.add(
                new DirectionalLight(new Color(GRAY).reduce(2), new Vector(1, 1, -10)));
        camera.setImageWriter(new ImageWriter(  "Billiard", 500, 500));
        camera.setRayTracer(new RayTracerBasic(myScene)); //
        camera.renderImage(); //
        camera.writeToImage();
    }
}

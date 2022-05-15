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


    @Test
    public void createFirstImage() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1 ), new Vector(0 , 1, 0))
                .setViewPlaneSize(200, 200)
                .setViewPlaneDistance(1000);
        myScene.setAmbientLight(new AmbientLight(new Color(MAGENTA),new Double3(0.2))).setBackground(new Color(BLACK));

        myScene.geometries.add(//
                new Polygon(new Point(100, 0, -100), new Point(0, 100, -100), new Point(-100, 0, -100), new Point(0,-100,-100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //

                new Sphere(new Point(7, 45, -50), 7d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6))),

                new Sphere(new Point(-8, 45, -50), 7d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6))),

                new Sphere(new Point(-0.5, 57, -50), 7d).setEmission(new Color(BLACK)) //
                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6))),

                new Sphere(new Point(-0.5, 0, -25), 7d).setEmission(new Color(WHITE)) //
                .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6))),

//                new Tube(3, new Ray(new Point(-0.5, -50, -25), new Vector(0, 4, 1)))
//                .setMaterial(new Material().setShininess(100).setKd(0.7).setKs(0.5).setKt(0.4))
//                .setEmission(new Color(red)));
                new Cylinder(3, new Ray(new Point(-0.5, -100, -25), new Vector(0, 4, 1)),80)
                .setMaterial(new Material().setShininess(100).setKd(0.7).setKs(0.5).setKt(0.4))
                        .setEmission(new Color(red)));


                myScene.lights.add( //
                new SpotLight(new Color(WHITE), new Point(15, 50, 0), new Vector(-1, -1, 0)) //
                        .setkL(0.0004).setkQ(0.000006));

        myScene.lights.add(
                new PointLight(new Color(YELLOW), new Point(100, 0, -100))
                        .setkL(0.00005).setkQ(0.0000012));

        myScene.lights.add(
                new DirectionalLight(new Color(GREEN), new Vector(1, 1, -23)));
        camera.setImageWriter(new ImageWriter(  "Billiard", 500, 500));
        camera.setRayTracer(new RayTracerBasic(myScene)); //
        camera.renderImage(); //
        camera.writeToImage();
    }
}
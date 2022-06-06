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


public class myImage
{
    Scene myScene = new Scene("firstImage");

    /**
     * Creates a room image with a couple of elements using lights, shadows and refraction techniques.
     */
    @Test
    public void createFirstImage()
    {
        Camera camera3 = new Camera(new Point(0, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200) //
                .setViewPlaneDistance(110)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(10)
                .setMultithreading(3);
// region construct
        Geometry floor= new Plane(new Point(0,0,0),new Vector(0,1,0))
                .setEmission(new Color(java.awt.Color.GRAY))
                .setMaterial(new Material().
                        setKd(0.4).
                        setKs(0.05).
                        setShininess(100));
        Geometry roof= new Plane(new Point(0,80,0),new Vector(0,1,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.7));
        Geometry wallRight= new Plane(new Point(35,0,0),new Vector(1,0,0))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().
                        setKd(0.5));
        Geometry wallLeft= new Plane(new Point(-100,0,0),new Vector(1,0,0))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(100));
        Geometry wallBehind= new Plane(new Point(0,0,200),new Vector(0,0,1))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().
                        setKd(0.5));
        Geometry wallFront= new Plane(new Point(0,0,-20),new Vector(0,0,1))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().
                        setKd(0.5));
        //endregion construct
//region closet
        //region doors
                // from right to left
                Geometry door1 = new Polygon(new Point(0,31,0),  new Point(0,61,0),
                new Point(15,61,0),  new Point(15,31,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                        .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry middle1 = new Polygon(new Point(0,0,0),  new Point(-1,0,0),
                new Point(-1,61,0),  new Point(0,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(300));

        Geometry door2 = new Polygon(new Point(-1,31,0),  new Point(-1,61,0),
                new Point(-16,61,0),  new Point(-16,31,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry middle2 = new Polygon(new Point(-16,0,0),  new Point(-17,0,0),
                new Point(-17,61,0),  new Point(-16,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        /////
        Geometry middle3 = new Polygon(new Point(15,31,0),  new Point(15,29,0),
                new Point(-16,29,0),  new Point(-16,31,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry middle4 = new Polygon(new Point(-32,0,0),  new Point(-33,0,0),
                new Point(-33,61,0),  new Point(-32,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry door3 = new Polygon(new Point(0,30,0),  new Point(0,0,0),
                new Point(15,0,0),  new Point(15,30,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry door4 = new Polygon(new Point(-1,30,0),  new Point(-1,0,0),
                new Point(-16,0,0),  new Point(-16,30,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry door5 = new Polygon(new Point(-17,61,0),  new Point(-17,0,0),
                new Point(-32,0,0),  new Point(-32,61,0))
                    .setMaterial(new Material().
                setKd(0.1).
                setKs(0.7).
                setShininess(300).
                setKr(1));
        Geometry door6 = new Polygon(new Point(-33,61,0),  new Point(-33,0,0),
                new Point(-48,0,0),  new Point(-48,61,0))
                    .setMaterial(new Material().
                setKd(0.1).
                setKs(0.7).
                setShininess(300).
                setKr(1));
        //endregion doors
        //region handles
        Geometry handle1 = new Sphere(new Point(2,33,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
//
        Geometry handle2 = new Sphere(new Point(2,28,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry handle3 = new Sphere(new Point(-14,33,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry handle4 = new Sphere(new Point(-14,28,1),1)
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        //endregion handles
//endregion closet
//region table
        //region foots
        //foot1
        Geometry foot11 = new Polygon(new Point(-40,0,60),new Point(-40,0,62),
                new Point(-40,20,62),new Point(-40,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot12 = new Polygon(new Point(-40,0,62),new Point(-42,0,62),
                new Point(-42,20,62),new Point(-40,20,62))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot13 = new Polygon(new Point(-42,0,60),new Point(-42,0,62),
                new Point(-42,20,62),new Point(-42,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot14 = new Polygon(new Point(-40,0,60),new Point(-42,0,60),
                new Point(-42,20,60),new Point(-40,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        //foot2

        Geometry foot21 = new Polygon(new Point(-40,0,80),new Point(-40,0,82),
                new Point(-40,20,82),new Point(-40,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot22 = new Polygon(new Point(-40,0,82),new Point(-42,0,82),
                new Point(-42,20,82),new Point(-40,20,82))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot23 = new Polygon(new Point(-42,0,80),new Point(-42,0,82),
                new Point(-42,20,82),new Point(-42,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot24 = new Polygon(new Point(-40,0,80),new Point(-42,0,80),
                new Point(-42,20,80),new Point(-40,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        //foot3
        Geometry foot31 = new Polygon(new Point(-70,0,60),new Point(-70,0,62),
                new Point(-70,20,62),new Point(-70,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot32 = new Polygon(new Point(-70,0,62),new Point(-72,0,62),
                new Point(-72,20,62),new Point(-70,20,62))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot33 = new Polygon(new Point(-72,0,60),new Point(-72,0,62),
                new Point(-72,20,62),new Point(-72,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot34 = new Polygon(new Point(-70,0,60),new Point(-72,0,60),
                new Point(-72,20,60),new Point(-70,20,60))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        //foot4
        Geometry foot41 = new Polygon(new Point(-70,0,80),new Point(-70,0,82),
                new Point(-70,20,82),new Point(-70,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot42 = new Polygon(new Point(-70,0,82),new Point(-72,0,82),
                new Point(-72,20,82),new Point(-70,20,82))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot43 = new Polygon(new Point(-72,0,80),new Point(-72,0,82),
                new Point(-72,20,82),new Point(-72,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry foot44 = new Polygon(new Point(-70,0,80),new Point(-72,0,80),
                new Point(-72,20,80),new Point(-70,20,80))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        //endregion foots
        //region plate
        Geometry plateDown = new Polygon(new Point(-37,20,58),new Point(-37,20,84),
                new Point(-75,20,84),new Point(-75,20,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry plateUp = new Polygon(new Point(-37,21,58),new Point(-37,21,84),
                new Point(-75,21,84),new Point(-75,21,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry plateSide1 = new Polygon(new Point(-37,20,58),new Point(-37,21,58),
                new Point(-75,21,58),new Point(-75,20,58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry plateSide2 = new Polygon(new Point(-37,20,58),new Point(-37,21,58),
                new Point(-37,21,84),new Point(-37,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry plateSide3 = new Polygon(new Point(-37,20,84),new Point(-37,21,84),
                new Point(-75,21,84),new Point(-75,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry plateSide4 = new Polygon(new Point(-75,20,58),new Point(-75,21,58),
                new Point(-75,21,84),new Point(-75,20,84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.1))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));
        //endregion plate
//endregion table
// region table decorations

        //decor outer
        Geometry sphere1 = new Sphere(new Point(-52,24,76), 2d)
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(100)
                );
        Geometry sphere2 = new Sphere(new Point(-57,24,74), 2d)
                .setEmission(new Color(RED)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(80));
        Geometry sphere3 = new Sphere(new Point(-53.5,24,80), 2d)
                .setEmission(new Color(YELLOW)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(10));
        Geometry sphere4 = new Sphere(new Point(-55,24,71), 2d)
                .setEmission(new Color(GREEN)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(10));
        //endregion table decorations
//region lights
        myScene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW)
                .add(new Color(java.awt.Color.YELLOW)).scale(0.2), new Point(0, 50, 40),3));
        myScene.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0),3));
        myScene.lights.add(new SpotLight(new Color(java.awt.Color.CYAN).scale(0.8), new Point(10, 55, 75),new Vector(1.5,-1,0),3));
//endregion lights

        myScene.geometries.add(floor,wallBehind,roof,wallFront
                ,wallRight,wallLeft,foot11,foot12,foot13,foot14,foot21,foot22,foot23,foot24,foot31,foot32,foot33,foot34,foot41,foot42,foot43,foot44,
                plateUp,plateDown,plateSide1,plateSide2,plateSide3,plateSide4,sphere1,sphere2,sphere3,sphere4);

        camera3.setImageWriter(new ImageWriter("Room", 1000, 1000))
                    .setRayTracer(new RayTracerBasic(myScene)
                            .setMIN_SHADOW_POINTS(10));
            camera3.renderImageWithTreads();
            camera3.writeToImage();
    }
}



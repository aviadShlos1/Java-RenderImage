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
                .setViewPlaneDistance(105)
                .setAntiAliasing(true)
                .setNumberOfRaysInPixel(120)
                .setMultithreading(3);
// region construct
        Geometry floor= new Plane(new Point(0,0,0),new Vector(0,1,0))
                .setEmission(new Color(245, 222, 179).reduce(4))
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

//region light sources
        Geometry whiteBolb = new Sphere(new Point(-35,85,52), 16.5)
                .setEmission(new Color(white)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(100)
                );
        Geometry shelfSpot1 = new Sphere(new Point(30,80,80), 3.5)
                .setEmission(new Color(0, 204, 0).scale(1)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(100)
                );
        Geometry shelfSpot2 = new Sphere(new Point(30,80,100), 3.5)
                .setEmission(new Color(red)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(100)
                );
//endregion light sources'
//region closet
        //region doors
                // from right to left
                Geometry upFirstDoor = new Polygon(new Point(0,30,0),  new Point(0,31,0),
                new Point(46,31,0),  new Point(46,30,0))
                .setEmission(new Color(white).scale(0.7))
                .setMaterial(new Material().
                        setKd(0.6).
                        setKs(0.8).
                        setShininess(400));

                Geometry sideDoor = new Polygon(new Point(0,30,0),  new Point(0,0,0),
                new Point(46,0,0),  new Point(46,30,0))
                .setEmission(new Color(white).scale(0.7))
                .setMaterial(new Material().
                        setKd(0.6).
                        setKs(0.8).
                        setShininess(400));

                Geometry verticalSide = new Polygon(new Point(16,0,1),  new Point(15,0,1),
                new Point(15,31.2,1),  new Point(16,31.2,1))
                .setEmission(new Color(black).scale(0.5))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300));


                Geometry upSecDoor = new Polygon(new Point(0,30,0),  new Point(0,31,0),
                new Point(15,31,0),  new Point(15,30,0))
                .setEmission(new Color(white).scale(0.7))
                        .setMaterial(new Material().
                        setKd(0.6).
                        setKs(0.8).
                        setShininess(400));

                Geometry secDoor = new Polygon(new Point(0,30,0),  new Point(0,0,0),
                new Point(14,0,0),  new Point(14,30,0))
                .setEmission(new Color(white).scale(0.7))
                        .setMaterial(new Material().
                        setKd(0.6).
                        setKs(0.8).
                        setShininess(400));


        Geometry vertical2 = new Polygon(new Point(0,0,0),  new Point(-1,0,0),
                new Point(-1,31,0),  new Point(0,31,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(300));

        Geometry upThirdDoor = new Polygon(new Point(-1,30,0),  new Point(-1,31,0),
                new Point(-16.3,31,0),  new Point(-16.3,30,0))
                .setEmission(new Color(white).scale(0.7))
                    .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry vertical3 = new Polygon(new Point(-16,0,0),  new Point(-17,0,0),
                new Point(-17,61,0),  new Point(-16,61,0))
                .setEmission(new Color(java.awt.Color.BLACK).scale(0.5))
                    .setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(100));

        Geometry vertical4 = new Polygon(new Point(-32,0,0),  new Point(-33,0,0),
                new Point(-33,61,0),  new Point(-32,61,0))
                .setEmission(new Color(140, 140, 140).scale(0.8))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(100));

        Geometry door3 = new Polygon(new Point(-1,30,0),  new Point(-1,0,0),
                new Point(-16.3,0,0),  new Point(-16.3,30,0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.7))
                    .setMaterial(new Material().
                setKd(0.6).
                setKs(0.8).
                setShininess(400));

        Geometry rightFridge = new Polygon(new Point(-17,61,0),  new Point(-17,0,0),
                new Point(-32,0,0),  new Point(-32,61,0))
                    .setMaterial(new Material().
                setKd(0.1).
                setKs(0.7).
                setShininess(300).
                setKr(1));

        Geometry vertical5 = new Polygon(new Point(-48,0,0),  new Point(-49,0,0),
                new Point(-49,61,0),  new Point(-48,61,0))
                .setEmission(new Color(black).scale(0.5))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(100));

        Geometry leftFridge = new Polygon(new Point(-33,61,0),  new Point(-33,0,0),
                new Point(-48.3,0,0),  new Point(-48.3,61,0))
                    .setMaterial(new Material().
                setKd(0.1).
                setKs(0.7).
                setShininess(300).
                setKr(1));

        Geometry upFridge = new Polygon(new Point(-16,61,0),  new Point(-16,62,0),
                new Point(-49,62,0),  new Point(-49,61,0))
                .setEmission(new Color(black).scale(0.7))
                .setMaterial(new Material().
                        setKd(0.6).
                        setKs(0.8).
                        setShininess(400));
        //endregion doors
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
        Geometry blueSphere = new Sphere(new Point(-52,22.5,76), 2d)
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(100)
                );
        Geometry redSphere = new Sphere(new Point(-57,22.5,74), 2d)
                .setEmission(new Color(RED)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(80));
        Geometry yellowSphere = new Sphere(new Point(-53.5,22.5,80), 2d)
                .setEmission(new Color(YELLOW)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(10));
        Geometry greenSphere = new Sphere(new Point(-55,22.5,71), 2d)
                .setEmission(new Color(GREEN)) //
                .setMaterial(new Material()
                        .setKd(0.2)
                        .setKs(0.8)
                        .setShininess(10));
        //endregion table decorations
//region shelf
        Geometry shelfUp = new Polygon(new Point(35, 40, 70),new Point(27,40,70),
                new Point(27,40,90),new Point(35,40,90))
                .setEmission(new Color(BLACK).scale(0.5))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfDown = new Polygon(new Point(35, 38, 70), new Point(27,38,70),
                new Point(27,38,90),new Point(35,38,90))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfRight = new Polygon(new Point(35,40,90),new Point(27,40,90),
                new Point(27,38,90),new Point(35,38,90))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfLeft = new Polygon(new Point(35, 40, 70),new Point(35, 38, 70),
                new Point(27,38,70),new Point(27,40,70))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfFront = new Polygon(new Point(27,40,90), new Point(27,38,90),
                new Point(27,38,70),new Point(27,40,70))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfUp2 = new Polygon(new Point(35, 50, 90),new Point(27,50,90),
                new Point(27,50,110),new Point(35,50,110))
                .setEmission(new Color(BLACK).scale(0.5))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfDown2 = new Polygon(new Point(35, 48, 90), new Point(27,48,90),
                new Point(27,48,110),new Point(35,48,110))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfRight2 = new Polygon(new Point(35,50,110),new Point(27,50,110),
                new Point(27,48,110),new Point(35,48,110))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfLeft2 = new Polygon(new Point(35, 50, 90),new Point(35, 48, 90),
                new Point(27,48,90),new Point(27,50,90))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));

        Geometry shelfFront2 = new Polygon(new Point(27,50,110), new Point(27,48,110),
                new Point(27,48,90),new Point(27,50,90))
                .setEmission(new Color(BLACK).scale(0.3))
                .setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.9).
                        setShininess(100));
//endregion shelf
//region lights
        //center
        myScene.lights.add(new PointLight(new Color(white).scale(1),new Point(-36,84,50),25));
        //top Left
        myScene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW)
                .add(new Color(java.awt.Color.YELLOW)).scale(0.4), new Point(0, 50, 40),3));
        //bottom Right
        myScene.lights.add(new SpotLight(new Color(java.awt.Color.orange).reduce(2), new Point(-56, 50, 71),new Vector(0,-1,0),3));
        //right wall
        myScene.lights.add(new SpotLight(new Color(white).scale(0.8), new Point(10, 55, 75),new Vector(1.5,-1,0),3));
        //green spot
        myScene.lights.add(new SpotLight(new Color(0, 204, 0).reduce(2), new Point(20,50,80),new Vector(1,-1,0),0.5));
        //red spot
        myScene.lights.add(new SpotLight(new Color(red).reduce(1.5), new Point(10,60,100),new Vector(1,-1,0),0.5));
//endregion lights
//
        myScene.geometries.add(
                floor,roof, wallFront,wallBehind,wallRight,wallLeft,
                whiteBolb,shelfSpot1,shelfSpot2,
                new Geometries(sideDoor,upFirstDoor,verticalSide),
                new Geometries(secDoor,upSecDoor,vertical2),
                new Geometries(upThirdDoor,door3,vertical3,vertical4),
                new Geometries(rightFridge,leftFridge,vertical5,upFridge),
                new Geometries(shelfFront,shelfLeft,shelfRight,shelfDown,shelfUp),
                new Geometries(shelfFront2,shelfLeft2,shelfRight2,shelfDown2,shelfUp2),
                new Geometries(foot11,foot12,foot13,foot14),
                new Geometries(foot21,foot22,foot23,foot24),
                new Geometries(foot31,foot32,foot33,foot34),
                new Geometries(foot41,foot42,foot43,foot44),
                new Geometries(plateDown,plateUp,
                        plateSide1,plateSide2,plateSide3,plateSide4),
                new Geometries(blueSphere,redSphere,yellowSphere,greenSphere)
        );

        camera3.setImageWriter(new ImageWriter("withAA", 1000, 1000))
                    .setRayTracer(new RayTracerBasic(myScene)
                            .setMIN_SHADOW_POINTS(200)
                            .turnAllBoxesOn());
            camera3.renderImageWithTreads();
            camera3.writeToImage();
    }
}


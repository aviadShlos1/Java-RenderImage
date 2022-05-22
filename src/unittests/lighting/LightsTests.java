/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 * Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package unittests.lighting;
import org.junit.jupiter.api.Test;
import java.awt.Color.*;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;


/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {
	private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(java.awt.Color.white), new Double3(0.15)));
	private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150) //
			.setViewPlaneDistance(1000);
	private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200) //
			.setViewPlaneDistance(1000);

	private Point[] p = { // The Triangles' vertices:
			new Point(-110, -110, -150), // the shared left-bottom
			new Point(95, 100, -150), // the shared right-top
			new Point(110, -110, -150), // the right-bottom
			new Point(-75, 78, 100) }; // the left-top
	private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
	private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
	private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);
	private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
	private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
	private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(java.awt.Color.blue).reduce(2)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		camera1.setImageWriter(imageWriter); //
		camera1	.setRayTracer(new RayTracerBasic(scene1)) ;//
		camera1	.renderImage() ;//
		camera1	.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(spCL, spPL,1).setkL(0.001).setkQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		camera1.setImageWriter(imageWriter); //
		camera1	.setRayTracer(new RayTracerBasic(scene1)); //
		camera1	.renderImage(); //
		camera1	.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5),1).setkL(0.001).setkQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		camera1.setImageWriter(imageWriter); //
		camera1	.setRayTracer(new RayTracerBasic(scene1)); //
		camera1	.renderImage(); //
		camera1	.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new DirectionalLight(trCL, trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		camera2.setImageWriter(imageWriter); //
		camera2	.setRayTracer(new RayTracerBasic(scene2)) ;//
		camera2	.renderImage(); //
		camera2	.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new PointLight(trCL, trPL,1).setkL(0.001).setkQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		camera2.setImageWriter(imageWriter); //
		camera2	.setRayTracer(new RayTracerBasic(scene2)) ;//
		camera2	.renderImage();//
		camera2	.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL,1).setkL(0.001).setkQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		camera2.setImageWriter(imageWriter);//
		camera2	.setRayTracer(new RayTracerBasic(scene2)); //
		camera2	.renderImage();//
		camera2.writeToImage(); //
	}
	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void triangleMultiLight() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

		scene2.lights.add(
				new SpotLight(
						Color.WHITE,
						new Point(-20, -150, -40),
						new Vector(19, 45, -22),1) //
						.setkL(0.0001).setkQ(0.000005)
		);

		scene2.lights.add(new PointLight(
				Color.RED,
				new Point(70, -150, -100),1) //
				.setkL(0).setkQ(0));

		scene2.lights.add(new DirectionalLight(
				Color.YELLOW.reduce(2),
				new Vector(0, 0, -1)));


		ImageWriter imageWriter = new ImageWriter("TriangleMultiLight", 500, 500);
		camera2 .setImageWriter(imageWriter); //
		camera2	.setRayTracer(new RayTracerBasic(scene2)) ;//
		camera2	.renderImage();//
		camera2	.writeToImage(); //
	}


	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereMultiLight() {
		scene1.geometries.add(new Sphere(new Point(0, 0, -50), 50) //
				.setEmission(Color.RED.reduce(2)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));

		scene1.lights.add(
				new DirectionalLight(
						Color.YELLOW,
						new Vector(1, 1.5, -1.5)));

		scene1.lights.add(
				new PointLight(
						Color.BLUE.scale(2),
						new Point(0, 0, 20),1)//
						.setkL(0.00001).setkQ(0.000001));
		scene1.lights.add(
				new SpotLight(
						Color.GREEN,
						new Point(10,0,0) ,new Vector(-2, -3, 4),1)
						.setkL(0.00001).setkQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("SphereMultiLight", 500, 500);
		camera1 .setImageWriter(imageWriter); //
		camera1	.setRayTracer(new RayTracerBasic(scene1)) ;//
		camera1.setViewPlaneDistance(750);//
		camera1	.renderImage();//
		camera1	.writeToImage(); //
	}



//	/**
//	 * Produce a picture of a sphere lighted by a narrow spot light
//	 */
//	@Test
//	public void sphereSpotSharp() {
//		scene1.geometries.add(sphere);
//		scene1.lights
//				.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setNarrowBeam(10).setKl(0.001).setKq(0.00004));
//
//		ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
//		camera1.setImageWriter(imageWriter); //
//		camera1.setRayTracer(new RayTracerBasic(scene1)) ;//
//		camera1.renderImage(); //
//		camera1.writeToImage(); //
//	}
//
//	/**
//	 * Produce a picture of a two triangles lighted by a narrow spot light
//	 */
//	@Test
//	public void trianglesSpotSharp() {
//		scene2.geometries.add(triangle1, triangle2);
//		scene2.lights.add(new SpotLight(trCL, trPL, trDL).setNarrowBeam(10).setKl(0.001).setKq(0.00004));
//
//		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
//		camera2.setImageWriter(imageWriter) ;//
//		camera2	.setRayTracer(new RayTracerBasic(scene2)) ;//
//		camera2	.renderImage(); //
//		camera2	.writeToImage(); //
//	}

}

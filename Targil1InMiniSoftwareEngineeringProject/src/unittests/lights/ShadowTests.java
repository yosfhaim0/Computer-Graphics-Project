package unittests.lights;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Testing basic shadows
 * 
 * @author Dan
 */
public class ShadowTests {
	private Scene scene = new Scene("Test scene");
	private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200).setVpDistance(1000);

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void sphereTriangleInitial() {
		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -200),60) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
				new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
						.setkL(1E-5).setkQ(1.5E-7));

		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
	 * producing a shading
	 */
	@Test
	public void trianglesSphere() {
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Sphere( new Point3D(0, 0, -115),30) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
						.setkL(4E-4).setkQ(2E-5));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 * move light
	 * Shadow not cover half of sphere
	 */
	@Test
	public void sphereTriangleInitialMoveLight1() {
		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -200),60) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
				new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), new Point3D(-85, -85, 120), new Vector(1, 1, -3)) //
						.setkL(1E-5).setkQ(1.5E-7));

		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitialLightMoreDown", 400, 400)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 * MoveLight
	 * Shadow cover half of sphere
	 */
	@Test
	public void sphereTriangleInitialMoveLight2() {
		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -200),60) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
				new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), new Point3D(-70, -70,50), new Vector(1, 1, -3)) //
						.setkL(1E-5).setkQ(1.5E-7));

		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitialLightMoreDownCoverHalfSphere", 400, 400)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 * MoveTriangle
	 */
	@Test
	public void sphereTriangleInitialMoveTriangle1() {
		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -200),60) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
				new Triangle(new Point3D(-65, -35, 0), new Point3D(-35, -65, 0), new Point3D(-63, -63, -4)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
						.setkL(1E-5).setkQ(1.5E-7));

		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitialMoveTriangle", 400, 400)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 * MoveTriangle
	 */
	@Test
	public void sphereTriangleInitialMoveTriangle2() {
		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -200),60) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
				new Triangle(new Point3D(-50, -20, 0), new Point3D(-20, -50, 0), new Point3D(-48, -48, -4)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
						.setkL(1E-5).setkQ(1.5E-7));

		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitialMoveTriangle2", 400, 400)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Picture for test shadow on cylinder 
	 */
	@Test
	public void cylinderShdowTest() {
		Scene scene2 = new Scene("Test scene") //
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200) //
				.setVpDistance(1000);
		Cylinder cylinder=new Cylinder(new Ray(new Point3D(-30, 0, 0), new Vector(1,0,1)), 50, 50);
		Cylinder cylinder2=new Cylinder(new Ray(new Point3D(90, 0, 0), new Vector(-1,0,1)), 50, 50);

		scene2.geometries.add(cylinder.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				cylinder2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
		scene2.lights.add(
				((SpotLight) new SpotLight(new Color(800, 400, 400), new Point3D(0, 0, 100), new Vector(0, 0, -1))
						.setkL(0.00005).setkQ(0.0000025)).setSharp(5));

		ImageWriter imageWriter = new ImageWriter("cylinderShdowTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Picture for test shadow on tube 
	 */
	@Test
	public void tubeShdowTest() {
		Scene scene2 = new Scene("Test scene") //
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200) //
				.setVpDistance(1000);
		Tube tube2=new Tube(new Ray(new Point3D(0, 0, 0), new Vector(100,50,20)), 20);
		Plane plane=new Plane(new Point3D(0, 0, -30), new Vector(1,0,1));

		scene2.geometries.add(tube2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				plane.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300))
				);
		scene2.lights.add(
				((SpotLight) new SpotLight(new Color(800, 400, 400), new Point3D(0, -100, 200),  new Point3D(0, 0, 0).subtract(new Point3D(0, -100, 200)))
						.setkL(0.00005).setkQ(0.0000025)).setSharp(5));

		ImageWriter imageWriter = new ImageWriter("tubeShdowTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Picture for test shadow on sphere 
	 */
	@Test
	public void sphereShdowTest() {
		Scene scene2 = new Scene("Test scene") //
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200) //
				.setVpDistance(1000);
		Sphere sphere=new Sphere(new Point3D(0, 0, 0), 60);
		Plane plane=new Plane(new Point3D(0, 0, -100), new Vector(-1,1,3));

		scene2.geometries.add(sphere.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				plane.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300))
				);
		scene2.lights.add(
				 (new SpotLight(new Color(800, 400, 400), new Point3D(100, 100, 0),new Point3D(167, -138, 0).subtract(new Point3D(100, 100, 0)))
						.setkL(0.00005).setkQ(0.0000025)));

		ImageWriter imageWriter = new ImageWriter("sphereShdowTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

}

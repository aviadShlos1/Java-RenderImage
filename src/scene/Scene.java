/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR05
 * Brief: Support color, add scheme and building image with ambient light
 */
package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;

import java.util.LinkedList;
import java.util.List;

/**
 * this class represents a scene in the real life -
 * containing different geometries to be seen by camera
 */
public class Scene {
    /**
     * name - the point the vector points to from (0,0,0)
     */
    public final String name;
    /**
     * background -  color of background in photo
     */
    public Color background = Color.BLACK;
    /**
     * ambientLight -  the surrounding light in the room
     */
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK,new Double3(1.d,1.d,1.d));
    /**
     * geometries - the shapes in scene
     */
    public Geometries geometries = null;
    /**
     * lights - the lights in scene
     */
    public List<LightSource> lights = new LinkedList<>();



    /**
     * constructor for scene
     *
     * @param name - name of scene
     */
    public Scene(String name) {
        this.name=name;
        geometries=new Geometries();
    }

    /**
     * setter - chaining method style
     *
     * @param ambientLight - the surrounding light in room
     * @return this instance
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight=ambientLight;
        return this;
    }
    /**
     * setter - chaining method style
     *
     * @param background - color of background
     * @return this instance
     */
    public Scene setBackground(Color background) {
        this.background=background;
        return this;
    }
    /**
     * setter - chaining method style
     *
     * @param geometries - shapes in photo
     * @return this instance
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * setter - chaining method style
     *
     * @param lights - lights in photo
     * @return this instance
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "_name='" + name + '\'' +
                ", background=" + background +
                ", ambientLight=" + ambientLight +
                ", geometries=" + geometries +
                '}';
    }
}

package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.awt.*;

public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight(Color.BLACK,new Double3(1.d,1.d,1.d));
    public Geometries geometries=null;

    public Scene(String name) {
        this.name=name;
        geometries=new Geometries();
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight=ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background=background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}

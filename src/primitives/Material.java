/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR06
 * Brief: In this level we add the color and material elements.
 * 		  In addition, we add light sources to the scene, through implementing the Phong model.
 */
package primitives;
/**
 * This class represents the different materials of the surfaces
 * and the reflection of a light component on it,
 * in three known values: diffusion, specular, and shininess.
 */
public class Material {
    /**
     *  Kd - diffuse component, represents the scattering of light rays to all directions from the surface
     */
    public Double3 kD = new Double3(0,0,0);
    /**
     *  Ks - specular component, represents the reflectance of the light source over the surface
     */
    public Double3 kS = new Double3(0,0,0);
    /**
     *  Shininess - the material shininess amount
     */
    public int nShininess = 0 ;

    /**
     * setter - chaining method
     *
     * @param kD - diffuse component double3 type
     * @return the material after setting the diffuse component
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param kD - diffuse component double type
     * @return the material after setting the diffuse component
     */
    public Material setKd(double kD) {
        this.kD.d1 = kD;
        this.kD.d2 = kD;
        this.kD.d3 = kD;
        return this;
    }

    /**
     * setter - chaining method
     *
     * @param kS - specular component double3 type
     * @return - the material after setting the
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param kS - specular component double type
     * @return - the material after setting the
     */
    public Material setKs(double kS) {
        this.kS.d1 = kS;
        this.kS.d2 = kS;
        this.kS.d3 = kS;
        return this;
    }

    /**
     * setter - chaining method
     *
     * @param shininess - how shiny the material is
     * @return the material after setting the
     */
    public Material setShininess(int shininess) {
        this.nShininess = shininess;
        return this;
    }
}

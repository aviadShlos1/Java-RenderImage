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
     * @member kD - Diffuse component
     * @member kS - Specular component
     * @member Shininess - how shiny the material is
     * @member kT - Transparency component
     * @member kR - Reflection component
     */

    public Double3 kD = new Double3(0,0,0);
    public Double3 kS = new Double3(0,0,0);
    public int nShininess = 0 ;
    public Double3 kT = new Double3(0,0,0);
    public Double3 kR = new Double3(0,0,0);


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

    /**
     * setter - chaining method
     *
     * @param kT - transparency component
     * @return the material after setting the transparency component
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param kt - transparency component
     * @return the material after setting the transparency component
     */
    public Material setKt(double kt) {
        this.kT.d1 = kt;
        this.kT.d2 = kt;
        this.kT.d3 = kt;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param kR -  reflection component
     * @return the material after setting the reflection component
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * setter - chaining method
     *
     * @param kr -  reflection component
     * @return the material after setting the reflection component
     */
    public Material setKr(double kr) {
        this.kR.d1 = kr;
        this.kR.d2 = kr;
        this.kR.d3 = kr;
        return this;
    }
}

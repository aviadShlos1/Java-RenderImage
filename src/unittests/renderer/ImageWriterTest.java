/**
 *@author: Aviad Shlosberg 314960881
 *         Evyatar Levi    318753993
 *Exercise: PR05
 * Brief: Support color, add scheme and building image with ambient light
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

class ImageWriterTest {
    //magic numbers
    private static final int ROW=800;
    private static final int COL=500;
    @Test
    void imageWriterTest(){
        ImageWriter imageWriter= new ImageWriter("imageWriterTest",ROW,COL);
        for (int i=0; i< imageWriter.getNx();i++){
            for (int j=0; j< imageWriter.getNy();j++){
                imageWriter.writePixel(i,j,new Color(java.awt.Color.YELLOW));
            }
        }
        for (int i=0; i< imageWriter.getNx();i+=50){
            for (int j=0; j< imageWriter.getNy();j++){
                imageWriter.writePixel(i,j,new Color(java.awt.Color.RED));
            }
        }
        for (int i=0; i< imageWriter.getNx();i++){
            for (int j=0; j< imageWriter.getNy();j+=50){
                imageWriter.writePixel(i,j,new Color(java.awt.Color.RED));
            }
        }
        imageWriter.writeToImage();
    }
}
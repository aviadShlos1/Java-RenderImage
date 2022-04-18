package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

class ImageWriterTest {
    @Test
    void imageWriterTest(){
        ImageWriter imageWriter= new ImageWriter("imageWriterTest",800,500);
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
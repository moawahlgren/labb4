/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import javafx.scene.image.*;

/**
 *
 * @author moawahlgren
 */
public class TransformImage {
    
    private static int[][] imageToPixels(Image image) { // Using PixelReader to transform image to pixel matrix
        PixelReader pixelReader = image.getPixelReader();
        int[][] pixelMatrix = null; 
        
        for (int i=0; i<(int)image.getWidth(); i++) {
            for (int j=0; j<(int)image.getHeight(); j++) {
                pixelMatrix[i][j] = pixelReader.getArgb(i,j);
            }
        }
        return pixelMatrix; 
    }
    
    private static Image pixelsToImage(int[][] pixelMatrix) { // Using PixelWriter to transform pixel matrix to image 
        //PixelWriter
        Image image = null; 
        int width = pixelMatrix.length; 
        int height = pixelMatrix[0].length; 
        
        WritableImage writableImage = new WritableImage(width, height);
        
        PixelWriter pixelWriter = writableImage.getPixelWriter(); 
        
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                pixelWriter.setArgb(i, j, pixelMatrix[i][j]); 
            }
        }
        return writableImage;
    }
    
}

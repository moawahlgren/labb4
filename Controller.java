/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.scene.image.Image;
import Model.*; 
import View.*; 
import java.io.IOException;

/**
 *
 * @author SaraRoempke & MoaWahlgren
 */
public class Controller {

    private final Model model;  
    private final MyView view; 
    
    public Controller(Model model, MyView view) {
        this.model = model; 
        this.view = view; 
    }
    
    
    public Image handleAddPic() {
        
        return fileImage.loadImage(); 
    }
    
    public Image handleContrast(Image image, int level, int window) {
      
        int[][] pixelMatrix = TransformImage.imageToPixels(image); 
        
        int[][] matrix = model.editContrast(pixelMatrix, level, window); 
        
        return TransformImage.pixelsToImage(matrix);
    }
    
    public Image handleBlurPic(Image image) {
        
        int[][] pixelMatrix = TransformImage.imageToPixels(image); 
        
        int[][] blurredMatrix = model.blurPic(pixelMatrix);
        
        return TransformImage.pixelsToImage(blurredMatrix); 
        
    }
    
    public Image handleInvertColor(Image image) {
        
        int[][] pixelMatrix = TransformImage.imageToPixels(image); 
        
        int[][] invertedMatrix = model.invertColor(pixelMatrix);   
        
        return TransformImage.pixelsToImage(invertedMatrix); 
    }
   
    
    public int[][] handleHistogram(Image image) {
        
        int[][] pixelMatrix = TransformImage.imageToPixels(image); 
        
        return model.histogram(pixelMatrix); 
    }
    
    public void handleSavePic(Image image) throws IOException {
        fileImage.saveImage(image); 
    }
    
     
}

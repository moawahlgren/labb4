/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author moawahlgren
 */
public class Controller {
    
    
    public static void handleAddPic() {
        
    }
    
    public static void handleContrast() {
        
    }
    
    public static void handleBlurPic() {
        
    }
    
    public static void handleInvertColor() {
        
    }
    
    public static void handleHistogram() {
        
    }
    
    public static void handleSavePic() {
        
    }
    
     
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InvertColor;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import Model.*; 
import View.*; 

/**
 *
 * @author moawahlgren
 */
public class Controller {

    private final Model model;
    private final Menu menu; 
    
    
    public Controller(Model model, Menu menu) {
        this.model = model;
        this.menu = menu;
    }
    
    
    public void handleAddPic() {
        
    }
    
    public void handleContrast(Image image) {
        
        int[][] pixelMatris = TransformImage.imageToPixels(image); 
        
    }
    
    public void handleBlurPic(Image image) {
        
        int[][] pixelMatris = TransformImage.imageToPixels(image); 
        
    }
    
    public Image handleInvertColor(Image image) {
        
        int[][] pixelMatrix = TransformImage.imageToPixels(image);
        model.invertColor(pixelMatrix); 
        return TransformImage.pixelsToImage(pixelMatrix); 
    }
    
    public void handleHistogram(Image image) {
        
        int[][] pixelMatris = TransformImage.imageToPixels(image); 
        
    }
    
    public void handleSavePic(Image image) {
        
    }
    
     
}
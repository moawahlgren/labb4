/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MyView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 *
 * @author moawahlgren
 */
public class fileImage {
    
    
    public static Image loadImage() {
        //Låter användaren välja bild bland filer
        FileChooser fileChoice = new FileChooser();

        File file = fileChoice.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image; 
        } catch (IOException ex) {
            Logger.getLogger(MyView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    }
    
    public static void saveImage(Image image) throws IOException {
        
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null); 
        FileChooser filechooser = new FileChooser(); 
        filechooser.setTitle("Save");
        filechooser.getExtensionFilters().addAll(new ExtensionFilter("PNG Files", "*.png")); 
        File file = filechooser.showSaveDialog(null); 
        
        if(file != null) {
            try {
                ImageIO.write(bImage,"png", file); 
            } catch (IOException ex) {
                Logger.getLogger(MyView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

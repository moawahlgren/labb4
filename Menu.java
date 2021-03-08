package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import Controller.*; 
import Model.*; 


/**
 * 
 *
 * @author sarar
 */
public class Menu extends Application /*implements EventHandler<ActionEvent>*/ {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    ImageView myImageView;
    private Model model; 
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        
        Controller controller = new Controller(model, this); 
        
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(30, 10, 10, 10));
        Button btAddPic = new Button("Add Image");
        Button btInvertColor = new Button("Invert Color");
        Button btBlurPic = new Button("Blur");
        Button btContrast = new Button("Contrast");
        Button btHistogram = new Button("View Histogram");
        Button btSavePic = new Button("Save Image");
        vBox.getChildren().addAll(btAddPic, btInvertColor, btBlurPic, btContrast, btHistogram, btSavePic);
        
        FileChooser filechoice = new FileChooser();
        myImageView = new ImageView();
        
        
        // Här ska det in 
        
        
        
        
        
        FileChooser fileChoice = new FileChooser();

        //FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        //FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        //fileChoice.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChoice.showOpenDialog(null);

        // kanske lägga til en if file == null
        if (ImageIO.read(file) == null) {
            // Throw EXCEPTION 
        }
        
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        myImageView.setImage(image);
        
        BorderPane pane = new BorderPane();
        pane.setLeft(vBox);
        pane.setRight(myImageView);

        Scene scene = new Scene(pane, 700, 600);
        primaryStage.setTitle("Testing testing");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        EventHandler<ActionEvent> eventAddPic = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleAddPic();       
            }
        };
        
        EventHandler<ActionEvent> eventInvertColor = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Hej"); 
                Image newImage = controller.handleInvertColor(image); 
                myImageView.setImage(newImage);
            }
        };      
        EventHandler<ActionEvent> eventBlurPic = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleBlurPic(image);
            }
        };
        
        EventHandler<ActionEvent> eventContrast = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleContrast(image); 
            }
        };
        
        EventHandler<ActionEvent> eventHistogram = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleHistogram(image); 
            }
        };
        
        EventHandler<ActionEvent> eventSavePic = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleSavePic(image); 
            }
        };
        
        btAddPic.setOnAction(eventAddPic); 
        btInvertColor.setOnAction(eventInvertColor); 
        btBlurPic.setOnAction(eventBlurPic); 
        btContrast.setOnAction(eventContrast); 
        btHistogram.setOnAction(eventHistogram); 
        btSavePic.setOnAction(eventSavePic); 
       
    }

    public static void main(String[] args) {
        launch(args);
    }

    void showAlert(String message) {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();
    }
    
    
}
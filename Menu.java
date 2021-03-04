package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;


/**
 * 
 *
 * @author sarar
 */
public class Menu extends Application {
    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();
        pane.setLeft(getVBox());
        
        Scene scene = new Scene(pane, 700, 600);
        primaryStage.setTitle("Testing testing");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
    }
    
    private VBox getVBox(){
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(30, 10, 10, 10));
        Button btAddPic = new Button("Add Image");
        Button btInvertColor = new Button("Invert Color");
        Button btBlurPic = new Button("Blur");
        Button btContrast = new Button("Contrast");
        Button btHistogram = new Button("View Histogram");
        Button btSavePic = new Button("Save Image");
        vBox.getChildren().addAll(btAddPic, btInvertColor, btBlurPic, btContrast, btHistogram, btSavePic);
        return vBox;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}

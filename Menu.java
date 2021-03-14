package View;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.Model;
import Controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MyView extends BorderPane {

    private final Model model;
    private final BorderPane pane;
    public ImageView myImageView;
    public boolean showSlider = false; 
    public static int windowValue;
    public static int levelValue;
    //private final Controller controller;

    
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public MyView(Model model) {
        this.model = model;
        
        Controller controller = new Controller(model, this);
        pane = new BorderPane();
        myImageView = new ImageView();
        MenuBar menuBar = createMenu(controller);
	
        this.setTop(menuBar);
        this.setRight(myImageView);
        
        if(showSlider == true) {
            VBox sliders = contrastView();
            this.setLeft(sliders);  
        }
                
    }    
    
    private MenuBar createMenu(Controller controller) {
		MenuItem exitItem = theExit();
                MenuItem savePic = theSave(controller);
                MenuItem openPic = toOpenImage(controller);
		
		Menu fileMenu = new Menu("File");
		fileMenu.getItems().addAll(exitItem, savePic, openPic);
                
                MenuItem blurPic = blurImage(controller);
                MenuItem invertColor = toInvertColor(controller);
                MenuItem histogram = toOpenHistogram(controller);
                MenuItem editContrast = toEditContrast(controller); 
                Menu processMenu = new Menu("Process");
                processMenu.getItems().addAll(histogram, blurPic, invertColor, editContrast);
                
                if(myImageView == null){
                    processMenu.hide();
                }
                else 
                    processMenu.show();
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, processMenu);
		
		return menuBar;
    }
    
    public VBox contrastView(){
        VBox sliders = new VBox(10); 
        Label windowlb = new Label("Window:");
        Label levellb = new Label("Level:");
        Slider Window = new Slider(0, 255, 0);
        Window.setShowTickLabels(true);
        Window.setShowTickMarks(true);
        Slider Level = new Slider(0, 255, 0);
        Level.setShowTickLabels(true);
        Level.setShowTickMarks(true);
        //Button btEdit = new Button("Edit");
        Button btDone = new Button("Apply");

        Window.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windowValue = (int) newValue;
            }
        });

        Level.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                levelValue = (int) newValue;
            }
        });

        btDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSlider = false;
            }
        });
            
        sliders.getChildren().addAll(windowlb, Window, levellb, Level, btDone);

        return sliders;

    }
        
    
    
    // ska fråga om du inte vill spara bild först
    public MenuItem theExit(){
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
        
        return exitItem;
    }
    
    public MenuItem theSave(Controller controller){
        MenuItem savePic = new MenuItem("Save Image");
        savePic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save the image");
                try {
                    controller.handleSavePic(myImageView.getImage());
                } catch (IOException ex) {
                    Logger.getLogger(MyView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return savePic;
    }
    
    public MenuItem toOpenImage(Controller controller){
        MenuItem openPic = new MenuItem("Open Image");
        openPic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Open Image");
                Image newImage = controller.handleAddPic();
                myImageView.setImage(newImage); 
            }
        });
        return openPic;
    }
    
    public MenuItem toInvertColor(Controller controller){
        MenuItem invertColor = new MenuItem("Invert Color");
        invertColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Invert Color");
                Image newImage = controller.handleInvertColor(myImageView.getImage()); 
                myImageView.setImage(newImage);
            }
        });
        return invertColor;
    }
    
    public MenuItem blurImage(Controller controller){
        MenuItem blurPic = new MenuItem("Blur");
        blurPic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Da blurr");
                Image newImage = controller.handleBlurPic(myImageView.getImage()); 
                myImageView.setImage(newImage);
            }
        });
        return blurPic;
    }
    
    public MenuItem toOpenHistogram(Controller controller){
        MenuItem histogram = new MenuItem("Show Histogram");
        histogram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Show Histogram");
            }
        });
        return histogram;
    }
    
    public MenuItem toEditContrast(Controller controller) {
        MenuItem editContrast = new MenuItem("Edit Contrast"); 
        editContrast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Editcontrassst");
                showSlider = true;
                Image newImage = controller.handleContrast(myImageView.getImage(), levelValue, windowValue); 
                myImageView.setImage(newImage);
            }
        });
        return editContrast;
    }

}
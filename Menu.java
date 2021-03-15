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
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author SaraRoempke & MoaWahlgren
 */

public class MyView extends BorderPane {

    private final Model model;
    private static BorderPane pane;
    public ImageView myImageView;
    public static int windowValue;
    public static int levelValue;
    LineChart lineChart;
    VBox sliders;

    public MyView(Model model) {
        windowValue = 0;
        levelValue = 0;
        this.model = model;

        Controller controller = new Controller(model, this);
        pane = new BorderPane();
        myImageView = new ImageView();
        MenuBar menuBar = createMenu(controller);

        this.setTop(menuBar);
        this.setRight(myImageView);

    }

    private MenuBar createMenu(Controller controller) {
        MenuItem exitItem = theExit(controller);
        MenuItem savePic = theSave(controller);

        MenuItem blurPic = blurImage(controller);
        MenuItem invertColor = toInvertColor(controller);
        MenuItem histogram = toOpenHistogram(controller);
        MenuItem editContrast = toEditContrast(controller);
        Menu processMenu = new Menu("Process");
        processMenu.getItems().addAll(histogram, blurPic, invertColor, editContrast);

        MenuItem openPic = toOpenImage(controller, processMenu);
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(exitItem, savePic, openPic);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, processMenu);

        processMenu.setVisible(false);

        return menuBar;
    }

    public void displayHistogram(int[][] matrix) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Histogram");

        /**/
        //defining series
        XYChart.Series seriesRed = new XYChart.Series();
        XYChart.Series seriesGreen = new XYChart.Series();
        XYChart.Series seriesBlue = new XYChart.Series();

        seriesRed.setName("Red");
        seriesGreen.setName("Green");
        seriesBlue.setName("Blue");

        for (int j = 0; j < matrix[0].length; j++) {
            // lägga data i series

            seriesRed.getData().add(new XYChart.Data(j, matrix[0][j]));
            seriesGreen.getData().add(new XYChart.Data(j, matrix[1][j]));
            seriesBlue.getData().add(new XYChart.Data(j, matrix[2][j]));
        }
        lineChart.getData().addAll(seriesRed, seriesGreen, seriesBlue);

        this.setLeft(lineChart);

    }

    public final void contrastView(Controller controller) {
        sliders = new VBox(10);
        Label windowlb = new Label("Window:");
        Label levellb = new Label("Level:");
        Label valueLevel = new Label(" ");
        Label valueWindow = new Label(" ");
        Slider Window = new Slider(0, 255, 0);
        Window.setShowTickLabels(true);
        Window.setShowTickMarks(true);
        Slider Level = new Slider(0, 255, 0);
        Level.setShowTickLabels(true);
        Level.setShowTickMarks(true);
        Button btDone = new Button("Apply");

        Window.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                windowValue = newValue.intValue();
                valueWindow.setText("Window: " + windowValue);
            }
        });

        Level.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                levelValue = newValue.intValue();
                valueLevel.setText("Level: " + levelValue);
            }
        });

        btDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image newImage = controller.handleContrast(myImageView.getImage(), levelValue, windowValue);
                myImageView.setImage(newImage);
                sliders.setVisible(false);
            }
        });

        sliders.getChildren().addAll(windowlb, Window, valueWindow, levellb, Level, valueLevel, btDone);
        this.setLeft(sliders);

    }

    // ska fråga om du inte vill spara bild först
    public MenuItem theExit(Controller controller) {
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Press OK to save, or No to exit", ButtonType.OK, ButtonType.NO);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Make sure to save your work before you go!");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        controller.handleSavePic(myImageView.getImage());
                    } catch (IOException ex) {
                        Logger.getLogger(MyView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.exit();
                }
                if (result.get() == ButtonType.NO) {
                    Platform.exit();
                }
            }
        });
        return exitItem;
    }

    public MenuItem theSave(Controller controller) {
        MenuItem savePic = new MenuItem("Save Image");
        savePic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (myImageView == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Press OK to exit", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.setHeaderText("You have to open an image first");
                } else {
                    try {
                        controller.handleSavePic(myImageView.getImage());
                    } catch (IOException ex) {
                        Logger.getLogger(MyView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        return savePic;
    }

    public MenuItem toOpenImage(Controller controller, Menu menu) {
        MenuItem openPic = new MenuItem("Open Image");
        openPic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image newImage = controller.handleAddPic();
                myImageView.setImage(newImage);
                menu.setVisible(true);
            }
        });
        return openPic;
    }

    public MenuItem toInvertColor(Controller controller) {
        MenuItem invertColor = new MenuItem("Invert Color");
        invertColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image newImage = controller.handleInvertColor(myImageView.getImage());
                myImageView.setImage(newImage);
            }
        });
        return invertColor;
    }

    public MenuItem blurImage(Controller controller) {
        MenuItem blurPic = new MenuItem("Blur");
        blurPic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image newImage = controller.handleBlurPic(myImageView.getImage());
                myImageView.setImage(newImage);
            }
        });
        return blurPic;
    }

    public MenuItem toOpenHistogram(Controller controller) {
        MenuItem histogram = new MenuItem("Show Histogram");
        histogram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Show Histogram");
                displayHistogram(controller.handleHistogram(myImageView.getImage()));
                //lineChart.setVisible(true);
                //pane.setLeft(lineChart);

            }
        });
        return histogram;
    }

    public MenuItem toEditContrast(Controller controller) {
        MenuItem editContrast = new MenuItem("Edit Contrast");
        editContrast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             contrastView(controller);
            }
        });
        return editContrast;
    }

}
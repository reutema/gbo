package gui.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindingSliderSlider extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO Auto-generated method stub
        VBox root = new VBox();
        HBox hbox = new HBox();

        Slider slider = new Slider(-2, 2, 0);
        ProgressBar bar = new ProgressBar();
        ProgressIndicator indicator = new ProgressIndicator();

        bar.progressProperty().bind(slider.valueProperty());
        indicator.progressProperty().bind(slider.valueProperty());

        hbox.getChildren().add(bar);
        hbox.getChildren().add(indicator);
        root.getChildren().add(slider);
        root.getChildren().add(hbox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binding-Beispiel: Slieder und Progress");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

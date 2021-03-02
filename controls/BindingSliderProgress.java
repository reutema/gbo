package gui.controls;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindingSliderProgress extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO Auto-generated method stub
        VBox root = new VBox();
        HBox hbox = new HBox();
        Slider slider1 = new Slider();
        Slider slider2 = new Slider();
        Slider slider3 = new Slider();
        Slider resultSlider = new Slider();
        DoubleBinding result;

        slider1.setMin(0);
        slider2.setMin(0);
        slider3.setMin(0);
        resultSlider.setMin(0);

        slider1.setMax(40);
        slider2.setMax(40);
        slider3.setMax(40);
        resultSlider.setMax(120);

        result = slider1.valueProperty().add(slider2.valueProperty().add(slider3.valueProperty()));

        resultSlider.valueProperty().bind(result);

        hbox.getChildren().add(slider1);
        hbox.getChildren().add(slider2);
        hbox.getChildren().add(slider3);
        root.getChildren().add(hbox);
        root.getChildren().add(resultSlider);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binding-Beispiel: Addition von Slider");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

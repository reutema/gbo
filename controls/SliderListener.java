package gui.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SliderListener extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO perhaps later
        VBox root = new VBox();
        Slider slider = new Slider();
        Label label = new Label("Slider wurde noch nicht bewegt.");

        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);

        slider.valueProperty().addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                // onScrollStarted //onScrollFinished
                // TODO Auto-generated method stub
                double temp = (double) newValue - (double) oldValue;
                System.out.println("oldValue: " + oldValue);
                System.out.println("newValue: " + newValue);
                System.out.println("temp: " + temp);
                label.setText("Änderung des Sliders um " + temp + " verschoben.");
            }

        });

        root.getChildren().add(slider);
        root.getChildren().add(label);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slider mit Listener");
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

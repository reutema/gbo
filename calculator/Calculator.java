package gui.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Calculator extends Application
{

    private double height = 480.0;

    private double width = 330.0;

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("CalculatorGUI.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Calculator");
            primaryStage.setMaxHeight(height);
            primaryStage.setMaxWidth(width);
            primaryStage.setMinHeight(height);
            primaryStage.setMinWidth(width);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

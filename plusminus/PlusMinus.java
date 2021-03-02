package gui.plusminus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlusMinus extends Application
{

    private String titel;

    public void start(Stage primaryStage)
    {
        // Initialize title of window.
        titel = "PlusMinus";

        try
        {
            // Load "PlusMinus.fxml".
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("PlusMinusGUI.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(titel);
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

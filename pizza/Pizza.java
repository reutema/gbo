package gui.pizza;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pizza extends Application
{

    private String title = "Pizza";

    private static Configuration config;

    private static int counter;

    private Scene scene;

    private static Initialize init;

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            // VBox root = (VBox)
            // FXMLLoader.load(getClass().getResource("Pizza_GUI.fxml"));

            // create Configuration object and initialize input-paramter
            config = ParameterConverter.createConfiguration(getParameters().getNamed());

            init = new Initialize(config);

            VBox root = initPanes();
            scene = new Scene(root);

            // Pizza_Controller.setToppings(init.getToppingList());
            // Pizza_Controller.setPizzaSizes(init.getPizzaSizesList());

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
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

    public VBox initPanes()
    {
        // Initialize Elements
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label labelSize = new Label();
        Label labelTopping = new Label();
        Button orderButton = new Button();
        TextArea textArea = new TextArea();

        // // work on Elements
        // vbox.maxHeight(600.0);
        // vbox.maxWidth(400.0);

        labelSize.setText("Size");
        labelTopping.setText("Topping");
        orderButton.setText("Bestellen!");

        orderButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                init.resetCalculatedPrice();
                counter++;
                System.out.println("I ordered a pizza!");
                System.out.println(Pizza.getInit().toString());
                System.out.println(Pizza.getConfig().toString());

                // Pizza Topping
                System.out.println("checking toppings");
                init.checkToppings();

                StringBuffer orderStringBuffer = new StringBuffer("Bestellung #" + counter + "\nSie haben eine Pizza bestellt.\nZutaten: ");

                // Build String for Topping
                orderStringBuffer.append(init.getSelectedToppingStringBuffer());
                System.out.println("topping String ready");

                // Pizza Size
                System.out.println("checking pizza sizes");
                init.checkPizzaSizes();

                // Build String for Pizza Size
                orderStringBuffer.append("\nDie Größe ist: " + init.getPizzaSize());
                System.out.println("pizza size ready");

                // Pizza Price
                double price = 0.0;
                price += init.getCalculatedPrice();
                orderStringBuffer.append("\nDer Preis beträgt: " + price + " €.\nVielen Dank.");

                // Build String and display it on TextArea
                String orderString = orderStringBuffer.toString();
                textArea.setText(orderString);
                System.out.println("String ready and displayed");
            }

        });
        textArea.setId("bestelltext");
        textArea.setEditable(false);

        // set Elements on Panes
        init.initialize();

        hbox.getChildren().add(orderButton);
        vbox.getChildren().add(labelSize);
        vbox.getChildren().add(init.getPizzaSizesPane());
        vbox.getChildren().add(labelTopping);
        vbox.getChildren().add(init.getToppingPane());
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(textArea);
        return vbox;
    }

    public static Initialize getInit()
    {
        return init;
    }

    public static Configuration getConfig()
    {
        return config;
    }
}

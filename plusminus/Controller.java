package gui.plusminus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller
{
    @FXML
    private Button plus, minus;

    @FXML
    private Label counterL;

    // Counter to show button function.
    private Integer counter = 0;

    // Increment counter with 1.
    public void buttonPlusClicked()
    {
        counter++;
        setCounterL();
    }

    // Decrement counter with 1.
    public void buttonMinusClicked()
    {
        counter--;
        setCounterL();
    }

    // Set counterL with new value.
    public void setCounterL()
    {
        counterL.setText(counter.toString());
    }
}

package gui.calculator;

import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller
{
    @FXML
    private TextField display;

    @FXML
    private Button clear, delete, equals;

    private Button buttonPressed; // actual pressed Button

    private String currentText = ""; // Text shown on TextField

    private Computation computation = new Computation();

    @FXML
    private void buttonClicked(ActionEvent event)
    {
        buttonPressed = (Button) event.getSource();

        // if eventButton is "="
        if (gTF(buttonPressed).equals(gTF(equals)))
        {
            if (currentText.equals(""))
            {
                setCurrentText("I haven't found anything to calculate");
            }
            else
            {
                try
                {
                    String tmp = computation.evaluate(currentText).toString();
                    setCurrentText("=" + tmp);
                }
                catch (ScriptException e)
                {
                    setCurrentText("=>FEHLER");
                }
            }

        }
        // if eventButton is "CLEAR"
        else if (gTF(buttonPressed).equals(gTF(clear)))
        {
            clearCurrentText();
        }
        // if eventButton is "DELETE"
        else if (gTF(buttonPressed).equals(gTF(delete)))
        {
            if (currentText.equals(""))
            {
                clearCurrentText();
            }
            else
            {
                currentText = currentText.substring(0, currentText.length() - 1);
            }
        }
        // every other Button
        else
        {
            setCurrentText(gTF(buttonPressed));
        }

        display.setText(currentText);
    }

    // easy way to get Text from a Button ;)
    private String gTF(Button button)
    {
        return button.getText();
    }

    private void setCurrentText(String text)
    {
        currentText = currentText + text;
    }

    private void clearCurrentText()
    {
        currentText = "";
    }
}

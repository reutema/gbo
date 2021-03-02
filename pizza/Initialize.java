package gui.pizza;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class Initialize
{

    private HBox hbox;

    private TilePane tp;

    private Configuration config;

    private String pizzaSize;

    private StringBuffer selectedToppingString;

    private double calculatedPrice;

    private CheckBox[] cbList; // Array with initialized Checkboxes

    private RadioButton[] rbList; // Array with initialized Radiobuttons

    public Initialize(Configuration config)
    {

        this.config = config;
    }

    public void initialize()
    {
        createPizzaSizesList();
        createToppingList();
    }

    public void createPizzaSizesList()
    {
        hbox = new HBox();
        String[] sizeNames = config.getSizeNames();
        int pSizeLength = (sizeNames.length);

        System.out.println("Size of Pizza sizesArray: " + pSizeLength);

        rbList = new RadioButton[pSizeLength];
        ToggleGroup toggleGroup = new ToggleGroup();

        for (int i = 0; i < pSizeLength; i++)
        {
            RadioButton rb = new RadioButton();
            rb.setText(sizeNames[i]);
            rb.setToggleGroup(toggleGroup);
            rbList[i] = rb;
            hbox.getChildren().add(rb);
        }
        rbList[0].setSelected(true);
    }

    public void createToppingList()
    {
        tp = new TilePane();
        tp.setVgap(5.0);
        String[] toppingNames = config.getToppingNames();
        int toppingLength = (toppingNames.length);

        System.out.println("Size of toppingArray: " + toppingLength);

        cbList = new CheckBox[toppingLength];

        for (int i = 0; i < toppingLength; i++)
        {
            CheckBox cb = new CheckBox();
            cb.setText(toppingNames[i]);
            if (i < config.getNumberOfDefaultToppings())
            {
                cb.setSelected(true);
                cb.setDisable(true);
            }
            cbList[i] = cb;
            tp.getChildren().add(i, cb);
        }
    }

    public void checkPizzaSizes()
    {
        System.out.println(getPizzaSizesList().length);
        for (int i = 0; i < getPizzaSizesList().length; i++)
        {
            if (getPizzaSizesList()[i].isSelected())
            {
                pizzaSize = config.getSizeNames()[i];
                calculatedPrice += config.getSizePrices()[i];
            }
        }
    }

    public void checkToppings()
    {
        System.out.println("inner checkToppings()");
        System.out.println("getToppingList.lenth: " + getToppingList().length);
        StringBuffer buf = new StringBuffer();
        Integer price = new Integer(0);
        for (int i = 0; i < getToppingList().length; i++)
        {
            if (getToppingList()[i].isSelected())
            {
                System.out.println("inner inner checkToppings()");
                buf.append(config.getToppingNames()[i] + ", ");
                price += config.getToppingPrices()[i];
            }
        }
        selectedToppingString = buf;
        calculatedPrice += price;
    }

    public HBox getHbox()
    {
        return hbox;
    }

    public TilePane getTilePane()
    {
        return tp;
    }

    public Configuration getConfig()
    {
        return config;
    }

    public String getPizzaSize()
    {
        return pizzaSize;
    }

    public StringBuffer getSelectedToppingString()
    {
        return selectedToppingString;
    }

    public CheckBox[] getCbList()
    {
        return cbList;
    }

    public RadioButton[] getRbList()
    {
        return rbList;
    }

    public RadioButton[] getPizzaSizesList()
    {
        return this.rbList;
    }

    public CheckBox[] getToppingList()
    {
        return this.cbList;
    }

    public HBox getPizzaSizesPane()
    {
        return this.hbox;
    }

    public TilePane getToppingPane()
    {
        return this.tp;
    }

    public StringBuffer getSelectedToppingStringBuffer()
    {
        return selectedToppingString;
    }

    public double getCalculatedPrice()
    {
        return balanceCalculatedPrice();
    }

    public void resetCalculatedPrice()
    {
        this.calculatedPrice = 0;
    }

    public double balanceCalculatedPrice()
    {
        double price = 0;
        price += (calculatedPrice / 100);
        System.out.println("Price in EUR: " + price);
        return price;
    }

}

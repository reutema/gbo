package gui.country.combo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CountryInfo extends Application
{
    private VBox root;

    private ComboBox<Country> combobox;

    private ObservableList<Country> oList;

    private Label countryValueLabel, capitalValueLabel, populationValueLabel;

    private Label areaValueLabel, populationDensityValueLabel;

    private CheckBox cb;

    //
    //
    //
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // VBox root =
        // FXMLLoader.load(getClass().getResource("Country_GUI.fxml"));
        initElements();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("L\u00e4nder-Informationen");
        primaryStage.show();
    }

    public void initElements()
    {
        // initialize new elements
        // panes
        root = new VBox();
        GridPane gp = new GridPane();
        // gp.setGridLinesVisible(true);
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();

        // others
        oList = FXCollections.observableArrayList();
        combobox = new ComboBox<Country>(oList);

        // dropdowns
        cb = new CheckBox("exakte Angaben");

        // labels
        Label countryLabel = new Label("Land:");
        countryValueLabel = new Label();
        Label capitalLabel = new Label("Hauptstadt:");
        capitalValueLabel = new Label();
        Label populationLabel = new Label("Einwohner:");
        populationValueLabel = new Label();
        Label areaLabel = new Label("Fl\u00e4che (in qkm):");
        areaValueLabel = new Label();
        Label populationDensityLabel = new Label("Bev\u00f6lkerungsdichte (in Personen pro qkm):");
        populationDensityValueLabel = new Label();
        Label errorMessage = new Label();

        // textfields
        TextField countryTF = new TextField();
        TextField capitalTF = new TextField();
        TextField populationTF = new TextField();
        TextField areaTF = new TextField();

        // buttons
        Button addButton = new Button("Hinzuf\u00fcgen");
        Button deleteButton = new Button("L\u00f6schen");

        // objects of country
        Country germany = new Country("Germany", "Berlin", 82175684, 357385);
        Country lux = new Country("Luxembourg", "Luxembourg", 590667, 2586);
        Country france = new Country("France", "Paris", 6699100, 643801);

        // add to oList
        oList.add(germany);
        oList.add(lux);
        oList.add(france);

        // set ID
        combobox.setId("countrySelector");
        cb.setId("exactValues");
        countryValueLabel.setId("countryName");
        capitalValueLabel.setId("capital");
        populationValueLabel.setId("population");
        areaValueLabel.setId("area");
        populationDensityValueLabel.setId("density");
        countryTF.setId("countryField");
        capitalTF.setId("capitalField");
        populationTF.setId("populationField");
        areaTF.setId("areaField");
        addButton.setId("add");
        deleteButton.setId("delete");

        //
        //
        // set prompt text for elements
        combobox.setPromptText("Keine L\u00e4nder vorhanden");
        countryTF.setPromptText("Land");
        capitalTF.setPromptText("Hauptstadt");
        populationTF.setPromptText("Einwohner");
        areaTF.setPromptText("Fl\u00e4che");

        // 0 != First, or?
        combobox.getSelectionModel().select(0);

        combobox.getSelectionModel().selectFirst();

        combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>()
        {

            @Override
            public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue)
            {
                // TODO Auto-generated method stub
                System.out.println("--------------------------------------");
                System.out.println("listener changed");
                errorMessage.setText("");
                if (!oList.isEmpty())
                {
                    System.out.println("listener, !oList.isEmpty, if");
                    try
                    {
                        combobox.getSelectionModel().select(oList.get(getCurrentComboBoxIndex()));
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        combobox.getSelectionModel().select(oList.get(getCurrentComboBoxIndex() + 1));
                    }
                }
                else
                {
                    System.out.println("listener, !oList.isEmty, else");
                    errorMessage.setText("Did not found Object to delete!");
                    clearValueLabel();
                }
                setValueLabel();
                System.out.println("<-------------------------------------");
            }

        });

        addButton.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("--------------------------------------");
                System.out.println("addButton event");
                // TODO Auto-generated method stub
                long tmpPopulation;
                long tmpArea;
                errorMessage.setText("");

                try
                {
                    System.out.println("addButton, try");
                    tmpPopulation = Long.parseLong(populationTF.getText());
                    tmpArea = Long.parseLong(areaTF.getText());
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("addButton, catch");
                    tmpPopulation = 0;
                    tmpArea = 0;
                }

                String newCountryName = countryTF.getText();
                String newCapital = capitalTF.getText();

                if (newCountryName.equals("") || newCountryName.equals("Land"))
                {
                    errorMessage.setText("Empty country name!");
                    return;
                }
                else if (newCapital.equals("") || newCapital.equals("Hauptstadt"))
                {
                    errorMessage.setText("Empty capital name!");
                    return;
                }
                else if (tmpPopulation < 0)
                {
                    errorMessage.setText("The population of the country is empty or was destroyed!");
                    return;
                }
                else if (tmpArea <= 0)
                {
                    errorMessage.setText("The area of the country is empty or to small!\nPlease notice that only countries with a area of min 1qkm could be added to this list!\nSorry Vatikan!");
                    return;
                }

                Country newCountry = new Country(newCountryName, newCapital, tmpPopulation, tmpArea);

                if (oList.contains(newCountry))
                {

                    return;
                }
                else if (tmpArea < 100)
                {
                    errorMessage.setText("Wow, " + tmpArea + " qkm . It seems to be very comfortable there!");
                }
                System.out.println("addButton newCountry worked!");
                oList.add(newCountry);
                oList.sorted();
                setValueLabel();
                combobox.getSelectionModel().select(0);

                // clear TextFields
                countryTF.clear();
                capitalTF.clear();
                populationTF.clear();
                areaTF.clear();
                System.out.println("<-------------------------------------");
            }

        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                // TODO Auto-generated method stub
                System.out.println("--------------------------------------");
                System.out.println("deletButton event");
                if (!oList.isEmpty())
                {
                    System.out.println("deleteButton, combobox is not empty");

                    int index = getCurrentComboBoxIndex();
                    oList.remove(index);
                    oList.sorted();
                }
                else
                {
                    System.out.println("deleteButton, combobox is empty");

                    errorMessage.setText("Did not found Object to delete!");
                    clearValueLabel();
                }
                System.out.println("<-------------------------------------");
            }
        });

        cb.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                // TODO Auto-generated method stub
                System.out.println("--------------------------------------");
                System.out.println("checkbox onAction:");
                Country tmpCountry = combobox.getSelectionModel().getSelectedItem();
                long tmpPopulationNumber = tmpCountry.getPeople();
                long tmpAreaNumber = tmpCountry.getArea();
                long tmpPopulationDensity = tmpCountry.getPopulationDencity();
                if (!cb.isSelected())
                {
                    System.out.println("!cb.isSelected, if, checkbox is not selected");
                    populationValueLabel.setText(formatNumberValue(tmpPopulationNumber));
                    areaValueLabel.setText(formatNumberValue(tmpAreaNumber));
                    populationDensityValueLabel.setText(formatNumberValue(tmpPopulationDensity));
                }
                else
                {
                    System.out.println("!cb.isSelected, else, checkbox is selected");
                    populationValueLabel.setText(formatValue(tmpPopulationNumber));
                    areaValueLabel.setText(formatValue(tmpAreaNumber));
                    populationDensityValueLabel.setText(formatValue(tmpPopulationDensity));
                }
                System.out.println("<-------------------------------------");
            }
        });

        // set alignment of ValueLabels in GridPane
        countryValueLabel.setAlignment(Pos.CENTER_RIGHT);
        capitalValueLabel.setAlignment(Pos.CENTER_RIGHT);
        populationValueLabel.setAlignment(Pos.CENTER_RIGHT);
        areaValueLabel.setAlignment(Pos.CENTER_RIGHT);
        populationDensityValueLabel.setAlignment(Pos.CENTER_RIGHT);

        // set color to errorMessage label
        errorMessage.setTextFill(Color.RED);

        // add elements to gp (GridPane)
        // first column
        gp.add(countryLabel, 1, 1);
        gp.add(capitalLabel, 1, 2);
        gp.add(populationLabel, 1, 3);
        gp.add(areaLabel, 1, 4);
        gp.add(populationDensityLabel, 1, 5);
        // second column
        gp.add(countryValueLabel, 2, 1);
        gp.add(capitalValueLabel, 2, 2);
        gp.add(populationValueLabel, 2, 3);
        gp.add(areaValueLabel, 2, 4);
        gp.add(populationDensityValueLabel, 2, 5);

        setValueLabel();

        // add elements to hbox
        hbox.getChildren().add(countryTF);
        hbox.getChildren().add(capitalTF);
        hbox.getChildren().add(populationTF);
        hbox.getChildren().add(areaTF);
        hbox.getChildren().add(addButton);

        hbox2.getChildren().add(deleteButton);
        hbox2.getChildren().add(errorMessage);

        // add elements to root (VBox)
        root.getChildren().add(combobox);
        root.getChildren().add(cb);
        root.getChildren().add(gp);
        root.getChildren().add(hbox);
        root.getChildren().add(hbox2);
    }

    public void setValueLabel()
    {
        System.out.println("--------------------------------------");
        int index = getCurrentComboBoxIndex();

        if (!oList.isEmpty())
        {
            System.out.println("setValueLabel, !oList.isEmty()");
            try
            {
                countryValueLabel.setText(oList.get(index).getName());
                capitalValueLabel.setText(oList.get(index).getCapital());
            }
            catch (IndexOutOfBoundsException e)
            {
                countryValueLabel.setText(oList.get(index + 1).getName());
                capitalValueLabel.setText(oList.get(index + 1).getCapital());
                index = index + 1;
            }

            if (cb.isSelected())
            {
                System.out.println("setValueLabel,combobox is selected, index:" + index);
                populationValueLabel.setText(formatValue((oList.get(index).getPeople())));
                areaValueLabel.setText(formatValue(oList.get(index).getArea()));
                populationDensityValueLabel.setText(formatValue(oList.get(index).getPopulationDencity()));
            }
            else // if (!oList.isEmpty() && cb.isSelected())
            {
                System.out.println("setValueLabel,combobox is not selected, index:" + index);
                populationValueLabel.setText(formatNumberValue((oList.get(index).getPeople())));
                areaValueLabel.setText(formatNumberValue((oList.get(index).getArea())));
                populationDensityValueLabel.setText(formatNumberValue(oList.get(index).getPopulationDencity()));
            }
        }
        System.out.println("<-------------------------------------");
    }

    public void clearValueLabel()
    {
        System.out.println("--------------------------------------");
        System.out.println("clearValueLabel");
        countryValueLabel.setText("");
        capitalValueLabel.setText("");
        populationValueLabel.setText("");
        areaValueLabel.setText("");
        populationDensityValueLabel.setText("");
        System.out.println("<-------------------------------------");
    }

    public int getCurrentComboBoxIndex()
    {
        return combobox.getSelectionModel().getSelectedIndex();
    }

    public int countLengthOfLong(long input)
    {
        System.out.println("--------------------------------------");
        System.out.println("countLengthOfLong");
        long tmp = input;
        int i = 0;
        while (tmp > 0)
        {
            tmp = tmp / 10;
            i++;
            System.out.println(i + " : " + tmp);
        }
        System.out.println("<-------------------------------------");
        return i;
    }

    public String formatValue(long input)
    {
        System.out.println("--------------------------------------");
        System.out.println("formatValue");
        int i = countLengthOfLong(input);
        StringBuilder builder = new StringBuilder();

        for (int j = 1; j <= i; j++)
        {
            System.out.println("loop i:" + i);
            builder.append(input % 10);
            if (j % 3 == 0 && (j < i))
            {
                System.out.println("innerLoop");
                builder.append('.');
            }
            input = input / 10;
        }
        builder.reverse();
        System.out.println(builder.toString());
        System.out.println("<-------------------------------------");
        return builder.toString();

    }

    public String formatNumberValue(long input)
    {
        System.out.println("--------------------------------------");
        System.out.println("formatNumberValue");
        StringBuilder builder = new StringBuilder();
        int i = countLengthOfLong(input);

        System.out.println("Stellen: " + i);
        if (i < 4)
        {
            System.out.println("Kleiner als 1000");
            System.out.println(input);
            return Long.toString(input);
        }
        else if (i < 7)
        {
            System.out.println("Kleiner als 1 Mio");
            builder.append(Math.round(input / Math.pow(10, 3)));
            builder.append(".000");
        }
        else if (i < 10)
        {
            System.out.println("Kleiner als 1 Mrd");
            builder.append(Math.round(input / Math.pow(10, 6)));
            builder.append(" Mill.");
        }
        else
        {
            // Will never be used, thanks long.
            System.out.println("Groesser als 1 Mrd");
            builder.append(Math.round(input / Math.pow(10, 9)));
            builder.append(" Mrd.");
        }
        System.out.println(builder.toString());
        System.out.println("<-------------------------------------");
        return builder.toString();

    }
}
// Congratulation you reached the END
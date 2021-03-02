package gui.pizza;

public class Configuration
{

    private String[] stringSizeNames;

    private String[] toppingNames;

    private int[] sizePrices;

    private int[] toppingPrices;

    private int numberOfDefaultToppings;

    public Configuration(String[] stringSizeNames, int[] sizePrices, String[] toppingNames, int[] toppingPrices, int numberOfDefaultToppings)
    {
        if (!(stringSizeNames.length == sizePrices.length))
        {
            throw new IllegalArgumentException("The length of arguments of pizza sizes and prices does not fit!");
        }
        if (!(toppingNames.length == toppingPrices.length))
        {
            throw new IllegalArgumentException("The length of topping names an toppic prices does not fit!");
        }
        if (!(numberOfDefaultToppings >= 0 && numberOfDefaultToppings <= stringSizeNames.length))
        {
            throw new IllegalArgumentException("The number of default toppings does not fit with the number of toppings");
        }
        this.stringSizeNames = stringSizeNames;
        this.toppingNames = toppingNames;
        this.sizePrices = sizePrices;
        this.toppingPrices = toppingPrices;
        this.numberOfDefaultToppings = numberOfDefaultToppings;
    }

    public String[] getSizeNames()
    {
        return stringSizeNames;
    }

    public String[] getToppingNames()
    {
        return toppingNames;
    }

    public int[] getSizePrices()
    {
        return sizePrices;
    }

    public int[] getToppingPrices()
    {
        return toppingPrices;
    }

    public int getNumberOfDefaultToppings()
    {
        return numberOfDefaultToppings;
    }

}

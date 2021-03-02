package gui.country.combo;

public class Country
{
    private String name;

    private String capital;

    private long people;

    private long area;

    public Country(String name, String capital, long people, long area)
    {
        this.name = name;
        this.capital = capital;
        this.people = people;
        this.area = area;
    }

    public String getName()
    {
        return name;
    }

    public String getCapital()
    {
        return capital;
    }

    public long getPeople()
    {
        return people;
    }

    public long getArea()
    {
        return area;
    }

    public long getPopulationDencity()
    {
        if (area == 0)
        {
            return 0;
        }
        double tmp = ((double) people / (double) area);
        System.out.println("qkm" + tmp);
        return Math.round(tmp);
    }

    public String toString()
    {
        return name;
    }
}
package Engine2D;

public abstract class Shape
{
    public float x, y;
    public Color color;
    public Shape(float x, float y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void move(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public enum Type
    {
        Rectangle, Line, Circle
    }

    public enum Color
    {
        red("red"), green("green"), blue("blue"), yellow("yellow"), white("white");

        public String color;

        Color(String color)
        {
            this.color = color;
        }
    }

    public abstract Type getType();
}

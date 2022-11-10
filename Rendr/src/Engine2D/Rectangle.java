package Engine2D;

public class Rectangle extends Shape
{
    public float width, height;

    public Rectangle(float x, float y, float width, float height, Color color)
    {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public Type getType()
    {
        return Type.Rectangle;
    }
}

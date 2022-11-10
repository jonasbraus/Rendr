package Engine2D;

public class Circle extends Shape
{
    public float radius;

    public Circle(float x, float y, float radius, Color color)
    {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public Type getType()
    {
        return Type.Circle;
    }
}

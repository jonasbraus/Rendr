package Engine2D;

public class Line extends Shape
{
    public float length;

    public float rotation;
    public float thickness;

    public Line(float x, float y, float length, float rotation, float thickness, Color color)
    {
        super(x, y, color);
        this.length = length;
        this.rotation = rotation;
        this.thickness = thickness;
    }

    @Override
    public Type getType()
    {
        return Type.Line;
    }
}

package EngineTest;

import Engine2D.*;

public class Test2D extends Window2D
{
    private static Test2D test;
    private static Rectangle rect;
    private static Line line;

    private static Circle circle;

    public static void main(String[] args)
    {
        test = new Test2D();
        test.createWindow("Test", 800, 600);

        test.setBackgroundColor(1, 1, 1);

        rect = new Rectangle(0, 0, 10, 10, Shape.Color.green);
        test.addShape(rect);

//        line = new Line(0, 0, -10, 5, 10, -5, 1, Shape.Color.blue);
        //test.addShape(line);

        circle = new Circle(0, 0, 10, Shape.Color.blue);
        test.addShape(circle);

        test.update();

        test.closeWindow();
    }

    @Override
    public void update()
    {
        while(!test.shouldClose())
        {
            super.update();

            if(Input.isMouseDown(Mouse.left))
            {
                circle.setPosition(Input.mouseX(), Input.mouseY());
            }
        }
    }
}

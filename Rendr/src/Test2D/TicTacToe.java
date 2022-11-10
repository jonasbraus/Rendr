package Test2D;

import Engine2D.*;

public class TicTacToe extends Window2D
{
    public static void main(String[] args)
    {
        new TicTacToe();
    }

    private int[][] grid = new int[3][3];

    private int currentPlayer = 1;

    public TicTacToe()
    {
        createWindow("TicTacToe", 800, 800);
        setBackgroundColor(1, 1, 1);

        addShape(new Line(-25, 0, 120, 90, 1, Shape.Color.blue));
        addShape(new Line(25, 0, 120, 90, 1, Shape.Color.blue));

        addShape(new Line(0, -25, 120, 0, 1, Shape.Color.blue));
        addShape(new Line(0, 25, 120, 0, 1, Shape.Color.blue));

        update();
        closeWindow();
    }

    @Override
    public void update()
    {
        while(!shouldClose())
        {
            super.update();

            if(Input.isMouseDown(Mouse.left))
            {
                //get clicked cell
                int xCell = (int)(Input.mouseX() / 25) + 1;
                int yCell = (int)(Input.mouseY() / 25) + 1;

                if(xCell <= -1) xCell++;
                if(xCell > 2) xCell--;

                if(yCell <= -1) yCell++;
                if(yCell > 2) yCell--;

                if(grid[xCell][yCell] == 0)
                {
                    if(currentPlayer == 2)
                    {
                        addShape(new Circle((xCell - 1) * 48, (yCell - 1) * 48, 15, Shape.Color.blue));
                    }
                    else
                    {
                        addShape(new Line((xCell - 1) * 48, (yCell - 1) * 48, 30, 45, 2, Shape.Color.red));
                        addShape(new Line((xCell - 1) * 48, (yCell - 1) * 48, 30, -45, 2, Shape.Color.red));
                    }

                    grid[xCell][yCell] = currentPlayer;
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                }
            }
        }
    }
}

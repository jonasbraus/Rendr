package Engine2D;

import RenderEngine.DisplayManager;

public class Input
{
    public static boolean isKeyPressed(Key key)
    {
        switch(key)
        {
            case a: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_A);
            case b: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_B);
            case c: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_C);
            case d: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_D);
            case e: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_E);
            case f: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_F);
            case g: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_G);
            case h: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_H);
            case i: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_I);
            case j: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_J);
            case k: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_K);
            case l: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_L);
            case m: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_M);
            case n: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_N);
            case o: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_O);
            case p: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_P);
            case q: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_Q);
            case r: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_R);
            case s: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_S);
            case t: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_T);
            case u: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_U);
            case v: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_V);
            case w: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_W);
            case x: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_X);
            case y: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_Y);
            case k1: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_1);
            case k2: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_2);
            case k3: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_3);
            case k4: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_4);
            case k5: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_5);
            case k6: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_6);
            case k7: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_7);
            case k8: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_8);
            case k9: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_9);
            case k0: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_0);
            case kUp: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_UP);
            case kDown: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_DOWN);
            case kLeft: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_LEFT);
            case kRight: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_RIGHT);
            case kSpace: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_SPACE);
            case kShift: return org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_LSHIFT);
        }

        return false;
    }

    public static float horizontal()
    {
        if(isKeyPressed(Key.a) || isKeyPressed(Key.kLeft))
        {
            return -1;
        }

        if(isKeyPressed(Key.d) || isKeyPressed(Key.kRight))
        {
            return 1;
        }

        return 0;
    }

    public static float vertical()
    {
        if(isKeyPressed(Key.w) || isKeyPressed(Key.kUp))
        {
            return 1;
        }

        if(isKeyPressed(Key.s) || isKeyPressed(Key.kDown))
        {
            return -1;
        }

        return 0;
    }

    public static boolean isMouseDown(Mouse button)
    {
        if(button == Mouse.left)
        {
            return org.lwjgl.input.Mouse.isButtonDown(0);
        }
        else
        {
            return org.lwjgl.input.Mouse.isButtonDown(1);
        }
    }

    public static float mouseX()
    {
        double res = org.lwjgl.input.Mouse.getX() - DisplayManager.width / 2f;
        res /= 5.714285714285714;

        return (float)res;
    }

    public static float mouseY()
    {
        double res = org.lwjgl.input.Mouse.getY() - DisplayManager.height / 2f;
        res /= 5.714285714285714;

        return (float)res;
    }
}

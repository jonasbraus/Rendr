package EngineTest;

import Entity.*;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import Models.RawModel;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import Models.TexturedModel;
import Tools.Axis;
import Tools.OBJLoader;
import Tools.Space;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import Textures.ModelTexture;
import org.lwjgl.util.vector.Vector3f;
import sun.awt.AWTAccessor;

import java.util.Arrays;

public class MainGameLoop
{
    /**
     * A test class of the render engine
     * @param args
     */

    public static void main(String[] args)
    {
        DisplayManager.createDisplay("Rendr", true);

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        OBJLoader objLoader = new OBJLoader("Object");
        float[] vertices = objLoader.getVertices();
        int[] indices = objLoader.getIndices();
        float[] uvs = objLoader.getUVs();


        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("mossyBricks"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);


        Camera camera = new Camera();

        float moveSpeed = 0.2f;

        Mouse.setGrabbed(true);

        boolean fPressed = false;

        while(!Display.isCloseRequested())
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_W))
            {
                camera.translate(0, 0, -moveSpeed, Space.Local);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S))
            {
                camera.translate(0, 0, moveSpeed, Space.Local);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D))
            {
                camera.translate(moveSpeed, 0, 0, Space.Local);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A))
            {
                camera.translate(-moveSpeed, 0, 0, Space.Local);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            {
                camera.translate(0, moveSpeed, 0, Space.Local);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                camera.translate(0, -moveSpeed, 0, Space.Local);
            }

            if(Mouse.isGrabbed())
            {
                camera.rotate(Axis.y, Mouse.getDX() / 10f);
                camera.rotate(Axis.x, Mouse.getDY() / -10f);
            }



            if(Keyboard.isKeyDown(Keyboard.KEY_F) && !fPressed)
            {
                fPressed = true;
                DisplayManager.setFullScreen(!DisplayManager.isFullScreen());
            }
            if(!Keyboard.isKeyDown(Keyboard.KEY_F))
            {
                fPressed = false;
            }

            if(Mouse.isButtonDown(0))
            {
                Mouse.setGrabbed(true);
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            {
                Mouse.setGrabbed(false);
            }

            //entity.rotate(0, 0.5f, 0);

            renderer.prepare(0.2f, 0.2f, 0.2f);
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.clear();
        loader.clear();
        DisplayManager.closeDisplay();
    }
}

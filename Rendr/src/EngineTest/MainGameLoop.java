package EngineTest;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import models.RawModel;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import textures.ModelTexture;

public class MainGameLoop
{
    /**
     * A test class of the render engine
     * @param args
     */

    public static void main(String[] args)
    {
        DisplayManager.createDisplay("Rendr");

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices =
                {
                        -.5f, .5f, 0,
                        -.5f, -.5f, 0,
                        .5f, -.5f, 0,
                        .5f, .5f, 0
                };
        int[] indices =
                {
                        0, 1, 3,
                        3, 1, 2,
                };
        float[] uvs =
                {
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 0
                };

        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("mossyBricks"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        while(!Display.isCloseRequested())
        {
            renderer.prepare(0, 0, 0);
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.clear();
        loader.clear();
        DisplayManager.closeDisplay();
    }
}

package EngineTest;

import Entity.Entity;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import Models.RawModel;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import Models.TexturedModel;
import org.lwjgl.opengl.Display;
import Textures.ModelTexture;
import org.lwjgl.util.vector.Vector3f;

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
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

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
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);

        while(!Display.isCloseRequested())
        {
            renderer.prepare(0, 0, 0);
            shader.start();
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.clear();
        loader.clear();
        DisplayManager.closeDisplay();
    }
}

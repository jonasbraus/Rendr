package EngineTest;

import Entity.Camera;
import Entity.Entity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Tools.Axis;
import Tools.OBJLoader;
import Tools.Space;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest
{
    public static void main(String[] args)
    {
        new SwingTest();
    }

    private Canvas canvas;
    private JFrame frame;
    private JPanel leftPanel;
    private JPanel centerPanel;

    public SwingTest()
    {
        frame = new JFrame();
        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        canvas = new Canvas();

        canvas.setIgnoreRepaint(true);
        canvas.setPreferredSize(new Dimension(800, 600));
        canvas.setMinimumSize(new Dimension(320, 240));
        canvas.setVisible(true);

        leftPanel = new JPanel();
        centerPanel = new JPanel();

        centerPanel.add(canvas);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                includeGL();
            }
        }).start();
    }

    private void includeGL(){
        DisplayManager.createDisplay("Redr", false, 800, 600, true);
        DisplayManager.setDisplayParent(canvas);

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new RenderEngine.Renderer(shader);

        Camera camera = new Camera();

        OBJLoader objLoader = new OBJLoader("Object");
        float[] vertices = objLoader.getVertices();
        int[] indices = objLoader.getIndices();
        float[] uvs = objLoader.getUVs();


        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("mossyBricks"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);

        while(!Display.isCloseRequested())
        {
            renderer.prepare(0.2f, 0.2f, 0.2f);
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            entity.rotate(0, 1, 0);
            DisplayManager.updateDisplay();
        }

        shader.clear();
        loader.clear();
        DisplayManager.closeDisplay();
    }
}

package Engine2D;

import Entity.Entity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Tools.OBJLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import Entity.Camera;

public abstract class Window2D
{
    private Loader loader;
    private StaticShader shader;
    private Renderer renderer;

    public float gameWidth, gameHeight;

    private float rBackground = 0, gBackground = 0, bBackground = 0;

    List<Entity> entities = new ArrayList<>();
    List<Shape> shapes = new ArrayList<>();

    private Camera cam = new Camera();

    public void createWindow(String title, int width, int height)
    {
        DisplayManager.createDisplay(title, true, width, height, false);
        loader = new Loader();
        shader = new StaticShader();
        renderer = new Renderer(shader);

        double res = DisplayManager.width / 2f;
        res /= 5.714285714285714;
        res *= 2;
        gameWidth = (float)res;

        System.out.println("Window game width : " + res);

        res = DisplayManager.height / 2f;
        res /= 5.714285714285714;
        res *= 2;
        gameHeight = (float)res;

        System.out.println("Window game height: " + res);
    }

    public void addShape(Shape shape)
    {
        switch (shape.getType())
        {
            case Rectangle:
                Rectangle rect = (Rectangle) shape;
                float[] vertices =
                        {
                                //links unten
                                0 - rect.width / 2, 0 - rect.height / 2, 0,
                                //links oben
                                0 - rect.width / 2, 0 + rect.height / 2, 0,
                                //rechts oben
                                0 + rect.width / 2, 0 + rect.height / 2, 0,
                                //rechts unten
                                0 + rect.width / 2, 0 - rect.height / 2, 0
                        };
                int[] indices =
                        {
                                3, 0, 1, 1, 3, 2
                        };
                float[] uvs =
                        {
                                0, 0,
                                0, 0,
                                0, 0,
                                0, 0
                        };


                RawModel model = loader.loadToVAO(vertices, indices, uvs);
                ModelTexture texture = new ModelTexture(loader.loadTexture(shape.color.color));
                TexturedModel texturedModel = new TexturedModel(model, texture);
                Entity entity = new Entity(texturedModel, new Vector3f(shape.x, shape.y, -100), 0, 0, 0, 1);
                entities.add(entity);
                shapes.add(shape);
                break;

            case Line:
                Line line = (Line) shape;
                float[] vertices1 =
                        {
                                //links unten
                                0 - line.length / 2, 0, 0,
                                //links oben
                                0  - line.length / 2, 0 + line.thickness / 2, 0,
                                //rechts oben
                                0 + line.length / 2, 0 + line.thickness / 2, 0,
                                //rechts unten
                                0 + line.length / 2, 0, 0
                        };
                int[] indices1 =
                        {
                                3, 0, 1, 1, 3, 2
                        };
                float[] uvs1 =
                        {
                                0, 0,
                                0, 0,
                                0, 0,
                                0, 0
                        };

                RawModel model1 = loader.loadToVAO(vertices1, indices1, uvs1);
                ModelTexture texture1 = new ModelTexture(loader.loadTexture(shape.color.color));
                TexturedModel texturedModel1 = new TexturedModel(model1, texture1);
                Entity entity1 = new Entity(texturedModel1, new Vector3f(shape.x, shape.y, -100), 0, 0, line.rotation, 1);
                entities.add(entity1);
                shapes.add(shape);

                break;
            case Circle:
                Circle circle = (Circle) shape;
                OBJLoader objLoader = new OBJLoader("circle");
                float[] vertices2 = objLoader.getVertices();
                int[] indices2 = objLoader.getIndices();
                float[] uvs2 = objLoader.getUVs();


                RawModel model2 = loader.loadToVAO(vertices2, indices2, uvs2);
                ModelTexture texture2 = new ModelTexture(loader.loadTexture(shape.color.color));
                TexturedModel texturedModel2 = new TexturedModel(model2, texture2);
                Entity entity2 = new Entity(texturedModel2, new Vector3f(shape.x, shape.y, -100), -90, 0, 0, circle.radius);
                entities.add(entity2);
                shapes.add(shape);
                break;
        }
    }

    public void setBackgroundColor(int r, int g, int b)
    {
        rBackground = r;
        gBackground = g;
        bBackground = b;
    }

    public void update()
    {
        renderer.prepare(rBackground, gBackground, bBackground);
        shader.start();
        shader.loadViewMatrix(cam);
        for (int i = 0; i < entities.size(); i++)
        {
            Entity e = entities.get(i);
            Shape s = shapes.get(i);
            e.setPosition(new Vector3f(s.x, s.y, e.getPosition().z));

            renderer.render(e, shader);
        }

        shader.stop();
        DisplayManager.updateDisplay();
    }

    public void closeWindow()
    {
        shader.clear();
        loader.clear();
        DisplayManager.closeDisplay();
    }

    public boolean shouldClose()
    {
        return Display.isCloseRequested();
    }
}

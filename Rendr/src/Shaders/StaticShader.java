package Shaders;

import Entity.Camera;
import Tools.Maths;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram
{
    private static final String vertexFile = "src/shaders/vertexShader.glsl", fragmentFile = "src/shaders/fragmentShader.glsl";
    private int locationTransform;
    private int locationProjection;
    private int locationView;

    public StaticShader()
    {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations()
    {
        locationTransform = getUniformLocation("transformationMatrix");
        locationProjection = getUniformLocation("projectionMatrix");
        locationView = getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes()
    {
        bindAttribute(0, "position");
        bindAttribute(1, "uvs");
    }

    public void loadTransformMatrix(Matrix4f matrix)
    {
        loadMatrix(locationTransform, matrix);
    }

    public void loadViewMatrix(Camera camera)
    {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        loadMatrix(locationView, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix)
    {
        loadMatrix(locationProjection, matrix);
    }
}

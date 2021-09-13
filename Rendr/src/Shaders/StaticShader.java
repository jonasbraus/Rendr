package Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram
{
    private static final String vertexFile = "src/shaders/vertexShader.glsl", fragmentFile = "src/shaders/fragmentShader.glsl";
    private int locationTransform;

    public StaticShader()
    {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations()
    {
        locationTransform = getUniformLocation("transformationMatrix");
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
}

package Shaders;

public class StaticShader extends ShaderProgram
{
    private static final String vertexFile = "src/shaders/vertexShader.glsl", fragmentFile = "src/shaders/fragmentShader.glsl";

    public StaticShader()
    {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void bindAttributes()
    {
        bindAttribute(0, "position");
        bindAttribute(1, "uvs");
    }
}

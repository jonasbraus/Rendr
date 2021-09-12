package Models;

public class RawModel
{
    private int vaoID;
    private int vertexCount;

    //stores information about the vao of an model
    public RawModel(int vaoID, int vertexCount)
    {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID()
    {
        return vaoID;
    }

    public int getVertexCount()
    {
        return vertexCount;
    }
}

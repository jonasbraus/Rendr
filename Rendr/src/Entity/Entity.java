package Entity;

import Models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Entity
{
    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale)
    {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void translate(Vector3f value)
    {
        position.x += value.x;
        position.y += value.y;
        position.z += value.z;
    }

    public void translate(float x, float y, float z)
    {
        position.x += x;
        position.y += y;
        position.z += z;
    }

    public void rotate(float x, float y, float z)
    {
        rotX += x;
        rotY += y;
        rotZ += z;
    }

    public TexturedModel getModel()
    {
        return model;
    }

    public void setModel(TexturedModel model)
    {
        this.model = model;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public float getRotX()
    {
        return rotX;
    }

    public void setRotX(float rotX)
    {
        this.rotX = rotX;
    }

    public float getRotY()
    {
        return rotY;
    }

    public void setRotY(float rotY)
    {
        this.rotY = rotY;
    }

    public float getRotZ()
    {
        return rotZ;
    }

    public void setRotZ(float rotZ)
    {
        this.rotZ = rotZ;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }
}

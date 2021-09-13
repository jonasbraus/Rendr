package Entity;

import org.lwjgl.util.vector.Vector3f;

public class Camera
{
    private Vector3f position = new Vector3f(0, 0, 0);
    private float rotX, rotY, rotZ;

    public Camera()
    {

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

    public Vector3f getPosition()
    {
        return position;
    }

    public float getRotX()
    {
        return rotX;
    }

    public float getRotY()
    {
        return rotY;
    }

    public float getRotZ()
    {
        return rotZ;
    }
}

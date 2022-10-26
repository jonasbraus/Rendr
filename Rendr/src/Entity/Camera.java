package Entity;

import Tools.Axis;
import Tools.Space;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f rotation = new Vector3f(0, 0, 0);

    public void translate(float x, float y, float z, Space space)
    {
        if(space == Space.Global)
        {
            position.x += x;
            position.z += z;
        }
        else
        {
            if(z != 0)
            {
                Vector3f rotated = new Vector3f((float)Math.sin(Math.toRadians(-getRotY())) * z, 0, (float)Math.cos(Math.toRadians(-getRotY())) * z);
                rotated.normalise();
                position.x += rotated.x / 4f;
                position.z += rotated.z / 4f;
            }
            if(x != 0)
            {
                Vector3f rotated = new Vector3f((float)Math.cos(Math.toRadians(-getRotY())) * x, 0, (float)-Math.sin(Math.toRadians(-getRotY())) * x);
                rotated.normalise();
                position.x += rotated.x / 4f;
                position.z += rotated.z / 4f;
            }
        }

        position.y += y;
    }

    public void translate(Vector3f translation, Space space)
    {
        translate(translation.x, translation.y, translation.z, space);
    }

    public void rotate(Axis axis, float rotationDegree)
    {
        this.rotation.x += axis == Axis.x ? rotationDegree : 0;
        this.rotation.y += axis == Axis.y ? rotationDegree : 0;
        this.rotation.z += axis == Axis.z ? rotationDegree : 0;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public float getRotX()
    {
        return rotation.x;
    }

    public float getRotY()
    {
        return rotation.y;
    }

    public float getRotZ()
    {
        return rotation.z;
    }
}

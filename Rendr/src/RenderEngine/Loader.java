package RenderEngine;

import Models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader
{
    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public RawModel loadToVAO(float[] vertices, int[] indices, float[] uvs)
    {
        //create a new vao
        int vaoID = createVAO();
        bindIndexBuffer(indices);
        storeDataToAttList(0, 3, vertices);
        storeDataToAttList(1, 2, uvs);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    public int loadTexture(String fileName)
    {
        Texture texture = null;
        try
        {
            texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;
    }

    public void clear()
    {
        //delete all the vaos from memory
        for(int vao:vaos)
        {
            GL30.glDeleteVertexArrays(vao);
        }

        //delete all the vbos from memory
        for(int vbo:vbos)
        {
            GL15.glDeleteBuffers(vbo);
        }

        //delete all the textures from memory
        for(int texture:textures)
        {
            GL11.glDeleteTextures(texture);
        }
    }

    private int createVAO()
    {
        //generate a new vao and store as an int pointer
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        //bind the current vao
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataToAttList(int attNumber, int coordinateSize, float[] data)
    {
        //generate a new vbo and store as an int pointer
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        //bind the vbo as an array buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        //prepare a float buffer to be load into the vbo
        FloatBuffer buffer = storeDataToFloatBuffer(data);
        //load in the float buffer as an static object
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        //add the vbo to the given list in the already bound vao
        GL20.glVertexAttribPointer(attNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        //unbind the vbo
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO()
    {
        GL30.glBindVertexArray(0);
    }

    private void bindIndexBuffer(int[] indices)
    {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataToIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataToIntBuffer(int[] data)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private FloatBuffer storeDataToFloatBuffer(float[] data)
    {
        //create a new float buffer with size of the data array
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        //put all the data into the float buffer
        buffer.put(data);
        //turn the float buffer from write to read mode
        buffer.flip();
        return buffer;
    }
}

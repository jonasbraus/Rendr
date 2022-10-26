package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader
{
    private File file;
    private List<Maths.Vector3> vertices = new ArrayList<>();
    private List<Integer> indices = new ArrayList<>();
    private List<Maths.Vector2> uvs = new ArrayList<>();

    private List<Integer> uvOrder = new ArrayList<>();

    public OBJLoader(String fileName)
    {
        file = new File("res/" + fileName + ".obj");

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath())));
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] split = line.split(" ");
                switch (split[0])
                {
                    case "v":
                        vertices.add(new Maths.Vector3(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                        break;
                    case "vt":
                        uvs.add(new Maths.Vector2(Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                        break;
                    case "f":
                        String[] s1 = split[1].split("/");
                        String[] s2 = split[2].split("/");
                        String[] s3 = split[3].split("/");
                        indices.add(Integer.parseInt(s1[0]));
                        indices.add(Integer.parseInt(s2[0]));
                        indices.add(Integer.parseInt(s3[0]));

                        uvOrder.add(Integer.parseInt(s1[1]));
                        uvOrder.add(Integer.parseInt(s2[1]));
                        uvOrder.add(Integer.parseInt(s3[1]));
                }
            }

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public float[] getVertices()
    {
        float[] result = new float[indices.size() * 3];

        int resultI = 0;
        int orderI = 0;

        while(resultI < result.length)
        {
            result[resultI] = vertices.get(indices.get(orderI) - 1).x;
            resultI++;
            result[resultI] = vertices.get(indices.get(orderI) - 1).y;
            resultI++;
            result[resultI] = vertices.get(indices.get(orderI) - 1).z;
            resultI++;
            orderI++;
        }

        return result;
    }

    public float[] getUVs()
    {
        float[] result = new float[uvOrder.size() * 2];

        int resultI = 0;
        int orderI = 0;

        while(resultI < result.length)
        {
            result[resultI] = uvs.get(uvOrder.get(orderI) - 1).x;
            resultI++;
            result[resultI] = uvs.get(uvOrder.get(orderI) - 1).y;
            resultI++;
            orderI++;
        }

        return result;
    }

    public int[] getIndices()
    {
        int[] result = new int[indices.size()];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = i;
        }

        return result;
    }
}

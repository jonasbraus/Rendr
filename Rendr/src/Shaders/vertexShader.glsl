#version 400 core

in vec3 position;
in vec2 uvs;

out vec3 color;
out vec2 passUvs;

void main(void)
{
    gl_Position = vec4(position.x, position.y, position.z, 1.0);
    passUvs = uvs;
}
#version 400 core

in vec3 color;
in vec2 passUvs;

out vec4 outColor;

uniform sampler2D textureSampler;

void main(void)
{
    outColor = texture(textureSampler, passUvs);
}
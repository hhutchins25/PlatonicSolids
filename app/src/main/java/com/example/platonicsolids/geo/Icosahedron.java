package com.example.platonicsolids.geo;

public class Icosahedron {
    private float u = 1.0f;
    private float nu = -1.0f;
    private float g = (float)(1.0f + Math.sqrt(5.0f)) / 2.0f;
    private float ng = -1.0f * g;

    private float[][] verts = {
            {u,0,g},{u,0,ng},{nu,0,ng},{nu,0,g},
            {g,u,0},{g,nu,0},{ng,nu,0},{ng,u,0},
            {0,g,u},{0,g,nu},{0,ng,nu},{0,ng,u}
    };
}

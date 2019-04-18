package com.example.platonicsolids.geo;

public class Icosahedron extends PlatonicSolid {

    public Icosahedron() { }

    public void draw(float[] mvpMatrix) {
        tris[0].draw(mvpMatrix);
    }

    private float u = 1.0f;
    private float nu = -1.0f;
    private float g = (float)(1.0f + Math.sqrt(5.0f)) / 2.0f;
    private float ng = -1.0f * g;

    private float[][] verts = {
            // xz rect
            {u,0,g},{u,0,ng},{nu,0,ng},{nu,0,g},
            // xy rect
            {g,u,0},{g,nu,0},{ng,nu,0},{ng,u,0},
            // yz rect
            {0,g,u},{0,g,nu},{0,ng,nu},{0,ng,u}
    };

    private Triangle[] tris = {
            new Triangle(verts[0],verts[3],verts[8]),
    };

}

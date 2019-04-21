package com.example.platonicsolids.geo;

public class Icosahedron extends PlatonicSolid {

    public Icosahedron() { }

    public void draw(float[] mvpMatrix, float[] rotationMatrix) {
        for(int i=0; i<20; i++) {
            tris[i].draw(mvpMatrix, rotationMatrix);
        }
    }

    // phi - golden ratio
    // s - circumradius of the golden-ratio method of creating icosahedron
    private float phi = (float)(1.0f + Math.sqrt(5.0f)) / 2.0f;
    private float s = (float)Math.sqrt(phi + 2.0);

    // u - base unit, normalized to unit length
    // g - golden ratio, normalized to unit length
    // n prefix - negative
    private float u = 1.0f / s;
    private float nu = -1.0f * u;
    private float g = (float)((1.0f + Math.sqrt(5.0f)) / (2.0f * s));
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
            // all triangles surrounding corner vertex
            new Triangle(verts[0],verts[8],verts[3]),
            // every other triangle corresponds to the triangle
            // linked on the opposite side of the main vertex
            new Triangle(verts[8],verts[7],verts[3]),
            new Triangle(verts[0],verts[4],verts[8]),
            new Triangle(verts[4],verts[9],verts[8]),
            new Triangle(verts[0],verts[5],verts[4]),
            new Triangle(verts[5],verts[1],verts[4]),
            new Triangle(verts[0],verts[11],verts[5]),
            new Triangle(verts[11],verts[10],verts[5]),
            new Triangle(verts[0],verts[3],verts[11]),
            new Triangle(verts[3],verts[6],verts[11]),

            // all triangles surrounding vertex opposite of previous one
            new Triangle(verts[2],verts[9],verts[1]),
            // same method applied to every other one
            new Triangle(verts[1],verts[9],verts[4]),
            new Triangle(verts[2],verts[1],verts[10]),
            new Triangle(verts[10],verts[1],verts[5]),
            new Triangle(verts[2],verts[10],verts[6]),
            new Triangle(verts[6],verts[10],verts[11]),
            new Triangle(verts[2],verts[6],verts[7]),
            new Triangle(verts[7],verts[6],verts[3]),
            new Triangle(verts[2],verts[7],verts[9]),
            new Triangle(verts[9],verts[7],verts[8])
    };

}

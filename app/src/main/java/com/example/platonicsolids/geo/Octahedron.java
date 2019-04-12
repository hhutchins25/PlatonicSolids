package com.example.platonicsolids.geo;

public class Octahedron extends PlatonicSolid {
    private float verts[][] = {
        {1, 0, 0},
        {-1, 0, 0},
        {0, 1, 0},
        {0, -1, 0},
        {0, 0, 1},
        {0, 0, -1}
    };

    private Triangle tris[] = {
        new Triangle(verts[0], verts[2], verts[4]),
        new Triangle(verts[0], verts[4], verts[3]),
        new Triangle(verts[0], verts[3], verts[5]),
        new Triangle(verts[0], verts[5], verts[2]),
        new Triangle(verts[1], verts[4], verts[2]),
        new Triangle(verts[1], verts[2], verts[5]),
        new Triangle(verts[1], verts[5], verts[3]),
        new Triangle(verts[1], verts[3], verts[4]),
    };

    public Octahedron() { }

    public void draw(float[] mvpMatrix) {
        for (int i=0; i<8; i++) {
            tris[i].draw(mvpMatrix);
        }
    }
}

package com.example.platonicsolids.geo;

public class Cube extends PlatonicSolid {

    private float verts[][] = {
        { 0.5774f, 0.5774f, 0.5774f },
        { 0.5774f, 0.5774f, -0.5774f },
        { -0.5774f, 0.5774f, 0.5774f },
        { -0.5774f, 0.5774f, -0.5774f },
        { 0.5774f, -0.5774f, 0.5774f },
        { 0.5774f, -0.5774f, -0.5774f },
        { -0.5774f, -0.5774f, 0.5774f },
        { -0.5774f, -0.5774f, -0.5774f }
    };
    
    private Triangle tris[] = {
        new Triangle(verts[0], verts[1], verts[3]),
        new Triangle(verts[0], verts[3], verts[2]),
        new Triangle(verts[4], verts[5], verts[7]),
        new Triangle(verts[4], verts[7], verts[6]),

        new Triangle(verts[0], verts[4], verts[6]),
        new Triangle(verts[0], verts[6], verts[2]),
        new Triangle(verts[1], verts[5], verts[4]),
        new Triangle(verts[1], verts[4], verts[0]),

        new Triangle(verts[2], verts[6], verts[7]),
        new Triangle(verts[2], verts[7], verts[3]),
        new Triangle(verts[3], verts[7], verts[5]),
        new Triangle(verts[3], verts[5], verts[1])
    };

    public Cube() {

    }

    public void draw(float[] mvpMatrix) {
        for (int i=0; i<12; i++) {
            tris[i].draw(mvpMatrix);
        }
    }

}

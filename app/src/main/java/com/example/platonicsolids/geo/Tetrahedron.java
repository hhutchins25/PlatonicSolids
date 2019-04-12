// Tetrahedron.java
// Creates the vertices required for a Tetrahedron and
// draws the corresponding triangles
// Holden Hutchins, 2019

package com.example.platonicsolids.geo;

public class Tetrahedron extends PlatonicSolid {

    private float verts[][] = {
            {0.5774f, 0.5774f, 0.5774f},
            {0.5774f, -0.5774f, -0.5774f},
            {-0.5774f, 0.5774f, -0.5774f},
            {-0.5774f, -0.5774f, 0.5774f},
    };
    
    private Triangle tris[] = {
            new Triangle(verts[0], verts[2], verts[1]),
            new Triangle(verts[0], verts[3], verts[2]),
            new Triangle(verts[0], verts[1], verts[3]),
            new Triangle(verts[3], verts[1], verts[2])
    };

    public Tetrahedron() {

    }

    public void draw(float[] mvpMatrix) {
        for (int i=0; i<4; i++) {
            tris[i].draw(mvpMatrix);
        }
    }
}

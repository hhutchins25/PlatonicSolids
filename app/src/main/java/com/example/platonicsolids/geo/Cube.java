package com.example.platonicsolids.geo;

public class Cube extends PlatonicSolid {

    public Cube() { }

    public void draw(float[] mvpMatrix) {
        for (int i=0; i<6; i++) {
            quads[i].draw(mvpMatrix);
        }
    }

    private float u = 0.5774f;
    private float nu = -0.5774f;

    private float[][] verts = {
            { u, u, u },
            { u, u, nu },
            { nu, u, u },
            { nu, u, nu },
            { u, nu, u },
            { u, nu, nu },
            { nu, nu, u },
            { nu, nu, nu }
    };

    private Square[] quads = {
            new Square(verts[0], verts[2], verts[3], verts[1]),
            new Square(verts[0], verts[1], verts[5], verts[4]),
            new Square(verts[0], verts[4], verts[6], verts[2]),
            new Square(verts[7], verts[6], verts[4], verts[5]),
            new Square(verts[7], verts[5], verts[1], verts[3]),
            new Square(verts[7], verts[3], verts[2], verts[6])
    };

}

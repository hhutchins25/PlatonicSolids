// Tetrahedron.java
// Creates the vertices required for a Tetrahedron and
// draws the corresponding triangles
// Holden Hutchins, 2019

package com.example.platonicsolids.geo;

public class Tetrahedron {

    private float vert1[] = new float[] { 0.5774f, 0.5774f, 0.5774f };
    private float vert2[] = new float[] { 0.5774f, -0.5774f, -0.5774f };
    private float vert3[] = new float[] { -0.5774f, 0.5774f, -0.5774f };
    private float vert4[] = new float[] { -0.5774f, -0.5774f, 0.5774f };

    Triangle tri1 = new Triangle(vert1, vert3, vert2);
    Triangle tri2 = new Triangle(vert1, vert4, vert3);
    Triangle tri3 = new Triangle(vert1, vert2, vert4);
    Triangle tri4 = new Triangle(vert4, vert2, vert3);

    public Tetrahedron() {

    }

    public void draw(float[] mvpMatrix) {
        tri1.draw(mvpMatrix);
        tri2.draw(mvpMatrix);
        tri3.draw(mvpMatrix);
        tri4.draw(mvpMatrix);
    }

}

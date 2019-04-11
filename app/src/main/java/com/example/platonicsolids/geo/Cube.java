package com.example.platonicsolids.geo;

public class Cube {

    private float vert1[] = new float[] { 0.5774f, 0.5774f, 0.5774f };
    private float vert2[] = new float[] { 0.5774f, 0.5774f, -0.5774f };
    private float vert3[] = new float[] { -0.5774f, 0.5774f, 0.5774f };
    private float vert4[] = new float[] { -0.5774f, 0.5774f, -0.5774f };

    private float vert5[] = new float[] { 0.5774f, -0.5774f, 0.5774f };
    private float vert6[] = new float[] { 0.5774f, -0.5774f, -0.5774f };
    private float vert7[] = new float[] { -0.5774f, -0.5774f, 0.5774f };
    private float vert8[] = new float[] { -0.5774f, -0.5774f, -0.5774f };

    Triangle tri1 = new Triangle(vert1, vert2, vert4);
    Triangle tri2 = new Triangle(vert1, vert4, vert3);
    Triangle tri3 = new Triangle(vert5, vert6, vert8);
    Triangle tri4 = new Triangle(vert5, vert8, vert7);

    Triangle tri5 = new Triangle(vert1, vert5, vert7);
    Triangle tri6 = new Triangle(vert1, vert7, vert3);
    Triangle tri7 = new Triangle(vert2, vert6, vert5);
    Triangle tri8 = new Triangle(vert2, vert5, vert1);

    Triangle tri9 = new Triangle(vert3, vert7, vert8);
    Triangle tri10 = new Triangle(vert3, vert8, vert4);
    Triangle tri11 = new Triangle(vert4, vert8, vert6);
    Triangle tri12 = new Triangle(vert4, vert6, vert2);

    public Cube() {

    }

    public void draw(float[] mvpMatrix) {
        tri1.draw(mvpMatrix); tri2.draw(mvpMatrix); tri3.draw(mvpMatrix); tri4.draw(mvpMatrix);
        tri5.draw(mvpMatrix); tri6.draw(mvpMatrix); tri7.draw(mvpMatrix); tri8.draw(mvpMatrix);
        tri9.draw(mvpMatrix); tri10.draw(mvpMatrix); tri11.draw(mvpMatrix); tri12.draw(mvpMatrix);
    }

}

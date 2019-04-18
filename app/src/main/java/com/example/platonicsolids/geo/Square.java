package com.example.platonicsolids.geo;

public class Square {

    public Square(float[] point1, float[] point2,
                  float[] point3, float[] point4){
        float[] color = { (float)Math.random(), (float)Math.random(),
                (float)Math.random(), 1.0f };
        tri1 = new Triangle(point1, point4, point3);
        tri2 = new Triangle(point1, point3, point2);
        tri1.setColor(color);
        tri2.setColor(color);
    }

    public void draw(float[] mvpMatrix) {
        tri1.draw(mvpMatrix);
        tri2.draw(mvpMatrix);
    }

    private Triangle tri1;
    private Triangle tri2;
}

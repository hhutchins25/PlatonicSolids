package com.example.platonicsolids.geo;

public class Square {

    public Square(float[] point1, float[] point2,
                  float[] point3, float[] point4){
        tri1 = new Triangle(point1, point4, point3);
        tri2 = new Triangle(point1, point3, point2);
    }

    public void draw(float[] mvpMatrix, float[] rotationMatrix) {
        tri1.draw(mvpMatrix, rotationMatrix);
        tri2.draw(mvpMatrix, rotationMatrix);
    }

    private Triangle tri1;
    private Triangle tri2;
}

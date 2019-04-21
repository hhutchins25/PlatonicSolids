package com.example.platonicsolids.geo;

public class NGon {

    public NGon(float[][] verts) {
        len = verts.length;
        setCenter(verts);
        tris = new Triangle[len];

        for (int i = 0; i < len - 1; i++) {
            tris[i] = new Triangle(verts[i], verts[i+1], center);
        }
        tris[len - 1] = new Triangle(verts[len - 1], verts[0], center);
    }

    public void setCenter(float[][] verts) {
        float xSum = 0; float ySum = 0; float zSum = 0;
        for(int i = 0; i < len; i++) {
            xSum += verts[i][0]; ySum += verts[i][1]; zSum += verts[i][2];
        }
        center = new float[] { xSum / len, ySum / len, zSum / len };
    }

    public void draw(float[] mvpMatrix, float[] rotationMatrix) {
        for(int i = 0; i < len; i++) {
            tris[i].draw(mvpMatrix, rotationMatrix);
        }
    }

    private Triangle[] tris;
    private float[] center;
    private int len;
}

package com.example.platonicsolids.geo;

public class NGon {

    public NGon(float verts[][]) {
        len = verts.length;
        setCenter(verts);
        tris = new Triangle[len];

        float color[] = { (float)Math.random(), (float)Math.random(),
                (float)Math.random(), 1.0f };
        for (int i = 0; i < len - 1; i++) {
            tris[i] = new Triangle(verts[i], center, verts[i + 1]);
            tris[i].setColor(color);
        }
        tris[len - 1] = new Triangle(verts[len - 1], center, verts[0]);
        tris[len - 1].setColor(color);
    }

    public void setCenter(float verts[][]) {
        float xSum = 0; float ySum = 0; float zSum = 0;
        for(int i = 0; i < len; i++) {
            xSum += verts[i][0]; ySum += verts[i][1]; zSum += verts[i][2];
        }
        center = new float[] { xSum / len, ySum / len, zSum / len };
    }

    public void draw(float mvpMatrix[]) {
        for(int i = 0; i < len; i++) {
            tris[i].draw(mvpMatrix);
        }
    }

    private Triangle tris[];
    private float center[];
    private int len;
}

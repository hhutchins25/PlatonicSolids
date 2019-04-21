package com.example.platonicsolids.math;

public class Vector {

    public static float[] cross(float[] p1, float[] p2) {
        return new float[]{
                p1[1] * p2[2] - p2[1] * p1[2],
                p1[2] * p2[0] - p2[2] * p1[0],
                p1[0] * p2[1] - p2[0] * p1[1]
        };
    }

    public static float[] diff(float[] p1, float[] p2) {
        return new float[] {
                p1[0] - p2[0],
                p1[1] - p2[1],
                p1[2] - p2[2]
        };
    }

}

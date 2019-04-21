package com.example.platonicsolids.math;

public class Vector {

    public static float dot(float[] p1, float[] p2) {
        return (p1[0] * p2[0]) + (p1[1] * p2[1]) + (p1[2] * p2[2]);
    }

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

    public static float[] mult(float[] vec, float scalar) {
        return new float[] {
                vec[0] * scalar,
                vec[1] * scalar,
                vec[2] * scalar
        };
    }

    public static float[] normalize(float[] vec) {
        float mag = (float)Math.sqrt(dot(vec, vec));
        return mult(vec, 1.0f / mag);
    }

}

// Triangle.java
// Primarily based off Android example
// https://developer.android.com/training/graphics/opengl/shapes.html
// Built upon by Holden Hutchins, 2019

package com.example.platonicsolids.geo;

import android.opengl.GLES20;

import com.example.platonicsolids.GLActivity.MyGLRenderer;
import com.example.platonicsolids.math.Vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    public Triangle(float point1[], float point2[], float point3[]) {
        float triangleCoords[] = {
                point1[0], point1[1], point1[2],
                point2[0], point2[1], point2[2],
                point3[0], point3[1], point3[2]
        };
        setNormal(point1, point2, point3);
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    // Set color with red, green, blue and alpha (opacity) values
    public float color[] = { 0.1f, 0.5f, 0.9f,  1.0f };

    public void draw(float[] mvpMatrix, float[] rotationMatrix) {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);
        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        normalHandle = GLES20.glGetUniformLocation(mProgram, "vNormal");
        objectHandle = GLES20.glGetUniformLocation(mProgram, "object");
        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        GLES20.glUniform3fv(normalHandle, 1, normal, 0);
        GLES20.glUniformMatrix4fv(objectHandle, 1, false, rotationMatrix, 0);
        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    public void setColor(float[] tempColor){
        color = tempColor;
    }

    private void setNormal(float[] point1, float[] point2, float[] point3) {
        float[] vec1 = Vector.diff(point2, point1);
        float[] vec2 = Vector.diff(point3, point1);
        float[] temp = Vector.cross(vec1, vec2);
        normal = Vector.normalize(temp);
    }

    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    // Use to access and set the view transformation
    private int vPMatrixHandle;
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "uniform vec3 vNormal;" +
                    "uniform mat4 object;" +
                    "void main() {" +
                    "  vec4 currNorm; currNorm.xyz = vNormal; currNorm.w = 1.0;" +
                    "  vec4 rotNorm = object * currNorm; vec3 norm = rotNorm.xyz / rotNorm.w;" +
                    "  vec3 lightDir = vec3(0.0, 0.0, -1.0);" +
                    "  float diff = max(dot(norm, lightDir),0.0);" +
                    "  vec4 diffuse = (diff + 0.1) * vColor;" +
                    "  gl_FragColor = diffuse;" +
                    "}";
    private FloatBuffer vertexBuffer;
    private int positionHandle;
    private int colorHandle;
    private int normalHandle;
    private int objectHandle;
    private float[] normal;
    private final int vertexCount = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    private final int mProgram;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
}

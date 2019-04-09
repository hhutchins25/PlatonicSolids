// GLActivity.java
// Primarily based off Android example
// https://developer.android.com/training/graphics/opengl/
// Built upon by Holden Hutchins, 2019

package com.example.platonicsolids;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.platonicsolids.geo.Triangle;
import com.example.platonicsolids.geo.Tetrahedron;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLActivity extends AppCompatActivity {

    public static class MyGLRenderer implements GLSurfaceView.Renderer {

        private float r = 0.0f;
        private float g = 0.0f;
        private float b = 0.0f;

        // vPMatrix is an abbreviation for "Model View Projection Matrix"
        private final float[] vPMatrix = new float[16];
        private final float[] projectionMatrix = new float[16];
        private final float[] viewMatrix = new float[16];

        private float[] rotationMatrix = new float[16];
        private Tetrahedron mTetrahedron;

        public volatile float mAngle;
        public float mSkew[] = {0.0f, 1.0f, 0.0f};

        public float getAngle() {
            return mAngle;
        }

        public void setAngle(float angle) {
            mAngle = angle;
        }

        public void setSkew(float skew[]) {
            mSkew[0] = (mSkew[0] + skew[0]) / 2;
            mSkew[1] = (mSkew[1] + skew[1]) / 2;
            mSkew[2] = (mSkew[2] + skew[2]) / 2;
        }

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            // Set the background frame color
            GLES20.glClearColor(r, g, b, 1.0f);
            // initialize a tetrahedron
            mTetrahedron = new Tetrahedron();
            // Enable depth test
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            // Accept fragment if it closer to the camera than the former one
            GLES20.glDepthFunc(GLES20.GL_LESS);
        }

        public void onDrawFrame(GL10 unused) {
            // Redraw background color
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

            // Set the camera position (View matrix)
            Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

            // Calculate the projection and view transformation
            Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

            float[] scratch = new float[16];

            // Create a rotation for the triangle
            Matrix.setRotateM(rotationMatrix, 0, mAngle, mSkew[0], mSkew[1], mSkew[2]);

            // Combine the rotation matrix with the projection and camera view
            // Note that the vPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);

            // Draw triangle
            mTetrahedron.draw(scratch);
        }

        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            float ratio = (float) width / height;

            // this projection matrix is applied to object coordinates
            // in the onDrawFrame() method
            Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 2, 100);
        }

        public static int loadShader(int type, String shaderCode){

            int shader = GLES20.glCreateShader(type);

            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);

            return shader;
        }
    }

    class MyGLSurfaceView extends GLSurfaceView {

        private final MyGLRenderer renderer;

        private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
        private float previousX;
        private float previousY;

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            // MotionEvent reports input details from the touch screen
            // and other input controls. In this case, you are only
            // interested in events where the touch position changed.

            float x = e.getX();
            float y = e.getY();

            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:

                    float dx = x - previousX;
                    float dy = y - previousY;

                    // reverse direction of rotation above the mid-line
                    if (y > getHeight() / 2) {
                        dx = dx * -1 ;
                    }

                    // reverse direction of rotation to left of the mid-line
                    if (x < getWidth() / 2) {
                        dy = dy * -1 ;
                    }

                    renderer.setAngle(
                            renderer.getAngle() +
                                    ((dx + dy) * TOUCH_SCALE_FACTOR));
                    renderer.setSkew(new float[] {dy / (dx + dy), dx / (dx + dy), 0.0f});
                    requestRender();
            }

            previousX = x;
            previousY = y;
            return true;
        }

        public MyGLSurfaceView(Context context){
            super(context);

            // Create an OpenGL ES 2.0 context
            setEGLContextClientVersion(2);

            renderer = new MyGLRenderer();

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(renderer);

            // Render the view only when there is a change in the drawing data
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }

    private GLSurfaceView gLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        gLView = new MyGLSurfaceView(this);
        setContentView(gLView);
    }
}

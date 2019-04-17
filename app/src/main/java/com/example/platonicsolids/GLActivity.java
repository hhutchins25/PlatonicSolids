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
import android.widget.Toast;

import com.example.platonicsolids.geo.PlatonicSolid;
import com.example.platonicsolids.geo.Cube;
import com.example.platonicsolids.geo.Tetrahedron;
import com.example.platonicsolids.geo.Octahedron;
import com.example.platonicsolids.geo.Dodecahedron;

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
        private PlatonicSolid mShapes[] = new PlatonicSolid[4];
        private PlatonicSolid mShape;
        private int shapeFlag;


        public volatile float mAngleX; public volatile float mAngleY;

        public float getAngleX() {
            return mAngleX;
        }
        public void setAngleX(float angle) {
            mAngleX = angle;
        }
        public float getAngleY() {
            return mAngleY;
        }
        public void setAngleY(float angle) {
            mAngleY = angle;
        }

        public String getCurrShapeStr() {
            return String.valueOf(mShapes[shapeFlag].getClass().getSimpleName());
        }

        public void switchShape() {
            shapeFlag = shapeFlag < 3 ? shapeFlag + 1 : 0;
        }

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            mShapes[0] = new Tetrahedron();
            mShapes[1] = new Cube();
            mShapes[2] = new Octahedron();
            mShapes[3] = new Dodecahedron();
            // Set the background frame color
            GLES20.glClearColor(r, g, b, 1.0f);
            mShape = new Tetrahedron();
            // Enable depth test
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            // Accept fragment if it closer to the camera than the former one
            GLES20.glDepthFunc(GLES20.GL_LESS);
        }

        public void onDrawFrame(GL10 unused) {
            // Redraw background color
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

            // Set the camera position (View matrix)
            Matrix.setLookAtM(viewMatrix, 0, 0, 0, -6, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

            // Calculate the projection and view transformation
            Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

            float[] scratch = new float[16];

            // Create a rotation for the triangle
            Matrix.setRotateM(rotationMatrix, 0, mAngleY, 1, 0, 0);
            Matrix.rotateM(rotationMatrix, 0, mAngleX, 0, 1, 0);

            // Combine the rotation matrix with the projection and camera view
            // Note that the vPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);

            // Draw shape
            mShapes[shapeFlag].draw(scratch);
        }

        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            float ratio = (float) width / height;

            // this projection matrix is applied to object coordinates
            // in the onDrawFrame() method
            Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 100);
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

        // For double-click events
        private int clickCount = 0;
        private long startTime;
        private long duration;
        private static final int CLICK_TIMEOUT = 300;

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            // MotionEvent reports input details from the touch screen
            // and other input controls. In this case, you are only
            // interested in events where the touch position changed.

            float x = e.getX();
            float y = e.getY();

            switch (e.getAction()) {
                // Detect double-tap events
                case MotionEvent.ACTION_DOWN:
                    // reset start time if this is the first click
                    if (clickCount == 0) { startTime = System.currentTimeMillis(); }
                    clickCount++;

                    // Start timers for checking tap speed
                    long time = System.currentTimeMillis() - startTime;
                    duration = duration + time;

                    // If it's the second, check if clicks timed out or not
                    if(clickCount == 2) {
                        if (duration <= CLICK_TIMEOUT) {
                            renderer.switchShape();
                            Toast.makeText(GLActivity.this,
                                    String.valueOf(renderer.getCurrShapeStr()),
                                    Toast.LENGTH_LONG).show();
                            clickCount = 0;
                        }
                        // Keep current click active if timeout happened
                        else {
                            clickCount = 1;
                        }
                        startTime = System.currentTimeMillis();
                        duration = 0;
                    }
                    break;
                // Edit rotation with dragging motions
                case MotionEvent.ACTION_MOVE:

                    float dx = x - previousX;
                    float dy = y - previousY;

                    renderer.setAngleX(
                            renderer.getAngleX() +
                                    (dx * TOUCH_SCALE_FACTOR));
                    renderer.setAngleY(
                            renderer.getAngleY() +
                                    (dy * TOUCH_SCALE_FACTOR));
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

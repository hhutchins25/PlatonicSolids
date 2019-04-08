// GLActivity.java
// Primarily based off Android example
// https://developer.android.com/training/graphics/opengl/
// Built upon by Holden Hutchins, 2019

package com.example.platonicsolids;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.platonicsolids.geo.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLActivity extends AppCompatActivity {

    public static class MyGLRenderer implements GLSurfaceView.Renderer {

        private float r = 0.25f;
        private float g = 0.5f;
        private float b = 1.0f;

        private Triangle mTriangle;

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            // Set the background frame color
            GLES20.glClearColor(r, g, b, 1.0f);
            // initialize a triangle
            mTriangle = new Triangle();
        }

        public void onDrawFrame(GL10 unused) {
            // Redraw background color
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            r += r < 1.0f ? 0.001f : -0.4f;
            g += g < 1.0f ? 0.002f : -0.8f;
            b += b < 1.0f ? 0.005f : -1.0f;
            GLES20.glClearColor(r, g, b, 1.0f);
            mTriangle.draw();
        }

        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }

        public static int loadShader(int type, String shaderCode){

            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            int shader = GLES20.glCreateShader(type);

            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);

            return shader;
        }
    }

    class MyGLSurfaceView extends GLSurfaceView {

        private final MyGLRenderer renderer;

        public MyGLSurfaceView(Context context){
            super(context);

            // Create an OpenGL ES 2.0 context
            setEGLContextClientVersion(2);

            renderer = new MyGLRenderer();

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(renderer);
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

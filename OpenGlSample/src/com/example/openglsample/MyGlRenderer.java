package com.example.openglsample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import Shapes.Square;
import Shapes.Triangle;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class MyGlRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
	private Square mSquare;

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
        
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

}

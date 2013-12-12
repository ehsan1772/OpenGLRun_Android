package com.example.openglsample;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {
	 private GLSurfaceView mGLView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class MyGLSurfaceView extends GLSurfaceView {

	    public MyGLSurfaceView(Context context){
	        super(context);
		     // Create an OpenGL ES 2.0 context
	        setEGLContextClientVersion(2);
	        
	        // Set the Renderer for drawing on the GLSurfaceView
	        setRenderer(new MyGlRenderer());

	     // Render the view only when there is a change in the drawing data
	        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	       
	    }
	}
}

package com.example.openglsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import util.TextResourceReader;

import android.content.Context;
import android.opengl.GLSurfaceView;

import static android.opengl.GLES20.*;

public class AirHockeyRenderer implements GLSurfaceView.Renderer {
	private static final int BYTES_PER_FLOAT = 4;
	private final FloatBuffer vertexData;
	private static final int POSITION_COMPONENT_COUNT = 2;
	private int program;
	
	private static final String U_COLOR = "u_Color"; 
	private int uColorLocation;
	
	private static final String A_POSITION = "a_Position"; 
	private int aPositionLocation;

	float[] tableVertices = { 0f, 0f, 0f, 14f, 9f, 14f, 9f, 0f };

	float[] tableVerticesWithTriangles = { 
			//triangle 1
			0f, 0f, 
			9f, 14f,
			0f, 14f,
			
			// Triangle 2
			0f, 0f,
			9f, 0f,
			9f, 14f,
			
			//Line 1
			0f, 7f,
			9f, 7f,
			
			//Mallets
			4.5f, 2f,
			4.5f, 12f
		};
	private Context mContext;

	public AirHockeyRenderer(Context context) {
		this.mContext = context;
		vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(tableVerticesWithTriangles);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		String vertexShaderSource = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_vertex_shader);
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_fragment_shader);
		
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
		
		program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
		
		ShaderHelper.validateProgram(program);
		
		glUseProgram(program);
		
		//getting the locations of the attribute and the uniform
		uColorLocation = glGetUniformLocation(program, U_COLOR);
		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		
		// poiting OpenGL toward the data that should be associated with the position location
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
		
		//telling OpenGL where to find the info it needs
		glEnableVertexAttribArray(aPositionLocation);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		//update the value of u_Color in our shader
		glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
		//draw triangle
	    glDrawArrays(GL_TRIANGLES, 0, 6);
	    
	  //update the value of u_Color in our shader
	    glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
	    //draw the deviding line
	    glDrawArrays(GL_LINES, 6, 2);
	    
	 // Draw the first mallet blue.
	    glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
	    glDrawArrays(GL_POINTS, 8, 1);
	    // Draw the second mallet red.
	    glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
	    glDrawArrays(GL_POINTS, 9, 1);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Set the OpenGL viewport to fill the entire surface.
	    glViewport(0, 0, width, height);

	}
}

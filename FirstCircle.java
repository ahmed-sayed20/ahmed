/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

/**
 *
 * @author ahmed
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;

/**
 * This is a basic JOGL app. Feel free to 
 * reuse this code or modify it.
 */
public class FirstCircle extends JFrame {

  /**
	 * 
	 */
	

public static void main(String[] args) {
    final FirstCircle app = new FirstCircle();

    // show what we've done
   SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          app.setVisible(true);
        }
      }
    );
  }

  public FirstCircle() {
    //set the JFrame title
//kill the process when the JFrame is closed
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//only three JOGL lines of code ... and here they are
GLCapabilities glcaps = new GLCapabilities();
GLCanvas glcanvas =
new GLCanvas();
glcanvas.addGLEventListener(
new FirstCircleListener()
);
//add the GLCanvas just like we would any Component
getContentPane().add(glcanvas, BorderLayout.CENTER);
setSize(500, 300);
//center the JFrame on the screen
centerWindow(this);
}
public void centerWindow(Component frame) {
Dimension screenSize =
Toolkit.getDefaultToolkit().getScreenSize();
Dimension frameSize = frame.getSize();
if (frameSize.width > screenSize.width )
frameSize.width = screenSize.width;
if (frameSize.height > screenSize.height)
frameSize.height = screenSize.height;
frame.setLocation (
(screenSize.width - frameSize.width ) >> 1,
(screenSize.height - frameSize.height) >> 1
);
}
  }

  

/**
 * For our purposes only two of the 
 * GLEventListeners matter. Those would 
 * be init() and display().
 */
class FirstCircleListener implements GLEventListener {
    final double ONE_DEGREE = (Math.PI/180);
final double THREE_SIXTY = 2 * Math.PI;
  /**
   * Take care of initialization here.
   */
  public void init(GLAutoDrawable drawable) {
	  	GL gl = drawable.getGL();
	    
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    
	    gl.glViewport(-250, -150, 250, 150);
	    gl.glMatrixMode(GL.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrtho(-250, 250, -150, 150, -1.0, 1.0);

  }
	
  /**
   * Take care of drawing here.
   */
  public void display(GLAutoDrawable drawable) {
		
		double x,y;
double radius = 100;
float red = 0.5f;
float green = 0.0f;
float blue = 0.5f;
GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(red, green, blue);
gl.glBegin(GL.GL_POLYGON);
for (double a=0; a<THREE_SIXTY; a+=ONE_DEGREE) {
x = radius * (Math.cos(a));
y = radius * (Math.sin(a));
gl.glVertex2d(x, y);
}
gl.glEnd();
}
  
  
    
  /**
   * Called when the GLDrawable (GLCanvas 
   * or GLJPanel) has changed in size. We 
   * won't need this, but you may eventually 
   * need it -- just not yet.
   */
  public void reshape(
                        GLAutoDrawable drawable, 
                        int x, 
                        int y, 
                        int width, 
                        int height
                      ) {}
	
  /**
   * If the display depth is changed while the 
   * program is running this method is called.
   * Nowadays this doesn't happen much, unless 
   * a programmer has his program do it.
   */
  public void displayChanged(
                              GLAutoDrawable drawable, 
                              boolean modeChanged, 
                              boolean deviceChanged
                            ) {}

public void dispose(GLAutoDrawable arg0) {
	// TODO Auto-generated method stub
	
}
	
}


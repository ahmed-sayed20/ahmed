/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

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
public class LineGraphApp extends JFrame implements
ActionListener {
LineGraphAppListener listener = new LineGraphAppListener();
GLCanvas glcanvas;
JTextField ajtf = new JTextField("3", 3);
JTextField bjtf = new JTextField("2", 3);
JTextField mjtf = new JTextField("-1", 6);
  /**
	 * 
	 */
	

public static void main(String[] args) {
    final LineGraphApp app = new LineGraphApp();

    // show what we've done
    SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          app.setVisible(true);
        }
      }
    );
  }

  public LineGraphApp() {
    //set the JFrame title
   super("Point-Slope Calculation");

   
//kill the process when the JFrame is closed
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Setting up our southern JPanel
JPanel jp = new JPanel();
//adding the JTextFields and JLabels
jp.add(new JLabel("x:"));
jp.add(ajtf);
jp.add(new JLabel(" y:"));
jp.add(bjtf);
jp.add(new JLabel(" slope: "));
jp.add(mjtf);
//adding the JButton
JButton jb = new JButton("Redraw");
jb.addActionListener(this);
jp.add(jb);
getContentPane().add("South", jp);
//only three JOGL lines of code ... and here they are
glcanvas =
new GLCanvas();
glcanvas.addGLEventListener(listener);
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
public void actionPerformed(ActionEvent ae) {
listener.a = Double.parseDouble( ajtf.getText() );
listener.b = Double.parseDouble( bjtf.getText() );
listener.m = Double.parseDouble( mjtf.getText() );
glcanvas.repaint();
}
}
/**
 * For our purposes only two of the 
 * GLEventListeners matter. Those would 
 * be init() and display().
 */
class LineGraphAppListener implements GLEventListener {
    public double m = -1;
public double a = 3;
public double b = 2;
    
  /**
   * Take care of initialization here.
   */
  public void init(GLAutoDrawable drawable) {
	  	GL gl = drawable.getGL();
	    
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    gl.glLineWidth(2.0f);
gl.glPointSize(2.0f);
	    gl.glViewport(-250, -150, 250, 150);
	    gl.glMatrixMode(GL.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrtho(-250, 250, -150, 150, -1.0, 1.0);

  }
	
  /**
   * Take care of drawing here.
   */
  public void display(GLAutoDrawable drawable) {
		
		float red ;
                float green ;
float blue ;
		GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		gl.glPointSize(5.0f);
		
		
red= .09f;
green= .12f;
blue= .15f;
gl.glColor3f(red, green, blue);
gl.glBegin(GL.GL_LINES);
for (int x=-250; x<=250; x+=10) {
gl.glVertex2d(x, -150);
gl.glVertex2d(x, 150);
}
for (int x=-250; x<=250; x+=10) {
gl.glVertex2d(x, -150);
gl.glVertex2d(x, 150);
}
for (int y=-150; y<=150; y+=10) {
    gl.glVertex2d(-250, y);
gl.glVertex2d(250, y);
}
gl.glEnd();
red = 0.0f;
green = 0.2f;
blue = 0.4f;
gl.glColor3f(red, green, blue);
gl.glBegin(GL.GL_LINES);
gl.glVertex2d(0, 140);
gl.glVertex2d(0, -140);
gl.glVertex2d(240, 0);
gl.glVertex2d(-240, 0);
gl.glEnd();
gl.glBegin(GL.GL_TRIANGLES);
gl.glVertex2d( 0, 150);
gl.glVertex2d(-5, 140);
gl.glVertex2d( 5, 140);
gl.glVertex2d( 0, -150);
gl.glVertex2d(-5, -140);
gl.glVertex2d( 5, -140);
gl.glVertex2d(250, 0);
gl.glVertex2d(240,-5);
gl.glVertex2d(240, 5);
gl.glVertex2d(-250, 0);
gl.glVertex2d(-240,-5);
gl.glVertex2d(-240, 5);
gl.glEnd();

red = 1.0f;
green = 0.2f;
blue = 0.2f;
gl.glColor3f(red, green, blue);
gl.glBegin(GL.GL_POINTS);
double a1 = a * 10;
double b1 = b * 10;
for (int x=-250; x<=250; x++) {
gl.glVertex2d(x, (m * (x - a1) + b1) );
}
gl.glEnd();
red = 1.0f;
green = 1.0f;
blue = 1.0f;
gl.glColor3f(red, green, blue);
gl.glPointSize(4.0f);
gl.glBegin(GL.GL_POINTS);
gl.glVertex2d( a1, b1 );
gl.glEnd();
gl.glPointSize(2.0f);
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

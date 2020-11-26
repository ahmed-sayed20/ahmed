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
public class TrigGraphApp extends JFrame implements ActionListener {
TrigGraphAppListener listener = new TrigGraphAppListener();
GLCanvas glcanvas;
  /**
	 * 
	 */
	

public static void main(String[] args) {
    final TrigGraphApp app = new TrigGraphApp();

    // show what we've done
    SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          app.setVisible(true);
        }
      }
    );
  }

  public TrigGraphApp() {
    //set the JFrame title
super("Sine, Cosine and Tangent");
    //kill the process when the JFrame is closed
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel jp = new JPanel();
ButtonGroup bg = new ButtonGroup();
JRadioButton jrb1 = new JRadioButton("Sine", true);
jrb1.setActionCommand("sine");
jrb1.addActionListener(this);
//setting up the cosine JRadioButton
JRadioButton jrb2 = new JRadioButton("Cosine");
jrb2.setActionCommand("cosine");
jrb2.addActionListener(this);
//setting up the tangent JRadioButton
JRadioButton jrb3 = new JRadioButton("Tangent");
jrb3.setActionCommand("tangent");
jrb3.addActionListener(this);
//adding the buttons to the ButtonGroup
bg.add( jrb1 );
bg.add( jrb2 );
bg.add( jrb3 );
//adding the buttons to the JPanel
jp.add( jrb1 );
jp.add( jrb2 );
jp.add( jrb3 );
getContentPane().add("South", jp);
//only three JOGL lines of code ... and here they are

glcanvas =
new GLCanvas();;
glcanvas.addGLEventListener(listener);
//add the GLCanvas just like we would any Component
getContentPane().add(glcanvas, BorderLayout.CENTER);
setSize(500, 300);
//center the JFrame on the screen
centerWindow(this);
}
    //only three JOGL lines of code ... and here they are 
   
  

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

    @Override
   public void actionPerformed(ActionEvent ae) {
listener.whatToDraw = ae.getActionCommand();
glcanvas.repaint();
}
}

/**
 * For our purposes only two of the 
 * GLEventListeners matter. Those would 
 * be init() and display().
 */
class TrigGraphAppListener implements GLEventListener {
    public String whatToDraw = "sine";
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
if (whatToDraw.equals("sine")) {
//draw enlarged sine function
for (int x=-250; x<250;x++)
gl.glVertex2d(x, (Math.sin(x/60.0)*100.0));
} else if (whatToDraw.equals("cosine")) {
//draw enlarged cosine function
for (int x=-250; x<250;x++)
gl.glVertex2d(x, (Math.cos(x/60.0)*100.0));
} else if (whatToDraw.equals("tangent")) {
//draw enlarged tangent function
for (int x=-250; x<250;x++)
gl.glVertex2d(x, (Math.tan(x/60.0)*30.0));
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



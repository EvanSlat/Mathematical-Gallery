
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;


public class NeatCurveViewer {

	
	
	/**
	 * The width of the frame.
	 */
	public static final int WIDTH = 800;
	/**
	 * The height of the frame.
	 */
	public static final int HEIGHT = 800;
	public static boolean animationStarted = false;
	public static boolean animationPaused = false;
	public static Timer timer;
	public static CurveComponent c;
	
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		JFrame frame = new JFrame();
//get the information to start		
		frame.setTitle("Neet curve");
		InfoComponent i = new InfoComponent();
		i.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.add(i);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(animationStarted) {
					System.out.println(e.getKeyChar());
					//allow for pausing and unpausing
					switch(e.getKeyChar()) {
						case ' ':
							if(animationPaused) {
								animationPaused = false;
								timer.start();
							}else {
								animationPaused = true;
								timer.stop();
							}
							break;
						case 'a':
							c.incrementDegree(-1);
							c.repaint();
							break;
						case 'd':
							c.incrementDegree(1);
							c.repaint();
							break;
						case 'w':
							c.incrementRadius(1);
							c.repaint();
							break;
						case 's':
							c.incrementRadius(-1);
							c.repaint();
							break;
						default:
					}
				}else {
					//get the typed key and repaint things
					i.reseveKey(e.getKeyChar());
					i.repaint();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});

		i.textToDisplay("Input number of points, standerd 2000 : This is how many lines there will be, more means a better quality");
		int points = -1;
		while(points == -1) {
			points = i.getData();
			System.out.print("");
		}
		if(points == -2) {
			points = 2000;
		}
		i.textToDisplay("Input radius, standerd 300 : This is the radius for the circle that is created");
		i.repaint();
		int radius = -1;
		while(radius == -1) {
			radius = i.getData();
		}
		if(radius == -2) {
			radius = 300;
		}
		i.textToDisplay("Input starting degree, standerd 1 : This is how far into the pattern it will start at");
		i.repaint();
		int degree = -1;
		while(degree == -1) {
			degree = i.getData();
		}
		if(degree == -2) {
			degree = 1;
		}
		
		i.textToDisplay("Input movement speed, standerd 10 : This is how fast the digree will increse the number is divided by 10000");
		i.repaint();
		int speed = -1;
		while(speed == -1) {
			speed = i.getData();
		}
		if(speed == -2) {
			speed = 10;
		}
		i.textToDisplay("Input FPS, default 60 : This is how fast the screen will try to refresh at");
		i.repaint();
		int fps = -1;
		while(fps == -1) {
			fps = i.getData();
		}
		if(fps == -2) {
			fps = 60;
		}
		fps = 1000/fps;
		
		//this now creates the actual art
		c = new CurveComponent(points,radius,degree,speed);
//		System.out.print("Will it be animated(insert a boolean)");
		frame.remove(i);
		c.setPreferredSize(new Dimension(radius*2,radius*2));
		frame.add(c);
		
		frame.pack();
		
		boolean animated = true;
		 timer = new Timer(fps,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.tick();
				
			}
		});
		if(animated) {
			timer.start();
		}
		animationStarted = true;
		
		keyboard.close();
	}
}

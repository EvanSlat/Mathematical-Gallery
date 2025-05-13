package NewScreens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/*
 * Goal of this class is to control what screen are seen 
 */
public class MainManager {

	private static JFrame frame;
	private static ScreenLogic currentScreen;
	public static final int FRAME_HEIGHT = 1000;
	public static final int FRAME_WIDTH = 1000;
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		//get the information to start		
		frame.setTitle("Neet curve");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1000,1000));
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
//				userInputs(e,null);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				userInputs(e,null);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});
		//TODO find out if I need the additinal information events
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				userInputs(null,e);
				
			}
		});
		
		currentScreen = new ControlPanel();
		frame.add(currentScreen);
		currentScreen.addSubComponents(frame);
//		frame.pack();
		
		frame.setVisible(true);
		
	}
	
	public static void changeScreens(ScreenLogic ns) {
		frame.getContentPane().removeAll();
		ns.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		ns.addSubComponents(frame);
//		ns.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		frame.add(ns);
		
		frame.revalidate();
		frame.repaint();
		frame.setFocusable(true); 
		frame.requestFocusInWindow(); 
		currentScreen = ns;
	}
	
	public static void userInputs(KeyEvent ke, MouseEvent me) {
		ScreenLogic newScreen = currentScreen.InterperetUserInput(ke, me);
		if(newScreen != null) {
			changeScreens(newScreen);
		}
	}
}

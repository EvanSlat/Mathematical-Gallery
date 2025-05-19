package NewScreens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		
		
		
//		currentScreen = new ControlPanel();
		currentScreen = new MandelViewer();
		frame.add(currentScreen);
		currentScreen.addSubComponents(frame);
//		frame.pack();
		UserInput.setUpListeners(frame);
		
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
	
	public static void userInputs(UserInput ui) {
		ScreenLogic newScreen = currentScreen.InterperetUserInput(ui);
		if(newScreen != null) {
			changeScreens(newScreen);
		}
	}
	
	
	
}

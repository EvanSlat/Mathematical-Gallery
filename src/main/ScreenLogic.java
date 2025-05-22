package main;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public abstract class ScreenLogic extends JComponent{

	public static int fps = 60;
	public static Timer timer = null;
	protected int timeStep;
	
	/*
	 * This returns what screen should be switched to, should return self when nothing needs to be updated
	 * If it does need to switch screens, then it will self populate the screen data.
	 */
	public abstract ScreenLogic InterperetUserInput(UserInput ui);
	
	public abstract void addSubComponents(JFrame frame);
	
	
	public void startTimer() {
		if(timer == null) {
			timeStep = 0;
		
			timer = new Timer(1000/fps, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					repaint();
					timeStep ++;
				}
			});
			timer.start();
		}else {
			timer.start();
		}
	}
	/*
	 * This will pause it letting you resume later
	 */
	public void pauseUnpauseTimer() {
		if(timer != null) {
			if(timer.isRunning()) {
				timer.stop();
			}else {
				timer.start();
			}
		}
	}
	
	/*
	 * this clears the timer letting it be remade in other components and to avoid memory loss.
	 */
	public void endTimer() {
		if(timer != null) {
			timer.stop();
			timer = null;
		}
	}
}

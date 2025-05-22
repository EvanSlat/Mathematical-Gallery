

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class UserInput{
	private static int oldX;
	private static int oldY;
	public int deltaX;
	public int deltaY;
	public KeyEvent ke;
	public MouseEvent me;
	public MouseWheelEvent mwe;
	public boolean mouseDragEvent = false;
	
	public UserInput(KeyEvent ke, MouseEvent me, MouseWheelEvent mwe) {
		this.ke = ke;
		this.me = me;
		this.mwe = mwe;
		if(me != null) {
			
			this.deltaX = oldX - me.getX();
			this.deltaY = oldY - me.getY();
			oldX = me.getX();
			oldY = me.getY();
		}
				
	}
	
	
	
	public static void setUpListeners(JFrame frame) {
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
//				userInputs(e,null);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				MainManager.userInputs(new UserInput(e, null,null));
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
				new UserInput(null, e,null);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {

			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MainManager.userInputs(new UserInput(null, e,null));
				
			}
		});
		
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				//TODO make a system for tracking how far the mouse has moved after being pressed
				UserInput temp = new UserInput(null, e,null);
				temp.mouseDragEvent = true;
				MainManager.userInputs(temp);
				
				
			}
		});
		
		frame.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				MainManager.userInputs(new UserInput(null, null, e));
			}
		});
	}
}

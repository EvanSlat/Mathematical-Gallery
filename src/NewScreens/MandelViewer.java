package NewScreens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MandelViewer extends ScreenLogic{
	
	private Mandelbrot func;
	private int xOffset;
	private int yOffset;
	MandelViewer(){
		func = new Mandelbrot(-2,-2,4,4,100,100,100,300,300);
		xOffset = 0;
		yOffset = 0;
	}
	
	@Override
	public ScreenLogic InterperetUserInput(KeyEvent ke, MouseEvent me) {
		if(ke != null) {
			switch(ke.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE:
					endTimer();
					return new ControlPanel();
				case KeyEvent.VK_SPACE:
					pauseUnpauseTimer();
					break;
				case KeyEvent.VK_LEFT:
					timeStep--;
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					timeStep++;
					repaint();
					break;
				default:
					break;
			}
			
			
		}
		
		if(me != null) {
			System.out.println("Event");
			if(me.getButton() == MouseEvent.BUTTON1) {
				System.out.println("Event1");
			}
			if(me.getButton() == MouseEvent.BUTTON2) {
				System.out.println("Event2");
			}
			if(me.getButton() == MouseEvent.BUTTON3) {
				System.out.println("Event3");
			}
		}
		
		
		
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//TODO factor in the screen shifting here, it will let people move the screen around more
		g2.translate(this.getWidth()/2, this.getHeight()/2); 
		g2.translate(xOffset,yOffset);
		func.drawFrame(g2,0);
	}
	
	@Override
	public void addSubComponents(JFrame frame) {
		// TODO Auto-generated method stub
	}
	

}

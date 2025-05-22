package newtonFractal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import auxilory.Point;
import main.ScreenLogic;
import main.UserInput;
import mandelBrot.MandelbrotControlPanel;
import mandelBrot.MandelbrotV2;

public class NewtonViewer extends ScreenLogic {

	private Point[] roots;
	private NewtonFractal func;
	private double baseX = 0;
	private double baseY = 0;
	private double baseScale = 4.0;
	private double scale = 1;
	private int itterations = 300;
	private double ScreenRadio = 1;
	
	public NewtonViewer(int itterations, Point[] roots) {
		this.itterations = itterations;
		this.roots = roots;
		ScreenRadio = (this.getHeight()+0.0)/(this.getWidth()+0.0);
		func = new NewtonFractal(baseY, baseX, baseScale,  (baseScale*ScreenRadio), itterations, this.getWidth(), this.getHeight(), roots);
		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						repaint();
					}
				},1000
				);
	}
	
	
	@Override
	public ScreenLogic InterperetUserInput(UserInput ui) {
		boolean ranInput = false;
		if(ui.ke != null) {
			switch(ui.ke.getKeyCode()) {
			case KeyEvent.VK_BACK_SPACE:
				endTimer();
				return new NewtonControlPanel();
			default:
				break;
			}
		}
		
		
		
		ScreenRadio = (this.getHeight()+0.0)/(this.getWidth()+0.0);
		if(ui.me != null) {
			if(ui.mouseDragEvent) {
				ranInput = true;
				baseY += baseScale/this.getWidth()*ui.deltaX;
				baseX += baseScale*ScreenRadio/this.getHeight()*ui.deltaY;
			}
		}
		
		if(ui.mwe != null) {
			ranInput = true;
			int scrollDirection = -1;
			if(ui.mwe.getUnitsToScroll()>0) {
				scrollDirection = 1;
			}
			baseScale += (scrollDirection*scale);  
			if(scrollDirection> 0) {
				scale = baseScale*2;
			}else {
				scale = baseScale/2;
			}

		}
		
		if(ranInput) {
			func = new NewtonFractal(baseY, baseX, baseScale,  (baseScale*ScreenRadio), itterations, this.getWidth(), this.getHeight(), roots);
			this.repaint();
		}
		return null;
	}

	@Override
	public void addSubComponents(JFrame frame) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		func.drawFrame(g2,0);
		
	}

}

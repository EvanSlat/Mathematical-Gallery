package NewScreens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;

public class MandelViewer extends ScreenLogic{
	
	private MandelbrotV2 func;
	private double baseX = 0;
	private double baseY = 0;
	private double baseScale = (long) 4.0;
	private double scale = 1;
//	private double scaleRate = 1.3;
	private int itterations = 300;
	private double ScreenRadio = 1;
	MandelViewer(){
		ScreenRadio = (this.getHeight()+0.0)/(this.getWidth()+0.0);
		func = new MandelbrotV2(baseY, baseX, baseScale, (baseScale*ScreenRadio), itterations, this.getWidth(), this.getHeight());
	}
	
	@Override
	public ScreenLogic InterperetUserInput(UserInput ui) {
		boolean ranInput = false;
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
		
		if(baseX > 2) {
			baseX = 2;
		}
		if(baseX < -2) {
			baseX = -2;
		}
		if(baseY > 2) {
			baseY = 2;
		}
		if(baseY < -2) {
			baseY = -2;
		}
		if(baseScale < 0) {
			baseScale = 0;
		}
		if(baseScale > 4) {
			baseScale = 4;
		}
		if(scale > 1) {
			scale = 1;
		}
		if(ranInput) {
			System.out.println("Origin:"+baseX +","+baseY+": sizes:"+baseScale +","+ baseScale*ScreenRadio+": Scale:"+scale);
			func = new MandelbrotV2(baseY, baseX, baseScale,  (baseScale*ScreenRadio), itterations, this.getWidth(), this.getHeight());
			ScreenRadio = (this.getHeight()+0.0)/(this.getWidth()+0.0);
			this.repaint();
		}
		
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		func.drawFrame(g2,0);
	}
	
	@Override
	public void addSubComponents(JFrame frame) {
		// TODO Auto-generated method stub
	}
	

}

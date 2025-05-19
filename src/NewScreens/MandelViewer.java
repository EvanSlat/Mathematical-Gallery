package NewScreens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;

public class MandelViewer extends ScreenLogic{
	
	private Mandelbrot func;
	private int xOffset;
	private int yOffset;
	private double baseX = -2.0;
	private double baseY = -2.0;
	private double baseW = 4.0;
	private double baseH = 4.0;
	private int sW = 300;
	private int sH = 300;
	MandelViewer(){
		func = new Mandelbrot(baseX,baseY,baseW,baseH,5000,5000,100,sW,sH);
		xOffset = 0;
		yOffset = 0;
	}
	
	@Override
	public ScreenLogic InterperetUserInput(UserInput ui) {
		if(ui.me != null) {
			if(ui.mouseDragEvent) {
				xOffset -= ui.deltaX;
				yOffset -= ui.deltaY;
				this.repaint();
			}
		}
		
		if(ui.mwe != null) {
			baseX += (ui.mwe.getUnitsToScroll()*0.1);
			baseY += (ui.mwe.getUnitsToScroll()*0.1);  
			baseW -= (ui.mwe.getUnitsToScroll()*0.2);  
			baseH -= (ui.mwe.getUnitsToScroll()*0.2);
			sW +=  (ui.mwe.getUnitsToScroll()*30);
			sH +=  (ui.mwe.getUnitsToScroll()*30);
			func = new Mandelbrot(baseX,baseY,baseW,baseH,5000,5000,100,sW,sH);
			this.repaint();
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

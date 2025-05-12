package NewScreens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class CurveViewer extends ScreenLogic {
	private static final long serialVersionUID = 1L;
	private MathFunction func;
	
	
	CurveViewer(int points, double stepScale, int radius){
		func = new Cardioid(points, stepScale, radius);
	}
	
	@Override
	public ScreenLogic InterperetUserInput(KeyEvent ke, MouseEvent me) {
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(this.getWidth()/2, this.getHeight()/2); 
		func.drawFrame(g2, this.timeStep);
	}
	
	@Override
	public void addSubComponents(JFrame frame) {
		// TODO Auto-generated method stub
		startTimer();
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
	
}

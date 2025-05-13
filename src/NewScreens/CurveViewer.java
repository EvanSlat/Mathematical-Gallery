package NewScreens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class CurveViewer extends ScreenLogic {
	private static final long serialVersionUID = 1L;
	private MathFunction func;
	private int degree;
	private double stepScale;
	private int inverseStepScale;
	
	CurveViewer(int points, double stepScale, int radius, int degree){
		func = new Cardioid(points, stepScale, radius);
		this.degree = degree;
		this.stepScale = stepScale;
		this.inverseStepScale = (int) (1/stepScale);
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
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double hold = this.timeStep* stepScale + +this.degree;
		g2.drawString("Left and Right to go forwards and backwards by 1 step", 0, 20);
		g2.drawString("Current Degree: "+hold+"", 0, 35);
		g2.drawString("Up and Down to increase and decraese Radius", 0, 50);
		g2.drawString("Current Radius: "+func.getRadius()+"", 0, 65);
		g2.drawString("Press Space to pause", 0, 80);
		
		
		g2.translate(this.getWidth()/2, this.getHeight()/2); 
		func.drawFrame(g2, this.timeStep +this.degree* inverseStepScale);
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

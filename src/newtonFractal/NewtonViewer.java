package newtonFractal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import main.ScreenLogic;
import main.UserInput;
import mandelBrot.MandelbrotControlPanel;
import mandelBrot.MandelbrotV2;

public class NewtonViewer extends ScreenLogic {

	
	private NewtonFractal func;
	
	public NewtonViewer() {
		func = new NewtonFractal();
	}
	
	
	@Override
	public ScreenLogic InterperetUserInput(UserInput ui) {
		if(ui.ke != null) {
			switch(ui.ke.getKeyCode()) {
			case KeyEvent.VK_BACK_SPACE:
				endTimer();
				return new NewtonControlPanel();
			default:
				break;
			}
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

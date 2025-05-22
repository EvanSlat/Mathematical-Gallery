package newtonFractal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ControlPanelLogic;
import main.MainManager;
import main.ScreenLogic;
import main.UserInput;
import mandelBrot.MandelViewer;

public class NewtonControlPanel extends ControlPanelLogic {
	
	private JPanel localPanel;
	private JButton inputButton;
	

	private void setUpComponents() {
		localPanel = new JPanel();
		localPanel.setBounds(0, 30, MainManager.FRAME_WIDTH, MainManager.FRAME_HEIGHT);
		localPanel.setLayout(null);
		inputButton = new JButton("Enter");
		inputButton.setBounds(10, 0, 100, 20);
		inputButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainManager.changeScreens(new NewtonViewer());
			}
		});
		
		localPanel.add(inputButton);
	}
	
	
	@Override
	public ScreenLogic InterperetUserInput(UserInput ui) {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	public void addMySubComponents(JFrame frame) {
		// TODO Auto-generated method stub
		setUpComponents();
		frame.add(localPanel);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Newton Fractal";
	}
}

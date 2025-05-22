package mandelBrot;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.*;

public class MandelbrotControlPanel extends ControlPanelLogic {
	private JPanel localPanel;
	
	private JTextField itterationText;
	private JTextField itterationInput;
	
	private JButton inputButton;
	
	
	private void setUpComponents() {
		localPanel = new JPanel();
		localPanel.setBounds(0, 30, MainManager.FRAME_WIDTH, MainManager.FRAME_HEIGHT);
		localPanel.setLayout(null);
		
		itterationText  = new JTextField("Enter Max Number of itteratiosn");
		itterationInput = new JTextField("1000");
		
		itterationText.setBounds(10, 10, 300, 20);
		itterationText.setEditable(false);
		itterationInput.setBounds(10, 40, 300, 20);
		
		localPanel.add(itterationText);
		localPanel.add(itterationInput);
		
		inputButton = new JButton("Enter");
		inputButton.setBounds(10, 70, 100, 20);
		inputButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int itterations = Integer.parseInt(itterationInput.getText());
				MainManager.changeScreens(new MandelViewer(itterations));
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
		setUpComponents();
		frame.add(localPanel);

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Mandelbrot";
	}
}

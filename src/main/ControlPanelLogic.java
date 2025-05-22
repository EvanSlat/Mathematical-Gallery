package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cardiod.CardiodControlPanel;
import mandelBrot.MandelbrotControlPanel;
import newtonFractal.NewtonControlPanel;

public abstract class ControlPanelLogic extends ScreenLogic{

	
	private JPanel dropDownPanel;
	private JComboBox<ScreenLogic> csDropDown;
	private static ScreenLogic[] controlScreens = {new CardiodControlPanel(), new MandelbrotControlPanel(), new NewtonControlPanel()};
	private static int selectedIndex = 0;
	
	
	public abstract ScreenLogic InterperetUserInput(UserInput ui);
	
	public abstract void addMySubComponents(JFrame frame);
	
	public void addSubComponents(JFrame frame) {
		addDropDown(frame);
		addMySubComponents(frame);
	}
	
	
	
	
	private void addDropDown(JFrame frame) {
		dropDownPanel = new JPanel();
		dropDownPanel.setBounds(0, 0, MainManager.FRAME_WIDTH, 30);
		csDropDown = new JComboBox<ScreenLogic>(controlScreens);
		csDropDown.setSelectedIndex(selectedIndex);
		csDropDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedIndex = csDropDown.getSelectedIndex();
				MainManager.changeScreens((ScreenLogic) csDropDown.getSelectedItem());
				
			}
		});
		
		dropDownPanel.add(csDropDown);
		frame.add(dropDownPanel);
	}

}

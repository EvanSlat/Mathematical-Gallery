package NewScreens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

/*
 * This will eventuialy be able to be used for selecting multiple stuff and setting the paramiters of it, however for now it will not due so
 */
public class ControlPanel extends ScreenLogic {
	private JTextField pointsText;
	private JTextField pointsInput;
	private JTextField radiusText;
	private JTextField radiusInput;
	private JTextField degreeText;
	private JTextField degreeInput;
	private JTextField speedText;
	private JTextField speedInput;
	private JTextField fpsText;
	private JTextField fpsInput;
	
	private JPanel localPanel;
	
	private JTextField[] allComponents;
	
	public ControlPanel() {
		setUpComponents();
	}
	
	
	private void setUpComponents() {
		allComponents = new JTextField[10];
		localPanel = new JPanel();
		localPanel.setBounds(0, 0, MainManager.FRAME_WIDTH, MainManager.FRAME_HEIGHT);
		localPanel.setLayout(null);
		
		pointsText  = new JTextField("Enter Number of Points");
		pointsInput = new JTextField("2000");
		radiusText  = new JTextField("Enter Desired Radius");
		radiusInput = new JTextField("300");
		degreeText  = new JTextField("Enter Starting Degree");
		degreeInput = new JTextField("2");
		speedText   = new JTextField("Enter Starting speed");
		speedInput  = new JTextField("1");
		fpsText     = new JTextField("Enter Frames per Second");
		fpsInput    = new JTextField("60");
		
		allComponents[0] = pointsText ;
		allComponents[1] = pointsInput;
		allComponents[2] = radiusText ;
		allComponents[3] = radiusInput;
		allComponents[4] = degreeText ;
		allComponents[5] = degreeInput;
		allComponents[6] = speedText  ;
		allComponents[7] = speedInput ;
		allComponents[8] = fpsText    ;
		allComponents[9] = fpsInput   ;
		
		for(int i = 0; i<allComponents.length;i++) {
			allComponents[i].setBounds(10, 30*i+10, 300, 20);
			if(i%2 == 0) {
				allComponents[i].setEditable(false);
			}
			localPanel.add(allComponents[i]);
		}
	}
	

	@Override
	public void addSubComponents(JFrame frame) {
		frame.add(localPanel);
	}

	
	@Override
	public ScreenLogic InterperetUserInput(KeyEvent ke, MouseEvent me) {
		// TODO Auto-generated method stub
		this.fps = Integer.parseInt(fpsInput.getText());
		return new CurveViewer(Integer.parseInt(pointsInput.getText()),Integer.parseInt(speedInput.getText())*0.001,Integer.parseInt(radiusInput.getText()));
	}
}

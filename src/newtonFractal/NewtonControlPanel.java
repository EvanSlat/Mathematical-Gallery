package newtonFractal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import auxilory.Point;
import main.ControlPanelLogic;
import main.MainManager;
import main.ScreenLogic;
import main.UserInput;
import mandelBrot.MandelViewer;

public class NewtonControlPanel extends ControlPanelLogic {
	
	private JPanel localPanel;
	
	private JTextField itterationText;
	private JTextField itterationInput;
	
	private JTextField pointText;
	private JTextField pointXInput;
	private JTextField pointYInput;
	private ArrayList<JTextField> xRoots;
	private ArrayList<JTextField> yRoots;
	
	
	private JButton addPointButton;
	private JButton removePointButton;
	private JButton inputButton;

	public NewtonControlPanel() {
		localPanel = new JPanel();
		localPanel.setBounds(0, 30, MainManager.FRAME_WIDTH, MainManager.FRAME_HEIGHT);
		localPanel.setLayout(null);
		
		xRoots = new ArrayList<>();
		yRoots = new ArrayList<>();
		
		xRoots.add(new JTextField("1"));
		yRoots.add(new JTextField("0"));
		xRoots.add(new JTextField("-1"));
		yRoots.add(new JTextField("1"));
	}
	

	private void setUpComponents() {
		int nextHeight = 10;
		
		
		itterationText  = new JTextField("Enter Max Number of itteratiosn");
		itterationInput = new JTextField("1000");
		itterationText.setBounds(10, nextHeight, 300, 20);
		nextHeight+=30;
		itterationText.setEditable(false);
		itterationInput.setBounds(10, nextHeight, 300, 20);
		nextHeight+=30;
		localPanel.add(itterationText);
		localPanel.add(itterationInput);
		
		
		
		
		itterationText  = new JTextField("Enter x,y point combos, will create imaginary pairs if not created already");
		itterationText.setBounds(10, nextHeight, 300, 20);
		nextHeight+=30;
		itterationText.setEditable(false);
		//draws on the points
		for(int i = 0; i<xRoots.size();i++) {
			xRoots.get(i).setBounds(10,nextHeight,150,20);
			yRoots.get(i).setBounds(160,nextHeight,150,20);
			localPanel.add(xRoots.get(i));
			localPanel.add(yRoots.get(i));
			nextHeight+= 30;
		}
		
		
		
		
		//add remove points
		addPointButton = new JButton("Add Point");
		addPointButton.setBounds(10, nextHeight, 150, 20);
		addPointButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				localPanel.removeAll();
				xRoots.add(new JTextField("0"));
				yRoots.add(new JTextField("0"));
				setUpComponents();
				repaint();
			}});
		
		localPanel.add(addPointButton);
		
		removePointButton = new JButton("Remove Point");
		removePointButton.setBounds(160, nextHeight, 150, 20);
		removePointButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				localPanel.removeAll();
				xRoots.remove(xRoots.size()-1);
				yRoots.remove(yRoots.size()-1);
				setUpComponents();
				repaint();
			}});
		
		localPanel.add(removePointButton);
		
		
		nextHeight+=30;
		//final button
		inputButton = new JButton("Enter");
		inputButton.setBounds(10, nextHeight, 100, 20);
		nextHeight+=30;
		inputButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				manageScreenChange();
				
			}
		});
		
		localPanel.add(inputButton);
	}
	
	private void manageScreenChange() {
		
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i<xRoots.size();i++) {
			double x = Double.parseDouble(xRoots.get(i).getText());
			double y = Double.parseDouble(yRoots.get(i).getText());
			points.add(new Point(x, y));
		}
		duplicateImaginaryPoints(points);
		
		
		Point[] localRoots = new Point[points.size()];
		points.toArray(localRoots);
		
		int itterations = Integer.parseInt(itterationInput.getText());
		MainManager.changeScreens(new NewtonViewer(itterations, localRoots));
	}
	
	
	private void duplicateImaginaryPoints(ArrayList<Point> roots) {
		for(int i = 0; i<roots.size();i++) {
			if(roots.get(i).y == 0.0 || roots.contains(new Point(roots.get(i).x, -roots.get(i).y))) {
				continue;
			}
			System.out.println("adding point");
			roots.add(0,new Point(roots.get(i).x,-roots.get(i).y));
			i++;
		}
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


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class InfoComponent extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String info;
	private boolean dataFull;
	private int lastData;
	private String text;
	public InfoComponent() {
		this.dataFull = false;
		this.info = "";
		this.lastData = -1;
		this.text = "";
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString(text, 50, 50);
		g2.drawString(info, 50, 70);
	}
	public void reseveKey(char k) {
		if(k == '' && info.length() > 0) {
			info = info.substring(0,info.length()-1);
		}
		//what happens when you press enter
		else if(k == '\n') {
			//what if nothing is entered
			if(info.length() == 0) {
				this.lastData = -2;
				dataFull = true;
				info = "";
			}else {
				this.lastData = Integer.parseInt(info);
				dataFull = true;
				info = "";
			}
		}else if(Character.isDigit(k)){
			info += k;
		}
		//System.out.println(k);
	}

	public int getData() {
		if(dataFull) {
		int hold = lastData;
		lastData = -1;
		dataFull = false;
		return hold;
		}
		return -1;
	}
	
	public void textToDisplay(String t) {
		text = t;
	}

}

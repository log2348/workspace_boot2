package my_maple_story;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel {

	private ImageIcon monster;

	private int x;
	private int y;
	
	private boolean isThread = true;

	public Monster() {
		initObject();
		initSetting();
	}

	private void initObject() {
		monster = new ImageIcon("images/monster.gif");

	}

	private void initSetting() {
		x = 150;
		y = 430;

		setIcon(monster);
		setSize(monster.getIconWidth(), monster.getIconHeight());
		setLocation(x, y);
	}
	
	private class customPanel implements Runnable {

		@Override
		public void run() {
			
			boolean direction = false;
			
			while(true) {
				if(isThread) {
					
					if(direction) {
						x += 10;
					} else {
						x -= 10;
					}
					
					if(x == 400) {
						direction = false;
					}
					
					if(x == 10) {
						direction = true;
					}
					
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}

	public static void main(String[] args) {
		new Monster();
	}

}

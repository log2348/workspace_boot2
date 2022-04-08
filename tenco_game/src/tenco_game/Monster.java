package tenco_game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel{
	
	private ImageIcon monster;
	
	private int x;
	private int y;
	
	public Monster(){
		initObject();
		initSetting();
	}
	
	private void initObject() {
		monster = new ImageIcon("images/monster.gif");
		
	}
	
	private void initSetting() {
		x = 50;
		y = 300;
		
		setIcon(monster);
		setSize(200, 200);
		setLocation(x, y);
	}
	
	public static void main(String[] args) {
		new Monster();
	}

}

package tenco_game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame{
	
	private JLabel backgroundImg;
	private Player player;
	
	public MyFrame() {
		initObject();
		initSetting();
		initListener();
		
		setVisible(true);
	}
	
	private void initObject() {
		backgroundImg = new JLabel(new ImageIcon("images/background.png"));
		setContentPane(backgroundImg);
		
		player = new Player();
		add(player);
	}
	
	private void initSetting() {	
		setSize(1000, 640);
		setLayout(null);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void initListener() {
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed");
				
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT :
					
					if(!player.isLeft()) {
						player.left();
					}
					
					break;
				case KeyEvent.VK_RIGHT :
					
					if(!player.isRight()) {
						player.right();					
					}
					break;
				case KeyEvent.VK_UP :
					player.up();
					break;
				case KeyEvent.VK_DOWN :
					player.down();
					break;
				}
					
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased");
				
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT :
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT :
					player.setRight(false);
					break;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

	
}

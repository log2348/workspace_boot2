package tenco_game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable{
	
	private int x;
	private int y;
	
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	private ImageIcon playerL;
	private ImageIcon playerR;
	
	public Player() {
		initObject();
		initSetting();

	}
	
	public boolean isRight() {
		return right;
	}



	public void setRight(boolean right) {
		this.right = right;
	}



	public boolean isLeft() {
		return left;
	}



	public void setLeft(boolean left) {
		this.left = left;
	}



	public boolean isUp() {
		return up;
	}



	public void setUp(boolean up) {
		this.up = up;
	}



	public boolean isDown() {
		return down;
	}



	public void setDown(boolean down) {
		this.down = down;
	}



	private void initObject() {
		playerL = new ImageIcon("images/olafL.png");
		playerR = new ImageIcon("images/olafR.png");
		
	}
	
	private void initSetting() {	
		x = 100;
		y = 100;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		setIcon(playerR);
		setSize(playerL.getIconWidth(), playerL.getIconHeight());
		setLocation(x, y);
		
	}

	@Override
	public void right() {
		System.out.println("right");
		
		right = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(right) {
					setIcon(playerR);
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	@Override
	public void left() {
		System.out.println("left");
		
		left = true;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(left) {
					setIcon(playerL);
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
	}

	@Override
	public void up() {
		System.out.println("up");
		up = true;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < (130 / JUMPSPEED); i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				up = false;
				down();
				
			}
		}).start();
		
	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < (130 / JUMPSPEED); i++) {
					y += JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				down = false;
				
			}
		}).start();
	}




}

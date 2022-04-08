package ver1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BackgroundMapFrame extends JFrame {

	private JLabel backgroundMap;
	private JLabel deliveryMap;
	private Player player;

	private JButton kitchenMapChangeBtn;
	private JButton deliveryMapChangeBtn;

	public BackgroundMapFrame() {
		initObject();
		initSetting();
		initListener();

		setVisible(true);

	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		deliveryMap = new JLabel(new ImageIcon("images/backgroundMapService.png"));

		kitchenMapChangeBtn = new JButton("주방으로");
		deliveryMapChangeBtn = new JButton("주방 나가기");

		setContentPane(backgroundMap);

		player = new Player();

	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null);
		add(kitchenMapChangeBtn);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initListener() {
		kitchenMapChangeBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	private void changePanel() {
		mapChangeBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				backgroundMap.setVisible(false);
				deliveryMap.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new BackgroundMapFrame();
	}

}
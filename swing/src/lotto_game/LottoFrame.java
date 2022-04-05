package lotto_game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LottoFrame extends JFrame implements ActionListener {

	private JButton startButton;
	private JPanel panel1;
	private JPanel panel2;

	int[] lottoNumbers = new int[6];

	public LottoFrame() {
		startButton = new JButton("로또");

		panel1 = new JPanel();
		panel2 = new JPanel();

		panel1.setBackground(Color.pink);
		panel1.add(startButton);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		add(panel1);
		add(panel2);

		setVisible(true);
		setLayout(new GridLayout(2, 1));
		setSize(600, 400);
		// setBackground(Color.pink);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lottoNumbers = getLotto();
		// 그림을 다시 그려라
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.black);
		Font font = new Font("굴림체", Font.BOLD, 15);
		g.setFont(font);

		if (lottoNumbers[0] == 0) {
			// 게임을 실행하지 않은 상태라면
			g.drawString("로또 버튼을 클릭하세요.", 200, 200);
			return;
		}

		// lottoNumber[0] 값이 있다면 (6개 번호를 생성한 후)
		// 여기서 번호를 그려 봅시다.

		for (int i = 0; i < lottoNumbers.length; i++) {
			g.drawString(lottoNumbers[i] + "", 50 * (i + 1), 100);
		}

	}

	public int[] getLotto() {

		int[] lotto = new int[6];
		Random random = new Random();
		for (int i = 0; i < lotto.length; i++) {
			int j = random.nextInt(45) + 1;
			lotto[i] = j;
			for (int k = 0; k < i; k++) {
				// 중복된 번호가 나왔을 경우
				if (lotto[i] == lotto[k]) {
					i = i - 1;
					break;
				}
			}
		}

		Arrays.sort(lotto); // 오름차순 정렬

		for (int i : lotto) {
			System.out.println(i);

		}

		return lotto;
	}

	public static void main(String[] args) {
		new LottoFrame();
	}

}

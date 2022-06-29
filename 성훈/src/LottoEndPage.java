import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LottoEndPage extends JFrame {
	public LottoEndPage() {
		JPanel pnlLotto = new JPanel(new BorderLayout(0, 30));
		JPanel pnlNorth = new JPanel(); // 회차
		JLabel center = new JLabel("당첨번호");
		JPanel pnlCenter = new JPanel(); // 당첨번호
		JPanel pnlCenter2 = new JPanel(); // 당점결과
		JLabel center2 = new JLabel();
		JPanel pnlSouth = new JPanel(); // 로또번호

		pnlLotto.setBackground(Color.WHITE);
		pnlCenter.setBackground(Color.blue);
		pnlCenter2.setBackground(Color.pink);
		
		//상단 설정
		
		//센터 설정
		center.setFont(new Font("PLAIN", Font.PLAIN, 25));
		center2.setFont(new Font("PLAIN", Font.CENTER_BASELINE, 20));
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));

		//하단 설정
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		pnlSouth.setBorder(new LineBorder(Color.BLACK));

		//위치
//		add(pnlLotto);
		pnlLotto.add(pnlNorth, "North");
		pnlCenter.add(center);
		pnlLotto.add(pnlCenter, "Center");
		pnlLotto.add(pnlCenter2, "Center");
		pnlLotto.add(pnlSouth, "South");
		
//		.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
//		.setBackgroud(Color.pink);
		//위쪽 센터 배경색 끄기
		pnlNorth.setOpaque(false);
		pnlCenter.setOpaque(false);
		setContentPane(pnlLotto);
//		pnlCenter.add(center);
		
		setSize(400, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//창의 크기를 조절할수 없게끔
		setResizable(false);
	}

	public static void main(String[] args) {
		new LottoEndPage().setVisible(true);
	}
}

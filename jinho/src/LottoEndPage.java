import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;

public class LottoEndPage extends JFrame {
	public LottoEndPage() {
		JPanel pnlLotto = new JPanel(new BorderLayout(0, 30));
		JPanel pnlTop = new JPanel(); // 당첨번호
		JLabel lblLotto = new JLabel("당첨번호는"); // 당첨번호
		JLabel lblLotto2 = new JLabel("입니다."); // 당첨번호
		
		JPanel pnlCenter = new JPanel(); // 당점결과
		JPanel pnlResultA = new JPanel(); 
		JPanel pnlResultB = new JPanel();  
		JPanel pnlResultC = new JPanel();
		JPanel pnlResultD = new JPanel();
		JPanel pnlResultE = new JPanel();
		
		JLabel lblResultA = new JLabel("A 미지정");
		JLabel lblResultB = new JLabel("B 미지정");
		JLabel lblResultC = new JLabel("C 미지정");
		JLabel lblResultD = new JLabel("D 미지정");
		JLabel lblResultE = new JLabel("E 미지정");
		
		JPanel pnlSouth = new JPanel(); // 당첨금
		JLabel lblWon = new JLabel("원");  
		
		TitledBorder tbNumber = new TitledBorder(new LineBorder(Color.black), "당첨번호");
		tbNumber.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbResult = new TitledBorder(new LineBorder(Color.black), "당첨결과");
		tbResult.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbMoney = new TitledBorder(new LineBorder(Color.black),"당첨금");
		tbMoney.setTitleColor(new Color(245, 136, 110));

		pnlTop.setBorder(tbNumber);
		pnlCenter.setBorder(tbResult);
		pnlSouth.setBorder(tbMoney);

		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlTop.setLayout(new BorderLayout(0, 0));
		pnlLotto.setLayout(new GridLayout(3, 0, 0, 10));

		// 위치잡아줌여
		pnlTop.add(lblLotto);
		pnlTop.add(lblLotto2, BorderLayout.EAST);
		
		
		pnlResultA.setLayout(new BorderLayout(0, 0));
		pnlResultA.add(lblResultA);
		pnlResultB.setLayout(new BorderLayout(0, 0));
		pnlResultB.add(lblResultB);
		pnlResultC.setLayout(new BorderLayout(0, 0));
		pnlResultC.add(lblResultC);
		pnlResultD.setLayout(new BorderLayout(0, 0));
		pnlResultD.add(lblResultD);
		pnlResultE.setLayout(new BorderLayout(0, 0));
		pnlResultE.add(lblResultE);
		
		pnlCenter.add(pnlResultA);
		pnlCenter.add(pnlResultB);
		pnlCenter.add(pnlResultC);
		pnlCenter.add(pnlResultD);
		pnlCenter.add(pnlResultE);
		pnlSouth.setLayout(new BorderLayout(0, 0));
		
		pnlSouth.add(lblWon, BorderLayout.EAST);
		pnlLotto.add(pnlTop);
				JLabel lblRound = new JLabel("회차"); // 회차
				pnlTop.add(lblRound, BorderLayout.NORTH);
		pnlLotto.add(pnlCenter);
		pnlLotto.add(pnlSouth);
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlSouth.add(lblNewLabel, BorderLayout.CENTER);
		
		
//		pnlLotto.setBackground(Color.white);

//		.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
//		.setBackgroud(Color.pink);
		// 위쪽 센터 배경색 끄기
		setContentPane(pnlLotto);

//		pack();
		setSize(400, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		addListeners();

		// 창의 크기를 조절할수 없게끔
		setResizable(false);
	}

	public static void main(String[] args) {
		new LottoEndPage().setVisible(true);
	}
}

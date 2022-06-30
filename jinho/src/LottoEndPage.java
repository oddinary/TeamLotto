import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LottoEndPage extends JDialog {
	
	public LottoEndPage(JFrame owner,int gameCount) {
		super(owner, true);
		
		JPanel pnlLotto = new JPanel();
		JPanel pnlTop = new JPanel(); // 당첨번호
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

//				new Login().setVisible(true);
			}
		});

		TitledBorder tbNumber = new TitledBorder(new LineBorder(Color.black), "당첨번호");
		tbNumber.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbResult = new TitledBorder(new LineBorder(Color.black), "당첨결과");
		tbResult.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbMoney = new TitledBorder(new LineBorder(Color.black), "당첨금");
		tbMoney.setTitleColor(new Color(245, 136, 110));
		SpringLayout sl_pnlLotto = new SpringLayout();
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlTop, 5, SpringLayout.NORTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlTop, 10, SpringLayout.WEST, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlTop, -347, SpringLayout.SOUTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlTop, 384, SpringLayout.WEST, pnlLotto);
		pnlLotto.setLayout(sl_pnlLotto);

		pnlTop.setBorder(tbNumber);
		pnlTop.setLayout(new BorderLayout(0, 0));
		JLabel lblRound = new JLabel(gameCount + " 회차"); // 회차
		pnlTop.add(lblRound, BorderLayout.NORTH);
		JLabel lblLotto = new JLabel("당첨번호는");
		pnlTop.add(lblLotto, BorderLayout.WEST);
		JLabel lblLotto2 = new JLabel("입니다.");
		pnlTop.add(lblLotto2, BorderLayout.EAST);
		pnlLotto.add(pnlTop);

		JPanel pnlCenter = new JPanel();
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlCenter, 6, SpringLayout.SOUTH, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlCenter, 0, SpringLayout.WEST, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlCenter, 0, SpringLayout.EAST, pnlTop);

		JPanel[] pnlResultBox = new JPanel[5];
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResultBox[i] = new JPanel();
		}

		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblResult.length; i++) {
			lblResult[i] = new JLabel((i + 1) + " 미지정");
		}

		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlCenter.add(pnlResultBox[i]);
			pnlResultBox[i].setLayout(new BorderLayout(0, 0));
			pnlResultBox[i].add(lblResult[i], BorderLayout.WEST);
		}

		pnlCenter.setBorder(tbResult);
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlLotto.add(pnlCenter);

		JPanel pnlSouth = new JPanel();
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlCenter, -6, SpringLayout.NORTH, pnlSouth);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlSouth, 0, SpringLayout.EAST, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlSouth, 10, SpringLayout.WEST, pnlLotto);
		JLabel lblWon = new JLabel("원");
		pnlSouth.setBorder(tbMoney);
		pnlSouth.setLayout(new BorderLayout(0, 0));

		pnlSouth.add(lblWon, BorderLayout.EAST);
		pnlLotto.add(pnlSouth);

		JLabel lblNewLabel = new JLabel("New label");
		pnlSouth.add(lblNewLabel, BorderLayout.WEST);

		JButton btnReplay = new JButton("다시하기");
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlSouth, -43, SpringLayout.NORTH, btnReplay);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlSouth, -6, SpringLayout.NORTH, btnReplay);
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, btnReplay);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, btnExit, 6, SpringLayout.EAST, btnReplay);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, btnReplay, 97, SpringLayout.WEST, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, btnReplay, -10, SpringLayout.SOUTH, pnlLotto);

		pnlLotto.add(btnReplay);
		pnlLotto.add(btnExit);

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
}


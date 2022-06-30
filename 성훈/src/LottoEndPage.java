import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LottoEndPage extends JFrame {
	public LottoEndPage() {
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
		TitledBorder tbMoney = new TitledBorder(new LineBorder(Color.black),"당첨금");
		tbMoney.setTitleColor(new Color(245, 136, 110));
		SpringLayout sl_pnlLotto = new SpringLayout();
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlTop, 5, SpringLayout.NORTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlTop, 10, SpringLayout.WEST, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlTop, -347, SpringLayout.SOUTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlTop, 384, SpringLayout.WEST, pnlLotto);
		pnlLotto.setLayout(sl_pnlLotto);

		pnlTop.setBorder(tbNumber);
		JLabel lblRound = new JLabel("회차"); // 회차
		pnlTop.add(lblRound, "4, 2, fill, top");
		JLabel lblLotto = new JLabel("당첨번호는"); // 당첨번호
		//		pnlLotto.setLayout(new GridLayout(3, 0, 0, 10));
		
				// 위치잡아줌여
				pnlTop.add(lblLotto, "2, 4, left, top");
		pnlLotto.add(pnlTop);
		
		JPanel pnlCenter = new JPanel();
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlCenter, 6, SpringLayout.SOUTH, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlCenter, 0, SpringLayout.WEST, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlCenter, 0, SpringLayout.EAST, pnlTop);
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
		pnlCenter.setBorder(tbResult);
		
				pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
				SpringLayout sl_pnlResultB = new SpringLayout();
				sl_pnlResultB.putConstraint(SpringLayout.NORTH, lblResultB, 0, SpringLayout.NORTH, pnlResultB);
				sl_pnlResultB.putConstraint(SpringLayout.WEST, lblResultB, 0, SpringLayout.WEST, pnlResultB);
				sl_pnlResultB.putConstraint(SpringLayout.SOUTH, lblResultB, 47, SpringLayout.NORTH, pnlResultB);
				sl_pnlResultB.putConstraint(SpringLayout.EAST, lblResultB, 364, SpringLayout.WEST, pnlResultB);
				pnlResultB.setLayout(sl_pnlResultB);
				pnlResultB.add(lblResultB);
				SpringLayout sl_pnlResultC = new SpringLayout();
				sl_pnlResultC.putConstraint(SpringLayout.NORTH, lblResultC, 0, SpringLayout.NORTH, pnlResultC);
				sl_pnlResultC.putConstraint(SpringLayout.WEST, lblResultC, 0, SpringLayout.WEST, pnlResultC);
				sl_pnlResultC.putConstraint(SpringLayout.SOUTH, lblResultC, 47, SpringLayout.NORTH, pnlResultC);
				sl_pnlResultC.putConstraint(SpringLayout.EAST, lblResultC, 364, SpringLayout.WEST, pnlResultC);
				pnlResultC.setLayout(sl_pnlResultC);
				pnlResultC.add(lblResultC);
				SpringLayout sl_pnlResultD = new SpringLayout();
				sl_pnlResultD.putConstraint(SpringLayout.NORTH, lblResultD, 0, SpringLayout.NORTH, pnlResultD);
				sl_pnlResultD.putConstraint(SpringLayout.WEST, lblResultD, 0, SpringLayout.WEST, pnlResultD);
				sl_pnlResultD.putConstraint(SpringLayout.SOUTH, lblResultD, 47, SpringLayout.NORTH, pnlResultD);
				sl_pnlResultD.putConstraint(SpringLayout.EAST, lblResultD, 364, SpringLayout.WEST, pnlResultD);
				pnlResultD.setLayout(sl_pnlResultD);
				pnlResultD.add(lblResultD);
				SpringLayout sl_pnlResultE = new SpringLayout();
				sl_pnlResultE.putConstraint(SpringLayout.NORTH, lblResultE, 0, SpringLayout.NORTH, pnlResultE);
				sl_pnlResultE.putConstraint(SpringLayout.WEST, lblResultE, 0, SpringLayout.WEST, pnlResultE);
				sl_pnlResultE.putConstraint(SpringLayout.SOUTH, lblResultE, 0, SpringLayout.SOUTH, pnlResultE);
				sl_pnlResultE.putConstraint(SpringLayout.EAST, lblResultE, 364, SpringLayout.WEST, pnlResultE);
				pnlResultE.setLayout(sl_pnlResultE);
				pnlResultE.add(lblResultE);
				
				pnlCenter.add(pnlResultA);
				GroupLayout gl_pnlResultA = new GroupLayout(pnlResultA);
				gl_pnlResultA.setHorizontalGroup(
					gl_pnlResultA.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResultA, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
				);
				gl_pnlResultA.setVerticalGroup(
					gl_pnlResultA.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResultA, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
				);
				pnlResultA.setLayout(gl_pnlResultA);
				pnlCenter.add(pnlResultB);
				pnlCenter.add(pnlResultC);
				pnlCenter.add(pnlResultD);
				pnlCenter.add(pnlResultE);
				pnlLotto.add(pnlCenter);
		
		JPanel pnlSouth = new JPanel();
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlCenter, -6, SpringLayout.NORTH, pnlSouth);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlSouth, 0, SpringLayout.EAST, pnlTop);
		JLabel lblLotto2 = new JLabel("입니다."); // 당첨번호
		pnlTop.add(lblLotto2, "17, 4, left, top");
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

	public static void main(String[] args) {
		new LottoEndPage().setVisible(true);
	}
}

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class LottoEndPage extends JFrame {
	public LottoEndPage() {
		JPanel pnlLotto = new JPanel();
		JPanel pnlTop = new JPanel(); // 당첨번호
		JButton btnExit = new JButton("프로그램 종료");
		
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
		pnlTop.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("60px"),
				ColumnSpec.decode("40px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("15px"),
				RowSpec.decode("15px"),}));
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
				SpringLayout sl_pnlResultA = new SpringLayout();
				sl_pnlResultA.putConstraint(SpringLayout.NORTH, lblResultA, 0, SpringLayout.NORTH, pnlResultA);
				sl_pnlResultA.putConstraint(SpringLayout.WEST, lblResultA, 0, SpringLayout.WEST, pnlResultA);
				sl_pnlResultA.putConstraint(SpringLayout.SOUTH, lblResultA, 47, SpringLayout.NORTH, pnlResultA);
				sl_pnlResultA.putConstraint(SpringLayout.EAST, lblResultA, 364, SpringLayout.WEST, pnlResultA);
				pnlResultA.setLayout(sl_pnlResultA);
				pnlResultA.add(lblResultA);
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
		
		JButton btnLogout = new JButton("로그아웃");
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlSouth, -43, SpringLayout.NORTH, btnLogout);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlSouth, -6, SpringLayout.NORTH, btnLogout);
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, btnLogout);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, btnExit, 6, SpringLayout.EAST, btnLogout);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, btnLogout, 97, SpringLayout.WEST, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, btnLogout, -10, SpringLayout.SOUTH, pnlLotto);
		
		pnlLotto.add(btnLogout);
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

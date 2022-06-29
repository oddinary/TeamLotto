package blacklotto;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Main extends JFrame {
	JCheckBox checkNum;
	private List<JCheckBox> list;

	public Main() {
		list= new ArrayList<>();
		JPanel pnlMain = new JPanel();
		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();
		JPanel pnlSelect = new JPanel(new GridLayout(0,5,0,-10));
		JPanel pnlAuto = new JPanel();
		JPanel pnlButton = new JPanel();
		JPanel pnlResult = new JPanel();
		JPanel pnlResultA = new JPanel();
		JPanel pnlResultB = new JPanel();
		JPanel pnlResultC = new JPanel();
		JPanel pnlResultD = new JPanel();
		JPanel pnlResultE = new JPanel();
		JRadioButton rdbManual = new JRadioButton("수동");
		JRadioButton rdbAuto = new JRadioButton("자동");
		JRadioButton rdbSemiAuto = new JRadioButton("반자동");
		ButtonGroup group = new ButtonGroup();
		
		group.add(rdbAuto);
		group.add(rdbManual);
		group.add(rdbSemiAuto);
		JButton btnReset = new JButton("초기화");
		JButton btnConfirm = new JButton("확인 ");
		JButton btnresult = new JButton("결과");
		
		JLabel lblResult = new JLabel("선택번호 확인");
		JLabel lblResultA = new JLabel("A 미지정");
		JLabel lblResultB = new JLabel("B 미지정");
		JLabel lblResultC = new JLabel("C 미지정");
		JLabel lblResultD = new JLabel("D 미지정");
		JLabel lblResultE = new JLabel("E 미지정");
		
		BoxLayout boxLeft = new BoxLayout(pnlLeft, BoxLayout.Y_AXIS);
		pnlLeft.setLayout(boxLeft);
		BoxLayout boxResult = new BoxLayout(pnlResult, BoxLayout.Y_AXIS);
		pnlResult.setLayout(boxResult);
		BoxLayout boxRight = new BoxLayout(pnlRight, BoxLayout.Y_AXIS);
		pnlRight.setLayout(boxRight);
		pnlLeft.add(pnlSelect);
		
		for (int i = 1; i <= 45; i++) {
			checkNum = new JCheckBox(String.valueOf(i));
			pnlSelect.add(checkNum);
			list.add(checkNum);
		}
		
		pnlSelect.add(pnlAuto);
		pnlAuto.add(rdbManual);
		pnlAuto.add(rdbAuto);
		pnlAuto.add(rdbSemiAuto);
		pnlButton.add(btnReset);
		pnlButton.add(btnConfirm);
		pnlLeft.add(pnlButton);
		pnlRight.add(lblResult);
		pnlRight.add(pnlResult);
		pnlRight.add(btnresult);
		pnlResult.add(pnlResultA);
		pnlResult.add(pnlResultB);
		pnlResult.add(pnlResultC);
		pnlResult.add(pnlResultD);
		pnlResult.add(pnlResultE);
		pnlResultA.add(lblResultA);
		pnlResultB.add(lblResultB);
		pnlResultC.add(lblResultC);
		pnlResultD.add(lblResultD);
		pnlResultE.add(lblResultE);
		JPanel pnlRecommend = new JPanel();
		pnlMain.add(pnlRecommend);
		
				JButton btnRecommend = new JButton("번호 추천");
				JButton btnRecent = new JButton("직전 5주");
				BoxLayout boxRecommend = new BoxLayout(pnlRecommend, BoxLayout.Y_AXIS);
				pnlRecommend.setLayout(boxRecommend);
				pnlRecommend.add(btnRecommend);
				pnlRecommend.add(btnRecent);
		
		pnlMain.add(pnlLeft);
		pnlMain.add(pnlRight);
		
		getContentPane().add(pnlMain);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}

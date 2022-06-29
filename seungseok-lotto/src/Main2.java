

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Main2 extends JFrame {
	int checkCount = 0;
	public Main2() {
		super("6조 롣또");
		JPanel pnlMain = new JPanel(new GridLayout(0, 2));
		JPanel pnlLeft = new JPanel();
		JPanel pnlBtn = new JPanel();
		BoxLayout box = new BoxLayout(pnlBtn, BoxLayout.Y_AXIS);
		pnlBtn.setLayout(box);
		JPanel pnlRight = new JPanel(new GridLayout(0, 2));
		JPanel pnlNum = new JPanel(new GridLayout(0, 5));
		JButton btn1 = new JButton("랜덤번호 추천버튼");
		JButton btn2 = new JButton("직전 회차 확인(5주)");
		
		pnlMain.add(pnlLeft);
		pnlMain.add(pnlRight);

		pnlLeft.add(pnlBtn);
		pnlBtn.add(btn1);
		pnlBtn.add(btn2);
		pnlLeft.add(pnlNum);
		// 로또 번호 체크박스 생성
		for (int i = 1; i <= 45; i++) {
			JCheckBox checkBox = new JCheckBox(String.valueOf(i));
			pnlNum.add(checkBox);
		}
		
		JRadioButton auto = new JRadioButton("자동");
		JRadioButton manual = new JRadioButton("수동");
		JRadioButton semiAuto = new JRadioButton("반자동");
		// 라디오버튼 하나만 선택 그룹만들기
		ButtonGroup group = new ButtonGroup();
		group.add(auto);
		group.add(manual);
		group.add(semiAuto);
		// 자동 라디오버튼 클릭시.
		auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == auto) {
					manual.setEnabled(false);
					semiAuto.setEnabled(false);
					// 체크박스 랜덤 6개 체크시키기 기능
					
				}
			}
		});
		auto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
				}
			}
		});
		manual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == manual) {
					auto.setEnabled(false);
					// 몇개 체크후 나머지 자동체크 기능추가필요
				}
			}
		});
		
		pnlNum.add(auto);
		pnlNum.add(manual);
		pnlNum.add(semiAuto);
		
		// 오른쪽 화면. pnlRight
		JPanel myPick = new JPanel();
		JPanel btns = new JPanel();
		JPanel RightBottom = new JPanel();
		// 결과창 버튼
		JButton result = new JButton("결과창");
		RightBottom.add(result);
		
		pnlRight.add(myPick);
		pnlRight.add(btns);
		pnlRight.add(RightBottom);
		
		BoxLayout box2 = new BoxLayout(myPick, BoxLayout.Y_AXIS);
		myPick.setLayout(box2);
		JLabel setA = new JLabel("A미지정");
		JLabel setB = new JLabel("B미지정");
		JLabel setC = new JLabel("C미지정");
		JLabel setD = new JLabel("D미지정");
		JLabel setE = new JLabel("E미지정");
		
		myPick.add(setA);
		myPick.add(setB);
		myPick.add(setC);
		myPick.add(setD);
		myPick.add(setE);
		
		// 수정, 삭제 버튼
		JButton edit = new JButton("수정");
		JButton delete = new JButton("삭제");
		
		btns.add(edit);
		btns.add(delete);

		
		getContentPane().add(pnlMain);
		pack();
//		setSize(1200, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Main2().setVisible(true);
	}
}

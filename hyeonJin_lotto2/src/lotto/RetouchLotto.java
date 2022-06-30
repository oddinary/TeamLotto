package lotto;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Box;
import java.awt.BorderLayout;

public class RetouchLotto extends JFrame {
	// 체크리스트를 모두 담는 리스트.(체크박스에서 6개 체크된 리스트가 여기에 담김)
	private List<List<JCheckBox>> ChBoxAll = new ArrayList<>();

	// 로또 번호 (6개 번호) 담을 리스트 작성
	List<Integer> checkedList = new ArrayList<>();

	List<JCheckBox> listOfChkBox = new ArrayList<>();

	// 체크박스 45개
//	JCheckBox checkBox = new JCheckBox();

	// 체크박스 체크 개수 카운트.
	int checkCount = 0;

	public RetouchLotto() {
		TitledBorder tbBtn = new TitledBorder(new LineBorder(Color.black), "추가 기능");
		tbBtn.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbSelect = new TitledBorder(new LineBorder(Color.black), "번호 선택");
		tbSelect.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbResult = new TitledBorder(new LineBorder(Color.black), "선택번호 확인");
		tbResult.setTitleColor(new Color(245, 136, 110));

		JPanel pnlMain = new JPanel(new GridLayout(0, 3));
		JPanel pnlLeft = new JPanel();
		JPanel pnlLeftBtn = new JPanel();
		JPanel pnlRight = new JPanel();

		JPanel pnlNum = new JPanel(new GridLayout(0, 5));
		JPanel pnlAuto = new JPanel();
		JPanel pnlButton = new JPanel();
		
		JPanel pnlResult = new JPanel();
		
		JPanel[] pnlResultBox = new JPanel[5];
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResultBox[i] = new JPanel();
		}
		//반복문으로 수정
//		JPanel pnlResultA = new JPanel();
//		JPanel pnlResultB = new JPanel();
//		JPanel pnlResultC = new JPanel();
//		JPanel pnlResultD = new JPanel();
//		JPanel pnlResultE = new JPanel();

		JRadioButton rdbManual = new JRadioButton("수동");
		JRadioButton rdbAuto = new JRadioButton("자동");
		JRadioButton rdbSemiAuto = new JRadioButton("반자동");

		ButtonGroup group = new ButtonGroup();
		group.add(rdbAuto);
		group.add(rdbManual);
		group.add(rdbSemiAuto);

		pnlLeftBtn.setBorder(tbBtn);
		pnlLeft.setBorder(tbSelect);
		pnlRight.setBorder(tbResult);

		JButton btnReset = new JButton("초기화");
		JButton btnConfirm = new JButton("확인 ");
		JButton btnResult = new JButton("결과");

		JLabel[] lblResult = new JLabel[5];
		for (int i = 0 ; i < lblResult.length; i++) {
			lblResult[i] = new JLabel("sdk" + "미지정");
		}

		BoxLayout boxLeft = new BoxLayout(pnlLeft, BoxLayout.Y_AXIS);
		pnlLeft.setLayout(boxLeft);
		BoxLayout boxResult = new BoxLayout(pnlResult, BoxLayout.Y_AXIS);
		pnlResult.setLayout(boxResult);
		BoxLayout boxRight = new BoxLayout(pnlRight, BoxLayout.Y_AXIS);
		pnlRight.setLayout(boxRight);

		for (int i = 1; i <= 45; i++) {
			JCheckBox checkBox = new JCheckBox(String.valueOf(i));

			pnlNum.add(checkBox);

			// 체크박스 액션 리스너
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkedList.size() > 6) {
						checkBox.setSelected(false);
						JOptionPane.showMessageDialog(null, "6개까지만 선택가능해요");
					} else if (checkedList.size() == 7) {
						checkedList.remove(6);
					}
				}
			});

			checkBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int state = e.getStateChange();
					int selectNum = Integer.parseInt(checkBox.getText());
					if (state == ItemEvent.SELECTED) {
						if (checkedList.size() <= 6) {
							checkedList.add(selectNum);
//							checkCount = checkedList.size();  왜 있나용? 삭제해도될듯
						}
						// } else if (state == ItemEvent.DESELECTED) { else로 해도될듯
					} else {
						for (int i = 0; i < checkedList.size(); i++) {
							if (selectNum == checkedList.get(i)) {
								checkedList.remove(i);
//								checkCount = checkedList.size();
							}
						}
					}
					Collections.sort(checkedList);
					System.out.println(checkBox.getText());
					System.out.println(checkedList);
				}
			});

			listOfChkBox.add(checkBox);
			ChBoxAll.add(listOfChkBox);
			checkBox.setEnabled(false);
		}

		pnlLeft.add(pnlNum);
		pnlLeft.add(pnlAuto);

		pnlAuto.add(rdbManual);
		pnlAuto.add(rdbAuto);
		pnlAuto.add(rdbSemiAuto);
		
		rdbSemiAuto.setEnabled(false);
//		// 로또 번호 (6개 번호) 담을 리스트 작성
//		List<Integer> checkedList = new ArrayList<>();
//		List<JCheckBox> listOfChkBox = new ArrayList<>();
//		JCheckBox checkBox = new JCheckBox();
		// 선택번호 체크박스 반복문으로 45개 생성.

		// 라디오 버튼 액션 리스너 (자동)
		rdbAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbAuto.equals(e.getSource())) {
//					rdbSemiAuto.setEnabled(false); // 반자동 버튼 off

					for (int j = 6; j < 6; j++) {
						int lottoNum = (int) (Math.random() * 45);
						JCheckBox chkBox = listOfChkBox.get(lottoNum);
						chkBox.setSelected(true);
					}
				}
			}
		});

		// 라디오 버튼 액션 리스너 (수동)
//		rdbManual.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (rdbManual.equals(e.getSource())) {
//					for (JCheckBox checkBox : listOfChkBox) {
//						checkBox.setEnabled(true);
//
//					}
//				}
//			}
//		});
		
		rdbManual.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setEnabled(true);
					}
					rdbSemiAuto.setEnabled(true);
				} else {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setEnabled(false);
					}
					rdbSemiAuto.setEnabled(false);
				}
			}
		});

		// 번호 선택패널 => 초기화, 확인 버튼을 가진 pnlButton 삽입
		pnlLeft.add(pnlButton);
		// 초기화, 확인 기능필요한 버튼 두개
		pnlButton.add(btnReset);
		pnlButton.add(btnConfirm);

// ***********************************************************************
// ************************** 선택번호 확인 ***********************************
		// 선택번호 확인패널; 결과 패널, 결과버튼 ( 버튼 기능 필요 )
		pnlRight.add(pnlResult);
		pnlRight.add(btnResult);

		// 결과버튼 액션리스너
		btnResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LottoEndPage().setVisible(true);
			}
		});

		// 선택번호 확인패널의 선택결과 확인 레이블.
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResult.add(pnlResultBox[i]);
			pnlResultBox[i].add(lblResult[i]);
		}
		
		//반복문으로 수정
//		pnlResult.add(pnlResultA);
//		pnlResult.add(pnlResultB);
//		pnlResult.add(pnlResultC);
//		pnlResult.add(pnlResultD);
//		pnlResult.add(pnlResultE);
//		pnlResultA.add(lblResult[0]);
//		pnlResultB.add(lblResult[1]);
//		pnlResultC.add(lblResult[2]);
//		pnlResultD.add(lblResult[3]);
//		pnlResultE.add(lblResult[4]);

// *************************************************************************
// **************************** 추가 기능 **************************************
		// 추가 기능 패널 - pnlLeft의 왼쪽에 추가기능 버튼 패널 추가함
		JPanel pnlRecommend = new JPanel();
		pnlLeftBtn.add(pnlRecommend);

		// 추가 기능 버튼 ( 번호 추천 )
		JButton btnRecommend = new JButton("번호 추천");
		// 버튼 크기설정
		btnRecommend.setPreferredSize(new Dimension(100, 100));
		// 추천번호 액션 리스너
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 추가 기능 버튼 ( 직전 5주 )
		JButton btnRecent = new JButton("직전 5주");
		// 버튼 크기설정
		btnRecent.setPreferredSize(new Dimension(100, 100));
		// 직전 5주 액션 리스너
		btnRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 버튼 정렬할 레이아웃.
		pnlRecommend.setLayout(new GridLayout(2, 0, 0, 55));
		pnlRecommend.add(btnRecommend);
		pnlRecommend.add(btnRecent);

// *********************************************************************
// ************************* 메인패널 추가 **********************************
		// 메인 패널에 주요 패널 3가지 집어넣기 / 3분할 되어있음
		pnlMain.add(pnlLeftBtn);
		pnlMain.add(pnlLeft);
		pnlMain.add(pnlRight);

		getContentPane().add(pnlMain);

		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new RetouchLotto().setVisible(true);
	}
}

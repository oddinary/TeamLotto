import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Box;
import java.awt.BorderLayout;

public class Lotto extends JFrame {
	// 체크리스트를 모두 담는 리스트.(체크박스에서 6개 체크된 리스트가 여기에 담김)
	private List<List<JCheckBox>> ChBoxAll = new ArrayList<>();

	// 로또 번호 (6개 번호) 담을 리스트 작성
	List<Integer> checkedList = new ArrayList<>();

	List<JCheckBox> listOfChkBox = new ArrayList<>();
	
	// 직전 5주 번호 담는 리스트 // 06/30
	List<List> savedLottoNum = new ArrayList<>();
	
	// 체크박스 45개
//	JCheckBox checkBox = new JCheckBox();

	// 체크박스 체크 개수 카운트.
//	int checkCount = 0;
	// 회차 카운트
	int gameCount = 0;

	public Lotto() {
		gameCount++;
		List<String> lottoOne = new ArrayList<>(Arrays.asList("1021회차 : 12, 15, 17, 24, 29, 45, + 16"));
		List<String> lottoTwo = new ArrayList<>(Arrays.asList("1020회차 : 12, 27, 29, 38, 41, 45, + 6"));
		List<String> lottoThree = new ArrayList<>(Arrays.asList("1019회차 : 1, 4, 13, 17, 34, 39, + 6"));
		List<String> lottoFour = new ArrayList<>(Arrays.asList("1018회차 : 3, 19, 21, 25, 37, 45, + 35"));
		List<String> lottoFive = new ArrayList<>(Arrays.asList("1017회차 : 12, 18, 22, 23, 30, 34, + 32"));
		savedLottoNum.add(lottoOne);
		savedLottoNum.add(lottoTwo);
		savedLottoNum.add(lottoThree);
		savedLottoNum.add(lottoFour);
		savedLottoNum.add(lottoFive);
		
		String lottoNumber = "직전 5회차 번호 + 보너스 번호\n" + lottoOne.toString() + "\n" + lottoTwo.toString() + "\n" + lottoThree.toString() + "\n" + lottoFour.toString() + "\n" + lottoFive.toString();
		TitledBorder tbBtn = new TitledBorder(new LineBorder(Color.black), "추가 기능");
		tbBtn.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbSelect = new TitledBorder(new LineBorder(Color.black), "번호 선택");
		tbSelect.setTitleColor(new Color(245, 136, 110));
		TitledBorder tbResult = new TitledBorder(new LineBorder(Color.black), "선택번호 확인");
		tbResult.setTitleColor(new Color(245, 136, 110));

		// 메인패널 - 그리드로 3분할; 추가기능 버튼 패널(왼쪽 - pnlLeftBtn) / 로또번호 선택 패널(중앙 - pnlLeft) /
		// 선택번호 확인 패널(오른쪽 - pnlRight)
		JPanel pnlMain = new JPanel(new GridLayout(0, 3));
		JPanel pnlLeft = new JPanel();
		JPanel pnlLeftBtn = new JPanel();
		JPanel pnlRight = new JPanel();
		// 체크박스 생성 패널
		JPanel pnlNum = new JPanel(new GridLayout(0, 5));
		JPanel pnlAuto = new JPanel();
		JPanel pnlButton = new JPanel();
		JPanel pnlResult = new JPanel();

		JPanel[] pnlResultBox = new JPanel[5];
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResultBox[i] = new JPanel();
		}

		// 선택번호 확인 패널
//		JPanel pnlResultA = new JPanel();
//		JPanel pnlResultB = new JPanel();
//		JPanel pnlResultC = new JPanel();
//		JPanel pnlResultD = new JPanel();
//		JPanel pnlResultE = new JPanel();

		// 뤠디오 버튼 .. 수동, 자동, 반자동 기능 구현필요
		JRadioButton rdbManual = new JRadioButton("수동");
		JRadioButton rdbAuto = new JRadioButton("자동");
		JRadioButton rdbSemiAuto = new JRadioButton("반자동");
		// 라디오 버튼 그룹
		ButtonGroup group = new ButtonGroup();
		group.add(rdbAuto);
		group.add(rdbManual);
		group.add(rdbSemiAuto);

		// 유형별 경계선 만들기; 추가기능, 번호선택, 선택번호확인 3가지
		pnlLeftBtn.setBorder(tbBtn);
		pnlLeft.setBorder(tbSelect);
		pnlRight.setBorder(tbResult);

		// 오른쪽 선택번호확인 패널의 기능 버튼
		JButton btnReset = new JButton("초기화");
		JButton btnConfirm = new JButton("확인 ");
		JButton btnResult = new JButton("결과");

		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblResult.length; i++) {
			lblResult[i] = new JLabel((i + 1) + " 미지정");
		}

		// 반복문으로 수정
		// 선택 번호 확인 패널 레이블
//		JLabel lblResultA = new JLabel("A 미지정");
//		JLabel lblResultB = new JLabel("B 미지정");
//		JLabel lblResultC = new JLabel("C 미지정");
//		JLabel lblResultD = new JLabel("D 미지정");
//		JLabel lblResultE = new JLabel("E 미지정");

		// 레이아웃; 박스레이아웃 ( 체크선택(왼쪽패널 안에 들어감)패널, 결과(오른쪽패널 안에 들어감)패널 )
		BoxLayout boxLeft = new BoxLayout(pnlLeft, BoxLayout.Y_AXIS);
		pnlLeft.setLayout(boxLeft);
		BoxLayout boxResult = new BoxLayout(pnlResult, BoxLayout.Y_AXIS);
		pnlResult.setLayout(boxResult);
		BoxLayout boxRight = new BoxLayout(pnlRight, BoxLayout.Y_AXIS);
		pnlRight.setLayout(boxRight);

//	*************************************************************************
//	************************ 번호 선택 (체크박스잇는 패널) ****************************

		// 구현해야 할 것 : 수동 자동 반자동 선택 전에 체크 눌렀을경우 경고 메세지 뜨게
		for (int i = 1; i <= 45; i++) {
			JCheckBox checkBox = new JCheckBox(String.valueOf(i));

			pnlNum.add(checkBox);

			// 체크박스 액션 리스너
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 6개 선택하면 금지시키기
					if (checkedList.size() > 6) {
						checkBox.setSelected(false);
						JOptionPane.showMessageDialog(null, "6개까지만 선택가능해요");
					} else if (checkedList.size() == 7) {
						checkedList.remove(6);
					}
					// 체크를 1 이상 누르거나 6개 다누르지 않았을 때 반자동 버튼을 사용 가능하게
					rdbSemiAuto.setEnabled((checkedList.size() >= 1 && checkedList.size() <= 5));
				}
			});

			// 해야할것.
			// 체크박스 아이템 리스너; 선택된 것에 따라 checkedList에 배열로 집어넣기. 오름차순 정렬완료

			// 정렬된 것들은 ChBoxAll 필드에 저장됨.
			checkBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int state = e.getStateChange();
					int selectNum = Integer.parseInt(checkBox.getText());
					if (state == ItemEvent.SELECTED) {
						if (checkedList.size() <= 6) {
							checkedList.add(selectNum);
//							checkCount = checkedList.size();

							// 체크된것 배열에 들어가는지 콘솔로 확인
							System.out.println(checkBox.getText());
							System.out.println(checkedList);
						}
					} else if (state == ItemEvent.DESELECTED) { 
						for (int i = 0; i < checkedList.size(); i++) {
							if (selectNum == checkedList.get(i)) {
								checkedList.remove(i);
//								checkCount = checkedList.size();
							}
						}
					}
					Collections.sort(checkedList);
				}
			});

		// 초기화 버튼 액션 리스너
//		btnReset.addActionListener(new ActionListener() {
//			int selectNum = Integer.parseInt(checkBox.getText());
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (int i = 0; i < checkedList.size(); i++) {
//					if (selectNum == checkedList.get(i)) {
//						checkedList.remove(i);
//					}
//				}
//				for (JCheckBox checkBox : listOfChkBox) {
//					checkBox.setSelected(false);
//				}
//				// 리스트에서 번호들 초기화 됐는지 확인
//				System.out.println(checkedList);
//			}
//		});
		
		ActionListener reset = new ActionListener() {
			int selectNum = Integer.parseInt(checkBox.getText());
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkedList.size(); i++) {
					if (selectNum == checkedList.get(i)) {
						checkedList.remove(i);
					}
				}
				for (JCheckBox checkBox : listOfChkBox) {
					checkBox.setSelected(false);
				}
			}
		};
		
		// 초기화 버튼 액션리스너 작동
		btnReset.addActionListener(reset);

		listOfChkBox.add(checkBox);
		ChBoxAll.add(listOfChkBox);
		checkBox.setEnabled(false);
	}
		// 번호선택 패널에 추가
		pnlLeft.add(pnlNum);
		pnlLeft.add(pnlAuto);

		// 라디오 버튼 => 번호선택패널 => 라디오버튼 전용패널(pnlAuto).
		pnlAuto.add(rdbManual);
		pnlAuto.add(rdbAuto);
		pnlAuto.add(rdbSemiAuto);

		// 반자동 버튼 시작시 비활성화
		rdbSemiAuto.setEnabled(false);

//		// 로또 번호 (6개 번호) 담을 리스트 작성
//		List<Integer> checkedList = new ArrayList<>();
//		List<JCheckBox> listOfChkBox = new ArrayList<>();
//		JCheckBox checkBox = new JCheckBox();
		// 선택번호 체크박스 반복문으로 45개 생성.

		// 라디오 버튼 액션 리스너 (수동)
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
						//체크박스들 끄게 함
						checkBox.setEnabled(false);
					}
					rdbSemiAuto.setEnabled(false);
				} 
				Collections.sort(checkedList);
			}
		});
//		rdbAuto.addActionListener(reset);

		// 한번 누르고 또 누를때 작동 안하게
		// 라디오 버튼 액션 리스너 (자동)
		rdbAuto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 리스트 리셋 됐는지 확인
				System.out.println(checkedList);
				if (e.getStateChange() == ItemEvent.SELECTED) {

					rdbSemiAuto.setEnabled(false); // 반자동 버튼 off
					while (e.getStateChange() == ItemEvent.SELECTED) {
						if (checkedList.size() < 6) {
							int autoNum = (int) (Math.random() * 45);
							JCheckBox chkBox = listOfChkBox.get(autoNum);
							chkBox.setSelected(true);
							//체크리스트 확인용
							System.out.println(checkedList);
						} else {
							break;
						}
					}
				}
			}
		});
		// 라디오 버튼 액션 리스너 (반자동)
		rdbSemiAuto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				while (e.getStateChange() == ItemEvent.SELECTED) {
					if (checkedList.size() < 6) {
						int autoNum = (int) (Math.random() * 45);
						JCheckBox chkBox = listOfChkBox.get(autoNum);
						chkBox.setSelected(true);
						//체크리스트 확인용
						System.out.println(checkedList);
					} else {
						break;
					}
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
		// 구매금액.
		JPanel pnlLast = new JPanel();
		pnlLast.setLayout(new BorderLayout(0, 0));
		JLabel lblPayMoney = new JLabel("구매금액");
		pnlLast.add(lblPayMoney, BorderLayout.WEST);
		JLabel lblWon = new JLabel("원");

		// 선택번호 확인패널; 결과 패널, 결과버튼 ( 버튼 기능 필요 )
		pnlLast.add(lblWon, BorderLayout.EAST);
		pnlLast.add(btnResult, BorderLayout.SOUTH);
		pnlRight.add(pnlResult);
		pnlRight.add(pnlLast);
		// 결과버튼 액션리스너
				btnResult.addActionListener(new ActionListener() {
					private LottoEndPage dialog;

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog = new LottoEndPage(Lotto.this, gameCount);
						dialog.setVisible(true);
					}
				});
				
		// 선택번호 확인패널의 선택결과 확인 레이블.
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResult.add(pnlResultBox[i]);
			pnlResultBox[i].setLayout(new BorderLayout(0, 0));
			pnlResultBox[i].add(lblResult[i],BorderLayout.WEST);
		}

		// 선택번호 확인패널의 선택결과 확인 레이블.
//		pnlResult.add(pnlResultA);
//		pnlResult.add(pnlResultB);
//		pnlResult.add(pnlResultC);
//		pnlResult.add(pnlResultD);
//		pnlResult.add(pnlResultE);
//		pnlResultA.add(lblResultA);
//		pnlResultB.add(lblResultB);
//		pnlResultC.add(lblResultC);
//		pnlResultD.add(lblResultD);
//		pnlResultE.add(lblResultE);

// *************************************************************************
// **************************** 추가 기능 **************************************
		// 추가 기능 패널 - pnlLeft의 왼쪽에 추가기능 버튼 패널 추가함
		JPanel pnlRecommend = new JPanel();
		pnlLeftBtn.add(pnlRecommend);
		JLabel lblRound = new JLabel(gameCount + " 회차");
		// 추가 기능 버튼 ( 나의 정보 )
		JButton btnMyInfo = new JButton("나의 정보");
		// 버튼 크기설정
		btnMyInfo.setPreferredSize(new Dimension(160, 60));
		// 추천번호 액션 리스너
		btnMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 추가 기능 버튼 ( 번호 추천 )
		JButton btnRecommend = new JButton("번호 추천");
		// 버튼 크기설정
		btnRecommend.setPreferredSize(new Dimension(160, 60));
		// 추천번호 액션 리스너
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 추가 기능 버튼 ( 직전 5주 )
		JButton btnRecent = new JButton("직전 5주");
		// 버튼 크기설정
		btnRecent.setPreferredSize(new Dimension(160, 60));
		// 직전 5주 액션 리스너
		btnRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Lotto.this, lottoNumber, "직전 5주차 당첨번호", JOptionPane.PLAIN_MESSAGE);
			}
		});
		// 보유금액 확인 구간
		JPanel pnlHasMoney = new JPanel();
		JLabel lblHasMoney = new JLabel("보유금액");
		JLabel lblWon2 = new JLabel("원");

		// 버튼 정렬할 레이아웃.
		pnlRecommend.setLayout(new GridLayout(5, 0, 0, 10));
		// 컴포넌트 추가
		pnlRecommend.add(btnMyInfo);
		pnlRecommend.add(btnRecommend);
		pnlRecommend.add(btnRecent);
		pnlRecommend.add(pnlHasMoney);
		pnlHasMoney.setLayout(new BorderLayout(0, 0));
		pnlHasMoney.add(lblHasMoney, BorderLayout.WEST);
		pnlHasMoney.add(lblWon2, BorderLayout.EAST);
// *********************************************************************
// ************************* 메인패널 추가 **********************************
		// 메인 패널에 주요 패널 3가지 집어넣기 / 3분할 되어있음
		pnlMain.add(pnlLeftBtn);
		pnlMain.add(pnlLeft);
		pnlMain.add(pnlRight);

		getContentPane().add(pnlMain);

		// x버튼 누를때, 로그아웃, 종료, 취소 물어보기.
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						String[] yesNo = { "로그아웃", "종료", "취소" };
						int result = JOptionPane.showOptionDialog(Lotto.this, "종료하시겠습니까?", "종료 및 로그아웃",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, yesNo, yesNo[0]);
						if (result == JOptionPane.YES_OPTION) {
							dispose();
							Lotto.this.dispose();
							new Login().setVisible(true);
							Lotto.this.dispose();
						} else if (result == JOptionPane.NO_OPTION) {
							System.exit(0);
						}
					}

				});

		pack();
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
//	public String showDialog() {
//		setVisible(true);
//
//		return tf.getText();
//	}
	public static void main(String[] args) {
		new Lotto().setVisible(true);

	}
}
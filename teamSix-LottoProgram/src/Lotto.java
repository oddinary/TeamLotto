import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private List<List<Integer>> chBoxAll = new ArrayList<List<Integer>>();
	// 로또 번호 (6개 번호) 담을 리스트 작성
	List<Integer> checkedList = new ArrayList<>();

	List<JCheckBox> listOfChkBox = new ArrayList<>();

	// 직전 5주 번호 담는 리스트 // 06/30
	List<List> savedLottoNum = new ArrayList<>();

	int bonusNumber;
	// 체크박스 45개
//	JCheckBox checkBox = new JCheckBox();

	// 체크박스 체크 개수 카운트.
//	int checkCount = 0;
	// 회차 카운트//////
	int gameCount = 0;
	// 로또타입필드
	String lottoType;

	public Lotto(Map<String, User> userInfo, String id) {
		gameCount++;

		User user = userInfo.get(id);

		for (int i = 0; i < 5; i++) {
			chBoxAll.add(new ArrayList<Integer>());
		}

		user.setLottoNumber(chBoxAll);

		// 직전 번호 뽑는 구간.
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

		String lottoNumber = "직전 5회차 번호 + 보너스 번호\n" + lottoOne.toString() + "\n" + lottoTwo.toString() + "\n"
				+ lottoThree.toString() + "\n" + lottoFour.toString() + "\n" + lottoFive.toString();
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

		// 선택번호 확인 패널
		JPanel[] pnlResultBox = new JPanel[5];
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResultBox[i] = new JPanel();
		}
		JPanel[] pnlResultbtn = new JPanel[5];
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResultbtn[i] = new JPanel();
		}

		// 선택번호 확인 패널 ; 위의 반복문으로 해결
//		JPanel pnlResultA = new JPanel();
//		JPanel pnlResultB = new JPanel();
//		JPanel pnlResultC = new JPanel();
//		JPanel pnlResultD = new JPanel();
//		JPanel pnlResultE = new JPanel();

		// 뤠디오 버튼 .. 수동, 자동, 반자동 기능 구현필요
		JRadioButton rdbManual = new JRadioButton("수동");
		JRadioButton rdbAuto = new JRadioButton("자동");
		JRadioButton rdbSemiAuto = new JRadioButton("반자동");
		JRadioButton rdbdummy = new JRadioButton();
		// 라디오 버튼 그룹
		ButtonGroup group = new ButtonGroup();
		group.add(rdbAuto);
		group.add(rdbManual);
		group.add(rdbSemiAuto);
		group.add(rdbdummy);

		// 유형별 경계선 만들기; 추가기능, 번호선택, 선택번호확인 3가지
		pnlLeftBtn.setBorder(tbBtn);
		pnlLeft.setBorder(tbSelect);
		pnlRight.setBorder(tbResult);

		// 오른쪽 선택번호확인 패널의 기능 버튼
		JButton btnReset = new JButton("초기화");
		JButton btnConfirm = new JButton("확인 ");
		JButton btnResult = new JButton("결과");

		lottoType = "미지정";

		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblResult.length; i++) {
			lblResult[i] = new JLabel((i + 1) + ". " + lottoType);
		}

		// 입력한 로또가 오른쪽에 뜨기 위한 라벨
		JLabel[] lblResultNum = new JLabel[5];
		for (int i = 0; i < lblResult.length; i++) {
			lblResultNum[i] = new JLabel();
		}
		// 입력한 로또 각각에 수정, 삭제 , 번호 추가 버튼을 만들기 위한 패널
		JPanel[] pnlResultBtn = new JPanel[5];
		for (int i = 0; i < lblResult.length; i++) {
			pnlResultBtn[i] = new JPanel();
		}
		// 입력한 로또 각각에 수정, 삭제 , 번호 추가 버튼
		JButton[] btnResultInst = new JButton[5];
		for (int i = 0; i < lblResult.length; i++) {
			btnResultInst[i] = new JButton("수정");
		}
		JButton[] btnResultDel = new JButton[5];
		for (int i = 0; i < lblResult.length; i++) {
			btnResultDel[i] = new JButton("삭제");
		}
		JButton[] btnResultCopy = new JButton[5];
		for (int i = 0; i < lblResult.length; i++) {
			btnResultCopy[i] = new JButton("번호 복사");
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
//							System.out.println(checkBox.getText());
//							System.out.println(checkedList);

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

		// 이름 참고
//		// 로또 번호 (6개 번호) 담을 리스트 작성 
//		List<Integer> checkedList = new ArrayList<>();
//		List<JCheckBox> listOfChkBox = new ArrayList<>();
//		JCheckBox checkBox = new JCheckBox();

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
						// 체크박스들 끄게 함
						checkBox.setEnabled(false);
					}
					rdbSemiAuto.setEnabled(false);
				}
				Collections.sort(checkedList);
				lottoType = "수동";
			}
		});

		// 라디오 버튼 액션 리스너 (자동)
		ActionListener auto = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
				rdbSemiAuto.setEnabled(false); // 반자동 버튼 off
				while (true) {
					if (checkedList.size() < 6) {
						int autoNum = (int) (Math.random() * 45);
						JCheckBox chkBox = listOfChkBox.get(autoNum);
						chkBox.setSelected(true);
					} else {
						break;
					}
				}
				lottoType = "자동";
			}
		};
		rdbAuto.addActionListener(auto);

		// 라디오 버튼 액션 리스너 (반자동)
		rdbSemiAuto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				while (e.getStateChange() == ItemEvent.SELECTED) {
					if (checkedList.size() < 6) {
						int autoNum = (int) (Math.random() * 45);
						JCheckBox chkBox = listOfChkBox.get(autoNum);
						chkBox.setSelected(true);
						// 체크리스트 확인용
//						System.out.println(checkedList);
					} else {
						break;
					}
				}
				lottoType = "반자동";
			}
		});

		// '자동'라디오 버튼을 마우스로 클릭하면
		// '초기화'버튼을 기능 하게 해줌
		rdbAuto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Toolkit.getDefaultToolkit().beep();
				btnReset.doClick(0);
				rdbAuto.addActionListener(auto);
			}
		});

		// 선택번호 확인 버튼.....
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbdummy.setSelected(true);
				if (checkedList.size() == 6) {
					int count = 0;
					if (count < 5) {
						for (int i = 0; i < chBoxAll.size(); i++) {
							if (chBoxAll.get(i).toString().equals("[]")) {
								chBoxAll.set(i, checkedList);
								break;
							} else {
								count++;
							}
						}
					}

					if (count < 5) {
						for (int i = 0; i < user.getLottoNumber().size(); i++) {
							if (!user.getLottoNumber().get(i).toString().equals("[]")) {
								lblResultNum[i].setText(user.getLottoNumber().get(i).toString());
							}
						}
					} else {
						count--;
						JOptionPane.showMessageDialog(Lotto.this, "한번에 5개까지만 구매가능합니다.");
					}
					checkedList = new ArrayList<Integer>();
					lblResult[count].setText((count + 1) + ". " + lottoType);

					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
					}
				} else {
					rdbManual.setSelected(true);
					JOptionPane.showMessageDialog(Lotto.this, "6개 다 체크해주세요.");
				}

				// 배열이 생기면 버튼들 활성화
				for (int i = 0; i < btnResultInst.length; i++) {
					if (user.getLottoNumber().get(i).size() > 2) {
						btnResultInst[i].setEnabled(true);
						btnResultDel[i].setEnabled(true);
						btnResultCopy[i].setEnabled(true);
					}
				}
				// 라디오 버튼이 그룹화되어서 사용 불가.
//				rdbAuto.set
//				rdbManual.setSelected(false);
//				rdbSemiAuto.setSelected(false);
			}
		});

		// 번호 선택패널 => 초기화, 확인 버튼을 가진 pnlButton 삽입
		pnlLeft.add(pnlButton);

		// 초기화, 확인 기능필요한 버튼 두개
		pnlButton.add(btnReset);
		pnlButton.add(btnConfirm);

// ***********************************************************************
// ************************** 선택번호 확인 ***********************************
		// 수정 , 삭제 , 복사 버튼 구현.

//		JButton[] btnResultInst = new JButton[5];
//		for (int i = 0; i < lblResult.length; i++) {
//			btnResultInst[i] = new JButton("수정");
//		}
//		JButton[] btnResultDel = new JButton[5];
//		for (int i = 0; i < lblResult.length; i++) {
//			btnResultDel[i] = new JButton("삭제");
//		}
//		JButton[] btnResultCopy = new JButton[5];
//		for (int i = 0; i < lblResult.length; i++) {
//			btnResultCopy[i] = new JButton("번호 복사");
//		}

		
		// 수정 버튼 , 삭제 버튼 , 번호복사 버튼 비활성화후 배열 들어오면 활성화
		for (int i = 0; i < btnResultInst.length; i++) {
			btnResultInst[i].setEnabled(false);
			btnResultDel[i].setEnabled(false);
			btnResultCopy[i].setEnabled(false);
		}

		

		// '수정'버튼

		for (int i = 0; i < btnResultInst.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			;

			btnResultInst[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
					}
					for (int j = 0; j < user.getLottoNumber().get(index).size(); j++) {
						List<Integer> list = user.getLottoNumber().get(index);
						JCheckBox chkBox = listOfChkBox.get(list.get(j) - 1);
						chkBox.setSelected(true);
					}
					user.getLottoNumber().set(index, new ArrayList<Integer>());
					lblResultNum[index].setText("");
					// 라디오 버튼 전체 해제(더미버튼작동)
					rdbManual.setSelected(true);
					lottoType = "미지정";
					
					//배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInst.length; i++) {
						if (user.getLottoNumber().get(i).size() < 2) {
							btnResultInst[i].setEnabled(false);
							btnResultDel[i].setEnabled(false);
							btnResultCopy[i].setEnabled(false);
						}
					}
				}
			});
		}

		// '삭제'버튼
		for (int i = 0; i < btnResultDel.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			btnResultDel[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					user.getLottoNumber().set(index, new ArrayList<Integer>());
					lblResultNum[index].setText("");
					lottoType = "미지정";
					
					//배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInst.length; i++) {
						if (user.getLottoNumber().get(i).size() < 2) {
							btnResultInst[i].setEnabled(false);
							btnResultDel[i].setEnabled(false);
							btnResultCopy[i].setEnabled(false);
						}
					}
				}
			});
		}

		// '전체복사'버튼
		for (int i = 0; i < btnResultCopy.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			btnResultCopy[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < user.getLottoNumber().get(index).size(); j++) {
						List<Integer> list = user.getLottoNumber().get(index);
						JCheckBox chkBox = listOfChkBox.get(list.get(j) - 1);
						chkBox.setSelected(true);
					}
					rdbManual.setSelected(true);
					lottoType = "미지정";
					
					for (int i = 0; i < btnResultInst.length; i++) {
						// i가 안먹혀서 새로만듬;;
						if (user.getLottoNumber().get(i).size() > 2) {
							btnResultInst[i].setEnabled(true);
							btnResultDel[i].setEnabled(true);
							btnResultCopy[i].setEnabled(true);
						}
					}
				}
			});
		}

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
				// 당첨번호 뽑는 구간.
				List<Integer> winNumber = new LinkedList<>();
				Random random = new Random();
				while (winNumber.size() < 6) {
					int r = (random.nextInt(45)) + 1;
					if (!winNumber.contains(r)) {
						winNumber.add(r);
					}
				}
				Collections.sort(winNumber);
				// 보너스 번호 뽑는 구간.
				int a = (random.nextInt(45)) + 1;
				if (!winNumber.contains(a)) {
					bonusNumber = a;
				}

				dialog = new LottoEndPage(Lotto.this, user, winNumber, bonusNumber, gameCount);
				dialog.setVisible(true);
			}
		});

		// 선택번호 확인패널의 선택결과 확인 레이블.
		for (int i = 0; i < pnlResultBox.length; i++) {
			pnlResult.add(pnlResultBox[i]);
			pnlResultBox[i].setLayout(new BorderLayout(0, 0));
			pnlResultBox[i].add(lblResult[i], BorderLayout.WEST);
			pnlResultBox[i].add(lblResultNum[i], BorderLayout.CENTER);
			pnlResultBtn[i].add(btnResultInst[i]);
			pnlResultBtn[i].add(btnResultDel[i]);
			pnlResultBtn[i].add(btnResultCopy[i]);
			pnlResultBox[i].add(pnlResultBtn[i], BorderLayout.SOUTH);
		}

		// 선택번호 확인패널의 선택결과 확인 레이블. ; 위의 반복문으로 생성함
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
		JLabel lblMyName = new JLabel(user.getName() + " 님의 로또 게임");
		// 추가 기능 버튼 ( 나의 정보 )
		JButton btnMyInfo = new JButton("나의 정보");
		// 버튼 크기설정
		btnMyInfo.setPreferredSize(new Dimension(160, 60));

		// 추가 기능 버튼 ( 번호 추천 )
		JButton btnRecommend = new JButton("번호 추천");
		// 버튼 크기설정
		btnRecommend.setPreferredSize(new Dimension(160, 60));
		// 추천번호 액션 리스너
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Integer> todayNumber = new LinkedList<>();
				Random random = new Random();
				while (todayNumber.size() < 6) {
					int r = (random.nextInt(45)) + 1;
					if (!todayNumber.contains(r)) {
						todayNumber.add(r);
					}
				}
				Collections.sort(todayNumber);
//				System.out.println(todayNumber);
				JOptionPane.showMessageDialog(Lotto.this, String.valueOf(todayNumber), "오늘의 추천번호",
						JOptionPane.QUESTION_MESSAGE);

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
		JLabel lblMoney = new JLabel(String.valueOf(user.getHaveMoney()));
		JLabel lblWon2 = new JLabel("원");

		// 버튼 정렬할 레이아웃.
		pnlRecommend.setLayout(new GridLayout(5, 0, 0, 10));

		// 컴포넌트 추가
		pnlRecommend.add(lblMyName);
		pnlRecommend.add(btnMyInfo);
		pnlRecommend.add(btnRecommend);
		pnlRecommend.add(btnRecent);
		pnlRecommend.add(pnlHasMoney);
		pnlHasMoney.setLayout(new BorderLayout(0, 0));
		pnlHasMoney.add(lblHasMoney, BorderLayout.WEST);
		pnlHasMoney.add(lblMoney, BorderLayout.CENTER);
		pnlHasMoney.add(lblWon2, BorderLayout.EAST);

		// 나의정보 액션 리스너
		btnMyInfo.addActionListener(new ActionListener() {
			private MyInfo dialog;

			public void actionPerformed(ActionEvent e) {

				dialog = new MyInfo(Lotto.this, user);
				dialog.setVisible(true);

				int money = user.getHaveMoney();
				lblMoney.setText(String.valueOf(money));

			}
		});
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

	public void blankPlus() {
		for (int i = 0; i < chBoxAll.size(); i++) {
			if (chBoxAll.get(i).toString().equals("[]")) {
				chBoxAll.set(i, checkedList);
				break;
			}
		}
	}
//	public String showDialog() {
//		setVisible(true);
//
//		return tf.getText();
//	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Lotto extends JFrame {
	// 체크리스트를 모두 담는 리스트.(체크박스에서 6개 체크된 리스트가 여기에 담김)
	private List<List<Integer>> chBoxAll = new ArrayList<List<Integer>>();
	// 로또 번호 (6개 번호) 담을 리스트 작성
	List<Integer> checkedList = new ArrayList<>();

	List<JCheckBox> listOfChkBox = new ArrayList<>();

	// 직전 5주 번호 담는 리스트 // 06/30
//	List<List> savedLottoNum = new ArrayList<>();
	static List<List<Integer>> lottoFive = new ArrayList<List<Integer>>();

	static int gameCount = 1022;
	int gameMoney = 0;
	int bonusNumber;
	// 체크박스 45개
//	JCheckBox checkBox = new JCheckBox();

	// 체크박스 체크 개수 카운트.
//	int checkCount = 0;
	// 회차 카운트//////
	
	static JLabel[] lblResult2 = new JLabel[5];

	// 로또타입필드
	String lottoType;

	// 당첨번호 필드

	public Lotto(Map<String, User> userInfo, String id) {

		User user = userInfo.get(id);
		setTitle("로또 게임");
		for (int i = 0; i < 5; i++) {
			chBoxAll.add(new ArrayList<Integer>());
		}
		user.setLottoNumber(chBoxAll);

		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(12, 15, 17, 24, 29, 45, 16));
		List<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(Arrays.asList(12, 27, 29, 38, 41, 45, 6));
		List<Integer> list3 = new ArrayList<Integer>();
		list3.addAll(Arrays.asList(1, 4, 13, 17, 34, 39, 6));
		List<Integer> list4 = new ArrayList<Integer>();
		list4.addAll(Arrays.asList(3, 19, 21, 25, 37, 45, 35));
		List<Integer> list5 = new ArrayList<Integer>();
		list5.addAll(Arrays.asList(12, 18, 22, 23, 30, 34, 32));

		lottoFive.add(list);
		lottoFive.add(list2);
		lottoFive.add(list3);
		lottoFive.add(list4);
		lottoFive.add(list5);
//		lottoFive.add("1021회차 : [12, 15, 17, 24, 29, 45] + 16");
//		lottoFive.add("1020회차 : [12, 27, 29, 38, 41, 45] + 6");
//		lottoFive.add("1019회차 : [1, 4, 13, 17, 34, 39] + 6");
//		lottoFive.add("1018회차 : [3, 19, 21, 25, 37, 45] + 35");
//		lottoFive.add("1017회차 : [12, 18, 22, 23, 30, 34] + 32");
//		List<String> lottoOne = new ArrayList<>(Arrays.asList("1021회차 : 12, 15, 17, 24, 29, 45, + 16"));
//		List<String> lottoTwo = new ArrayList<>(Arrays.asList("1020회차 : 12, 27, 29, 38, 41, 45, + 6"));
//		List<String> lottoThree = new ArrayList<>(Arrays.asList("1019회차 : 1, 4, 13, 17, 34, 39, + 6"));
//		List<String> lottoFour = new ArrayList<>(Arrays.asList("1018회차 : 3, 19, 21, 25, 37, 45, + 35"));
//		List<String> lottoFive = new ArrayList<>(Arrays.asList("1017회차 : 12, 18, 22, 23, 30, 34, + 32"));
//		savedLottoNum.add(lottoOne);
//		savedLottoNum.add(lottoTwo);
//		savedLottoNum.add(lottoThree);
//		savedLottoNum.add(lottoFour);
//		savedLottoNum.add(lottoFive);

		TitledBorder tbBtn = new TitledBorder(new LineBorder(Color.black), "메뉴");
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

		lottoType = "미지정";

		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblResult.length; i++) {
			lblResult[i] = new JLabel((i + 1) + ". " + lottoType);
			lblResult2[i] = new JLabel((i + 1) + ". " + lottoType);
		}

		// 입력한 로또가 오른쪽에 뜨기 위한 라벨
		JPanel[] lblResultNum = new JPanel[5];
		JLabel[][] iconlbl = new JLabel[5][6];
		for (int i = 0; i < iconlbl.length; i++) {
			lblResultNum[i] = new JPanel();
			for (int j = 0; j < iconlbl[i].length; j++) {
				iconlbl[i][j] = new JLabel();
				lblResultNum[i].add(iconlbl[i][j]);
			}
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
			URL url = Lotto.class.getClassLoader().getResource("images/un" + String.format("%02d", i) + ".png");
			URL url2 = Lotto.class.getClassLoader().getResource("images/" + String.format("%02d", i) + ".png");
			ImageIcon icon = new ImageIcon(url);
			ImageIcon setIcon = new ImageIcon(url2);
			JCheckBox checkBox = new JCheckBox(icon);
			checkBox.setSelectedIcon(setIcon);
			pnlNum.add(checkBox);

			int index = i;
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
					int selectNum = index;
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

			// '자동','반자동' 선택시 수정방지 아이템리스너
			checkBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// '자동'
					if (rdbAuto.isSelected()) {
						checkBox.setSelected(true);
						// '반자동'
					} else if (rdbSemiAuto.isSelected()) {
						checkBox.setSelected(true);
					}
				}
			});

			listOfChkBox.add(checkBox);
			checkBox.setEnabled(false);
		}

		// 'reset' 버튼 액션 리스너
		ActionListener reset = new ActionListener() {
			int selectNum;

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkedList.size(); i++) {
					if (selectNum == checkedList.get(i)) {
						checkedList.remove(i);
					}
				}
				if (rdbAuto.isSelected() || rdbSemiAuto.isSelected()) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
						checkBox.setEnabled(false);
					}
				} else if (rdbManual.isSelected()) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
						checkBox.setEnabled(true);
					}
				}
			}
		};
		// 초기화버튼 '리셋'기능
		btnReset.addActionListener(reset);

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
					if (1 < listOfChkBox.size() && listOfChkBox.size() < 6 ) {
						rdbSemiAuto.setEnabled(true);
					}
				} else {
					for (JCheckBox checkBox : listOfChkBox) {
						// 체크박스들 끄게 함
						checkBox.setEnabled(false);
					}
//					rdbSemiAuto.setEnabled(false);
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
						// 랜덤으로 선택된 수만 활성화
					} else {
						break;
					}
				}
				for (int i = 0; i < listOfChkBox.size(); i++) {
					if (listOfChkBox.get(i).isSelected()) {
						listOfChkBox.get(i).setEnabled(true);
					} else {
						listOfChkBox.get(i).setEnabled(false);
					}
				}
				lottoType = "자동";
			}
		};

		// 자동 버튼 'auto','reset' 기능
		rdbAuto.addActionListener(auto);
		rdbAuto.addActionListener(reset);

		// 라디오 버튼 액션 리스너 (반자동)
		rdbSemiAuto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				while (e.getStateChange() == ItemEvent.SELECTED) {
					if (checkedList.size() < 6) {
						int autoNum = (int) (Math.random() * 45);
						JCheckBox chkBox = listOfChkBox.get(autoNum);
						chkBox.setSelected(true);
						// 고른 수만 활성화!
						// 체크리스트 확인용
//						System.out.println(checkedList);
					} else {
						break;
					}
				}
				for (int i = 0; i < listOfChkBox.size(); i++) {
					if (listOfChkBox.get(i).isSelected()) {
						listOfChkBox.get(i).setEnabled(true);
					}
				}
				lottoType = "반자동";
			}
		});

		// '자동'라디오 버튼을 마우스로 클릭하면
		// '초기화'버튼을 기능 하게 해줌
//		rdbAuto.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				Toolkit.getDefaultToolkit().beep();
//				btnReset.doClick(0);
//				rdbAuto.addActionListener(auto);
//			}
//		});

		// 구매금액.
		JPanel pnlLast = new JPanel();
		pnlLast.setLayout(new BorderLayout(0, 0));

		// 보유금액 확인 구간
		JPanel pnlHasMoney = new JPanel();
		JLabel lblHasMoney = new JLabel("보유금액");
		JLabel lblMoney = new JLabel(String.valueOf(user.getHaveMoney()));
		lblMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoney.setHorizontalTextPosition(SwingConstants.LEFT);
		JLabel lblWon2 = new JLabel("원");

		JLabel lblgameMoney = new JLabel(String.valueOf(gameMoney));

		// JSpinner 사용; 로또 개수 1 ~ 5개 한번에 같은번호 만들 수 있게 도와줄 스피너
		// 텍스트 입력 불가처리 화살표만 사용 가능
//		JSpinner spinner = new JSpinner();
//		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
//		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
//		editor.getTextField().setEnabled(true);
//		editor.getTextField().setEditable(false);

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
								for (int j = 0; j < user.getLottoNumber().get(i).size(); j++) {
									int num = user.getLottoNumber().get(i).get(j);
									URL url = Lotto.class.getClassLoader()
											.getResource("images/middle" + String.format("%02d", num) + ".png");
									ImageIcon icon = new ImageIcon(url);
									iconlbl[i][j].setIcon(icon);
								}
							}
						}
						gameMoney += 1000;
					} else {
						count--;
						JOptionPane.showMessageDialog(Lotto.this, "한번에 5개까지만 구매가능합니다.");
					}
					checkedList = new ArrayList<Integer>();
					lblResult[count].setText((count + 1) + ". " + lottoType);
					lblResult2[count].setText((count + 1) + ". " + lottoType);
					lblgameMoney.setText(String.valueOf(gameMoney));

					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
						checkBox.setEnabled(false);

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
					if (btnResultInst[i].isSelected()) {
						btnResultInst[i].setEnabled(true);
					}
				}
				// 라디오 버튼이 그룹화되어서 사용 불가.
//						rdbAuto.set
//						rdbManual.setSelected(false);
//						rdbSemiAuto.setSelected(false);
			}
		});

		// 번호 선택패널 => 초기화, 확인 버튼을 가진 pnlButton 삽입
		pnlLeft.add(pnlButton);
		// 개수 스피너;
//		pnlButton.add(spinner);
		// 초기화, 확인 기능필요한 버튼 두개
		pnlButton.add(btnReset);
		pnlButton.add(btnConfirm);
		// *************************************************************************
		// **************************** 추가 기능 **************************************
		// 추가 기능 패널 - pnlLeft의 왼쪽에 추가기능 버튼 패널 추가함
		JPanel pnlRecommend = new JPanel();
		pnlLeftBtn.add(pnlRecommend);
		JLabel countGame = new JLabel(gameCount + "회차");
		JLabel lblMyName = new JLabel(user.getName() + " 님의 로또 게임");
		// 추가 기능 버튼 ( 나의 정보 )
		JButton btnMyInfo = new JButton("나의 정보");
		// 버튼 크기설정
		btnMyInfo.setPreferredSize(new Dimension(160, 50));

		// 추가 기능 버튼 ( 번호 추천 )
		JButton btnRecommend = new JButton("번호 추천 (프리미엄)");
		// 버튼 크기설정
		btnRecommend.setPreferredSize(new Dimension(160, 50));

		if (user.isPremier()) {
			btnRecommend.setEnabled(true);
		} else {
			btnRecommend.setEnabled(false);
		}

		// 추가 기능 버튼 ( 직전 5주 )
		JButton btnRecent = new JButton("직전 5회차");
		// 버튼 크기설정
		btnRecent.setPreferredSize(new Dimension(160, 50));
		// 직전 5주 액션 리스너
		btnRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//						String lottoNumber = "직전 5회차 번호 + 보너스 번호\n" + lottoFive.get(0) + "\n" + lottoFive.get(1) + "\n"
//								+ lottoFive.get(2) + "\n" + lottoFive.get(3) + "\n" + lottoFive.get(4);
//						JOptionPane.showMessageDialog(Lotto.this, lottoNumber, "직전 5회차 당첨번호", JOptionPane.PLAIN_MESSAGE);
				FiveListDialog five = new FiveListDialog(Lotto.this, lottoFive, gameCount);
				five.setVisible(true);
			}
		});

		// 로또 도움말 버튼
		JButton btnHelp = new JButton("※로또 게임 방법");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"1 ~ 45까지 6개의 숫자를 고르면\r\n" + "\r\n" + "로또는 보너스 번호를 포함해 총 7개의 번호를 뽑습니다.\r\n" + "\r\n"
								+ "본번호 6개가 다 맞으면 1등, 본번호 5개 + 보너스번호 1개가 같으면 2등\r\n" + "\r\n"
								+ "본번호 5개가 맞으면 3등, 본번호 4개가 같으면 4등, 본번호 3개가 같으면 5등입니다.",
						"로또 게임 정보", JOptionPane.DEFAULT_OPTION);
			}
		});
		btnHelp.setPreferredSize(new Dimension(160, 50));

		// 버튼 정렬할 레이아웃.
		pnlRecommend.setLayout(new GridLayout(6, 0, 0, 10));

		// 컴포넌트 추가
		JPanel pnlLabel = new JPanel();
		pnlLabel.setLayout(new BorderLayout());
		pnlLabel.add(countGame, BorderLayout.NORTH); // <- 이거 추가 가능하게 부탁드려요 ㅠㅠ
		pnlLabel.add(lblMyName, BorderLayout.CENTER);
		pnlRecommend.add(pnlLabel);
		pnlRecommend.add(pnlHasMoney);
		pnlRecommend.add(btnMyInfo);
		pnlRecommend.add(btnRecommend);
		pnlRecommend.add(btnRecent);
		pnlRecommend.add(btnHelp);
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
				boolean premier = user.isPremier();

				if (premier) {
					btnRecommend.setEnabled(true);
				} else {
					btnRecommend.setEnabled(false);
				}
				lblMoney.setText(String.valueOf(money));

			}
		});

		// 추천번호 액션 리스너
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NonModal recommendNum = new NonModal(Lotto.this);
				recommendNum.setVisible(true);

			}
		});

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
						// 번호삭제
						iconlbl[index][j].setIcon(null);
					}

					// 배열초기화
					user.getLottoNumber().set(index, new ArrayList<Integer>());

					// 라디오 버튼 전체 해제(더미버튼작동)
					rdbManual.setSelected(true);

					// 배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInst.length; i++) {
						if (user.getLottoNumber().get(i).size() < 2) {
							btnResultInst[i].setEnabled(false);
							btnResultDel[i].setEnabled(false);
							btnResultCopy[i].setEnabled(false);
						}
						if (!btnResultInst[i].isSelected()) {
							btnResultInst[i].setEnabled(false);
						}
					}
					gameMoney -= 1000;
					lblgameMoney.setText(String.valueOf(gameMoney));
					lottoType = "미지정";
					lblResult[index].setText((index + 1) + ". " + lottoType);
					lblResult2[index].setText((index + 1) + ". " + lottoType);
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
					// 번호 삭제
					for (int j = 0; j < user.getLottoNumber().get(index).size(); j++) {
						iconlbl[index][j].setIcon(null);
					}

					// 배열초기화
					user.getLottoNumber().set(index, new ArrayList<Integer>());

					// 배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInst.length; i++) {
						if (user.getLottoNumber().get(i).size() < 2) {
							btnResultInst[i].setEnabled(false);
							btnResultDel[i].setEnabled(false);
							btnResultCopy[i].setEnabled(false);
						}
					}
					gameMoney -= 1000;
					lblgameMoney.setText(String.valueOf(gameMoney));
					lottoType = "미지정";
					lblResult[index].setText((index + 1) + ". " + lottoType);
					lblResult2[index].setText((index + 1) + ". " + lottoType);
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
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
					}
					for (int j = 0; j < user.getLottoNumber().get(index).size(); j++) {
						List<Integer> list = user.getLottoNumber().get(index);
						JCheckBox chkBox = listOfChkBox.get(list.get(j) - 1);
						chkBox.setSelected(true);
					}
					rdbManual.setSelected(true);

//					for (int i = 0; i < btnResultInst.length; i++) {
//						if (user.getLottoNumber().get(i).size() > 2) {
//							btnResultInst[i].setEnabled(true);
//							btnResultDel[i].setEnabled(true);
//							btnResultCopy[i].setEnabled(true);
//						}
//					}
				}
			});
		}
		pnlRight.add(pnlResult);
		pnlRight.add(pnlLast);

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

// *********************************************************************
// ************************* 메인패널 추가 **********************************
		// 메인 패널에 주요 패널 3가지 집어넣기 / 3분할 되어있음
		pnlMain.add(pnlLeftBtn);
		pnlMain.add(pnlLeft);
		pnlMain.add(pnlRight);

		JPanel panel_1 = new JPanel();
		pnlRight.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		JLabel lblPayMoney = new JLabel("구매금액");
		panel_1.add(lblPayMoney, BorderLayout.WEST);
		JLabel lblWon = new JLabel("원");
		panel_1.add(lblWon, BorderLayout.EAST);

		panel_1.add(lblgameMoney, BorderLayout.CENTER);
		lblgameMoney.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panel = new JPanel();
		pnlRight.add(panel);

		// 초기화
		JButton btnGameClear = new JButton("전체초기화");
		panel.add(btnGameClear);
		btnGameClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < btnResultDel.length; i++) {
					int index = i;
					// 번호 삭제
					for (int j = 0; j < user.getLottoNumber().get(index).size(); j++) {
						iconlbl[index][j].setIcon(null);
					}
					// 배열초기화
					user.getLottoNumber().set(index, new ArrayList<Integer>());
					// 배열이 사라지면 버튼들 비활성화
					for (int j = 0; j < btnResultInst.length; j++) {
						if (user.getLottoNumber().get(j).size() < 2) {
							btnResultInst[j].setEnabled(false);
							btnResultDel[j].setEnabled(false);
							btnResultCopy[j].setEnabled(false);
						}
					}
					gameMoney = 0;
					lblgameMoney.setText(String.valueOf(gameMoney));
					lottoType = "미지정";
					lblResult[index].setText((index + 1) + ". " + lottoType);
				}
			}
//				for (int j = 0; j < lblResultNum.length; j++) {
//					for (int i = 0; i < user.getLottoNumber().get(j).size(); i++) {
//						iconlbl[j][i].setIcon(null);
//					}
//					lblResult[j].setText((j + 1) + ". 미지정");
//					gameMoney = 0;
//					lblgameMoney.setText(String.valueOf(gameMoney));
//					btnResultInst[j].setEnabled(false);
//					btnResultDel[j].setEnabled(false);
//					btnResultCopy[j].setEnabled(false);
//				}
//				for (int i = 0; i < user.getLottoNumber().size(); i++) {
//					chBoxAll.remove(i);
//					user.getLottoNumber().remove(i);
//				}
//			}
		});

		// 결과 확인 버튼
		JButton btnResult = new JButton("구매 & 결과");
		panel.add(btnResult);

		// 결과버튼 액션리스너
		btnResult.addActionListener(new ActionListener() {
			private LottoEndPage dialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				int count = 0;

				for (int i = 0; i < user.getLottoNumber().size(); i++) {
					if (user.getLottoNumber().get(i).toString().equals("[]")) {
						count++;
					}
				}
				if (count >= 5) {
					JOptionPane.showMessageDialog(Lotto.this, "하나는 해야지?");
				} else {
					// 당첨번호 뽑는 구간.
					List<Integer> winNumber = new LinkedList<>();
					Random random = new Random();
					for (int i = 0; i < 6; i++) {
						while (true) {
							int r = (random.nextInt(45)) + 1;
							if (!winNumber.contains(r)) {
								winNumber.add(r);
								break;
							}
						}
					}
					// 이러면 6번째 수가 콘테인일수있음
					// while (winNumber.size() < 6) {
					// int r = (random.nextInt(45)) + 1;
					// if (!winNumber.contains(r)) {
					// winNumber.add(r);
					// }
					// }
					Collections.sort(winNumber);
					// 보너스 번호 뽑는 구간
					// while 이 없어서 a가 윈넘버에 들어있으면 오류남 (수정완료)
					while (true) {
						int a = (random.nextInt(45)) + 1;
						if (!winNumber.contains(a)) {
							bonusNumber = a;
							break;
						}
					}
					// if ((user.getHaveMoney() - gameMoney) < 0) {
					// JOptionPane.showMessageDialog(Lotto.this, "보유금액이 구매금액보다 적습니다.");
					// } else {
					user.setHaveMoney(user.getHaveMoney() - gameMoney);
					dialog = new LottoEndPage(Lotto.this, user, winNumber, bonusNumber, gameCount, userInfo);
					dialog.setVisible(true);
					winNumber.add(bonusNumber);
					lottoFive.add(0, winNumber);
					// lottoFive.add(0, gameCount + "회차 : " + winNumber + " + " + bonusNumber);
					gameCount++;
					// 라벨 새로고침
					countGame.setText(gameCount + "회차");
					user.setHaveMoney(user.getHaveMoney() + dialog.getWinMoney());
					lblMoney.setText(String.valueOf(user.getHaveMoney()));
					// }
				}
			}
		});

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
					new Login(userInfo).setVisible(true);
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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CardPanel extends JPanel {
	JPanel[] pnlResultBoxs;
	JLabel[] lblResults;
	JPanel[] pnlResultBtns;
	JButton[] btnResultInsts;
	JButton[] btnResultDels;
	JButton[] btnResultCopies;
	JPanel[] lblResultNums;
	private JLabel[][] iconlbls;

	public JPanel[] getPnlResultBoxs() {
		return pnlResultBoxs;
	}

	public JLabel[] getLblResults() {
		return lblResults;
	}

	public JPanel[] getPnlResultBtns() {
		return pnlResultBtns;
	}

	public JButton[] getBtnResultInsts() {
		return btnResultInsts;
	}

	public JButton[] getBtnResultDels() {
		return btnResultDels;
	}

	public JButton[] getBtnResultCopies() {
		return btnResultCopies;
	}

	public JPanel[] getLblResultNums() {
		return lblResultNums;
	}

	public void setPnlResultBoxs(JPanel[] pnlResultBoxs) {
		this.pnlResultBoxs = pnlResultBoxs;
	}

	public void setLblResults(JLabel[] lblResults) {
		this.lblResults = lblResults;
	}

	public void setPnlResultBtns(JPanel[] pnlResultBtns) {
		this.pnlResultBtns = pnlResultBtns;
	}

	public void setBtnResultInsts(JButton[] btnResultInsts) {
		this.btnResultInsts = btnResultInsts;
	}

	public void setBtnResultDels(JButton[] btnResultDels) {
		this.btnResultDels = btnResultDels;
	}

	public void setBtnResultCopies(JButton[] btnResultCopies) {
		this.btnResultCopies = btnResultCopies;
	}

	public void setLblResultNums(JPanel[] lblResultNums) {
		this.lblResultNums = lblResultNums;
	}

	CardPanel(String lottoType, List<JCheckBox> listOfChkBox, 
			List<List<Integer>> chBoxAll,JRadioButton rdbManual,
			JLabel lblNewLabel, JLabel lblWon) {
		
		
		BoxLayout boxResult = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxResult);

		// 선택번호 확인 패널
		pnlResultBoxs = new JPanel[5];
		for (int i = 0; i < pnlResultBoxs.length; i++) {
			pnlResultBoxs[i] = new JPanel();
		}

		lblResults = new JLabel[5];
		for (int i = 0; i < lblResults.length; i++) {
			/////////////
			lottoType = "미지정";
			lblResults[i] = new JLabel((i + 1) + ". " + lottoType);
		}
		
		

		// 입력한 로또 각각에 수정, 삭제 , 번호 추가 버튼을 만들기 위한 패널
		pnlResultBtns = new JPanel[5];
		for (int i = 0; i < lblResults.length; i++) {
			pnlResultBtns[i] = new JPanel();
		}
		// 입력한 로또 각각에 수정, 삭제 , 번호 추가 버튼
		btnResultInsts = new JButton[5];
		for (int i = 0; i < lblResults.length; i++) {
			btnResultInsts[i] = new JButton("수정");
		}
		btnResultDels = new JButton[5];
		for (int i = 0; i < lblResults.length; i++) {
			btnResultDels[i] = new JButton("삭제");
		}
		btnResultCopies = new JButton[5];
		for (int i = 0; i < lblResults.length; i++) {
			btnResultCopies[i] = new JButton("번호 복사");
		}

		// 입력한 로또가 오른쪽에 뜨기 위한 라벨
		lblResultNums = new JPanel[5];
		setIconlbls(new JLabel[5][6]);
		for (int i = 0; i < getIconlbls().length; i++) {
			lblResultNums[i] = new JPanel();
			for (int j = 0; j < getIconlbls()[i].length; j++) {
				getIconlbls()[i][j] = new JLabel();
				lblResultNums[i].add(getIconlbls()[i][j]);
			}
		}

		for (int i = 0; i < pnlResultBoxs.length; i++) {
			add(pnlResultBoxs[i]);
			pnlResultBoxs[i].setLayout(new BorderLayout(0, 0));
			pnlResultBoxs[i].add(lblResults[i], BorderLayout.WEST);
			pnlResultBoxs[i].add(lblResultNums[i], BorderLayout.CENTER);
			pnlResultBtns[i].add(btnResultInsts[i]);
			pnlResultBtns[i].add(btnResultDels[i]);
			pnlResultBtns[i].add(btnResultCopies[i]);
			pnlResultBoxs[i].add(pnlResultBtns[i], BorderLayout.SOUTH);
		}

		// 수정 버튼 , 삭제 버튼 , 번호복사 버튼 비활성화후 배열 들어오면 활성화
		for (int i = 0; i < btnResultInsts.length; i++) {
			btnResultInsts[i].setEnabled(false);
			btnResultDels[i].setEnabled(false);
			btnResultCopies[i].setEnabled(false);
		}

		// '수정'버튼
		
		for (int i = 0; i < btnResultInsts.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			btnResultInsts[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
					}
					for (int j = 0; j < chBoxAll.get(index).size(); j++) {
						List<Integer> list = chBoxAll.get(index);
						JCheckBox chkBox = listOfChkBox.get(list.get(j) - 1);
						chkBox.setSelected(true);
						// 번호삭제
						iconlbls[index][j].setIcon(null);
					}

					// 배열초기화
					chBoxAll.set(index, new ArrayList<Integer>());

					// 라디오 버튼 전체 해제(더미버튼작동)
					rdbManual.setSelected(true);

					// 배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInsts.length; i++) {
						if (chBoxAll.get(i).size() < 2) {
							btnResultInsts[i].setEnabled(false);
							btnResultDels[i].setEnabled(false);
							btnResultCopies[i].setEnabled(false);
						}
						if (!btnResultInsts[i].isSelected()) {
							btnResultInsts[i].setEnabled(false);
						}
					}
					Lotto.buyGameCount--;
					lblNewLabel.setText("구매 횟수 " + Lotto.buyGameCount);
					Lotto.gameMoney -= 1000;
					lblWon.setText(String.valueOf(Lotto.gameMoney) + "원");
					////////////////////////
					String lottoType = "미지급";
					lblResults[index].setText((index + 1) + ". " + lottoType);
				}
			});
		}

		// '삭제'버튼
		for (int i = 0; i < btnResultDels.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			btnResultDels[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 번호 삭제
					for (int j = 0; j < chBoxAll.get(index).size(); j++) {
						iconlbls[index][j].setIcon(null);
					}

					// 배열초기화
					chBoxAll.set(index, new ArrayList<Integer>());

					// 배열이 사라지면 버튼들 비활성화
					for (int i = 0; i < btnResultInsts.length; i++) {
						if (chBoxAll.get(i).size() < 2) {
							btnResultInsts[i].setEnabled(false);
							btnResultDels[i].setEnabled(false);
							btnResultCopies[i].setEnabled(false);
						}
					}
					Lotto.buyGameCount--;
					lblNewLabel.setText("구매 횟수 " + Lotto.buyGameCount);
					Lotto.gameMoney -= 1000;
					lblWon.setText(String.valueOf(Lotto.gameMoney) + "원");
					////////////
					String lottoType = "미지급";
					lblResults[index].setText((index + 1) + ". " + lottoType);
				}
			});
		}

		// '전체복사'버튼
		for (int i = 0; i < btnResultCopies.length; i++) {
			// i가 안먹혀서 새로만듬;;
			int index = i;

			btnResultCopies[index].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JCheckBox checkBox : listOfChkBox) {
						checkBox.setSelected(false);
					}
					for (int j = 0; j < chBoxAll.get(index).size(); j++) {
						List<Integer> list = chBoxAll.get(index);
						JCheckBox chkBox = listOfChkBox.get(list.get(j) - 1);
						chkBox.setSelected(true);
					}
					rdbManual.setSelected(true);
				}
			});
		}
	}

	public JLabel[][] getIconlbls() {
		return iconlbls;
	}

	public void setIconlbls(JLabel[][] iconlbls) {
		this.iconlbls = iconlbls;
	}
}

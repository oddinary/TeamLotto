import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.SwingConstants;

public class LottoEndPage extends JDialog {

	private int winMoney = 0;

	public int getWinMoney() {
		return winMoney;
	}

	public LottoEndPage(JFrame owner, User user, List<Integer> winNumber, int bonusNumber, int gameCount,
			Map<String, User> userInfo) {
		super(owner, true);
		setTitle("당첨 결과");
		JPanel pnlLotto = new JPanel();
		JPanel pnlTop = new JPanel(); // 당첨번호

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
		JLabel lblRound = new JLabel(gameCount + "회차"); // 회차
		pnlTop.add(lblRound, BorderLayout.NORTH);
		JLabel lblLotto = new JLabel("당첨번호는");
		pnlTop.add(lblLotto, BorderLayout.WEST);

		// 당첨번호 부분 이미지 삽입 완료
		JPanel lblWin = new JPanel();
		JLabel[] lblWinIcon = new JLabel[winNumber.size()];
		JLabel plus = new JLabel(new ImageIcon(Lotto.class.getClassLoader().getResource("images/Plus.png")));
		JLabel bonuseIcon = new JLabel(new ImageIcon(Lotto.class.getClassLoader()
				.getResource("images/middle" + String.format("%02d", bonusNumber) + ".png")));
		for (int i = 0; i < winNumber.size(); i++) {
			lblWinIcon[i] = new JLabel();
			lblWin.add(lblWinIcon[i]);
			int num = winNumber.get(i);
			URL url = Lotto.class.getClassLoader().getResource("images/middle" + String.format("%02d", num) + ".png");
			ImageIcon icon = new ImageIcon(url);
			lblWinIcon[i].setIcon(icon);
		}
		lblWin.setLayout(new GridLayout(0, 8, 0, 0));
		lblWin.add(plus);
		lblWin.add(bonuseIcon);

		// lblWin.setText(String.valueOf(winNumber) + " + " + bonusNumber);
		pnlTop.add(lblWin, BorderLayout.CENTER);
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
		// 574673e;
//		JLabel[] lblResult = new JLabel[5];
//		for (int i = 0; i < lblResult.length; i++) {
//			lblResult[i] = new JLabel((i + 1) + " 미지정");
//		}

		// 번호를 넣을 라벨을 만들기
//		JPanel[] lblResultNum = new JPanel[5];
//		for (int i = 0; i < iconlbl.length; i++) {
//			lblResultNum[i] = new JPanel();
//			for (int j = 0; j < iconlbl[i].length; j++) {
//				iconlbl[i][j] = new JLabel();
//				lblResultNum[i].add(iconlbl[i][j]);
//			}
//		}

		JPanel[] lblInputNum = new JPanel[user.getLottoNumber().size()];
		JLabel[][] iconlbl = new JLabel[user.getLottoNumber().size()][6];
		JLabel[] winCountLbl = new JLabel[user.getLottoNumber().size()];
		// 당첨 이미지 삽입완료
		for (int i = 0; i < user.getLottoNumber().size(); i++) {

			int winCount = 0;
			lblInputNum[i] = new JPanel();
			winCountLbl[i] = new JLabel();

			for (int j = 0; j < user.getLottoNumber().get(i).size(); j++) {
				iconlbl[i][j] = new JLabel();
				lblInputNum[i].add(iconlbl[i][j], BorderLayout.CENTER);
				int num = user.getLottoNumber().get(i).get(j);
				URL url = Lotto.class.getClassLoader()
						.getResource("images/middleun" + String.format("%02d", num) + ".png");
				URL url2 = Lotto.class.getClassLoader().getResource("images/" + String.format("%02d", num) + ".png");
				ImageIcon icon = new ImageIcon(url);
				ImageIcon icon2 = new ImageIcon(url2);

				for (int k = 0; k < 6; k++) {
					if (winNumber.get(k) == num) {
						iconlbl[i][j].setIcon(icon2);
						winCount++;
						break;
					} else {
						iconlbl[i][j].setIcon(icon);
					}
				}

				// 당첨금 초기화
				if (j == 5) {
					switch (winCount) {
					case 3:
						winCountLbl[i].setText("5등 당첨 !");
						winMoney += 5000;

						break;
					case 4:
						winCountLbl[i].setText("4등 당첨 !");
						winMoney += 50000;
						break;
					case 5:
						if (bonusNumber == user.getLottoNumber().get(i).get(j)) {
							winCountLbl[i].setText("2등 당첨 !");
							iconlbl[i][j].setIcon(icon2);
							winMoney += 100000000;
						} else {
							winCountLbl[i].setText("3등 당첨 !");
							winMoney += 1000000;
						}
						break;
					case 6:
						winCountLbl[i].setText("1등 당첨 !");
						winMoney += 1000000000;
						break;
					default:
						winCountLbl[i].setText("낙첨 ㅠㅠ");
						break;
					}
				}
//				winCountLbl[i].setText(String.valueOf(winCount) + "개 맞음");
			}
//			lblInputNum[i].setText(user.getLottoNumber().get(i).toString());
		}

		for (int i = 0; i < user.getLottoNumber().size(); i++) {
			pnlCenter.add(pnlResultBox[i]);
			pnlResultBox[i].setLayout(new BorderLayout(0, 0));
//			pnlResultBox[i].add(lblResult[i], BorderLayout.WEST);
			pnlResultBox[i].add(Lotto.lblResult2[i], BorderLayout.WEST);
			pnlResultBox[i].add(lblInputNum[i]);
			pnlResultBox[i].add(winCountLbl[i], BorderLayout.EAST);
		}

//		for (int i = 0; i < pnlResultBox.length; i++) {
//			pnlCenter.add(pnlResultBox[i]);
//			pnlResultBox[i].setLayout(new BorderLayout(0, 0));
//			pnlResultBox[i].add(lblResult[i], BorderLayout.WEST);
//		}

		pnlCenter.setBorder(tbResult);
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		pnlLotto.add(pnlCenter);

		JPanel pnlSouth = new JPanel();
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, pnlSouth, 395, SpringLayout.NORTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlSouth, -39, SpringLayout.SOUTH, pnlLotto);
		sl_pnlLotto.putConstraint(SpringLayout.SOUTH, pnlCenter, -6, SpringLayout.NORTH, pnlSouth);
		sl_pnlLotto.putConstraint(SpringLayout.EAST, pnlSouth, 0, SpringLayout.EAST, pnlTop);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, pnlSouth, 10, SpringLayout.WEST, pnlLotto);

		JLabel lblWon = new JLabel("원");
		pnlSouth.setBorder(tbMoney);
		pnlSouth.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(String.valueOf(winMoney));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(lblNewLabel, BorderLayout.CENTER);

		pnlSouth.add(lblWon, BorderLayout.EAST);
		pnlLotto.add(pnlSouth);

		JButton btnReplay = new JButton("다시하기");
		sl_pnlLotto.putConstraint(SpringLayout.NORTH, btnReplay, 6, SpringLayout.SOUTH, pnlSouth);
		sl_pnlLotto.putConstraint(SpringLayout.WEST, btnReplay, 148, SpringLayout.WEST, pnlLotto);
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pnlLotto.add(btnReplay);

		// x버튼 누를때, 로그아웃, 종료, 취소 물어보기.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}

		});

		setLocationRelativeTo(null);
		// 위쪽 센터 배경색 끄기
		setContentPane(pnlLotto);

//		pack();
		setSize(400, 500);
//		addListeners();

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// 창의 크기를 조절할수 없게끔
		setResizable(false);
		// 창이 중앙에 뜨게끔
		setLocationRelativeTo(null);
	}
}

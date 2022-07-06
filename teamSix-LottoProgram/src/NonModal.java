import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NonModal extends JDialog {
	NonModal(JFrame owner) {
		super(owner, false);
		setTitle("프리미엄 전용");

		// 누적 회차에 나온 수 중 많이 나온 순 리스트 저장 후, 가장 많이 나왔던 순서대로 오름차순 정렬후
		// 추천번호에 입력.
		////////////////////
		Set<Integer> recNum = new HashSet<>();
		List<Integer> recNumber = new ArrayList<>();
		List<Integer> countNumber = new ArrayList<>();
		for (int i = 0; i < Lotto.lottoFive.size(); i++) {
			for (int j = 0; j < Lotto.lottoFive.get(i).size(); j++) {
				recNum.add(Lotto.lottoFive.get(i).get(j));
			}
		}

		//////////////////////////////
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < Lotto.lottoFive.size(); i++) {
			for (int j = 0; j < Lotto.lottoFive.get(i).size(); j++) {
				int num = Lotto.lottoFive.get(i).get(j);
				if (!map.containsKey(num)) {
					map.put(num, 1);
				} else {
					map.put(num, map.get(num) + 1);
				}
			}
		}

		for (int i = 1; i <= 45; i++) {
			if (map.containsKey(i)) {
				if (map.get(i) >= 2) {

					recNumber.add(i);
					countNumber.add(map.get(i));

				}
			}
		}


		for (int i = 0; i < recNumber.size(); i++) {
			for (int j = i + 1; j < recNumber.size(); j++) {
				if (map.get(recNumber.get(i)) < map.get(recNumber.get(j))) {
					int copy = recNumber.get(i);

					recNumber.set(i, recNumber.get(j));
					recNumber.set(j, copy);
				}
			}
		}


		List<Integer> todayNumber = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			todayNumber.add(recNumber.get(i));
		}
		Collections.sort(todayNumber);

		TitledBorder tbNonModal = new TitledBorder(new LineBorder(Color.black), "추천 번호");
		tbNonModal.setTitleColor(new Color(245, 136, 110));

		JPanel pnl = new JPanel();
		JLabel lblToday = new JLabel("로또 " + Lotto.gameCount + "회차 기준 추천번호");
		JPanel numPnl = new JPanel();
		JLabel lblNum[] = new JLabel[todayNumber.size()];
		

		pnl.setBorder(tbNonModal);
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		pnl.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(lblToday, BorderLayout.NORTH);
		
		for (int i = 0; i < todayNumber.size(); i++) {
			lblNum[i] = new JLabel();
			URL url = NonModal.class.getClassLoader().getResource("images/middle" + String.format("%02d", todayNumber.get(i)) + ".png");
			ImageIcon icon = new ImageIcon(url);
			lblNum[i].setIcon(icon);
			numPnl.add(lblNum[i]);
		}
		
		panel.add(numPnl);

		JPanel panelbtn = new JPanel();
		pnl.add(panelbtn);

		JButton btnCopy = new JButton("추천 번호 복사");
		panelbtn.add(btnCopy);
		JButton pnlBtn = new JButton("확인");
		panelbtn.add(pnlBtn);

		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};

		pnlBtn.addActionListener(escListener);

		// 추천번호 복사 액션리스너 => 바로 체크박스에 체크 시키기.
		btnCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkBox : Lotto.listOfChkBox) {
					checkBox.setSelected(false);
				}
				for (int i = 0; i < todayNumber.size(); i++) {
					List<Integer> list = todayNumber;
					JCheckBox chkBox = Lotto.listOfChkBox.get(list.get(i) - 1);
					chkBox.setSelected(true);
				}
				Lotto.rdbManual.setSelected(true);
			}
		});

		this.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		getContentPane().add(pnl);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 5);
	}
}

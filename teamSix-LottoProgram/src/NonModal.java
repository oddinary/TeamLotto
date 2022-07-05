import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NonModal extends JDialog {
	NonModal(JFrame owner) {
		super(owner, false);
		setTitle("프리미엄 전용");

		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

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
//		System.out.println(recNum);

//		recNumber.addAll(recNum);
//		int[] recommendArray = new int[recNum.size()];
//		for (int i = 0; i < recNumber.size(); i++) {
//			int number = recNumber.get(i);
//			recommendArray[i] = number;
//		}
//		for (int i = 0; i < recommendArray.length; i++) {
//			Set<Integer> num = new HashSet<>();
//			num.add(recommendArray[i]);
//		}

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

//		System.out.println(map);

		for (int i = 0; i < recNumber.size(); i++) {
			for (int j = i + 1; j < recNumber.size(); j++) {
				if (map.get(recNumber.get(i)) < map.get(recNumber.get(j))) {
					int copy = recNumber.get(i);

					recNumber.set(i, recNumber.get(j));
					recNumber.set(j, copy);
				}
			}
		}

//		System.out.println(recNumber);

		List<Integer> todayNumber = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			todayNumber.add(recNumber.get(i));
		}
		Collections.sort(todayNumber);
//		System.out.println(todayNumber);

//		for (int i = 0; i <= 45; i++) {
//			if (!map.containsKey(i)) {
//				map.put(i, 1);
//			} else {
//				map.put(i, map.get(i) + 1);
//			}
//		}
//		
		////////////

//		Random random = new Random();
//		List<Integer> todayNumber = new LinkedList<>();
//		while (todayNumber.size() < 6) {
//			int r = (random.nextInt(45)) + 1;
//			if (!todayNumber.contains(r)) {
//				todayNumber.add(r);
//			}
//		}
//		Collections.sort(todayNumber);
////		System.out.println(todayNumber);

		TitledBorder tbNonModal = new TitledBorder(new LineBorder(Color.black), "추천 번호");
		tbNonModal.setTitleColor(new Color(245, 136, 110));

		JPanel pnl = new JPanel();
		JLabel lblToday = new JLabel("현재 시간: " + d.format(formatter));
		JLabel lblNum = new JLabel("" + todayNumber);
		lblNum.setFont(new Font("굴림", Font.BOLD, 20));
		JButton pnlBtn = new JButton("확인");

		pnl.setBorder(tbNonModal);
		pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel = new JPanel();
		pnl.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblToday);
		panel.add(lblNum);
		pnl.add(pnlBtn);

		pnlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getContentPane().add(pnl);
		pack();
//		setSize(230,120);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setLocation(500, 500);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 5);
	}
}

//List<Integer> todayNumber = new LinkedList<>();
//Random random = new Random();
//while (todayNumber.size() < 6) {
//	int r = (random.nextInt(45)) + 1;
//	if (!todayNumber.contains(r)) {
//		todayNumber.add(r);
//	}
//}
//Collections.sort(todayNumber);
////System.out.println(todayNumber);
//JOptionPane.showMessageDialog(Lotto.this, String.valueOf(todayNumber), "오늘의 추천번호",
//		JOptionPane.QUESTION_MESSAGE);

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class NonModal extends JDialog {
	NonModal(JFrame owner) {
		super(owner, false);
		setTitle("프리미엄 전용");

		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
		
		List<Integer> todayNumber = new LinkedList<>();
		Random random = new Random();
		while (todayNumber.size() < 6) {
			int r = (random.nextInt(45)) + 1;
			if (!todayNumber.contains(r)) {
				todayNumber.add(r);
			}
		}
		Collections.sort(todayNumber);
//		System.out.println(todayNumber);

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
		setLocationRelativeTo(null);

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

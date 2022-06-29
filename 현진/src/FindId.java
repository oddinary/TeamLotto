package lotto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FindId extends JPanel {

	JTextField nameTf;

	public FindId(Map<String, User> userInfo, JPanel center, CardLayout layout) {
		JPanel pnl = new JPanel();
		BoxLayout box = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(box);

		JLabel inputName = new JLabel("이름을 입력하세요.");
		nameTf = new JTextField(10);

		JPanel btnPnl = new JPanel();
		JButton cancelBtn = new JButton("취소");
		JButton findBtn = new JButton("ID 찾기");

		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "A");
				nameTf.setText("");
			}
		});

		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				String id = "";
				for (String key : userInfo.keySet()) {
					String value = userInfo.get(key).getName();
					if (value.equals(nameTf.getText())) {
						count++;
						id += key;
					}
				}
				if (count > 0) {
					JOptionPane.showMessageDialog(FindId.this, nameTf.getText() + "님 아이디는 : " + id);
					layout.show(center, "A");
				} else {
					JOptionPane.showMessageDialog(FindId.this, nameTf.getText() + " 이름으로 등록된 아이디가 없습니다.");
					nameTf.setText("");
				}
			}
		});

		btnPnl.add(cancelBtn);
		btnPnl.add(findBtn);

		pnl.add(inputName);
		pnl.add(nameTf);
		pnl.add(btnPnl);

		add(pnl);

		setSize(300, 300);
	}
}

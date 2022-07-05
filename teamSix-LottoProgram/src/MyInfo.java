import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class NumInput extends JDialog {
	private JTextField tf;

	public NumInput(JDialog owner) {
		super(owner, true);
		setTitle("입력 창");

		tf = new JTextField(5);
		add(tf, "North");

		JButton btn = new JButton("확인");
		add(btn);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(150, 150);
	}

	public int showDialog() {
		setVisible(true);
		int num = 0;
		try {
			num = Integer.valueOf(tf.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(NumInput.this, "정수로만 입력하시기 바랍니다.");
		}
		return num;
	}
}

public class MyInfo extends JDialog {
	MyInfo(JFrame owner, User user) {
		super(owner, true);

		TitledBorder tbMyInfo = new TitledBorder(new LineBorder(Color.black), "나의 정보");
		tbMyInfo.setTitleColor(new Color(245, 136, 110));

		JPanel pnl = new JPanel();
		JLabel lblName = new JLabel(user.getName() + " 님의 정보");
		JPanel pnlbtn = new JPanel();

		JPanel pnlMoney = new JPanel();

		pnl.setBorder(tbMyInfo);

		JPanel panel = new JPanel();
		pnl.add(panel);
		panel.setLayout(new GridLayout(6, 0, 0, 10));
		panel.add(lblName);
		JLabel lblPhone = new JLabel("전화번호 : " + user.getPhoneNum());
		JLabel lblPrime = new JLabel("일반회원");
		JLabel lblMoney = new JLabel("보유금 : " + user.getHaveMoney() + " 원");

		JButton btnPrime = new JButton("프리미엄 결제 (10,000)");
		if (user.isPremier() == true) {
			lblPrime.setText("프리미엄회원");
			btnPrime.setEnabled(false);
		}

		btnPrime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getHaveMoney() < 10000) {
					JOptionPane.showMessageDialog(MyInfo.this, "보유금이 부족합니다.");
				} else {
					if (!user.isPremier()) {
						user.setPremier(true);
						user.setHaveMoney(user.getHaveMoney() - 10000);
						lblMoney.setText("보유금 : " + user.getHaveMoney() + " 원");
						JOptionPane.showMessageDialog(MyInfo.this, "결제 완료되었습니다.");
						lblPrime.setText("프리미엄회원");
						btnPrime.setEnabled(false);
					} else {

					}
				}
			}
		});

		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pnlbtn.add(btnPrime);
		pnlbtn.add(btnExit);
		panel.add(lblPhone);
		panel.add(lblPrime);

		panel.add(lblMoney);
		JButton btnMoney = new JButton("입금");
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumInput dialog = new NumInput(MyInfo.this);
				int result = dialog.showDialog();

				if (result != 0) {
					user.setHaveMoney(user.getHaveMoney() + result);
					lblMoney.setText("보유금 : " + user.getHaveMoney() + " 원");
					JOptionPane.showMessageDialog(MyInfo.this, "입금 완료되었습니다.");
				}
			}
		});
		pnlMoney.add(btnMoney);

		// 환전 버튼
		JButton btnChange = new JButton("출금");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumInput dialog = new NumInput(MyInfo.this);
				int result = dialog.showDialog();

				if (user.getHaveMoney() < result) {
					JOptionPane.showMessageDialog(MyInfo.this, "보유금이 부족합니다.");
				} else if (result == 0) {

				} else {
					user.setHaveMoney(user.getHaveMoney() - result);
					lblMoney.setText("보유금 : " + user.getHaveMoney() + " 원");
					JOptionPane.showMessageDialog(MyInfo.this, "출금 완료되었습니다.");
				}
			}
		});
		pnlMoney.add(btnChange);
		panel.add(pnlMoney);
		panel.add(pnlbtn);

		getContentPane().add(pnl);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}
}

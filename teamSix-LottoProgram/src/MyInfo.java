
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

class NumInput extends JDialog {
	private JTextField tf;
	int num;

	public NumInput(JDialog owner) {
		super(owner, true);
		setTitle("입력 창");

		JPanel pnlInput = new JPanel();
		JPanel pnlbtn = new JPanel();
		JLabel lblInput = new JLabel("금액 입력");
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);

		pnlInput.setLayout(new GridLayout(3, 0, 0, 5));

		tf = new JTextField(10);

		JButton btn = new JButton("확인");

		pnlbtn.add(btn);
		pnlInput.add(lblInput);
		pnlInput.add(tf);
		pnlInput.add(pnlbtn);

		add(pnlInput);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean blank = false;
				for (int i = 0; i < tf.getText().length(); i++) {
					if (tf.getText().charAt(i) == ' ') {
						blank = true;
					}
				}

				if (blank || tf.getText().equals("")) {
					JOptionPane.showMessageDialog(NumInput.this, "공백은 입력하실 수 없습니다.");
				} else {
					try {
						if (Integer.valueOf(tf.getText()) > 0) {
							if (Integer.valueOf(tf.getText()) % 1000 == 0) {
								num = Integer.valueOf(tf.getText());
								dispose();
							} else {
								JOptionPane.showMessageDialog(NumInput.this, "천원단위로만 입금가능합니다.");
							}
						} else {
							JOptionPane.showMessageDialog(NumInput.this, "정확한 금액을 입력해주시기 바랍니다.");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(NumInput.this, "정수로만 입력해주시기 바랍니다.");
					}
				}
			}
		});
		
		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				dispose();
			}
		};

		this.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		pack();
//		setSize(150,150);
		setLocationRelativeTo(null);
	}

	public int showDialog() {
		setVisible(true);
		int result = num;
		
		return result;
	}
}

public class MyInfo extends JDialog {
	MyInfo(JFrame owner, User user) {
		super(owner, true);
		setTitle("나의 정보");

		TitledBorder tbMyInfo = new TitledBorder(new LineBorder(Color.black), "나의 정보");
		tbMyInfo.setTitleColor(new Color(245, 136, 110));

		JPanel pnl = new JPanel();
		JLabel lblName = new JLabel(user.getName() + " 님의 정보");
		JPanel pnlbtn = new JPanel();

		pnl.setBorder(tbMyInfo);

		JPanel panel = new JPanel();
		pnl.add(panel);
		panel.setLayout(new GridLayout(6, 0, 0, 10));

		String phone = "";
		for (int i = 0; i < user.getPhoneNum().length(); i++) {
			phone += user.getPhoneNum().charAt(i);
			if (i == 2 || i == 6) {
				phone += '-';
			} 
		}
		JLabel lblPhone = new JLabel("전화번호 : " + phone);
		JLabel lblPrime = new JLabel("일반회원");
		JLabel lblMoney = new JLabel("보유금 : " + user.getHaveMoney() + " 원");

		JPanel pnlMoney = new JPanel();
		
		//입금 버튼
		JButton btnMoney = new JButton("입금");
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumInput dialog = new NumInput(MyInfo.this);
				
				int result = dialog.showDialog();

				if (result != 0) {
					user.setHaveMoney(user.getHaveMoney() + result);
					lblMoney.setText("보유금 : " + user.getHaveMoney() + " 원");
					Lotto.lblMoney.setText("" + user.getHaveMoney());
					JOptionPane.showMessageDialog(MyInfo.this, "입금 완료되었습니다.");
				}
			}
		});
		
		pnlMoney.add(btnMoney);

		// 출금 버튼
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
					Lotto.lblMoney.setText("" + user.getHaveMoney());
					JOptionPane.showMessageDialog(MyInfo.this, "출금 완료되었습니다.");
				}
			}
		});
		pnlMoney.add(btnChange);
		panel.add(pnlMoney);
		panel.add(lblName);

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
						Lotto.lblMoney.setText("" + user.getHaveMoney());
						lblMoney.setText("보유금 : " + user.getHaveMoney() + " 원");
						JOptionPane.showMessageDialog(MyInfo.this, "결제 완료되었습니다.");
						lblPrime.setText("프리미엄회원");
						btnPrime.setEnabled(false);
					} else {

					}
				}
			}
		});
		
		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};

		this.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

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
		panel.add(pnlbtn);

		getContentPane().add(pnl);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}
}

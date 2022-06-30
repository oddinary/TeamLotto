
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

public class SignUp extends JDialog {
	private Map<String, User> map = new HashMap<String, User>();
	private boolean idCheck;

	public Map<String, User> getMap() {
		return map;
	}

	public void setMap(Map<String, User> map) {
		this.map = map;
	}

	SignUp(JFrame owner) {
		super(owner, true);
		JPanel pnl = new JPanel();

		JPanel menu = new JPanel();
		BoxLayout box = new BoxLayout(menu, BoxLayout.Y_AXIS);
		menu.setLayout(box);
		idCheck = false;

		// enter키 누르면 가입버튼
				
				SpringLayout sl_pnl = new SpringLayout();
				sl_pnl.putConstraint(SpringLayout.NORTH, menu, 103, SpringLayout.NORTH, pnl);
				sl_pnl.putConstraint(SpringLayout.WEST, menu, 388, SpringLayout.WEST, pnl);
				pnl.setLayout(sl_pnl);

				JPanel panel_2 = new JPanel();
				sl_pnl.putConstraint(SpringLayout.NORTH, panel_2, 22, SpringLayout.SOUTH, menu);
				sl_pnl.putConstraint(SpringLayout.EAST, panel_2, -54, SpringLayout.EAST, pnl);
				pnl.add(panel_2);
				panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

				JPanel panel_3 = new JPanel();
				panel_2.add(panel_3);

				JPanel panel = new JPanel();
				panel_3.add(panel);
				panel.setLayout(new GridLayout(4, 0, 0, 20));
				JLabel idLbl = new JLabel("ID 입력 (4 ~ 8자)");
				panel.add(idLbl);
				JLabel nameLbl = new JLabel("이름 입력");
				panel.add(nameLbl);
				JLabel pwLbl = new JLabel("PW 입력 (4 ~ 8자)");
				panel.add(pwLbl);
				JLabel pwCheckLbl = new JLabel("PW 확인");
				panel.add(pwCheckLbl);

				JPanel panel_1 = new JPanel();
				panel_3.add(panel_1);
				panel_1.setLayout(new GridLayout(4, 0, 0, 12));
				JTextField tf = new JTextField(15);
				panel_1.add(tf);
				JTextField tf2 = new JTextField(15);
				panel_1.add(tf2);
				JPasswordField pf = new JPasswordField(15);
				panel_1.add(pf);
				JPasswordField checkPf = new JPasswordField(15);
				panel_1.add(checkPf);
				
				JPanel panel_4 = new JPanel();
				panel_3.add(panel_4);
				panel_4.setLayout(new GridLayout(4, 0, 0, 10));
				JButton idCheckBtn = new JButton("ID 체크!");
				panel_4.add(idCheckBtn);
				
				JLabel lblNewLabel = new JLabel("");
				panel_4.add(lblNewLabel);
				
						idCheckBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String id = tf.getText();
				
								if (id.length() < 4 || id.length() > 8) {
									JOptionPane.showMessageDialog(SignUp.this, "ID는 4 ~ 8자로 입력해주세요.");
									tf.setText("");
								} else {
									if (map.containsKey(id)) {
										JOptionPane.showMessageDialog(SignUp.this, "이미 등록된 아이디입니다.");
										tf.setText("");
									} else {
										JOptionPane.showMessageDialog(SignUp.this, "사용가능한 아이디입니다.");
										idCheck = true;
									}
								}
							}
						});
		
		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				tf2.setText("");
				pf.setText("");
				checkPf.setText("");
				dispose();
			}
		};

		// esc키 누르면 가입버튼 (단 다른 버튼이 escListener를 사용하면 곤란쓰)
		this.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener enterListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				String pw = String.valueOf(pf.getPassword());
				String checkPw = String.valueOf(checkPf.getPassword());

				if (pw.length() < 4 || pw.length() > 8) {
					JOptionPane.showMessageDialog(SignUp.this, "PW는 4 ~ 8자로 입력해주세요.");
					pf.setText("");
					checkPf.setText("");
				} else {
					if (idCheck == false) {
						JOptionPane.showMessageDialog(SignUp.this, "ID체크가 필요합니다.");
					} else {
						if (pw.equals(checkPw)) {
							User user = new User();
							map.put(id, user);
							user.setName(tf2.getText());
							user.setPw(String.valueOf(pf.getPassword()));
							JOptionPane.showMessageDialog(SignUp.this, "가입 축하드립니다.");
							tf.setText("");
							tf2.setText("");
							pf.setText("");
							checkPf.setText("");
							dispose();
						} else {
							JOptionPane.showMessageDialog(SignUp.this, "비밀번호가 일치하지않습니다.");
							pf.setText("");
							checkPf.setText("");
						}
					}
				}
			}
		};

		this.getRootPane().registerKeyboardAction(enterListener, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		tf.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				idCheck = false;
			}
		});

		JPanel btnPnl = new JPanel();
		panel_2.add(btnPnl);
		JButton cancelBtn = new JButton("취소");
		JButton signUpBtn = new JButton("가입");

		btnPnl.add(signUpBtn);
		btnPnl.add(cancelBtn);

		cancelBtn.addActionListener(escListener);

		signUpBtn.addActionListener(enterListener);

		pnl.add(menu);

		getContentPane().add(pnl);

		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

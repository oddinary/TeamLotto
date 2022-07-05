
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
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
	private JTextField tf;
	private JTextField tf2;
	private JPasswordField pf;
	private JPasswordField checkPf;
	private JTextField tf3;

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

		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				tf2.setText("");
				tf3.setText("");
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
				String name = tf2.getText();
				String phoneNum = tf3.getText();
				String pw = String.valueOf(pf.getPassword());
				String checkPw = String.valueOf(checkPf.getPassword());
				boolean nameBlank = false;
				boolean phoneBlank = false;
				int count = 0;

				for (String key : map.keySet()) {
					String value = map.get(key).getName();
					String value2 = map.get(key).getPhoneNum();

					if (value.equals(name)) {
						if (value2.equals(phoneNum)) {
							count++;
						}
					}
				}
				
				for (int i = 0; i < name.length(); i++) {
					if (name.charAt(i) == ' ') {
						nameBlank = true;
					}
				}

				for (int i = 0; i < phoneNum.length(); i++) {
					if (phoneNum.charAt(i) == ' ') {
						phoneBlank = true;
					}
				}

				if (nameBlank || name.length() < 2) {
					JOptionPane.showMessageDialog(SignUp.this, "이름은 2글자이상, 공백불가입니다.");
					tf2.setText("");
				} else {
					if (phoneNum.length() != 11 || phoneBlank) {
						JOptionPane.showMessageDialog(SignUp.this, "xxx-xxxx-xxxx (- 제외 입력, 공백불가)");
						tf3.setText("");
					} else {
						if (count > 0) {
							JOptionPane.showMessageDialog(SignUp.this, name + "님은 이미 ID가 있으십니다.");
						} else {
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
										user.setPhoneNum(tf3.getText());
										user.setPw(String.valueOf(pf.getPassword()));
										JOptionPane.showMessageDialog(SignUp.this, "가입 축하드립니다.");
										tf.setText("");
										tf2.setText("");
										tf3.setText("");
										pf.setText("");
										checkPf.setText("");
										idCheck = false;
										dispose();
									} else {
										JOptionPane.showMessageDialog(SignUp.this, "비밀번호가 일치하지않습니다.");
										pf.setText("");
										checkPf.setText("");
									}
								}
							}
						}
					}
				}
			}
		};

		// enter키 누르면 가입버튼
		this.getRootPane().registerKeyboardAction(enterListener, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		SpringLayout sl_pnl = new SpringLayout();
		sl_pnl.putConstraint(SpringLayout.NORTH, menu, 87, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, menu, 428, SpringLayout.WEST, pnl);
		pnl.setLayout(sl_pnl);

		JPanel panel_3 = new JPanel();
		sl_pnl.putConstraint(SpringLayout.NORTH, panel_3, 131, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, panel_3, 56, SpringLayout.WEST, pnl);
		pnl.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(5, 0, 0, 20));
		JLabel idLbl = new JLabel("ID 입력 (4 ~ 8자)");
		panel.add(idLbl);
		JLabel nameLbl = new JLabel("이름 입력 (2자 이상)");
		panel.add(nameLbl);
		JLabel phoneLbl = new JLabel("핸드폰번호 (- 제외 입력)");
		panel.add(phoneLbl);
		JLabel pwLbl = new JLabel("PW 입력 (4 ~ 8자)");
		panel.add(pwLbl);
		JLabel pwCheckLbl = new JLabel("PW 확인");
		panel.add(pwCheckLbl);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(5, 0, 0, 12));
		tf = new JTextField(15);
		panel_1.add(tf);
		tf2 = new JTextField(15);
		panel_1.add(tf2);
		tf3 = new JTextField(15);
		panel_1.add(tf3);
		pf = new JPasswordField(15);
		panel_1.add(pf);
		checkPf = new JPasswordField(15);
		panel_1.add(checkPf);

		JPanel idPnl = new JPanel();
		panel_2.add(idPnl);
		JButton idCheckBtn = new JButton("ID 체크!");
		idPnl.setLayout(new GridLayout(5, 0, 0, 10));
		idPnl.add(idCheckBtn);

		JPanel btnPnl = new JPanel();
		panel_3.add(btnPnl);
		JButton cancelBtn = new JButton("취소");
		JButton signUpBtn = new JButton("가입");

		btnPnl.add(signUpBtn);
		btnPnl.add(cancelBtn);

		cancelBtn.addActionListener(escListener);

		signUpBtn.addActionListener(enterListener);

		idCheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				boolean blank = false;
				for (int i = 0; i < id.length(); i++) {
					if (id.charAt(i) == ' ') {
						blank = true;
					}
				}

				if (blank) {
					JOptionPane.showMessageDialog(SignUp.this, "공백은 아이디에 추가하실수없습니다.");
				} else {
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
			}
		});

		tf.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				idCheck = false;
			}
		});

		pnl.add(menu);

		getContentPane().add(pnl);

		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

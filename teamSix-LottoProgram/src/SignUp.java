
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

public class SignUp extends JDialog {
	private Map<String, User> map = new HashMap<String, User>();
	private boolean idCheck;
	private JTextField tf;

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

		JPanel idPnl = new JPanel();
		JLabel idLbl = new JLabel("ID 입력 (4 ~ 8자)");
		tf = new JTextField(15);
		JButton idCheckBtn = new JButton("ID 체크!");
		idCheck = false;

		idPnl.add(idLbl);
		idPnl.add(tf);
		idPnl.add(idCheckBtn);

		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("이름 입력");
		JTextField tf2 = new JTextField(15);

		namePnl.add(nameLbl);
		namePnl.add(tf2);

		JPanel pwPnl = new JPanel();
		JLabel pwLbl = new JLabel("PW 입력 (4 ~ 8자)");
		JPasswordField pf = new JPasswordField(15);

		pwPnl.add(pwLbl);
		pwPnl.add(pf);

		JPanel pwCheckPnl = new JPanel();
		JLabel pwCheckLbl = new JLabel("PW 확인");
		JPasswordField checkPf = new JPasswordField(15);

		pwCheckPnl.add(pwCheckLbl);
		pwCheckPnl.add(checkPf);

		JPanel btnPnl = new JPanel();
		JButton cancelBtn = new JButton("취소");
		JButton signUpBtn = new JButton("가입");

		btnPnl.add(signUpBtn);
		btnPnl.add(cancelBtn);

		menu.add(idPnl);
		menu.add(namePnl);
		menu.add(pwPnl);
		menu.add(pwCheckPnl);
		menu.add(btnPnl);

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

		cancelBtn.addActionListener(escListener);

		// esc키 누르면 가입버튼 (단 다른 버튼이 escListener를 사용하면 곤란쓰)
		this.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener enterListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				String name = tf2.getText();
				String pw = String.valueOf(pf.getPassword());
				String checkPw = String.valueOf(checkPf.getPassword());
				boolean blank = false;

				for (int i = 0; i < name.length(); i++) {
					if (name.charAt(i) == ' ') {
						blank = true;
					}
				}

				if (blank || name.length() < 2) {
					JOptionPane.showMessageDialog(SignUp.this, "이름은 2글자이상, 공백불가입니다.");
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
			}
		};

		signUpBtn.addActionListener(enterListener);

		tf.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				idCheck = false;
			}
		});

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

		// enter키 누르면 가입버튼
		this.getRootPane().registerKeyboardAction(enterListener, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		pnl.add(menu);

		add(pnl);

		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

package lotto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		JTextField tf = new JTextField(15);

		idPnl.add(idLbl);
		idPnl.add(tf);

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
				String pw = String.valueOf(pf.getPassword());
				String checkPw = String.valueOf(checkPf.getPassword());
				if (id.length() < 4 || id.length() > 8) {
					JOptionPane.showMessageDialog(SignUp.this, "ID는 4 ~ 8자로 입력해주세요.");
					tf.setText("");
				} else {
					if (pw.length() < 4 || pw.length() > 8) {
						JOptionPane.showMessageDialog(SignUp.this, "PW는 4 ~ 8자로 입력해주세요.");
						pf.setText("");
						checkPf.setText("");
					} else {
						if (map.containsKey(id)) {
							JOptionPane.showMessageDialog(SignUp.this, "이미 등록된 아이디입니다.");
							tf.setText("");
							tf2.setText("");
							pf.setText("");
							checkPf.setText("");
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
		
		// enter키 누르면 가입버튼
		this.getRootPane().registerKeyboardAction(enterListener, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		pnl.add(menu);

		add(pnl);

		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

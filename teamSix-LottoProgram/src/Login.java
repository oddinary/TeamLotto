
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SpringLayout;

public class Login extends JFrame {
	URL url = Login.class.getClassLoader().getResource("images/lottoImage.png");
	ImageIcon i = new ImageIcon(url);
	Image im = i.getImage();

	SignUp signUp = new SignUp(Login.this);

	private Map<String, User> userInfo = signUp.getMap();

	public SignUp getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}

	// 생성자
	Login() {
		userInfo.put("admin", new User());
		userInfo.get("admin").setName("관리자");
		userInfo.get("admin").setPw("qwqw1234");
		userInfo.get("admin").setPhoneNum("11111111111");
		setTitle("로그인 화면");
		

		// 윗줄
		JPanel menuPnl = new JPanel();

		// 어느 포커스든 엔터누르면 로그인되게 하는 것! (JRootPane)
		JRootPane rootPane = this.getRootPane();
		SpringLayout sl_menuPnl = new SpringLayout();
		menuPnl.setLayout(sl_menuPnl);

		JPanel panel_2 = new JPanel();
		sl_menuPnl.putConstraint(SpringLayout.NORTH, panel_2, 126, SpringLayout.NORTH, menuPnl);
		sl_menuPnl.putConstraint(SpringLayout.WEST, panel_2, 152, SpringLayout.WEST, menuPnl);
		menuPnl.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_2.add(panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel idPnl = new JPanel();
		panel_1.add(idPnl);
		JLabel lbl1 = new JLabel("아이디    ");
		JTextField tf = new JTextField(10);

		idPnl.add(lbl1);
		idPnl.add(tf);

		JPanel pwPnl = new JPanel();
		panel_1.add(pwPnl);
		JLabel lbl2 = new JLabel("비밀번호");
		JPasswordField pf = new JPasswordField(10);
		
		tf.setText("admin");
		pf.setText("qwqw1234");
		pwPnl.add(lbl2);
		pwPnl.add(pf);

		JButton loginBtn = new JButton("로그인");
		loginBtn.setPreferredSize(new Dimension(75, 50));
		panel.add(loginBtn);
		rootPane.setDefaultButton(loginBtn);

		JPanel pnl3 = new JPanel();
		panel_2.add(pnl3);
		JButton signUpBtn = new JButton("회원가입");
		JButton findBtn = new JButton("ID/PW 찾기");

		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signUp.setVisible(true);
			}
		});

		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindIdPw dialog = new FindIdPw(Login.this, userInfo);
				dialog.setVisible(true);
			}
		});

		pnl3.add(signUpBtn);
		pnl3.add(findBtn);

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				String pw = String.valueOf(pf.getPassword());

				if (!userInfo.containsKey(id)) {
					JOptionPane.showMessageDialog(Login.this, "등록되지 않은 아이디입니다.");
					tf.setText("");
					pf.setText("");
				} else {
					if (!userInfo.get(id).getPw().equals(pw)) {
						JOptionPane.showMessageDialog(Login.this, "비밀번호가 틀렸습니다.");
						tf.setText("");
						pf.setText("");
					} else {
						JOptionPane.showMessageDialog(Login.this, "로그인 되었습니다.");
						dispose();
						new Lotto(userInfo, id).setVisible(true);
					}
				}
			}
		});

		getContentPane().add(menuPnl);
		
		JLabel lblImgLabel = new JLabel("");
		sl_menuPnl.putConstraint(SpringLayout.NORTH, lblImgLabel, -382, SpringLayout.SOUTH, menuPnl);
		sl_menuPnl.putConstraint(SpringLayout.SOUTH, lblImgLabel, 39, SpringLayout.SOUTH, menuPnl);
		lblImgLabel.setIcon(i);
		sl_menuPnl.putConstraint(SpringLayout.WEST, lblImgLabel, 0, SpringLayout.WEST, menuPnl);
		menuPnl.add(lblImgLabel);
//		pnl.add(bgLbl);

		setSize(602, 421);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	class MyPanel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// 메인
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}

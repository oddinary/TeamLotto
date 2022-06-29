package lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
		// JFrame 설정(이미지크기)
		setTitle("로그인 화면");
		// 전체 패널

		MyPanel pnl = new MyPanel();
		add(pnl);

		// 이미지 라벨
//		JLabel bgLbl = new JLabel(image);
//		pnl.setBounds(0, 0, 602, 421);

		// 로그인 패널
		JPanel allPnl = new JPanel();
		BoxLayout box1 = new BoxLayout(allPnl, BoxLayout.Y_AXIS);
		allPnl.setLayout(box1);
//		allPnl.setPreferredSize();

		// 윗줄
		JPanel menuPnl = new JPanel();

		// id,pw
		JPanel pnl2 = new JPanel();
		BoxLayout box2 = new BoxLayout(pnl2, BoxLayout.Y_AXIS);
		pnl2.setLayout(box2);

		JPanel idPnl = new JPanel();
		JLabel lbl1 = new JLabel("아이디    ");
		JTextField tf = new JTextField(10);

		JPanel pwPnl = new JPanel();
		JLabel lbl2 = new JLabel("비밀번호");
		JPasswordField pf = new JPasswordField(10);

		JButton loginBtn = new JButton("로그인");

		loginBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				String pw = String.valueOf(pf.getPassword());
				
				
				if (!userInfo.containsKey(id)) {
					JOptionPane.showMessageDialog(Login.this, "등록되지 않은 아이디입니다.");
				} else {
					if (!userInfo.get(id).getPw().equals(pw)) {
						JOptionPane.showMessageDialog(Login.this, "비밀번호가 틀렸습니다.");
					} else {
						JOptionPane.showMessageDialog(Login.this, "로그인 되었습니다.");
					}
				}
			}
		});
		
		idPnl.add(lbl1);
		idPnl.add(tf);

		pwPnl.add(lbl2);
		pwPnl.add(pf);

		pnl2.add(idPnl);
		pnl2.add(pwPnl);

		menuPnl.add(pnl2);
		menuPnl.add(loginBtn);

		JPanel pnl3 = new JPanel();
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
				String a = dialog.showDialog();
				System.out.println(a);
			}
		});
		
		pnl3.add(signUpBtn);
		pnl3.add(findBtn);
		


		allPnl.add(menuPnl);
		allPnl.add(pnl3);

		pnl.add(allPnl);

//		pnl.add(bgLbl);

		setSize(602, 421);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

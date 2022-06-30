
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FindPw extends JPanel {
	JTextField idTf;
	JTextField nameTf;
	private JButton findBtn;

	public JButton getFindBtn() {
		return findBtn;
	}

	public FindPw(Map<String, User> userInfo, JPanel center, CardLayout layout, JPanel parentsPnl) {
		JLabel inputId = new JLabel("ID를 입력하세요.");
		idTf = new JTextField(10);
		JLabel inputName = new JLabel("이름을 입력하세요.");
		nameTf = new JTextField(10);
		
		JPanel btnPnl = new JPanel();
		JButton cancelBtn = new JButton("취소");
		findBtn = new JButton("PW 찾기");

		KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					Toolkit.getDefaultToolkit().beep();
					findBtn.doClick();
				} else if (code == KeyEvent.VK_ESCAPE) {
					Toolkit.getDefaultToolkit().beep();
					cancelBtn.doClick();
				}
			}
		};
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		setSize(300, 300);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 79, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 47, SpringLayout.WEST, this);
		add(panel);
		JPanel pnl = new JPanel();
		panel.add(pnl);
		BoxLayout box = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(box);


		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "A");
				idTf.setText("");
				nameTf.setText("");
				parentsPnl.requestFocus();
			}
		});

		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTf.getText();
				String name = nameTf.getText();

				if (!userInfo.containsKey(id)) {
					JOptionPane.showMessageDialog(FindPw.this, "없는 아이디입니다.");
					idTf.setText("");
					nameTf.setText("");
				} else {
					if (!userInfo.get(id).getName().equals(name)) {
						JOptionPane.showMessageDialog(FindPw.this, id + "님의 성함이 일치하지 않습니다.");
						idTf.setText("");
						nameTf.setText("");
					} else {
						JOptionPane.showMessageDialog(FindPw.this, id + "님의 비밀번호는 : " + userInfo.get(id).getPw());
						layout.show(center, "A");
					}
				}
			}
		});

		idTf.addKeyListener(keyListener);
		nameTf.addKeyListener(keyListener);
		springLayout.putConstraint(SpringLayout.NORTH, pnl, 0, SpringLayout.NORTH, this);

		btnPnl.add(findBtn);
		btnPnl.add(cancelBtn);

		pnl.add(inputId);
		pnl.add(idTf);
		pnl.add(inputName);
		pnl.add(nameTf);
		pnl.add(btnPnl);
		springLayout.putConstraint(SpringLayout.WEST, pnl, 20, SpringLayout.EAST, panel);
	}
}

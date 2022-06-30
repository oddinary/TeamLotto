

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

public class FindId extends JPanel {

	JTextField nameTf;
	private JButton findBtn;

	public JButton getFindBtn() {
		return findBtn;
	}


	public FindId(Map<String, User> userInfo, JPanel center, CardLayout layout, JPanel parentsPnl) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 103, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, -54, SpringLayout.EAST, this);
		add(panel);
		JPanel pnl = new JPanel();
		panel.add(pnl);
		BoxLayout box = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(box);
		
				JLabel inputName = new JLabel("이름을 입력하세요.");
				nameTf = new JTextField(10);
				
						JPanel btnPnl = new JPanel();
						JButton cancelBtn = new JButton("취소");
						findBtn = new JButton("ID 찾기");
						
						cancelBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								layout.show(center, "A");
								nameTf.setText("");
								parentsPnl.requestFocus();
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
								
								
								nameTf.addKeyListener(new KeyAdapter() {
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
								});
		
		
		btnPnl.add(findBtn);
		btnPnl.add(cancelBtn);

		pnl.add(inputName);
		pnl.add(nameTf);
		pnl.add(btnPnl);

		setSize(300, 300);
	}
}

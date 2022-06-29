package lotto;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FindIdPw extends JDialog {
	private FindId idWindow;
	private FindPw pwWindow;
	private JPanel center;

	FindIdPw(JFrame owner,Map<String, User> userInfo) {
		super(owner, true);
		CardLayout layout = new CardLayout();
		center = new JPanel(layout);
		
		JPanel pnl = new JPanel();
		JButton findIdBtn = new JButton("ID 찾기");
		JButton findPwBtn = new JButton("PW 찾기");

		idWindow = new FindId(userInfo, center, layout);
		pwWindow = new FindPw(userInfo, center, layout);
		
		findIdBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "B");
			}
		});
		
		findPwBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "C");
			}
		});
		
		
		pnl.add(findIdBtn);
		pnl.add(findPwBtn);

		center.add(pnl, "A");
		center.add(idWindow, "B");
		center.add(pwWindow, "C");
		
		add(center);

		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public String showDialog() {
		setVisible(true);
		
		return idWindow.nameTf.getText();
	}
}

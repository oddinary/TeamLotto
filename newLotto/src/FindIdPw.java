

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

public class FindIdPw extends JDialog {
	private FindId idWindow;
	private FindPw pwWindow;
	private JPanel center;
	private int code;

	FindIdPw(JFrame owner,Map<String, User> userInfo) {
		super(owner, true);
		CardLayout layout = new CardLayout();
		center = new JPanel(layout);
		
		JPanel pnl = new JPanel();

		idWindow = new FindId(userInfo, center, layout, pnl);
		pwWindow = new FindPw(userInfo, center, layout, pnl);
		
		pnl.setFocusable(true);
		pnl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				code = e.getKeyCode();
				if (code == KeyEvent.VK_ESCAPE) {
					dispose();
				}
			};
		});
		
		KeyListener escListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				code = e.getKeyCode();
				if (code == KeyEvent.VK_ESCAPE) {
					layout.show(center, "A");
					pnl.requestFocus();
				}
			};
		};
		
		idWindow.addKeyListener(escListener);
		pwWindow.addKeyListener(escListener);
		SpringLayout sl_pnl = new SpringLayout();
		pnl.setLayout(sl_pnl);
		
		JPanel panel = new JPanel();
		sl_pnl.putConstraint(SpringLayout.NORTH, panel, 100, SpringLayout.NORTH, pnl);
		sl_pnl.putConstraint(SpringLayout.WEST, panel, 59, SpringLayout.WEST, pnl);
		pnl.add(panel);
		JButton findIdBtn = new JButton("ID 찾기");
		panel.add(findIdBtn);
		JButton findPwBtn = new JButton("PW 찾기");
		panel.add(findPwBtn);
		
		findPwBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "C");
				pwWindow.requestFocus();
			}
		});
		
		findIdBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(center, "B");
				idWindow.requestFocus();
			}
		});
		
		center.add(pnl, "A");
		center.add(idWindow, "B");
		center.add(pwWindow, "C");
		
		getContentPane().add(center);
		
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

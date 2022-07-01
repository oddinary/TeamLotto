import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyInfo extends JDialog {
	MyInfo(JFrame owner, User user) {
		super(owner, true);

		TitledBorder tbMyInfo = new TitledBorder(new LineBorder(Color.black), "나의 정보");
		tbMyInfo.setTitleColor(new Color(245, 136, 110));

		JPanel pnl = new JPanel();
		JLabel lblName = new JLabel(user.getName() + " 님의 정보");
		JPanel pnlbtn = new JPanel();

		JPanel pnlMoney = new JPanel();

		pnl.setBorder(tbMyInfo);

		JPanel panel = new JPanel();
		pnl.add(panel);
		panel.setLayout(new GridLayout(6, 0, 0, 10));
		panel.add(lblName);
		JLabel lblPhone = new JLabel("전화번호 : " + user.getPhoneNum());
		JLabel lblPrime = new JLabel("일반회원입니다.");
		if (user.isPremier() == true) {
			lblPrime.setText("프리미엄회원입니다.");
		}

		JButton btnPrime = new JButton("프리미엄 전환");
		btnPrime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.setPremier(true);
				lblPrime.setText("프리미엄회원입니다.");
			}
		});
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pnlbtn.add(btnPrime);
		pnlbtn.add(btnExit);
		panel.add(lblPhone);
		panel.add(lblPrime);
		JLabel lblMoney = new JLabel("보유금 : " + user.getHaveMoney() + " 원");
		panel.add(lblMoney);
		JButton btnMoney = new JButton("보유금 충전(5000)");
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = user.getHaveMoney();
				money += 5000;
				user.setHaveMoney(money);
				lblMoney.setText("보유금 : " + money + " 원");
			}
		});
		pnlMoney.add(btnMoney);
		panel.add(pnlMoney);
		panel.add(pnlbtn);

		getContentPane().add(pnl);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}

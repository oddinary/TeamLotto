
import java.net.URL;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FiveListDialog extends JDialog{
	FiveListDialog(JFrame owner, List<List<Integer>> lottoFive, int gameCount) {
		super(owner, true);
		
		JPanel pnl = new JPanel();
		
		JPanel topPnl = new JPanel();
		JLabel topLbl = new JLabel("직전 5회차 번호 + 보너스 번호");
		
		topPnl.add(topLbl);
		
		JPanel[] round = new JPanel[5];
		JLabel[] roundLbl = new JLabel[5];
		JLabel[][] roundNumLbl = new JLabel[5][6];
		JLabel[] plusLbl = new JLabel[5];
		JLabel[] bonusNumLbl = new JLabel[5];
		
		for (int i = 0; i < round.length; i++) {
			round[i] = new JPanel();
			roundLbl[i] = new JLabel(gameCount - 1 - i + "회차");
			round[i].add(roundLbl[i]);
			for (int j = 0; j < roundNumLbl[i].length; j++) {
				URL url = Lotto.class.getClassLoader()
						.getResource("images/middle" + String.format("%02d", lottoFive.get(i).get(j)) + ".png");
				ImageIcon icon = new ImageIcon(url);
				
				roundNumLbl[i][j] = new JLabel(icon);
				round[i].add(roundNumLbl[i][j]);
			}
			plusLbl[i] = new JLabel("+");
			URL url = Lotto.class.getClassLoader()
					.getResource("images/middle" + String.format("%02d", lottoFive.get(i).get(6)) + ".png");
			ImageIcon icon = new ImageIcon(url);
			bonusNumLbl[i] = new JLabel(icon);
			round[i].add(plusLbl[i]);
			round[i].add(bonusNumLbl[i]);
		}
		
		BoxLayout box = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(box);
		
		pnl.add(topPnl);
		for (int i = 0; i < round.length; i++) {
			pnl.add(round[i]);
		}
		
		add(pnl);
		setSize(300,300);
	}
}

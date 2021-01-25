import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccesfullyCreatedAccount {
	JFrame frame = new JFrame();
	JPanel panelLabel = new JPanel();
	JPanel panelButton = new JPanel();
	JLabel createdAccountLabel = new JLabel("Felicitari! Contul dumneavoastra a fost creat cu succes!");
	JButton enterAccount = new JButton("Intra in cont");
	JPanel finalPanel = new JPanel();

	public SuccesfullyCreatedAccount() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBackground(Color.WHITE);
		panelLabel.setBackground(Color.WHITE);
		panelButton.setBackground(Color.WHITE);
		finalPanel.setBackground(Color.WHITE);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		enterAccount.setBorder(compound);
		enterAccount.setBackground(Color.LIGHT_GRAY);
		createdAccountLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));

		panelLabel.setSize(30, 15);
		panelLabel.add(createdAccountLabel);
		panelButton.setSize(30, 15);
		panelButton.add(enterAccount);
		finalPanel.add(panelLabel, BorderLayout.LINE_END);
		finalPanel.add(Box.createRigidArea(new Dimension(screenSize.width + 50, 20)));
		finalPanel.add(panelButton);
		frame.add(finalPanel);
		frame.setVisible(true);
		enterAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn log = new LogIn();
			}
		});

	}
}

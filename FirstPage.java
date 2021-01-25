import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstPage {
	public static JFrame frameF = new JFrame();
	JPanel panel = new JPanel();
	JPanel middlePanel1 = new JPanel();
	JPanel middlePanel2 = new JPanel();
	JLabel welcomeLabel = new JLabel("Bine ai venit pe ElectronicsBD!");
	JLabel haveAccount = new JLabel("Ai deja un cont?");
	JButton enterAccount = new JButton("Intra in cont");
	JLabel noAccount = new JLabel("Nu ai un cont?");
	JButton createAccount = new JButton("Creeaza cont");
	JPanel intermediatePanel = new JPanel();

	public FirstPage() {
		haveAccount.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		noAccount.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		enterAccount.setBorder(compound);
		enterAccount.setBackground(Color.LIGHT_GRAY);
		createAccount.setBorder(compound);
		createAccount.setBackground(Color.LIGHT_GRAY);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameF.setBounds(0, 0, screenSize.width, screenSize.height);
		frameF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameF.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameF.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		middlePanel2.setLayout(new BoxLayout(middlePanel2, BoxLayout.Y_AXIS));
		middlePanel1.setLayout(new BoxLayout(middlePanel1, BoxLayout.Y_AXIS));
		intermediatePanel.setLayout(new BoxLayout(intermediatePanel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		middlePanel1.setBackground(Color.WHITE);
		middlePanel2.setBackground(Color.WHITE);
		intermediatePanel.setBackground(Color.WHITE);
		frameF.getContentPane().setBackground(Color.WHITE);
		welcomeLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(welcomeLabel);
		panel.add(Box.createRigidArea(new Dimension(0, screenSize.height / 6)));
		middlePanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		middlePanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		haveAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		middlePanel1.add(haveAccount);
		enterAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		middlePanel1.add(enterAccount);
		middlePanel1.add(Box.createRigidArea(new Dimension(0, screenSize.height / 6)));
		noAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		middlePanel2.add(noAccount);
		createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		middlePanel2.add(createAccount);
		intermediatePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		intermediatePanel.add(middlePanel1);
		intermediatePanel.add(middlePanel2);

		frameF.add(panel);
		frameF.add(Box.createRigidArea(new Dimension(screenSize.width, 0)));
		frameF.add(intermediatePanel);

		frameF.setVisible(true);
		enterAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn user = new LogIn();
				frameF.setVisible(false);
			}
		});
		createAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateAccount userToCreate = new CreateAccount();
				frameF.setVisible(false);

			}
		});
	}

}

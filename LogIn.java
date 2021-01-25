

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";

	public static int IDCont;
	JFrame frame = new JFrame();
	JButton logIn = new JButton("Login");
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel rowPanel = new JPanel();
	JLabel userLabel = new JLabel("Username");
	JPanel rowPanel2 = new JPanel();
	JTextField userText = new JTextField(20);
	String getUsernameTextField = userText.getText();
	JPasswordField passText = new JPasswordField(20);
	String getPasswordTextField = passText.getText();

	public LogIn() {
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		logIn.setBorder(compound);
		logIn.setBackground(Color.LIGHT_GRAY);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel1.setBackground(Color.WHITE);
		rowPanel.setBackground(Color.WHITE);
		rowPanel2.setBackground(Color.WHITE);
		rowPanel.setSize(30, 15);

		userLabel.setSize(20, 15);
		userText.setSize(20, 15);

		rowPanel.add(userLabel);

		rowPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		rowPanel.add(userText);
		panel.add(rowPanel);

		rowPanel2.setSize(30, 15);
		JLabel passLabel = new JLabel("Password");

		passLabel.setSize(20, 15);
		passText.setSize(20, 15);
		rowPanel2.add(passLabel);
		rowPanel2.add(Box.createRigidArea(new Dimension(5, 0)));
		rowPanel2.add(passText);

		panel.add(Box.createRigidArea(new Dimension(screenSize.width + 15, 20)));// 15 e lungimea de la username

		panel.add(rowPanel2, BorderLayout.LINE_END);//

		panel.add(Box.createRigidArea(new Dimension(screenSize.width + 50, 20)));

		logIn.setSize(10, 15);
		panel.add(logIn);
		panel1.add(panel);

		final JPanel[] rowPanelError = { new JPanel() };
		rowPanelError[0].setSize(30, 15);
		final int[] contorIncorrect = { 0 };
		logIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				getUsernameTextField = userText.getText();
				getPasswordTextField = passText.getText();
				Connection c;
				int IDclient = 0;
				int ok = 0;

				try {
					c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement s = c.prepareStatement("select * from contclient");
					ResultSet rez = s.executeQuery();
					while (rez.next()) {
						if (rez.getString("Username").equals(getUsernameTextField)) {
							ok = 1;
							IDclient = rez.getInt("ID");
							IDCont=IDclient;
						}

						System.out.println("Username:  " + rez.getString("Username"));
					}

				} catch (Exception e) {
					System.out.println("SQLException: " + e.getMessage());
					System.out.println("SQLState: " + ((SQLException) e).getSQLState());
					System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
				}
				int okPassword = 0;
				try {
					c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement st2 = c.prepareStatement("select * from contclient");
					ResultSet rez = st2.executeQuery();
					while (rez.next()) {
						if (rez.getString("Parola").equals(getPasswordTextField) && rez.getInt("ID") == IDclient)
							okPassword = 1;
						System.out.println("Parola:  " + rez.getString("Parola"));
					}

				} catch (Exception e) {
					System.out.println("SQLException: " + e.getMessage());
					System.out.println("SQLState: " + ((SQLException) e).getSQLState());
					System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
				}

				if (okPassword == 0 && contorIncorrect[0] == 0) {
					JLabel error = new JLabel("Datele contului sunt gresite");
					error.setBounds(0, 0, screenSize.width, screenSize.height);
					panel1.setBackground(Color.WHITE);
					rowPanelError[0].add(error);
					rowPanelError[0].setBackground(Color.WHITE);
					panel1.add(rowPanelError[0], BorderLayout.LINE_END);
					frame.setVisible(true);
					contorIncorrect[0] = 1; // Verifica sa se afiseze label ul doar o data

				} else if (okPassword == 1) {
					frame.setVisible(false);
					Menu menu = new Menu();
				}
				System.out.println(ok);
				System.out.println(okPassword);
			}
		});

		frame.add(panel1);
		frame.setVisible(true);
	}

	public String getUsername() {
		String str = userText.getText();
		return str;
	}

	public String getUsernameTextField() {
		return getUsernameTextField;
	}
}

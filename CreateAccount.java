import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class CreateAccount {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";
	JFrame frame = new JFrame();
	JLabel lastNameLabel = new JLabel("Nume: ");
	JLabel firstNameLabel = new JLabel("Prenume: ");
	JLabel birthLabel = new JLabel("Data nasterii: ");
	JLabel addressLabel = new JLabel("Adresa: ");
	JLabel phoneLabel = new JLabel("Telefon: ");
	JLabel emailLabel = new JLabel("Email: ");
	JLabel usernameLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Parola: ");
	JTextField lastNameText = new JTextField(25);
	JTextField firstNameText = new JTextField(25);
	JTextField birthText = new JTextField(25);
	JTextField addressText = new JTextField(25);
	JTextField phoneText = new JTextField(25);
	JTextField emailText = new JTextField(25);
	JTextField usernameText = new JTextField(25);
	JTextField passwordText = new JTextField(25);
	JButton createAccount = new JButton("Creeaza cont");
	JPanel lastNamePanel = new JPanel();
	JPanel firstNamePanel = new JPanel();
	JPanel birthPanel = new JPanel();
	JPanel addressPanel = new JPanel();
	JPanel phonePanel = new JPanel();
	JPanel emailPanel = new JPanel();
	JPanel usernamePanel = new JPanel();
	JPanel passwordPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel panel = new JPanel();
	JPanel finalPanel = new JPanel();

	public CreateAccount() {
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		createAccount.setBorder(compound);
		createAccount.setBackground(Color.LIGHT_GRAY);
		lastNamePanel.setBackground(Color.WHITE);
		firstNamePanel.setBackground(Color.WHITE);
		birthPanel.setBackground(Color.WHITE);
		addressPanel.setBackground(Color.WHITE);
		phonePanel.setBackground(Color.WHITE);
		emailPanel.setBackground(Color.WHITE);
		usernamePanel.setBackground(Color.WHITE);
		passwordPanel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		finalPanel.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		JLabel dateIncorecte = new JLabel("Datele introduse sunt invalide, incercati din nou");
		dateIncorecte.setSize(20, 15);
		dateIncorecte.setVisible(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		lastNamePanel.setSize(30, 15);
		lastNameLabel.setSize(20, 15);
		lastNameText.setSize(15, 15);
		lastNamePanel.add(lastNameLabel);

		lastNamePanel.add(lastNameText);
		panel.add(lastNamePanel);

		firstNamePanel.setSize(30, 15);
		firstNameLabel.setSize(20, 15);
		firstNameText.setSize(15, 15);
		firstNamePanel.add(firstNameLabel);

		firstNamePanel.add(firstNameText);

		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		panel.add(firstNamePanel, BorderLayout.LINE_END);

		birthPanel.setSize(30, 15);
		birthLabel.setSize(20, 15);
		birthText.setSize(15, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		birthPanel.add(birthLabel);
		birthPanel.add(birthText);
		panel.add(birthPanel, BorderLayout.LINE_END);

		addressPanel.setSize(30, 15);
		addressLabel.setSize(20, 15);
		addressText.setSize(15, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		addressPanel.add(addressLabel);
		addressPanel.add(addressText);
		panel.add(addressPanel, BorderLayout.LINE_END);

		phonePanel.setSize(30, 15);
		phoneLabel.setSize(15, 15);
		phoneText.setSize(20, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneText);
		panel.add(phonePanel, BorderLayout.LINE_END);

		emailPanel.setSize(30, 15);
		emailLabel.setSize(20, 15);
		emailText.setSize(15, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		panel.add(emailPanel, BorderLayout.LINE_END);

		usernamePanel.setSize(30, 15);
		usernameLabel.setSize(20, 15);
		usernameText.setSize(15, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameText);
		panel.add(usernamePanel, BorderLayout.LINE_END);

		passwordPanel.setSize(30, 15);
		passwordLabel.setSize(20, 15);
		passwordText.setSize(15, 15);
		panel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		panel.add(passwordPanel, BorderLayout.LINE_END);

		finalPanel.add(panel);
		finalPanel.add(createAccount);
		finalPanel.add(Box.createRigidArea(new Dimension(screenSize.width, 15)));
		finalPanel.add(dateIncorecte);
		frame.add(finalPanel);
		frame.setVisible(true);

		createAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection c;
				int OK = 1;
				try {
					c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement query = c.prepareStatement(
							"INSERT INTO client(ID,Nume,Prenume,Data_nasterii,Adresa,Nr_telefon,Email)"
									+ "VALUES(?,?,?,?,?,?,?)");
					PreparedStatement q = c.prepareStatement("SELECT * FROM client");
					ResultSet idClient = q.executeQuery();
					int idInt = 1;

					while (idClient.next()) {
						idInt = idClient.getInt("ID") + 1;
					}

					System.out.println(idInt);
					String getLastName = lastNameText.getText();

					String getFirstName = firstNameText.getText();
					String getBirthString = birthText.getText();
					java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
							.ofPattern("dd/MM/yyyy");
					java.time.LocalDate textFieldAsDate = java.time.LocalDate.parse(getBirthString, formatter);
					String getAddress = addressText.getText();
					String getPhone = phoneText.getText();
					String getEmail = emailText.getText();

					query.setInt(1, idInt);
					query.setString(2, getLastName);
					query.setString(3, getFirstName);
					query.setDate(4, Date.valueOf(textFieldAsDate));
					query.setString(5, getAddress);
					query.setString(6, getPhone);
					query.setString(7, getEmail);
					int res = query.executeUpdate();
					PreparedStatement query2 = c.prepareStatement(
							"INSERT INTO contclient(ID,Username,Parola,Administrator)" + "VALUES(?,?,?,?)");
					String getUsername = usernameText.getText();
					String getPassword = passwordText.getText();
					PreparedStatement qID = c.prepareStatement("SELECT * FROM contclient");
					ResultSet idContClient = qID.executeQuery();
					int idContClientInt = 1;
					while (idContClient.next()) {
						idContClientInt = idContClient.getInt("ID") + 1;
					}
					query2.setInt(1, idContClientInt);
					query2.setString(2, getUsername);
					query2.setString(3, getPassword);
					Boolean b = false;
					query2.setBoolean(4, b);
					int res2 = query2.executeUpdate();

				} catch (Exception ea) {
					System.err.println("Got an exception!");
					System.err.println(ea.getMessage());
					dateIncorecte.setVisible(true);
					OK = 0;
				}
				if (OK == 1) {
					frame.setVisible(false);
					SuccesfullyCreatedAccount enterAccountFrame = new SuccesfullyCreatedAccount();
				}

			}
		});

	}
}


import javax.management.relation.InvalidRoleInfoException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.concurrent.Flow;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";

	public static JFrame frameM = new JFrame();
	JMenu menu = new JMenu("Meniu");
	JMenuItem cont = new JMenuItem("Cont");
	JMenuItem laptop = new JMenuItem("Laptop");
	JMenuItem telefoane = new JMenuItem("Telefoane");
	JMenuItem tablete = new JMenuItem("Tablete");
	JMenuItem pc = new JMenuItem("PC");
	JMenuItem periferice = new JMenuItem("Periferice");
	JMenuItem tvAudioVideo = new JMenuItem("TV, Audio-Video");
	JMenuItem foto = new JMenuItem("Foto");
	JMenuItem electrocasnice = new JMenuItem("Electrocasnice");
	JMenuItem climatizare = new JMenuItem("Climatizare");
	JMenuBar menuBar = new JMenuBar();
	JPanel logoPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JTextField searchBar = new JTextField(40);
	JButton searchButton = new JButton("Cauta");
	JPanel searchPanel = new JPanel();
	JPanel foundProductPanel = new JPanel();
	JLabel notFoundLabel = new JLabel("Nu am gasit");
	String getSearchBarText = searchBar.getText();
	JPanel logoMenuPanel = new JPanel();
	JPanel whitePanel = new JPanel();
	JMenuItem logOut= new JMenuItem("Log out");

	public Menu() {
		frameM.setBackground(Color.WHITE);
		logoPanel.setBackground(Color.WHITE);
		menuPanel.setBackground(Color.WHITE);
		searchPanel.setBackground(Color.WHITE);
		foundProductPanel.setBackground(Color.WHITE);
		logoMenuPanel.setBackground(Color.WHITE);
		whitePanel.setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameM.setBounds(0, 0, screenSize.width, screenSize.height);
		frameM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameM.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameM.setBackground(Color.BLACK);
		menu.add(laptop);
		menu.add(telefoane);
		menu.add(tablete);
		menu.add(pc);
		menu.add(periferice);
		menu.add(tvAudioVideo);
		menu.add(foto);
		menu.add(electrocasnice);
		menu.add(climatizare);
		menuBar.add(menu);
		menuBar.add(cont);
		menuBar.add(logOut);//
		ImageIcon ii = new ImageIcon("D:\\AN2\\POO\\LogoProiectMagazinElectro\\logo.png");
		JLabel label = new JLabel(ii);
		logoMenuPanel.setLayout(new FlowLayout());
		searchBar.setSize(40, 15);
		searchButton.setSize(10, 15);
		logoPanel.setSize(70, 300);
		frameM.setLayout(new BorderLayout());
		logoPanel.setLayout(new BorderLayout());
		searchPanel.setLayout(new FlowLayout());
		logoPanel.add(label, BorderLayout.PAGE_START);
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);
		logoMenuPanel.add(logoPanel, FlowLayout.LEFT);
		logoMenuPanel.add(searchPanel);
		menuPanel.setSize(10, 60);
		menuPanel.add(menuBar);
		frameM.add(logoMenuPanel, BorderLayout.PAGE_START);
		frameM.add(menuPanel, BorderLayout.LINE_START);
		frameM.add(Box.createRigidArea(new Dimension(screenSize.width, 20)));
		foundProductPanel.setLayout(new BoxLayout(foundProductPanel, BoxLayout.PAGE_AXIS));
		whitePanel.setVisible(true);
		foundProductPanel.add(whitePanel);
		foundProductPanel.setVisible(true);
		notFoundLabel.setVisible(false);
		frameM.add(foundProductPanel);
		frameM.setVisible(true);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement query = c.prepareStatement("select * from produs");

					getSearchBarText = searchBar.getText();
					/// query.setString(1,getSearchBarText);
					ResultSet result = query.executeQuery();
					int ok = 0;
					while (result.next()) {
						if (result.getString("Nume_produs").equals(getSearchBarText)) {
							System.out.println(result.getString("Nume_produs"));
							String s1 = "SELECT Imagine FROM produs WHERE ID = ";
							String s2 = result.getString("ID");
							String s = ";";
							String s3 = s1.concat(s2);
							String s4 = s3.concat(s);
							System.out.println(s4);
							PreparedStatement queryImage = c.prepareStatement(s4);

							JLabel nameLabel = new JLabel(getSearchBarText);
							JLabel productDescriptionLabel = new JLabel(result.getString("DescriereProdus"));
							nameLabel.setSize(30, 15);
							productDescriptionLabel.setSize(60, 15);
							JLabel imageLabel = new JLabel();

							ResultSet resultImage = queryImage.executeQuery();
							if (resultImage.next()) {
								byte[] prImage = resultImage.getBytes("Imagine");
								// System.out.println(prImage);
							}
							byte[] image = resultImage.getBytes("Imagine");
							Image img = Toolkit.getDefaultToolkit().createImage(image);
							ImageIcon icon = new ImageIcon(img);
							Image imageProduct = icon.getImage(); // transform it
							Image newimg = imageProduct.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);   // scale
																													// it
																													// the
																													// smooth
																													// way
							icon = new ImageIcon(newimg);
							imageLabel.setIcon(icon);
							if (whitePanel.isVisible()) {
								foundProductPanel.setVisible(false);
								foundProductPanel.removeAll();
								foundProductPanel.add(nameLabel);
								foundProductPanel.add(imageLabel);
								foundProductPanel.add(Box.createRigidArea(new Dimension(screenSize.width / 4, 100)));
								foundProductPanel.add(productDescriptionLabel);
								foundProductPanel.setVisible(true);
								frameM.add(foundProductPanel, BorderLayout.CENTER);
								frameM.revalidate();
								frameM.repaint();
								ok = 1;
							}

							else {
								foundProductPanel.add(nameLabel);
								foundProductPanel.add(imageLabel);
								foundProductPanel.add(Box.createRigidArea(new Dimension(screenSize.width / 4, 100)));
								foundProductPanel.add(productDescriptionLabel);
								foundProductPanel.setVisible(true);
								frameM.add(foundProductPanel, BorderLayout.CENTER);
								frameM.revalidate();
								frameM.repaint();
								ok = 1;
							}

						}
						System.out.println(result.getString("Nume_produs"));
					}
					if (ok == 0)
						notFoundLabel.setVisible(true);

				} catch (SQLException throwables) {
					throwables.printStackTrace();
					notFoundLabel.setVisible(true);
				}

			}
		});
		laptop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Laptop", 5);
				frameM.setVisible(false);
			}
		});
		telefoane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Telefoane", 2);
				frameM.setVisible(false);
			}
		});
		tablete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Tablete", 6);
				frameM.setVisible(false);
			}
		});
		electrocasnice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Electrocasnice", 1);
				frameM.setVisible(false);
			}
		});
		pc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("PC", 3);
				frameM.setVisible(false);
			}
		});
		climatizare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Climatizare", 4);
				frameM.setVisible(false);
			}
		});
		tvAudioVideo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("TV,Audio,Video", 7);
				frameM.setVisible(false);
			}
		});
		foto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Foto", 8);
				frameM.setVisible(false);
			}
		});
		periferice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product.printAllProductsfromCategory("Periferice", 9);
				frameM.setVisible(false);
			}
		});
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cont.ShowAccountDetails(LogIn.IDCont);
				frameM.setVisible(false);
			}
		});
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameM.setVisible(false);
				FirstPage.frameF.setVisible(true);
			}
		});
	}
}

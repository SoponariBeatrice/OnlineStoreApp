import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
public class Product {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";
	public static JLabel prod_name;
	public static String name;
	public static String name2;
	public static JButton backToMButton = new JButton("Back");
	public static JPanel backToMPanel = new JPanel();
	
	public static void printProduct(int i, JPanel contentPane) {
		java.sql.Connection c;
		// IMAGE&NAME
		JPanel panelForEachProd = new JPanel();
		panelForEachProd.setLayout(new BoxLayout(panelForEachProd, BoxLayout.Y_AXIS));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			PreparedStatement query = c.prepareStatement("SELECT Imagine FROM produs WHERE ID=" + i);
			ResultSet productImage = query.executeQuery();
			if (productImage.next()) {
				byte[] prImage = productImage.getBytes("Imagine");
			}
			byte[] prImage = productImage.getBytes("Imagine");
			Image img = Toolkit.getDefaultToolkit().createImage(prImage);

			ImageIcon icon = new ImageIcon(img);
			Image image = icon.getImage(); // transform it
			Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			icon = new ImageIcon(newimg); // transform it back

			ImageIcon icon2 = new ImageIcon(newimg);
			JLabel lPhoto = new JLabel();
			lPhoto.setIcon(icon);
			contentPane.add(lPhoto, BorderLayout.CENTER);
			JLabel lPhoto2 = new JLabel();
			lPhoto2.setIcon(icon2);

			panelForEachProd.add(lPhoto2, BorderLayout.CENTER);
			// PRODUCTNAME
			try {
				c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				Statement query1 = c.createStatement();
				ResultSet productName = query1.executeQuery("SELECT Nume_produs FROM produs WHERE ID=" + i);
				if (productName.next()) {
					name = new String(productName.getString("Nume_produs"));
					System.out.println(name);
				}
				name = new String(productName.getString("Nume_produs"));
				lPhoto.setText(name);
				contentPane.add(lPhoto, BorderLayout.CENTER);
				lPhoto2.setText(name);
				panelForEachProd.add(lPhoto2, BorderLayout.CENTER);

			} catch (SQLException excep) {
				System.out.println("Something went wrong when 'SELECT DescriereProdus FROM produs WHERE ID= " + i);
			}
		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT Imagine FROM produs WHERE ID= " + i);
		}

		// DESCRIPTION BOX
		JLabel descriptionBox = new JLabel();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			Statement query = c.createStatement();
			ResultSet productDescription = query.executeQuery("SELECT DescriereProdus FROM produs WHERE ID=" + i);
			if (productDescription.next()) {
				String description = productDescription.getString("DescriereProdus");
				System.out.println("Descriere: " + description);
			}
			String description = productDescription.getString("DescriereProdus");
			descriptionBox.setText("Descriere: " + description);
			JLabel descriptionBox2 = new JLabel();
			descriptionBox2.setText("Descriere: " + description);
			contentPane.add(descriptionBox, BorderLayout.CENTER);

			panelForEachProd.add(descriptionBox2, BoxLayout.Y_AXIS);
			/// descriptionBox2.add(Box.createRigidArea(new Dimension(100,100)));

		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT DescriereProdus FROM produs WHERE ID= " + i);
		}

		// GUARANTEE
		JLabel guaranteeBox = new JLabel();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "select Perioada_in_luni  from garantie \r\n"
					+ "where id= (select id_garantie from produs where id=" + i + ")";
			Statement query = c.createStatement();
			ResultSet guarantee = query.executeQuery(sql);
			if (guarantee.next()) {
				int guarantee_period = guarantee.getInt("Perioada_in_luni");
				System.out.println("Garantie: " + guarantee_period + " luni");
			}
			int guarantee_period = guarantee.getInt("Perioada_in_luni");
			guaranteeBox.setText("Garantie: " + guarantee_period + " luni");
			contentPane.add(guaranteeBox, BorderLayout.CENTER);
			JLabel guaranteeBox2 = new JLabel();
			guaranteeBox2.setText("Garantie: " + guarantee_period + " luni");
			panelForEachProd.add(guaranteeBox2, BorderLayout.CENTER);
		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'select Perioada_in_luni  from garantie \\r\\n\"\r\n"
					+ "					+ \"where id= (select id_garantie from produs where id= " + i + ")");
		}

		// AVAILABILITY
		JLabel availabilityBox = new JLabel();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			Statement query = c.createStatement();
			ResultSet availability = query.executeQuery("SELECT Disponibilitate FROM produs WHERE ID=" + i);
			if (availability.next()) {
				int available = availability.getInt("Disponibilitate");
				System.out.println("Disponibilitate: " + available);
			}
			int available = availability.getInt("Disponibilitate");
			availabilityBox.setText("Disponibilitate: " + available);
			contentPane.add(availabilityBox, BorderLayout.CENTER);
			JLabel availabilityBox2 = new JLabel();
			availabilityBox2.setText("Disponibilitate: " + available);
			panelForEachProd.add(availabilityBox2, BorderLayout.CENTER);
		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT Disponibilitate FROM produs WHERE ID= " + i);
		}

		// PRICE
		JLabel priceBox = new JLabel();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			Statement query = c.createStatement();
			ResultSet price = query.executeQuery("SELECT ValoareUnitara FROM produs WHERE ID= " + i);
			if (price.next()) {
				int productPrice = price.getInt("ValoareUnitara");
				System.out.println("Pret: " + productPrice + " RON");
			}
			int productPrice = price.getInt("ValoareUnitara");
			priceBox.setText("Pret: " + productPrice + " RON");
			contentPane.add(priceBox, BorderLayout.CENTER);
			JLabel priceBox2 = new JLabel();
			priceBox2.setText("Pret: " + productPrice + " RON");
			panelForEachProd.add(priceBox2, BorderLayout.CENTER);

		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT ValoareUnitara FROM produs WHERE ID= " + i);
		}
		// ADD TO CART
		JButton addToCart = new JButton("ADAUGA IN COS");
		addToCart.setAlignmentX(contentPane.LEFT_ALIGNMENT);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		addToCart.setBorder(compound);
		addToCart.setBackground(Color.LIGHT_GRAY);
		contentPane.add(addToCart);

		addToCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name2=name;
				Cart openMyCart = new Cart(panelForEachProd, priceBox, name2);
				frame1.setVisible(false);
			}
		});
	}

	public static JFrame frameP;
	public static void printAllProductsfromCategory(String CategoryName, int ID_categorie) {
		Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
		frameP = new JFrame(CategoryName);
		frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameP.setExtendedState(Frame.MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameP.setBounds(0, 0, screenSize.width, screenSize.height);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setSize(new Dimension(180, 200));
		contentPane.add(Box.createRigidArea(new Dimension(30, 80)));

		java.sql.Connection c;
		ArrayList<Integer> rsIDs = new ArrayList<Integer>();
		
		backToMPanel.setBackground(Color.white);
		backToMPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		backToMButton.setBorder(compound);
		backToMButton.setBackground(Color.LIGHT_GRAY);
		backToMPanel.add(backToMButton);
		
		frameP.add(backToMPanel, BorderLayout.NORTH);
		
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT ID FROM produs WHERE ID_categorie= " + ID_categorie;
			Statement query = c.createStatement();
			ResultSet idProduct = query.executeQuery(sql);
			while (idProduct.next()) {
				rsIDs.add(idProduct.getInt(1));
			}
		} catch (SQLException excep) {
			System.out.println("Something went wrong when printLaptop");
		}
		for (int pr : rsIDs) {
			System.out.println(pr);
			printProduct(pr, contentPane);
			prod_name= new JLabel();
			frameP.add(contentPane, BorderLayout.CENTER);
		}
		
		
		JScrollPane jScrollPane = new JScrollPane(contentPane);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frameP.getContentPane().add(jScrollPane);
		frameP.add(jScrollPane);
		frameP.setSize(new Dimension(screenSize.width, screenSize.height));
		frameP.setVisible(true);
		
		backToMButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameP.setVisible(false);
				Menu.frameM.setVisible(true);
			}
		});
	}

	static JFrame frame1 = new JFrame();
	static JPanel contentPane1 = new JPanel();

	public static void printAll(String CategoryName, int ID_categorie) {
		java.sql.Connection c;
		ArrayList<Integer> rsIDs = new ArrayList<Integer>();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT ID FROM produs WHERE ID_categorie= " + ID_categorie;
			Statement query = c.createStatement();
			ResultSet idProduct = query.executeQuery(sql);
			while (idProduct.next()) {
				rsIDs.add(idProduct.getInt(1));
			}
		} catch (SQLException excep) {
			System.out.println("Something went wrong when printLaptop");
		}
		for (int pr : rsIDs) {
			System.out.println(pr);
			printProduct(pr, contentPane1);
			frame1.add(contentPane1, BorderLayout.CENTER);
		}
	}
/*
	public static void main(String args[]) {
		printAllProductsfromCategory("Electrocasnice", 1);
		printAllProductsfromCategory("Telefoane", 2);
		printAllProductsfromCategory("PC", 3);
		printAllProductsfromCategory("Climatizare", 4);
		printAllProductsfromCategory("Laptop", 5);
		printAllProductsfromCategory("Tablete", 6);
		printAllProductsfromCategory("TV,Audio,Video", 7);
		printAllProductsfromCategory("Foto", 8);
		printAllProductsfromCategory("Periferice", 9);
		//Print all products frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane1.setLayout(new BoxLayout(contentPane1, BoxLayout.Y_AXIS));
		contentPane1.setSize(new Dimension(180, 200));
		contentPane1.add(Box.createRigidArea(new Dimension(30, 80)));
		printAll("Electrocasnice", 1); 
		printAll("Telefoane", 2); 
		printAll("PC", 3);
		printAll("Climatizare", 4); 
		printAll("Laptop", 5); 
		printAll("Tablete", 6);
		printAll("TV,Audio,Video", 7); 
		printAll("Foto", 8); 
		printAll("Periferice",9);
		JScrollPane jScrollPane = new JScrollPane(contentPane1);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame1.getContentPane().add(jScrollPane);
		frame1.add(jScrollPane); 
		frame1.setSize(new Dimension(900, 700));
		frame1.setVisible(true);
	} */
}

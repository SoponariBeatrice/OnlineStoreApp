import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Cont {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";
	public static JButton backToMenuButton = new JButton("Back");
	public static JPanel backToMenuPanel = new JPanel();
	
	public static void Comenzi(int i, JPanel mainPanel) {
		java.sql.Connection c;
		try {
			JPanel order = new JPanel();
			order.setLayout(new GridLayout(1, 4, 10, 40));
			order.setBackground(Color.white);
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			Statement query = c.createStatement();
			ResultSet value = query.executeQuery("SELECT * FROM istoriccomenzi WHERE ID_comanda= " + i);
			if (value.next()) {
				int orderID = value.getInt("ID_comanda");
				String orderStat = value.getString("StatusComanda");
				float orderValue = value.getFloat("PretComanda");
				Date orderDate = value.getDate("Data");
				System.out.println(orderID + ", " + orderStat + ", " + orderValue + ", " + orderDate);
			}
			String orderStat = value.getString("StatusComanda");
			JLabel IDorder = new JLabel();
			IDorder.setText(value.getString("ID_comanda"));
			order.add(IDorder);

			JLabel StatOrder = new JLabel();
			StatOrder.setText(orderStat);
			order.add(StatOrder);

			JLabel ValueOrder = new JLabel();
			ValueOrder.setText(value.getString("PretComanda"));
			order.add(ValueOrder);

			JLabel DateOrder = new JLabel();
			DateOrder.setText(value.getString("Data"));
			order.add(DateOrder);

			mainPanel.add(order);

		} catch (SQLException excep) {
			System.out.println("SELECT * FROM istoriccomenzi WHERE ID_comanda= " + i);
		}
	}

	static JPanel mainPanelOrders = new JPanel();
	static JFrame frame = new JFrame();

	public static void IstoricComenziCont(int idCont) {
		JPanel ordersPanel = new JPanel();
		ordersPanel.setLayout(new GridLayout(1, 4, 10, 40));
		mainPanelOrders.setSize(200, 200);
		JLabel ordersLabel = new JLabel();
		ordersLabel.setText("Istoric Comenzi");
		mainPanelOrders.add(ordersLabel);
		mainPanelOrders.setLayout((LayoutManager) new BoxLayout(mainPanelOrders, BoxLayout.Y_AXIS));
		JLabel labelID = new JLabel();
		labelID.setText("ID");
		ordersPanel.add(labelID);
		JLabel labelStatus = new JLabel();
		labelStatus.setText("Status comanda");
		ordersPanel.add(labelStatus);
		JLabel labelOrderPrice = new JLabel();
		labelOrderPrice.setText("Pret comanda");
		ordersPanel.add(labelOrderPrice);
		JLabel labelDate = new JLabel();
		labelDate.setText("Data plasarii");
		ordersPanel.add(labelDate);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		ordersPanel.setBorder(blackline);
		mainPanelOrders.add(ordersPanel, "Istoric Comenzi");
		mainPanelOrders.setBorder(blackline);

		java.sql.Connection c;
		ArrayList<Integer> rsIDs = new ArrayList<Integer>();
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT ID_comanda FROM istoriccomenzi WHERE idContClient= " + idCont;
			Statement query = c.createStatement();
			ResultSet res = query.executeQuery(sql);
			while (res.next()) {
				rsIDs.add(res.getInt(1));
			}
		} catch (SQLException excep) {
			System.out.println("Something went wrong when printOrders");
		}
		for (int pr : rsIDs) {
			System.out.println(pr);
			Comenzi(pr, mainPanelOrders);
			frame.add(mainPanelOrders);
		}
		frame.add(mainPanelOrders);
		mainPanelOrders.setBackground(Color.white);
		mainPanelOrders.setBorder(blackline);
		ordersPanel.setBackground(Color.white);
	}

	static JPanel main = new JPanel();
	static JPanel mainPanelAccount = new JPanel();
	static JPanel mainPanelCards = new JPanel();

	public static void Account(int idCont) {
		mainPanelAccount.setLayout((LayoutManager) new BoxLayout(mainPanelAccount, BoxLayout.Y_AXIS));
		mainPanelAccount.setBackground(Color.white);
		mainPanelAccount.setSize(200, 200);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		mainPanelAccount.setBorder(blackline);

		JLabel accountDetails = new JLabel();
		accountDetails.setText("Date cont");
		mainPanelAccount.add(accountDetails, BorderLayout.CENTER);
		java.sql.Connection c;
		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT * FROM contclient WHERE ID= " + idCont;
			Statement query = c.createStatement();
			ResultSet res = query.executeQuery(sql);
			if (res.next()) {
				int ID = res.getInt("ID");
				String username = res.getString("Username");
				System.out.println(ID + ", " + username);
			}
			JLabel ID = new JLabel();
			ID.setText("ID: " + res.getString("ID"));
			JLabel username = new JLabel();
			username.setText("Username: " + res.getString("Username"));
			mainPanelAccount.add(ID, BorderLayout.CENTER);
			mainPanelAccount.add(username, BorderLayout.CENTER);

		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT * FROM contclient WHERE ID= " + idCont + "'");

		}

		try {
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT * FROM client WHERE ID= " + idCont;
			Statement query = c.createStatement();
			ResultSet res = query.executeQuery(sql);
			if (res.next()) {
				String lastname = res.getString("Nume");
				String firstname = res.getString("Prenume");
				Date dateOfBirth = res.getDate("Data_nasterii");
				String adress = res.getString("Adresa");
				String phoneNumber = res.getString("Nr_telefon");
				String email = res.getString("Email");
				System.out.println("Nume " + lastname + "\nPrenume " + firstname + "\nData nasterii: " + dateOfBirth
						+ "\nAdresa: " + adress + "\nNr tel: " + phoneNumber + "\nEmail: " + email);
			}
			JLabel lastname = new JLabel();
			lastname.setText("Nume: " + res.getString("Nume"));
			mainPanelAccount.add(lastname);

			JLabel firstname = new JLabel();
			firstname.setText("Prenume: " + res.getString("Prenume"));
			mainPanelAccount.add(firstname);

			JLabel dateOfBirth = new JLabel();
			dateOfBirth.setText("Data nasterii: " + res.getDate("Data_nasterii"));
			mainPanelAccount.add(dateOfBirth);

			JLabel adress = new JLabel();
			adress.setText("Adresa: " + res.getString("Adresa"));
			mainPanelAccount.add(adress);

			JLabel phoneNumber = new JLabel();
			phoneNumber.setText("Tel.: " + res.getString("Nr_telefon"));
			mainPanelAccount.add(phoneNumber);

			JLabel email = new JLabel();
			email.setText("Email: " + res.getString("Email"));
			mainPanelAccount.add(email);

		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT * FROM client WHERE ID= " + idCont + "'");

		}

	}

	public static void Cards(int id_client) {
		mainPanelCards.setBackground(Color.white);
		mainPanelCards.setLayout((LayoutManager) new BoxLayout(mainPanelCards, BoxLayout.Y_AXIS));
		java.sql.Connection c;
		JLabel cardLabel = new JLabel();
		cardLabel.setText("Cardurile mele: ");
		Border blackline = BorderFactory.createLineBorder(Color.black);
		cardLabel.setBorder(blackline);
		mainPanelCards.add(cardLabel);
		
		JPanel card = new JPanel();
		card.setLayout(new GridLayout(1, 3));
		card.setBackground(Color.white);
		JLabel name = new JLabel();
		name.setText("Nume proprietar");
		card.add(name);
		JLabel nbCard = new JLabel();
		nbCard.setText("Numar card");
		card.add(nbCard);
		JLabel CVV = new JLabel();
		CVV.setText("Cod CVV");
		card.add(CVV);
		
		mainPanelCards.add(card);
		try {
			
			c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
			String sql = "SELECT * FROM card WHERE IDClient= " + id_client;
			Statement query = c.createStatement();
			ResultSet res = query.executeQuery(sql);
			while (res.next()) {
				JPanel cards = new JPanel();
				cards.setBackground(Color.white);
				cards.setLayout(new GridLayout(1, 3));
				JLabel clname = new JLabel();
				clname.setText(res.getString("NumeProprietar"));
				cards.add(clname);
				JLabel clnbCard = new JLabel();
				clnbCard.setText(res.getString("NumarCard"));
				cards.add(clnbCard);
				JLabel clCVV = new JLabel();
				clCVV.setText(res.getString("CodCVV"));
				cards.add(clCVV);
				mainPanelCards.add(cards);
			}

		} catch (SQLException excep) {
			System.out.println("Something went wrong when 'SELECT * FROM card WHERE IDClient= " + id_client + "'");
		}
	}

	public static void ShowAccountDetails(int i) {
		Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
		frame.setLayout(new BorderLayout());
		
		main.setBackground(Color.white);
		main.setSize(new Dimension(700, 500));
		main.setLayout(new BorderLayout());
		IstoricComenziCont(i);
		Account(i);
		Cards(i);
		
		main.add(mainPanelOrders, BorderLayout.EAST);
		main.add(mainPanelAccount, BorderLayout.WEST);
		main.add(mainPanelCards, BorderLayout.CENTER);
		frame.add(main, BorderLayout.CENTER);
		frame.setBackground(Color.white);
		frame.setSize(new Dimension(700, 500));

		backToMenuPanel.setBackground(Color.white);
		backToMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		backToMenuButton.setBorder(compound);
		backToMenuButton.setBackground(Color.LIGHT_GRAY);
		backToMenuPanel.add(backToMenuButton);
		frame.add(backToMenuPanel, BorderLayout.NORTH);
		
		JScrollPane jScrollPane = new JScrollPane(main);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(jScrollPane, BorderLayout.EAST);
		
		frame.add(jScrollPane);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		backToMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Menu.frameM.setVisible(true);
			}
		});
	}
	
}

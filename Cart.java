import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";
	
	private JButton backButton = new JButton("Back");
	JPanel backPanel= new JPanel();
	
	JFrame frame = new JFrame();
	JLabel myCartLabel = new JLabel("Cosul meu");
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel finalPanel = new JPanel();
	JButton placeOrder=new JButton("Plasare comanda");
	JPanel placeOrderPanel= new JPanel();
	
	public Cart(JPanel panel, JLabel price, String name) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		panel.setBackground(Color.WHITE);
		finalPanel.setBackground(Color.WHITE);
		leftPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		backPanel.setBackground(Color.white);
		backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
        backButton.setBorder(compound);
        backButton.setBackground(Color.LIGHT_GRAY);
		backPanel.add(backButton);
		
		placeOrderPanel.setBackground(Color.white);
		placeOrderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		myCartLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		frame.setLayout(new BorderLayout());
		myCartLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		leftPanel.add(myCartLabel, BorderLayout.LINE_START);
		leftPanel.add(panel, BorderLayout.CENTER);
		finalPanel.add(leftPanel, BorderLayout.LINE_START);
		finalPanel.add(rightPanel, BorderLayout.CENTER);
        placeOrder.setBorder(compound);
        placeOrder.setBackground(Color.LIGHT_GRAY);
		placeOrderPanel.add(placeOrder);
		frame.add(backButton, BorderLayout.NORTH);
		frame.add(placeOrderPanel, BorderLayout.SOUTH);
		frame.add(finalPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Product.frameP.setVisible(true);
			}
		});
		
		placeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftPanel.removeAll();
				rightPanel.removeAll();
				finalPanel.removeAll();
				frame.setVisible(false);
			}
		});
		
	}
}

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private final static String DB_NAME = "proiect_v4";
	private final static String USER = "root";
	private final static String PASSWORD = "parola";
	
	public static void main(String args[]) {
		FirstPage first = new FirstPage();
	}
}

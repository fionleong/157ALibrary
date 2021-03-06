import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;

import com.mysql.jdbc.Connection;

public class SearchBooksPage extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextfield;
	private String bookToSearch;
	private DatabaseManager con = new DatabaseManager();
	private User user;
	private JTable table;

	/**
	 * Create the frame. Adds all the things. Makes it visible.
	 */
	public SearchBooksPage() {
		//setup frame bounds
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set up frame contents
		JLabel lblSearchBooks = new JLabel("Search Books");
		lblSearchBooks.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchBooks.setBounds(163, 11, 101, 28);
		contentPane.add(lblSearchBooks);
		
		searchTextfield = new JTextField();
		searchTextfield.setBounds(100, 78, 121, 20);
		contentPane.add(searchTextfield);
		searchTextfield.setColumns(10);
		
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(300, 78, 121, 20);
		contentPane.add(searchBtn);
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					 bookToSearch = searchTextfield.getText();
					 ArrayList<Book> books = con.searchBookTitle(bookToSearch);
					 String[] columnNames = {"BookID", "Title", "Author", "Copies", "LocationID"};
					 
					 TableTester tableCreate = new TableTester(columnNames, books);
					  table = tableCreate.draw();
					// table.setAutoResizeMode();
					 table.setBounds(50, 100,400, 300);
					 contentPane.add(table);
					 contentPane.repaint();
				}
					 
				
		
		});
		
		JButton selectButton = new JButton("select");
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int s=  table.getSelectedRow();
				int bookID = (int) table.getValueAt(s, 0);	
				//new page
				dispose();
				new BookDetailsPage(bookID);
				
			}
		});
		selectButton.setBounds(50, 400,100, 50);
		contentPane.add(selectButton);
	
		JButton btnBack = new JButton("<");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new MainMenuPage();
			}
		});
		btnBack.setBounds(10, 11, 46, 23);
		contentPane.add(btnBack);

		setVisible(true);

	}

}

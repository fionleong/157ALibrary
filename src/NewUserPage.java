import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

public class NewUserPage extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextfield;
	private JTextField birthdayTextfield;
	private String username = "";
	private Date dob = new Date(0, 0, 0);
	private String dateofBirth;
	private DatabaseManager dbm = new DatabaseManager();
	private User user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUserPage frame = new NewUserPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewUserPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateANew = new JLabel("Create A New User");
		lblCreateANew.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreateANew.setBounds(143, 21, 148, 35);
		contentPane.add(lblCreateANew);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(122, 81, 100, 14);
		contentPane.add(lblName);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		lblBirthday.setBounds(107, 119, 100, 14);
		contentPane.add(lblBirthday);
		
		nameTextfield = new JTextField();
		nameTextfield.setBounds(165, 78, 121, 20);
		contentPane.add(nameTextfield);
		nameTextfield.setColumns(10);
		username = nameTextfield.getText();
		
		birthdayTextfield = new JTextField();
		birthdayTextfield.setToolTipText("yyyy-mm-dd");
		birthdayTextfield.setText("yyyy-mm-dd");
		birthdayTextfield.setColumns(10);
		birthdayTextfield.setBounds(165, 116, 121, 20);
		contentPane.add(birthdayTextfield);
		
		
		JButton createUserButton = new JButton("Create User");
		createUserButton.setBounds(150, 177, 150, 25);
		contentPane.add(createUserButton);
		createUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				username = nameTextfield.getText();
				
				int year = Integer.parseInt(birthdayTextfield.getText().substring(0, 4));
				System.out.println(year);
				int month = Integer.parseInt(birthdayTextfield.getText().substring(5,7));
				System.out.println(month);
				int day = Integer.parseInt(birthdayTextfield.getText().substring(8, 10));
				dob.setDate(day);
				//The month will loop back around. 13 = 0,1 so January. 
				dob.setMonth(month-1);
				//because Java copied C, Dates use year - 1900 or something like that. This is why there is a magic number to fix this error. 
				dob.setYear(year-1900);
				System.out.println(dob.toString());
				dbm.insertUser(username, dob);
				User insertedUser = dbm.selectUserDob(username, dob);
				int UserID = insertedUser.getUid();
				
				JOptionPane.showMessageDialog(new JFrame(), "Congrats. New User is created. Use the back button to browse library. Your PIN is " + UserID + ". Find an employee if you forget it.");
				setVisible(true);
				
				
				/*
				DatabaseManager dbm = new DatabaseManager();
				try {
					Connection connection = (Connection) dbm.getConnection();
					username = nameTextfield.getText();
					dateofBirth = birthdayTextfield.getText();
					 
					
					String year = dateofBirth.substring(0, 4);
					int y = Integer.parseInt(year);
					
				
					String month = dateofBirth.substring(5, 7);
					int m = Integer.parseInt(month);
					
					String day = dateofBirth.substring(8, 10);
					
					int d = Integer.parseInt(day);
					dob = new Date(y,m,d);
					
					
					String testQuery = "Insert into user (name, isEmployee, borrowed, birthday, fees) values (?, 0,0,?,0.0)";
					
					PreparedStatement ts = connection.prepareStatement(testQuery);
					ts.setString (1,username);
				     ts.setDate(2, dob );
				     
					ts.executeUpdate();
					connection.close();
					JFrame f = new JFrame();
					
					JOptionPane.showMessageDialog(f, "Congrats. New User is created. Use the back button to browse library. ");
					f.setVisible(true);
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				*/
			}
		});
		
		JButton backButton = new JButton("<");
		backButton.setBounds(10, 11, 46, 23);
		contentPane.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new MainMenuPage();
			}
		});
		/*The pack method sizes the frame so that all its contents are at or above their preferred sizes. 
		 * An alternative to pack is to establish a frame size explicitly by calling setSize or setBounds 
		 * (which also sets the frame location). */
		//WE ARE CURRENTLY SETTING BOUNDS SO NO NEED FOR PACK FOR NOW.
		//pack();
		setVisible(true);
		
	}
}
	

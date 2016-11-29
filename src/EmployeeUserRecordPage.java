import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class EmployeeUserRecordPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeUserRecordPage frame = new EmployeeUserRecordPage();
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
	public EmployeeUserRecordPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserRecords = new JLabel("User Records");
		lblUserRecords.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserRecords.setBounds(156, 11, 105, 28);
		contentPane.add(lblUserRecords);
		
		JButton btnBack = new JButton("<");
		btnBack.setBounds(10, 11, 46, 23);
		contentPane.add(btnBack);
	}

}

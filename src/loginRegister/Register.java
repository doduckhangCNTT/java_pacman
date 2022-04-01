package loginRegister;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtGmail;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField_1;


	private static String DB_URL = "jdbc:mysql://localhost:3306/formaccount";
	private static String USER_NAME = "root";
	private static String PASSWORD = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 436, 296);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Btn Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);

				dispose();
			}

		});
		btnLogin.setBounds(167, 235, 89, 23);
		panel.add(btnLogin);
		
		// Action Register
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement pre = null;
				int dk = JOptionPane.showConfirmDialog(btnRegister, "Do you want register", "Confirm", JOptionPane.YES_NO_OPTION);
				if(dk != JOptionPane.YES_OPTION) {
					return ;
				}
				try {
					// Ket noi database
					conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

					// query - insert
					String sql = "insert into account values(?, ?, ?, ?)";
					pre = conn.prepareStatement(sql);
					pre.setString(1, txtUser.getText());
					pre.setString(2, txtGmail.getText());
					pre.setString(3, passwordField.getText());
					pre.setString(4, confirmPasswordField_1.getText());
					int n = pre.executeUpdate();
					
					if(txtUser.getText().equals("")|txtGmail.getText().equals("")|passwordField.getText().equals("")|confirmPasswordField_1.getText().equals("")) {
						JOptionPane.showMessageDialog(btnRegister, "Invalid info");
					}
					else if(n!=0) {
						txtUser.setText("");
						txtGmail.setText("");
						passwordField.setText("");
						confirmPasswordField_1.setText("");
						JOptionPane.showMessageDialog(btnRegister, "Register successfully");
					} else {
						JOptionPane.showMessageDialog(btnRegister, "Can't Register");
					}
					 
					// close connection
				} catch (Exception err) {
					err.printStackTrace();
				} finally {
					try {
						pre.close();
						conn.close();

					} catch (SQLException e2) {
						// TODO: handle exception
					}
				}
				
				
			}
		});
		
		btnRegister.setBounds(280, 235, 89, 23);
		panel.add(btnRegister);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(29, 33, 46, 14);
		panel.add(lblUser);
		
		JLabel lblGmail = new JLabel("Gmail:");
		lblGmail.setBounds(29, 80, 46, 14);
		panel.add(lblGmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(29, 125, 62, 23);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Cinfirm Password:");
		lblConfirmPassword.setBounds(29, 184, 117, 14);
		panel.add(lblConfirmPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(167, 30, 202, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtGmail = new JTextField();
		txtGmail.setColumns(10);
		txtGmail.setBounds(167, 77, 202, 20);
		panel.add(txtGmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 126, 202, 20);
		panel.add(passwordField);
		
		confirmPasswordField_1 = new JPasswordField();
		confirmPasswordField_1.setBounds(167, 181, 202, 20);
		panel.add(confirmPasswordField_1);
		setLocationRelativeTo(null);
	}
}














































package loginRegister;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.pacman.view.GameTitleUI;

import userPacman.UserPacman;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserLogin;
	private JPasswordField loginPasswordField;

	private static String DB_URL = "jdbc:mysql://localhost:3306/formaccount";
	private static String USER_NAME = "root";
	private static String PASSWORD = "";
	
	static boolean check=true;
	

	public static String name= "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					if(check) {
						frame.setVisible(true);						
					} else {
						frame.dispose();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 331, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 318, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserLogin = new JLabel("User:");
		lblUserLogin.setBounds(30, 39, 46, 14);
		panel.add(lblUserLogin);
		
		JLabel lblPasswordLogin = new JLabel("Password");
		lblPasswordLogin.setBounds(30, 83, 62, 14);
		panel.add(lblPasswordLogin);
		
		txtUserLogin = new JTextField();
		txtUserLogin.setBounds(102, 36, 190, 20);
		panel.add(txtUserLogin);
		txtUserLogin.setColumns(10);
		
		// Btn Register
		JButton btnRegisterForm = new JButton("Register");
		btnRegisterForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}
		});
		btnRegisterForm.setBounds(102, 142, 89, 23);
		panel.add(btnRegisterForm);
		
		// Btn Loggin
		JButton btnLoginForm = new JButton("Login");

		btnLoginForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement pre = null;
				ResultSet rs = null;
				
				try {
					// Ket noi database
					conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

					// query - insert
					String sql = "select * from account where USERNAME=? AND PASS = ?";
					pre = conn.prepareStatement(sql);
					pre.setString(1, txtUserLogin.getText());
					pre.setString(2, loginPasswordField.getText());
					
					name =  txtUserLogin.getText();
					UserPacman userName = new UserPacman(name);
					
					System.out.println("Name: " + name);
					rs = pre.executeQuery();
					
					if(txtUserLogin.getText().equals("")|loginPasswordField.getText().equals("")) {
						JOptionPane.showMessageDialog(btnLoginForm, "Invalid info");
					}
					else if(rs.next()) {
						GameTitleUI gameTitleUI = new GameTitleUI();

						JOptionPane.showMessageDialog(btnLoginForm, "Login successfully");
						dispose();
					} else {
						JOptionPane.showMessageDialog(btnLoginForm, "Can't Login");
					}
					btnLoginForm.setVisible(true);
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
		

		btnLoginForm.setBounds(203, 142, 89, 23);
		panel.add(btnLoginForm);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(102, 80, 190, 20);
		panel.add(loginPasswordField);
		setLocationRelativeTo(null);
	}

}

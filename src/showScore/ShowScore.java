package showScore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import loginRegister.Login;

import javax.swing.border.TitledBorder;

public class ShowScore extends JFrame {

	private JPanel contentPane;
	static DefaultTableModel tableModel;
	private static JTable tableScoreList;
	

	static List<HandleScore> scoreList = new ArrayList<HandleScore>();

	private static String DB_URL = "jdbc:mysql://localhost:3306/formaccount";
	private static String USER_NAME = "root";
	private static String PASSWORD = "";

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowScore frame = new ShowScore();
//					tableModel = (DefaultTableModel) tableScoreList.getModel();
//					loadDataFromDatabase();
//					showData();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ShowScore() throws SQLException {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Result Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 414, 221);
		panel.add(scrollPane);

		tableScoreList = new JTable();
		scrollPane.setViewportView(tableScoreList);
		tableScoreList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "User Name", "Date", "Score", "Game Time", "Status" }));

		tableModel = (DefaultTableModel) tableScoreList.getModel();
		loadDataFromDatabase();
		showData();
	}

	private static void loadDataFromDatabase() throws SQLException {
		scoreList.clear();
		Connection conn = null;
		PreparedStatement pre = null;
		Statement statement = null;

		Login login = new Login();

		System.out.println("Login username: "+ login.name);
		try {
			// Ket noi den database
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("Connecting to database...");
			// khoi tao doi
			statement = conn.createStatement();
			// Tạo câu truy vấn

			String sql = "select * from userresult where username='"+ login.name +"'";

			
			// Thuc thi
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				// Lay du lieu theo ten
				String username = rs.getString("username");
				String date = rs.getString("date");
				Integer score = rs.getInt("score");
				String gameTime = rs.getString("timeplay");
				Integer status = rs.getInt("status");

				System.out.println(username + " " + date + " " + score + " " + gameTime + " " + status);
				HandleScore handleScore = new HandleScore(username, date, score, gameTime, status);
				scoreList.add(handleScore);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}

	public static void showData() {
		for (HandleScore handleScore : scoreList) {
			tableModel.addRow(new Object[] { handleScore.getUsername(), handleScore.getDate(), handleScore.getScore(),
					handleScore.getTimePlay(), handleScore.getStatus() });
		}
	}
}

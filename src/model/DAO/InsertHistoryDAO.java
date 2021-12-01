package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertHistoryDAO {
	public static String DB_URL = "jdbc:mysql://localhost:3306/ltmangck";
	public static String USER_NAME = "root";
	public static String PASSWORD = "";
	
	public InsertHistoryDAO() {}

	public static void insertHistory(String id, String userId, String fileName, String status) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully!");
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO history (id, userId, fileName, status, time) VALUES ('" + id + "', '" +  userId + "', '" + fileName + "', '" + status + "', '" + time + "')");
			con.close();
		} catch (Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace();
		}
	}
}

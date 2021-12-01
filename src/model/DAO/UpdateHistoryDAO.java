package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateHistoryDAO {
	public static String DB_URL = "jdbc:mysql://localhost:3306/ltmangck";
	public static String USER_NAME = "root";
	public static String PASSWORD = "";
	
	public UpdateHistoryDAO() {}

	public static void updateHistory(String id, String status) {
	    Connection con = null;
	  try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully!");
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE history SET status = '" + status + "' WHERE id = '" + id + "'");
			con.close();
	  	} catch (Exception e) {
	  		System.out.println("connect failure!");
	  		e.printStackTrace();
	  	}
	}
}

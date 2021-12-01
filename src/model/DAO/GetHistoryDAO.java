package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean.History;

public class GetHistoryDAO {
	//Truy cap CSDL
	public static String DB_URL = "jdbc:mysql://localhost:3306/ltmangck";
	public static String USER_NAME = "root";
	public static String PASSWORD = "";
	
	public GetHistoryDAO() {}

	public static List<History> getHistory(String userId) {
		List<History> list = new ArrayList<History>();
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully!");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM history WHERE history.userId = " + userId + " ORDER BY time DESC");

			while (rs.next()) {
				History record = new History();
				record.id = rs.getString(1);
				record.fileName = rs.getString(3);
				record.status = rs.getString(4);
				record.time = rs.getTimestamp(5);
				list.add(record);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace();
		}

		return list;
	}
}

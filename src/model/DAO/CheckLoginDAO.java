package model.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Bean.Account;

public class CheckLoginDAO {
	//Truy cap CSDL
	public static String DB_URL = "jdbc:mysql://localhost:3306/ltmangck";
	public static String USER_NAME = "root";
	public static String PASSWORD = "";
	
	public CheckLoginDAO() {}

	public static Account checkAccount(String username, String password) {
		List<Account> l = new ArrayList<Account>();
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully!");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account");

			while (rs.next()) {
					Account account = new Account();
					System.out.print(rs.getString(1));
					account.id = rs.getString(1);
					account.username = rs.getString(2);
					account.password = rs.getString(3);
					account.fullName = rs.getString(4);

					l.add(account);
			}
			con.close();
		} catch (Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace();
		}
		for (Account a : l) {
			System.out.print(username + ' ' + password);
			if (a.getUserName().equals(username) && a.getPassword().equals(password)) {
				System.out.print(a.getFullName());
				return a;
			}
		}
		return null;
	}

}

package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_DB {
	
	static Connection conn = null;
	
	public static Connection ConDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salle_sports", "root", "12345");
			return conn;
		} catch (Exception e) {
			return null;
		}
	}
}

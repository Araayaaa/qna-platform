package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// sqlite3
	private final static String jdbcURL = "jdbc:sqlite:C:\\Users\\Derangga Abim\\eclipse-workspace\\PlatformTanyaJawab\\database\\nanyadong.db";	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(jdbcURL);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import model.User;

public class UserDAO {
	public UserDAO() {}
	
	public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?);";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			
			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User login(String username, String password) throws ClassNotFoundException {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new User(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM users WHERE id = ?;";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email")
				);
			}
		}
		return null;
	}
}

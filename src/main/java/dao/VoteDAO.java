package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import db.DBConnection;

public class VoteDAO {
	public boolean hasUserVotedForQuestion(int userId, int questionId) throws ClassNotFoundException {
	    String sql = "SELECT COUNT(*) FROM votes WHERE user_id = ? AND question_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        stmt.setInt(2, questionId);
	        ResultSet rs = stmt.executeQuery();
	        return rs.next() && rs.getInt(1) > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean hasUserVotedForAnswer(int userId, int answerId) throws ClassNotFoundException {
	    String sql = "SELECT COUNT(*) FROM votes WHERE user_id = ? AND answer_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        stmt.setInt(2, answerId);
	        ResultSet rs = stmt.executeQuery();
	        return rs.next() && rs.getInt(1) > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public boolean upvoteAnswer(int userId, int answerId) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO votes(user_id, answer_id, vote_type) VALUES(?, ?, 'UP');";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, userId);
			stmt.setInt(2, answerId);
			return stmt.executeUpdate() > 0;
		} catch(SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean upvoteQuestion(int userId, int questionId) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO votes(user_id, question_id, vote_type) VALUES(?, ?, 'UP');";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, questionId);
			return stmt.executeUpdate() > 0;
		} catch(SQLIntegrityConstraintViolationException e) {
			return false;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
}

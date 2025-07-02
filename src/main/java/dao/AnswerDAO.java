package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Answer;

public class AnswerDAO {
	public AnswerDAO() {
	}
	
	public boolean addAnswer(Answer answer) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO answers(question_id, user_id, content) VALUES(?, ?, ?);";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, answer.getQuestionId());
			stmt.setInt(2, answer.getUserId());
			stmt.setString(3, answer.getContent());
			return stmt.executeUpdate() > 0;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Answer> getAnswerByQuestionId(int questionId) throws SQLException, ClassNotFoundException {
		List<Answer> list = new ArrayList<>();
		String sql = "SELECT a.*, IFNULL(SUM(v.vote_type='UP'),0) AS votes FROM answers a LEFT JOIN votes v ON v.answer_id = a.id WHERE a.question_id = ? GROUP BY a.id;";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, questionId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Answer a = new Answer();
				a.setId(rs.getInt("id"));
				a.setQuestionId(rs.getInt("question_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setContent(rs.getString("content"));
				a.setCreatedAt(rs.getTimestamp("created_at"));
				a.setVoteCount(rs.getInt("votes"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

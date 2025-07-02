package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import db.DBConnection;
import model.Question;

public class QuestionDAO {
	public QuestionDAO() {}
	
	public boolean addQuestion(Question q) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO questions(user_id, title, content) VALUES(?, ?, ?);";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, q.getUserId());
			stmt.setString(2, q.getTitle());
			stmt.setString(3, q.getContent());
			
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Question> getAllQuestion() throws SQLException, ClassNotFoundException {
		List<Question> list = new ArrayList<>();
		String sql = "SELECT q.*, IFNULL(SUM(v.vote_type='UP'), 0) AS votes FROM questions q LEFT JOIN votes v ON v.question_id = q.id GROUP BY q.id;";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while(rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp createdAt = rs.getTimestamp("created_at");
				int voteCount = rs.getInt("votes");
				list.add(new Question(id, userId, title, content, createdAt, voteCount));
			}
			return list;
		}
	}
	
	public Question getById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT q.*, COALESCE(v.vote_count, 0) AS vote_count FROM questions q LEFT JOIN (SELECT question_id, COUNT(*) AS vote_count FROM votes WHERE question_id IS NOT NULL GROUP BY question_id) v ON q.id = v.question_id WHERE q.id = ?;";
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Question(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("content"), rs.getTimestamp("created_at"), rs.getInt("vote_count"));
			}
			return null;
		}
	}
	
	public List<Question> getAllSortedByCreatedAt() throws SQLException, ClassNotFoundException {
		String sql = "SELECT q.*, COALESCE(v.vote_count, 0) AS vote_count FROM questions q LEFT JOIN(SELECT question_id, COUNT(*) AS vote_count FROM votes WHERE question_id IS NOT NULL GROUP BY question_id) v ON q.id = v.question_id ORDER BY q.created_at DESC;";
		return getQuestionsFromQuery(sql);
	}
	
	public List<Question> getAllSortedByVoteCount() throws SQLException, ClassNotFoundException {
		String sql = "SELECT q.*, COALESCE(v.vote_count, 0) AS vote_count FROM questions q LEFT JOIN(SELECT question_id, COUNT(*) AS vote_count FROM votes WHERE question_id IS NOT NULL GROUP BY question_id) v ON q.id = v.question_id ORDER BY vote_count DESC;";
		return getQuestionsFromQuery(sql);
	}
	
	private List<Question> getQuestionsFromQuery(String sql) throws SQLException, ClassNotFoundException {
		List<Question> list = new ArrayList<>();
		try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Question q = new Question();
				q.setId(rs.getInt("id"));
				q.setUserId(rs.getInt("user_id"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setCreatedAt(rs.getTimestamp("created_at"));
				q.setVoteCount(rs.getInt("vote_count"));
				list.add(q);
			}
		}
		return list;
	}
}

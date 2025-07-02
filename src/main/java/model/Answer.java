package model;

import java.sql.Timestamp;

public class Answer {
	private int id;
	private int questionId;
	private int userId;
	private String content;
	private Timestamp createdAt;
	private int voteCount;
	
	public Answer() {	
	}
	
	public Answer(int id, int questionId, int userId, String content, Timestamp createdAt, int voteCount) {	
		this.id = id;
		this.questionId = questionId;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	
}

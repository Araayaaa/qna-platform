<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*, dao.*, java.util.*"%>
<%@ page session="true"%>
<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}

int questionId = Integer.parseInt(request.getParameter("id"));
QuestionDAO questionDAO = new QuestionDAO();
AnswerDAO answerDAO = new AnswerDAO();
UserDAO userDAO = new UserDAO();

Question question = questionDAO.getById(questionId);
List<Answer> answers = answerDAO.getAnswerByQuestionId(questionId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=question.getTitle()%></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<jsp:include page="navbar.jsp" />
	<div class="container my-5">

		<a href="index.jsp" class="btn btn-secondary mb-4">← Kembali ke
			Beranda</a>

		<!-- Detail Pertanyaan -->
		<div class="card mb-4">
			<div
				class="card-header d-flex justify-content-between align-items-center">
				<h5 class="mb-0"><%=question.getTitle()%></h5>
				<span class="text-muted small"> oleh <%=userDAO.getUserById(question.getUserId()).getUsername()%>
					• <%=question.getCreatedAt()%>
				</span>
			</div>
			<div class="card-body">
				<p><%=question.getContent()%></p>
			</div>
			<div
				class="card-footer d-flex align-items-center justify-content-between">
				<form action="vote" method="post" class="mb-0">
					<input type="hidden" name="type" value="question"> <input
						type="hidden" name="id" value="<%=question.getId()%>"> <input
						type="hidden" name="question_id" value="<%=question.getId()%>">
					<button type="submit" class="btn btn-sm btn-outline-primary">
						👍 Upvote</button>
				</form>
				<span><strong><%=question.getVoteCount()%></strong> vote</span>
			</div>
		</div>

		<!-- Form Jawaban -->
		<div class="card mb-4">
			<div class="card-header">Berikan Jawaban Anda</div>
			<div class="card-body">
				<form action="answer" method="post">
					<input type="hidden" name="question_id"
						value="<%=question.getId()%>">
					<div class="mb-3">
						<textarea name="content" class="form-control" rows="4"
							placeholder="Tulis jawaban Anda..." required></textarea>
					</div>
					<button type="submit" class="btn btn-success">Kirim
						Jawaban</button>
				</form>
			</div>
		</div>

		<!-- Daftar Jawaban -->
		<h5 class="mb-3">
			Jawaban (<%=answers.size()%>)
		</h5>
		<%
		for (Answer answer : answers) {
			String answerUsername = userDAO.getUserById(answer.getUserId()).getUsername();
		%>
		<div class="card mb-3">
			<div class="card-body">
				<p><%=answer.getContent()%></p>
				<div class="d-flex justify-content-between">
					<small class="text-muted"> oleh <%=answerUsername%> • <%=answer.getCreatedAt()%>
					</small>
					<form action="vote" method="post" class="d-inline">
						<input type="hidden" name="type" value="answer"> <input
							type="hidden" name="id" value="<%=answer.getId()%>"> <input
							type="hidden" name="question_id" value="<%=question.getId()%>">
						<button type="submit" class="btn btn-sm btn-outline-primary">
							👍
							<%=answer.getVoteCount()%>
						</button>
					</form>
				</div>
			</div>
		</div>
		<%
		}
		%>

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*, dao.*, java.util.*"%>
<%@ page session="true"%>
<%
User user = (User) session.getAttribute("user");
QuestionDAO questionDAO = new QuestionDAO();
UserDAO userDAO = new UserDAO();

String sort = request.getParameter("sort");
List<Question> questions;

if ("popular".equals(sort)) {
	questions = questionDAO.getAllSortedByVoteCount();
} else {
	questions = questionDAO.getAllSortedByCreatedAt();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beranda - TanyaJawab</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<jsp:include page="navbar.jsp" />
	<div class="container my-5">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3>Pertanyaan</h3>
			<div>
				<a href="index.jsp?sort=newest"
					class="btn btn-outline-primary btn-sm <%=(sort == null || "newest".equals(sort)) ? "active" : ""%>">Terbaru</a>
				<a href="index.jsp?sort=popular"
					class="btn btn-outline-primary btn-sm <%="popular".equals(sort) ? "active" : ""%>">Terpopuler</a>
			</div>
		</div>

		<%
		for (Question q : questions) {
			String username = userDAO.getUserById(q.getUserId()).getUsername();
		%>
		<div class="card mb-3">
			<div class="card-body">
				<h5>
					<a href="question.jsp?id=<%=q.getId()%>"
						class="text-decoration-none"><%=q.getTitle()%></a>
				</h5>
				<p class="text-muted small mb-1">
					oleh
					<%=username%>
					•
					<%=q.getCreatedAt()%></p>
				<p class="mb-0"><%=q.getContent().length() > 150 ? q.getContent().substring(0, 150) + "..." : q.getContent()%></p>
			</div>
			<div class="card-footer text-muted d-flex justify-content-between">
				<span>👍 <%=q.getVoteCount()%> vote
				</span> <a href="question.jsp?id=<%=q.getId()%>"
					class="btn btn-sm btn-outline-secondary">Lihat Detail</a>
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

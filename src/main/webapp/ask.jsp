<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page session="true"%>
<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajukan Pertanyaan</title>
<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<jsp:include page="navbar.jsp" />
	<div class="container mt-5">
		<div class="mb-4">
			<a href="index.jsp" class="btn btn-secondary">← Kembali ke
				Beranda</a>
		</div>

		<div class="card">
			<div class="card-header">
				<h4>Ajukan Pertanyaan Baru</h4>
			</div>
			<div class="card-body">
				<form action="ask" method="post">
					<div class="mb-3">
						<label for="title" class="form-label">Judul Pertanyaan</label> <input
							type="text" class="form-control" id="title" name="title" required
							maxlength="200">
					</div>
					<div class="mb-3">
						<label for="content" class="form-label">Isi Pertanyaan</label>
						<textarea class="form-control" id="content" name="content"
							rows="6" required></textarea>
					</div>
					<button type="submit" class="btn btn-primary">Kirim
						Pertanyaan</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>

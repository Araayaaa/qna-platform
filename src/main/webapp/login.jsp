<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.User"%>
<%@ page session="true"%>
<%
User currentUser = (User) session.getAttribute("user");
String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - NanyaDong</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div
		class="container-fluid min-vh-100 d-flex align-items-center justify-content-center">
		<div class="row w-100">

			<div
				class="col-lg-6 d-flex align-items-center justify-content-center mb-5 mb-lg-0">
				<h2 class="display-5 fw-semibold text-center">
					Selamat datang di<br />NanyaDong
				</h2>
			</div>

			<div class="col-lg-4 offset-lg-1 d-flex justify-content-center">
				<div class="card shadow-sm w-100" style="max-width: 400px;">
					<div class="card-header text-center">
						<h5 class="mb-0">Masuk ke Akun Anda</h5>
					</div>

					<div class="card-body">
						<%
						if (error != null) {
						%>
						<div class="alert alert-danger text-center">Username atau
							password salah.</div>
						<%
						}
						%>

						<form action="login" method="post">
							<div class="mb-3">
								<label class="form-label">Nama Pengguna</label> <input
									type="text" name="username" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Kata Sandi</label> <input
									type="password" name="password" class="form-control" required>
							</div>
							<button type="submit" class="btn btn-primary w-100">Login</button>
						</form>
					</div>

					<div class="card-footer text-center">
						Belum punya akun? <a href="register.jsp">Daftar di sini</a>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrasi Pengguna</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center justify-content-center" style="height: 100vh;">

<div class="card shadow p-4" style="width: 100%; max-width: 400px;">
    <h4 class="mb-4 text-center">Buat Akun Baru</h4>
    <form action="register" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Nama Pengguna</label>
            <input type="text" class="form-control" id="username" name="username" required maxlength="50">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required maxlength="100">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Kata Sandi</label>
            <input type="password" class="form-control" id="password" name="password" required minlength="4">
        </div>
        <button type="submit" class="btn btn-primary w-100">Daftar</button>
    </form>

    <div class="mt-3 text-center">
        <small>Sudah punya akun? <a href="login.jsp">Masuk di sini</a></small>
    </div>
</div>

</body>
</html>
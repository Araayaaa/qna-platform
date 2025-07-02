
# 🧠 Simple Q&A Platform (Java + JSP + Servlet)

Sebuah aplikasi web sederhana seperti StackOverflow mini yang memungkinkan pengguna untuk:
- Mendaftar dan login
- Mengajukan pertanyaan
- Menjawab pertanyaan
- Melakukan upvote pada pertanyaan dan jawaban
- Melihat pertanyaan terbaru dan terpopuler

## 🔧 Teknologi yang Digunakan

- Java (JSP & Servlet)
- MySQL
- HTML + Bootstrap 5
- Pattern: MVC, DAO, Singleton, Strategy
- IDE: Eclipse
- Tidak menggunakan Maven

---

## 📁 Struktur Project

```
📦SimpleQnA
 ┣ 📂src/
 ┃ ┣ 📂controller/       # Servlet
 ┃ ┣ 📂dao/              # Data Access Object
 ┃ ┣ 📂model/            # Model (User, Question, etc.)
 ┃ ┗ DBConnection.java   # Singleton DB helper
 ┣ 📂WebContent/
 ┃ ┣ 📂css/              # (optional) static files
 ┃ ┣ 📂jsp/              # JSP views
 ┃ ┣ navbar.jsp          # Reusable navbar
 ┃ ┗ index.jsp, login.jsp, question.jsp, etc.
 ┣ web.xml               # Servlet config
 ┗ backup.sql            # File SQL untuk import database
```

---

## 💾 Cara Menjalankan

### 1. Clone / Download Project
```bash
git clone https://github.com/username/SimpleQnA.git
```

### 2. Import ke Eclipse
- File → Import → Existing Project into Workspace → arahkan ke folder project

### 3. Setup MySQL Database
1. Buat database baru, misal `qna_db`
2. Import file `backup.sql` ke dalamnya:
```bash
mysql -u root -p qna_db < backup.sql
```

### 4. Ubah konfigurasi koneksi database (src/DBConnection.java)
```java
String url = "jdbc:mysql://localhost:3306/qna_db";
String user = "root";
String password = ""; // atau isi sesuai password MySQL kamu
```

### 5. Jalankan dengan Apache Tomcat
- Pastikan server Tomcat sudah terhubung di Eclipse
- Klik kanan project → Run on Server

---

## 🧪 Fitur

- ✅ Registrasi & Login
- ✅ Tanya & Jawab
- ✅ Upvote dengan batasan 1 kali per user per post
- ✅ Sort pertanyaan berdasarkan terbaru / populer
- ✅ Validasi sederhana (form, login)
- ✅ Tampilan responsive (Bootstrap)

---

## 👨‍💻 Kontributor

- Araya ✨

---

## 📄 Lisensi

Lisensi bebas untuk penggunaan edukasi / pembelajaran.

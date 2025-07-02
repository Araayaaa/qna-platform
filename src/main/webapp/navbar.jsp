<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">MauNanyaDong</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <% if (currentUser != null) { %>
          <li class="nav-item">
            <a class="nav-link" href="ask.jsp">Ajukan Pertanyaan</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Halo, <%= currentUser.getUsername() %></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login.jsp">Logout</a>
          </li>
        <% } else { %>
          <li class="nav-item">
            <a class="nav-link" href="register.jsp">Register</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login.jsp">Login</a>
          </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
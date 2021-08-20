<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.password.manager.dao.CredentialDAO"%>
<%@page import="com.password.manager.Credential"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Integer userId = (Integer) session.getAttribute("userID");
if (session.getAttribute("loggedInUser") == null || userId == null) {
	response.sendRedirect("login.jsp");
	return;
}
try {
	Integer id = Integer.parseInt(request.getParameter("id"));
	CredentialDAO dao = new CredentialDAO();

	if (dao.isBelongsTo(userId, id)) {
		Credential existingData = dao.getCredentialById(id);
		pageContext.setAttribute("existingCred", existingData);
	} else {
		response.sendRedirect("index.jsp");
		return;
	}
} catch (Exception ex) {
	response.sendRedirect("index.jsp");
	return;
}
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<!-- Bootstrap core CSS -->
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>
<body>
<header class="p-3 bg-dark text-white">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="/"
					class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
				</a>

				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="index.jsp" class="nav-link px-2 active text-white"><i class="bi bi-house-door"></i> Home</a></li>
					<li><a href="add-credential.jsp"
						class="nav-link px-2 text-white"><i class="bi bi-plus-square"></i> Add credential</a></li>
				</ul>

				<div class="text-end">
					Hello, ${loggedInUser}!&emsp; <a type="button"
						class="btn btn-warning" id="logout" href="logout">Logout <i class="bi bi-box-arrow-right"></i></a>
				</div>
			</div>
		</div>
	</header>

	<div class="container d-flex justify-content-center">
		<main class="w-50">
			<form method="POST" action="edit-credential">
				<h1 class="h3 mt-3 mb-3 text-center">Edit Credential Form</h1>
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success text-center">${message}</div>
				</c:if>

				<c:if test="${not empty errorMessage}">
					<div id="errormessage" class="alert alert-danger text-center">${errorMessage}</div>
				</c:if>

				<input type="hidden" value="${existingCred.id}" name="id"></input>
				<div class="form-group">
					<label for="website">Website</label>
					<input type="text" class="form-control" id="website" name="website" value="${existingCred.url}"
						placeholder="website">
				</div>
				<div class="form-group">
					<label for="username">Username</label>
					<input type="text" class="form-control" id="username" value="${existingCred.username}"
						name="username" placeholder="Username">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" value="${existingCred.password}"
						name="password" placeholder="Password">
				</div>
				<br/>
				<button id="editcred" class="w-100 btn btn-lg btn-primary"
					type="submit">Update Credential</button>
			</form>

		</main>
	</div>
</body>
</html>
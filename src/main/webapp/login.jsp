<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if (request.getSession().getAttribute("loggedInUser") != null) {
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
<meta name="theme-color" content="#7952b3">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
	
			
		<form method="POST" action="login">
		<img class="mb-2" src="HCL_Technologies_logo.svg" alt="" width="150">
		<img class="mb-4" style="margin-left: -80px;" src="PasswordManager.svg" alt="" width="472">
		
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			<c:if test="${not empty message}">
				<div id="message" class="alert alert-danger" >${message}</div>
			</c:if>

			<div class="form-floating">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="Username"> <label
					for="username">Username</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="password"
					name="password" placeholder="Password"> <label
					for="password">Password</label>
			</div>
			<button id="signin" class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			<a id="register" class="w-100" href="register.jsp">Not a user? Register now!</a><br/>
			
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
		</form>

	</main>



</body>
</html>

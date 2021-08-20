<%@page import="com.password.manager.Credential"%>
<%@page import="java.util.List"%>
<%@page import="com.password.manager.dao.CredentialDAO"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
if (session.getAttribute("loggedInUser") == null || session.getAttribute("userID") == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password manager</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<style type="text/css">
.flex-grow {
	flex-grow: 1;
}
</style>
<script type="text/javascript">
function confirmDelete(id, event) {
	const result = confirm("Are you to delete?");
	if (result) {
		fetch("delete-credential?id=" + id)
		.then(() => {
			const element = document.getElementById("cred-" + id);
			element.remove();
		})
		.catch(() => {
			const alertElement = document.getElementById("errorMessage");
			alertElement.classList.remove("d-none");
			setTimeout(() => {
				alertElement.classList.add("d-none");
			}, 3000);
		});
	}
}
function clipboardCopy(id) {
	  const passwordElement = document.getElementById(id);
	  const textArea = document.createElement("textarea");
	  textArea.value = passwordElement.value;
	  document.body.appendChild(textArea);
	  textArea.select();
	  textArea.setSelectionRange(0, 99999); /* For mobile devices */
	  document.execCommand("copy");
	  textArea.remove();
	}

	function makeItTextField(id, iconId) {
		const element = document.getElementById(id);
		const iconElement = document.getElementById(iconId);
		if (element.type === "text") {
			element.type = "password";
			iconElement.classList.remove("bi-eye-fill");
			iconElement.classList.add("bi-eye-slash-fill")
		} else {
			element.type = "text";
			iconElement.classList.add("bi-eye-fill");
			iconElement.classList.remove("bi-eye-slash-fill")
		}
	}
</script>
</head>
<body>
	<header class="p-3 bg-dark text-white">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="index.jsp"
						class="nav-link px-2 active text-white"><i
							class="bi bi-house-door"></i> Home</a></li>
					<li><a href="add-credential.jsp" id="addcred"
						class="nav-link px-2 text-white"><i class="bi bi-plus-square"></i>
							Add credential</a></li>
				</ul>

				<div class="text-end">
					Hello, ${loggedInUser}!&emsp; <a type="button"
						class="btn btn-warning" id="logout" href="logout">Logout <i
						class="bi bi-box-arrow-right"></i></a>
				</div>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="alert alert-danger d-none m-2" id="errorMessage">Failed
			to delete credential!</div>
		<div class="row">
			<%
			Integer userid = (Integer) request.getSession().getAttribute("userID");
			CredentialDAO dao = new CredentialDAO();
			List<Credential> credentailList = dao.getAllCredentials(userid);
			pageContext.setAttribute("credList", credentailList);
			%>

			<c:forEach items="${credList}" var="cred">
				<div class="col-3">
					<div class="card m-2" id="cred-${cred.id}">
						<div class="card-body">
							<a href="${cred.url}" target="_blank" class="text-decoration-none"><h5 class="card-title d-inline">${cred.url}</h5> <i class="bi bi-box-arrow-up-right"></i></a>
					<!--    <h6 class="card-subtitle mb-2 text-muted">
								Username: <span id="username-${cred.id}">${cred.username} <i
									onclick="clipboardCopy('username-${cred.id}')"
									class="bi bi-clipboard"></i></span>
							</h6> -->
							
							<h6 class="card-subtitle mb-2 text-muted">
								<span>Username: </span> <span> <input id="username-${cred.id}"
									value="${cred.username}" type="text" class="border-0 w-25" style="background-color:#fff;"
									disabled /><i
									onclick="clipboardCopy('username-${cred.id}')"
									class="bi bi-clipboard"></i> </span> 
							</h6>
							
							<h6 class="card-subtitle mb-2 text-muted">
								<span>Password: <input id="password-${cred.id}"
									value="${cred.password}" type="password" class="border-0 w-25" style="background-color:#fff;"
									disabled /> <i class="bi bi-eye-slash-fill"
									onclick="makeItTextField('password-${cred.id}', 'icon-${cred.id}')"
									id="icon-${cred.id}"></i> <i
									onclick="clipboardCopy('password-${cred.id}')"
									class="bi bi-clipboard"></i></span>
							</h6>
							<a class="card-link btn btn-primary" id="edit-${cred.id}"
								href="edit-credential.jsp?id=${cred.id}"> <i
								class="bi bi-pencil-square"></i> Edit
							</a> <a class="card-link btn btn-danger" id="delete-${cred.id} }"
								onclick="confirmDelete(${cred.id})"> <i class="bi bi-trash"></i>
								Delete
							</a>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
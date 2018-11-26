<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/LoginStyle.css" />" rel="stylesheet">
<script src="/resources/js/validationMessage.js"></script>

<title>Reverse Auction Login</title>
</head>
<body class="background-login">
	<div class="login">
		<div class="login-triangle"></div>
		<h3 class="login-header">Login</h3>

		<div class="form-group form">
			<form action="login" class="login-container" method="post">
			<div class="validationMessages" style="color:red; display:block" class="container">		
				${loginValidationMessage}
				<hr/>
			</div>
				<label>Username:</label>
				<p>
				<input name="username" type="text" id="username" required placeholder="Enter username"
						class="form-control login-field" />
				</p>

				<label>Password:</label>
				<p>
					<input name="password" type="password" id="password" required placeholder="Enter password"
						class="form-control login-field" />
				</p>

				<button id="loginButton" type="submit" class="login">Login</button>
				  <p style="padding-left:60px; width: 295px">
      				Don't have an account? 
      				<a href="register">Sign up</a>
      			  </p>
			</form>
		</div>
	</div>
</body>
</html>
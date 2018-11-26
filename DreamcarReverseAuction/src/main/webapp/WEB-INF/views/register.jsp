<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/LoginStyle.css" />"
	rel="stylesheet">

	<link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
	
	<script src="/resources/js/validationMessage.js"></script>
<title>Reverse Auction Register</title>
</head>
<body class="background-login">

	<div class="login">
		<div class="login-triangle"></div>
		<h3 class="login-header"><a href="login"><i class="fa fa-angle-left" style="right:90px; position:relative;"></i></a>  
		New Account</h3>
		<div class="form-group form">
			<form action="register" class="login-container" method="post">
			<div class="validationMessages" style="color:red; display:block" class="container">		
				${usernameExistsMessage}
				<hr/>
			</div>
				<label>Username:</label>
				<p>
					<input name="username" type="text" id="username" pattern=".{5,}"
						required title="6 characters minimum" placeholder="Enter username"
						class="form-control login-field" />
				</p>

				<label>Password:</label>
				<p>
					<input name="password" type="password" id="password"
						pattern=".{6,}" required title="6 characters minimum"
						placeholder="Enter password" class="form-control login-field" />
				</p>

				<label>Email:</label>
				<p>
					<input name="email" type="email" id="email" required
						placeholder="Enter email" class="form-control login-field" />
				</p>
				<label>Phone number:</label>
				<p>
					<input name="phoneNumber" type="tel" required
						pattern="^\+?\d{11,13}" id="phoneNumber"
						title='Phone Number (eg. +80 999 999 999)'
						placeholder="Enter phone number" class="form-control login-field" />
				</p>

				<label for="company">Company:</label> 
				<select
					class="form-control register-field" required path="companyId" name="companyId"
					id="companyId">
					<option value="">Select Company</option>

					<c:forEach items="${companyList}" var="company">
						<option id="${company.getCompanyId()}" value="${company.getCompanyId()}">${company.getName()}</option>
					</c:forEach>
				</select>

				<button id="registerButton" type="submit" class="login">Create
					Account</button>
			</form>
		</div>
	</div>
</body>
</html>
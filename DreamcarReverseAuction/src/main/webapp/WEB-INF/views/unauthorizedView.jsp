
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="../resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<title>Unauthorized</title>
</head>
<body>
<div class="text-center">
<img src="../resources/images/no_access_image.jpg"/>
<h1>Sorry you do not have access to this page, please Login <a href="/dreamcar/login">here</a></h1>
</div>
</body>
</html>
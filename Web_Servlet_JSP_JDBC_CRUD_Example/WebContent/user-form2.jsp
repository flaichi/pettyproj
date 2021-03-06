<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	

	<div class="container">

		<c:if test="${user != null}">
			<h2>Update Existing User</h2>
			<form action="updateuser" method="post">
		</c:if>
		<c:if test="${user == null}">
			<h2>Add New User</h2>
			<form action="newuser" method="post">
		</c:if>

		<div class="form-group">
			<label for="name">User Name</label> <input type="text"
				class="form-control" name="name"
				value="<c:out value='${user.name}' />" />
		</div>
		<div class="form-group">
			<label for="email">User Email</label> <input type="text"
				class="form-control" name="email"
				value="<c:out value='${user.email}' />">
		</div>
		<div class="form-group">
			<label for="country">User Country</label> <input type="text"
				class="form-control" name="country"
				value="<c:out value='${user.country}' />">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
		</form>
</body>
</html>
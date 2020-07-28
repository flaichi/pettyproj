<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<!-- Content Begin -->
	<div class="container">
		<br />
		<br />
		<a href="<%=request.getContextPath()%>/userform" class="btn btn-success">Create New User</a><br/><br/>
		<a href="<%=request.getContextPath()%>/userform" class="btn btn-success">logout</a><br/><br/>
		<h3>Users List</h3> <br/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Country</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${listUser}">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.name}"></c:out></td>
						<td><c:out value="${user.email}"></c:out></td>
						<td><c:out value="${user.country}"></c:out></td>
						<td>
							<a href="edituser?id=<c:out value="${user.id}"/>" class="btn btn-secondary">Edit</a>
							 <a href="deleteuser?id=<c:out value='${user.id}' />"  class="btn btn-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Content End -->

	<!-- Footer Begin -->
	<!-- Footer End -->

	<!-- JS, Popper.js, and jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Admin Access</title>
</head>
<body>

	<% 
		if(session.getAttribute("username")== null) 
		{
			response.sendRedirect("login.jsp");
		}
		else
		{ 
			
			response.sendRedirect("UserServlet");
		}
	%>
		<h2> login success </h2>


</body>
</html>
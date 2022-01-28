<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
	out.write("Hello World");
%>

<form action="/DistributedFinalProject/MyFirstServlet" method="GET">	

	First Name: <input type="text" name="txtUsername">
				<input type="password" name="txtPassword">
				<input type="submit" value="Submit">


</form>

</body>
</html>
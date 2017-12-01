<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saluto JSP</title>
</head>
<body>
	<h1>Un saluto dalla JSP</h1>
	<p> Ciao
	
	<%
		out.print(" "+request.getAttribute("persona")+" ");
	%>
	!
	</p>

</body>
</html>
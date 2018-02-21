<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="util.*" %>

<%

DBConnectionManager connessione = new DBConnectionManager();        
Connection con = connessione.connect();

Statement st = null;
ResultSet rs = null;

try {            
st = con.createStatement();
rs = st.executeQuery("SELECT * FROM autore");


String nome="";
String cognome="";
String cf="";

out.println("<h1>ELENCO ANAGRAFICA:</h1>");

while(rs.next()){
cf = rs.getString(1);
nome = rs.getString(2);
cognome = rs.getString(3);   
out.println("<br><br><b>ID:</b> "+ cf + " <br> " + "<b>NOME:</b> "+nome+" <br> " + "<b>COGNOME:</b> "+cognome+" <br> ");
}    

connessione.freeResources(con, st, rs);

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

%>

<h1>Saluta un amico</h1>
<form action="HelloWorldWithJSP" method="POST">
		<label for="id_nome">Nome*</label>
		<input type="text" id="id_nome" name="nome" required>
		<br><br>
		<label for="id_cognome">Cognome*</label>
		<input type="text" id="id_cognome" name="cognome" required>
		<br><br>
		<input type="submit" value="Vai">
	</form>

</body>
</html>




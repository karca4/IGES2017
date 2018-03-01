<%-- 
    Document   : posizionamentoVolume
    Created on : 1-mar-2018, 10.30.25
    Author     : carmi
--%>

<%@page import="entities.Volume"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List<Volume> volumi = (List)request.getAttribute("volumi");
            for(Volume v : volumi){
        %>
        <p><%= v.getCodice()+" - "+v.getTitolo()+" - "+v.getEdizione()+" - "+v.getDataPubblicazione()+" - "+v.getDurataMaxPrestito()+" - "+v.getLingua()+" - "+v.getDenominazioneEditore()+" - "+v.getCittaEditore() %>
        <%
            }
        %>
    </body>
</html>

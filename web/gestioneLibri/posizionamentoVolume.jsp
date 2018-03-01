<%-- 
    Document   : posizionamentoVolume
    Created on : 1-mar-2018, 10.30.25
    Author     : carmi
--%>

<%@page import="entities.Copia"%>
<%@page import="entities.Volume"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%     
    String nomePagina = "inserimento-libro";
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        <div class="container-fluid" style="padding: 30px; margin-bottom: 200px;">
                <p>Volumi</p>
                <%
                    List<Volume> volumi = (List)request.getAttribute("volumi");
                    for(Volume v : volumi){
                %>
                <a href="<%=application.getContextPath()%>/gestioneLibri/posizionamentoVolumeForm.jsp?volumeId=<%= v.getCodice()%>"><%= v.getCodice()+" - "+v.getTitolo()+" - "+v.getEdizione()+" - "+v.getDataPubblicazione()+" - "+v.getDurataMaxPrestito()+" - "+v.getLingua()+" - "+v.getDenominazioneEditore()+" - "+v.getCittaEditore() %></a><br>
                <%
                    }
                %>
                <p>Copie</p>
                <%
                    List<Copia> copie = (List)request.getAttribute("copie");
                    for(Copia c : copie){
                %>
                <p><%= c.getNumeroRegistrazione()+" - "+c.getNumeroScaffale()+" - "+c.getPosizione()+" - "+c.getCodiceVolume()+" - "+c.isDisponibilita() %>
                <%
                    }
                %>
        </div>
    </body>
</html>

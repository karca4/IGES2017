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
        <div class="container" style="padding: 30px; margin-bottom: 200px;">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <h3 class="mb-3 mx-auto" style="text-align: center">Copie Posizionate</h3>
                    <%
                        List<Copia> copie = (List)request.getAttribute("copie");
                        for(Copia c : copie){
                    %>
                    <p><%="Volume: "+c.getCodiceVolume()+"  Posizione: "+c.getNumeroScaffale()+"-"+c.getPosizione()+"  Disponibilità: "+c.isDisponibilita() %>
                        <a href="<%=application.getContextPath()%>/gestioneLibri/eliminaVolumeForm.jsp?volumeId=<%=c.getCodiceVolume()%>">Elimina</a>
                    </p>
                    <%
                        }
                    %>
                    <h3 class="mb-3 mx-auto" style="text-align: center">Volumi Da Posizionare</h3>
                    <%
                        List<Volume> volumi = (List)request.getAttribute("volumi");
                        for(Volume v : volumi){
                    %>
                    <a href="<%=application.getContextPath()%>/gestioneLibri/posizionamentoVolumeForm.jsp?volumeId=<%= v.getCodice()%>"><%= v.getCodice()+" - "+v.getTitolo()+" - "+v.getEdizione()+" - "+v.getDataPubblicazione()+" - "+v.getDurataMaxPrestito()+" - "+v.getLingua()+" - "+v.getDenominazioneEditore()+" - "+v.getCittaEditore() %></a><br>
                    <%
                        }
                    %>  
                </div>
            </div>
        </div>
    </body>
</html>

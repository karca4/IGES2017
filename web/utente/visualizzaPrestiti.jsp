<%-- 
    Document   : visualizzaPrestiti
    Created on : 2-mar-2018, 15.30.13
    Author     : carmi
--%>

<%@page import="entities.Prestito"%>
<%@page import="entities.Copia"%>
<%@page import="entities.Volume"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%     
    String nomePagina = "visualizza-prestiti";
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        <div class="container" style="padding: 30px; margin-bottom: 200px;">
            <div class="row">
                <div class="col-lg-10 offset-lg-1" style="text-align: center">
                    <h3 class="mb-3 mx-auto" style="text-align: center">I Tuoi Prestiti</h3>
                    <div class="divTableCenter">
                        <table class="table table-striped product-dashboard-table" >
                            <thead>
                                <tr>
                                    <th>ISBN</th>
                                    <th>Titolo</th>
                                    <th>Edizione</th>
                                    <th>Casa Editrice</th>
                                    <th>Città Casa Editrice</th>
                                    <th>Lingua</th>
                                    <th>Data Prestito</th>
                                    <th>Data Restituzione</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Prestito> prestiti = (List)request.getAttribute("prestiti");
                                    for(Prestito p : prestiti){
                                %>
                                <tr>
                                    <th><%=p.getVolume().getCodice()%></th>
                                    <th><%=p.getVolume().getTitolo()%></th>
                                    <th><%=p.getVolume().getEdizione()%></th>
                                    <th><%=p.getVolume().getDenominazioneEditore()%></th>
                                    <th><%=p.getVolume().getCittaEditore()%></th>
                                    <th><%=p.getVolume().getLingua()%></th>
                                    <th><%=p.getDataPrestitoString() %></th>
                                    <th><%=p.getDataRestituzioneString()%></th>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                        <a href="../index.jsp"><button class="btn-primary">Torna alla biblioteca</button></a>
                </div>
            </div>
        </div>
    </body>
</html>

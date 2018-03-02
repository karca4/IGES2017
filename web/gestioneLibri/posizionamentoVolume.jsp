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
                    <table class="table table-responsive table-striped product-dashboard-table">
                        <thead>
                            <tr>
                                <th>ISBN</th>
                                <th>Codice Reg</th>
                                <th>Scaffale</th>
                                <th>Posizione</th>
                                <th>Disponibilità</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Copia> copie = (List)request.getAttribute("copie");
                                for(Copia c : copie){
                            %>
                            <tr>
                                <th><%=c.getCodiceVolume()%></th>
                                <th><%=c.getNumeroRegistrazione()%></th>
                                <th><%=c.getNumeroScaffale()%></th>
                                <th><%=c.getPosizione()%></th>
                                <th><%=c.isDisponibilita() %></th>
                                <th><a href="<%=application.getContextPath()%>/gestioneLibri/eliminaVolumeForm.jsp?volumeId=<%=c.getCodiceVolume()%>">Elimina</a></th>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    
                    <h3 class="mb-3 mx-auto" style="text-align: center">Volumi Da Posizionare</h3>
                    <table class="table table-responsive table-striped product-dashboard-table">
                        <thead>
                            <tr>
                                <th>ISBN</th>
                                <th>Titolo</th>
                                <th>Edizione</th>
                                <th>Data Pubb</th>
                                <th>Durata Prestito</th>
                                <th>Lingua</th>
                                <th>Casa Editrice</th>
                                <th>Città Casa Ed</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Volume> volumi = (List)request.getAttribute("volumi");
                                for(Volume v : volumi){
                            %>
                            <tr>
                                <th><%= v.getCodice()%></th>
                                <th><%= v.getTitolo() %></th>
                                <th><%= v.getEdizione()%></th>
                                <th><%= v.getDataPubblicazione()%></th>
                                <th><%= v.getDurataMaxPrestito()%></th>
                                <th><%= v.getLingua()%></th>
                                <th><%= v.getDenominazioneEditore()%></th>
                                <th><%= v.getCittaEditore()%></th>
                                <th><a href="<%=application.getContextPath()%>/gestioneLibri/posizionamentoVolumeForm.jsp?volumeId=<%=v.getCodice()%>">Posiziona</a></th>
                            </tr>
                            <%
                                }
                            %> 
                        </tbody>
                    </table>
                     
                </div>
            </div>
        </div>
    </body>
</html>

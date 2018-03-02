<%-- 
    Document   : posizionamentoVolumeForm
    Created on : 1-mar-2018, 15.45.29
    Author     : carmi
--%>

<%@page import="entities.Collana"%>
<%@page import="entities.Autore"%>
<%@page import="entities.CasaEditrice"%>
<%@page import="java.util.List"%>
<%     
    String nomePagina = "inserimento-libro";
    String message = (String) request.getAttribute("message");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        <div class="container-fluid" style="padding: 30px; margin-bottom: 200px;">
                <div class="row text-center" >               
                     <div class="bg-light col-md-4 col-md-offset-4" id="box">
                      <h2>Posiziona Volume</h2>
                      <%=request.getParameter("volumeId")%>
                            <hr>
                                <form class="form-horizontal" action="position" method="post" id="contact_form">
                                    <fieldset>  
                                        <input name="scelta" value="posiziona" hidden type="text">
                                        <input name="volumeId" value="<%=request.getParameter("volumeId")%>" hidden type="text">
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="numRegistrazione" required placeholder="Numero registrazione*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="numScaffale" required placeholder="Nome scaffale*" class="form-control" type="text" size="3">
                                                </div>
                                            </div>
                                        </div>    
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="posizione" required placeholder="Posizione*" class="form-control" type="number" min="0">
                                                </div>
                                            </div>
                                        </div> 
                                        <%
                                        if(message!=null) {
                                        %>
                                            <div class="alert alert-danger">
                                                <strong>Errore!</strong> <%= message %>
                                            </div>
                                        <%
                                        }
                                        %>
                                       
                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <button type="submit" class="btn btn-md btn-primary" style="margin-bottom: 15px !important;">Posiziona</button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                    </div> 
            </div>
        </div>


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>



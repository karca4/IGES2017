<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Prestito"%>
<% String nomePagina = "Home Prestiti";
    List<Prestito> prestiti = new ArrayList<Prestito>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>

            <section class="user-profile section" style="margin-top: 170px; margin-bottom: 190px;">
            <div class="container">
                <div class="row">
                    
                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">

                        <div class="sidebar">

                            <div class="widget user-dashboard-menu bg-light" style="padding: 23px;">
                                <ul>
                                    <li>
                                        <a class="mod" href=""><i class="fa fa-file-archive-o"></i> Prestiti Utente </a>
                                    </li>
                                    <li>
                                        <a class="mod" href=""><i class="fa fa-pencil"></i> Effettua Prestito </a>
                                    </li>

                                    <li>
                                        <a class="mod" href=""><i class="fa fa-trash"></i> Prestiti Da Restituire </a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    
                    <div class="col-md-9" style="margin-top: -90px;">
                        <form id="libro-form" action="<%=application.getContextPath()%>/gestionePrestiti/cercaPrestiti" method="GET" style="display: block;">
                            <div id="custom-search-input" align="center">
                                <div class="input-group col-md-4">
                                    <input type="number" required min="0" name="searchKey" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="form-control" placeholder="Tessera" />
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary btn-lg" type="submit">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </form>
                        
                    <%
                                
                        String message = (String) request.getAttribute("message");
                        if (!message.equals("correct")) {

                    %>
                    
                        <h3 class="widget-header" style="margin-left: 300px; margin-bottom: 100px; margin-top: 40px;"><%=message%></h3>
                    <%
                        } else {
                        
                            if(request.getAttribute("prestiti") != null){
                                prestiti = (ArrayList<Prestito>) request.getAttribute("prestiti");
                            }
                            

                        }
                    %>
                    
                    <table class="table table-responsive table-striped product-dashboard-table">
                                <thead>
                                    <tr>
                                       <th>Copertina</th>
                                        <th style="padding-left: 40px;">Descrizione</th>                                        
                                        <th style="text-align: center;">Azioni</th>
                                    </tr>
                                </thead>
                                <tbody>                                   
                                    
                                </tbody>
                            </table>
                    
                    
                    </div>
                    
                    
                    
                 </div>                   
             </div>                      
            </section>

    <%@include file="footer.jsp" %>
        
 </body>
</html>
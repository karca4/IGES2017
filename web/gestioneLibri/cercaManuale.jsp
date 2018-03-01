<%@page import="entities.Manuale"%>
<%@page import="entities.Autore"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Libro"%>
<% String nomePagina = "Risultati Ricerca";
    List<Manuale> manuali = new ArrayList<Manuale>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>
    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        
        <%@include file="../skeleton-pages/searchbarRicerca.jsp" %>
        
        <section class="dashboard section">
            <div class="container">
                <div class="row">
                    
                    <div>

                            <br>
                            <h3 class="widget-header"></h3>

                            <%
                                
                                String message = (String) request.getAttribute("message");
                                if (!message.equals("correct")) {

                            %>
                                <h3 class="widget-header" style="margin-left: 300px; margin-bottom: 100px; margin-top: 40px;"><%=message%></h3>
                            <%
                            } else {
                                
                                manuali = (ArrayList<Manuale>) request.getAttribute("manuali");
                            %>
                                
                            <table class="table table-responsive table-striped product-dashboard-table">
                                <thead>
                                    <tr>
                                        <%
                                            if ( (request.getSession().getAttribute("bibliotecario") != null) || (request.getSession().getAttribute("persona") != null) ) {
                                        %>
                                            <th>Copertina</th>
                                            <th style="padding-left: 40px;">Descrizione</th>                                        
                                            <th style="text-align: center;">Azioni</th>
                                        <% }else{
                                            %>
                                            <th>Copertina</th>
                                            <th style="padding-left: 40px;">Descrizione</th>                                        
                                        <% } 
                                           %>
                                    </tr>
                                </thead>
                                <tbody>                                   
                                    <%
                                        int pageIndex = 0;
                                        
                                        for (Manuale man : manuali) {
                                            List<Autore> autori = man.getAutori();
                                    %>


                                    <tr>
                                        <td align="center" valign="middle" class="product-thumb" style="position: relative; top: 30px; left: 10px;">


                                            <img style="width:50px; height:auto" src="images/defaultBook.png" alt="images/defaultBook.png" id="book-img"></td>
                                        <td align="center" valign="middle" class="product-details">
                                            <h3 class="title"> <%= man.getTitolo()%> </h3>
                                            <span class="location"><strong>ISBN:</strong> <%= man.getCodice()%></span>
                                            <br/>
                                            
                                            <%
                                                if(!man.getAutori().isEmpty()){
                                                int i = 0;
                                                for (Autore aut : autori) {
                                                    if (i == 0) {
                                            %>
                                                <span class="add-id"><strong>Autori:</strong>

                                                <%
                                                } else {
                                                %>
                                                    <span class="add-id"><strong style="color:rgba(0, 0, 0, 0);"></strong>
                                                    <%
                                                        }
                                                        if(i>0){
                                                    %>
                                                    <%= " - " + aut.getNome() + " " + aut.getCognome()%>
                                                </span>
                                                <%  }else{
                                                %>
                                                    <%=aut.getNome() + " " + aut.getCognome()%>
  
                                                <%
                                                    }
                                                        i++;
                                                    }

                                                %>
                                                <br/>
                                                <%

                                                    }  
                                                %>
                                                
                                                <span><strong>Editore: </strong><%= man.getDenominazioneEditore()%></span>
                                                </br>
                                                <%
                                                    if(!man.getCopie().isEmpty()){
                                                %>
                                                    <span><strong>Numero Registrazione: </strong> <%= man.getCopie().get(pageIndex).getNumeroRegistrazione() %></span>
                                                    <br/>
                                                    <span><strong>Scaffale: </strong><%= man.getCopie().get(pageIndex).getNumeroScaffale() %> <strong>Posizione: </strong> <%= man.getCopie().get(pageIndex).getPosizione() %><strong>   Disponibile: </strong><span style="color: red;"><%= man.getCopie().get(pageIndex).disponibile()  %> </span></span>
                                                <%
                                                    }else{
                                                %>
                                                    <span><strong>Attualmente non risultano copie disponibili!</strong></span>
                                                <%
                                                    }
                                                %>
                                                
                                        </td>       
                                        <%
                                                        if ( (request.getSession().getAttribute("bibliotecario") != null) || (request.getSession().getAttribute("persona") != null) ) {
                                                    %>
                                        <td align="center" valign="middle" class="action" data-title="Action" style="position: relative; top: 30px; right: 10px;">
                                            <div class="">
                                                <form action="" method="GET">
                                                    <input type="hidden" name="isbn" value="<%= man.getCodice()%>">
                                                    <%}
                                                        %>
                                                    <%
                                                        if (request.getSession().getAttribute("bibliotecario") != null){
                                                    %>
                                                        <button class="btn btn-main">Modifica</button>
                                                    <%
                                                        }else if (request.getSession().getAttribute("persona") != null){
                                                    %>
                                                        <button class="btn btn-main">Prenota</button>
                                                    <%
                                                        }
                                                    %>
                                                      
                                                </form>
                                            </div>
                                        </td>
                                    </tr>                                   
                                    <%
                                            pageIndex++;
                                        }
                                    %>
                                </tbody>
                            </table>
     
                            
                            
                            <%
                                }
                            %>

                                                        
                        </div>

                    </div>
                    
                    
                </div>
            
        </section>
        
        
        
        <%@include file="../skeleton-pages/footer.jsp" %>
        
    </body>
</html>
                        
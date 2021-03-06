<%@page import="entities.Autore"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Libro"%>
<% String nomePagina = "Risultati Ricerca";
    List<Libro> libri = new ArrayList<Libro>();
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
                            } else{
                                libri = (ArrayList<Libro>) request.getAttribute("libri");
                            %>
                                
                            <table class="table table-responsive table-striped product-dashboard-table tableU">
                                <thead>
                                    <tr>
                                        <%
                                            if (request.getSession().getAttribute("bibliotecario") != null)  {
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
                                        for (Libro book : libri) {
                                            List<Autore> autori = book.getAutori();
                                    %>


                                    <tr>
                                        <td align="center" valign="middle" class="product-thumb" style="position: relative; top: 30px; left: 10px;">


                                            <img style="width:50px; height:auto" src="images/defaultBook.png" alt="images/defaultBook.png" id="book-img"></td>
                                        <td align="center" valign="middle" class="product-details">
                                            <h3 class="title"> <%= book.getTitolo()%> </h3>
                                            <span class="location"><strong>ISBN:</strong> <%= book.getCodice()%></span>
                                            <br/>



                                            <%
                                                if(!book.getAutori().isEmpty()){
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
                                                
                                                <span><strong>Editore: </strong><%= book.getDenominazioneEditore()%></span>
                                                </br>
                                                <%
                                                    if(!book.getCopie().isEmpty()){
                                                %>
                                                    <span><strong>Numero Registrazione: </strong> <%= book.getCopie().get(pageIndex).getNumeroRegistrazione() %></span>
                                                    <br/>
                                                    <span><strong>Scaffale: </strong><%= book.getCopie().get(pageIndex).getNumeroScaffale() %> <strong>Posizione: </strong> <%= book.getCopie().get(pageIndex).getPosizione() %><strong>   Disponibile: </strong><span style="color: red;"><%= book.getCopie().get(pageIndex).disponibile()  %> </span></span>
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
                                                    <input type="hidden" name="isbn" value="<%= book.getCodice()%>">
                                                    <%}
                                                        %>
                                                    <%
                                                        if (request.getSession().getAttribute("bibliotecario") != null){
                                                    %>
                                                        <button class="btn btn-main">Modifica</button>
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
                        
<%@page import="entities.Periodico"%>
<%@page import="entities.Manuale"%>
<%@page import="entities.Autore"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Libro"%>
<% String nomePagina = "Risultati Ricerca";
    List<Periodico> periodici = new ArrayList<Periodico>();
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
                                
                                periodici = (ArrayList<Periodico>) request.getAttribute("periodici");
                            %>
                            
                            <h3 class="widget-header" style="margin-left: 270px; margin-top: 10px;">Si ricorda che i periodici sono di sola consultazione.</h3>
                            <table class="table table-responsive table-striped product-dashboard-table">
                                <thead>
                                    <tr>
                                        <th style="padding-left: 30px;">Copertina</th>
                                        <th style="padding-left: 40px;">Descrizione</th>                                        
                                    </tr>
                                </thead>
                                <tbody>                                   
                                    <%
                                        int pageIndex = 0;
                                        
                                        for (Periodico periodico : periodici) {
                                            List<Autore> autori = periodico.getAutori();
                                    %>


                                    <tr>
                                        <td align="center" valign="middle" class="product-thumb" style="position: relative; top: 30px; left: 10px;">


                                            <img style="width:50px; height:auto" src="images/defaultBook.png" alt="images/defaultBook.png" id="book-img"></td>
                                        <td align="center" valign="middle" class="product-details">
                                            <h3 class="title"> <%= periodico.getTitolo()%> </h3>
                                            <span class="location"><strong>ISBN:</strong> <%= periodico.getCodice()%></span>
                                            <br/>
                                            
                                            <%
                                                if(!periodico.getAutori().isEmpty()){
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
                                                
                                                <span><strong>Editore: </strong><%= periodico.getDenominazioneEditore()%></span>
                                                </br>
                                                <%
                                                    if(!periodico.getCopie().isEmpty()){
                                                %>
                                                    <span><strong>Numero Registrazione: </strong> <%= periodico.getCopie().get(pageIndex).getNumeroRegistrazione() %></span>
                                                    <br/>
                                                    <span><strong>Scaffale: </strong><%= periodico.getCopie().get(pageIndex).getNumeroScaffale() %> <strong>Posizione: </strong> <%= periodico.getCopie().get(pageIndex).getPosizione() %><strong>   Disponibile: </strong><span style="color: red;"><%= periodico.getCopie().get(pageIndex).disponibile()  %> </span></span>
                                                <%
                                                    }else{
                                                %>
                                                    <span><strong>Attualmente non risultano copie disponibili!</strong></span>
                                                <%
                                                    }
                                                %>
                                                
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
                        
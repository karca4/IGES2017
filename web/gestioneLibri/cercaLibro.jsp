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
        
        <section class="dashboard section">
            <div class="container">
                <div class="row">
                    
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">                            
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>

                            <%
                                
                                String message = (String) request.getAttribute("message");
                                if (!message.equals("correct")) {

                            %>
                                <h3 class="widget-header"><%=message%></h3>
                            <%
                            } else {
                                
                                libri = (ArrayList<Libro>) request.getAttribute("libri");
                            %>

                            <table class="table table-responsive product-dashboard-table">
                                <thead>
                                    <tr>
                                        <th>Copertina</th>
                                        <th>Descrizione</th>                                        
                                        <th class="text-center">Azioni</th>
                                    </tr>
                                </thead>
                                <tbody>                                   
                                    <%
                                        int pageIndex = 0;
                                        for (Libro book : libri) {
                                            List<Autore> autori = book.getAutori();
                                    %>


                                    <tr>
                                        <td class="product-thumb">


                                            <img style="width:150px; height:auto" src="images/defaultBook.png" alt="images/defaultBook.png" id="book-img"></td>
                                        <td class="product-details">
                                            <h3 class="title"><%= book.getTitolo()%> </h3>
                                            <span class="location"><strong>ISBN:</strong><%= book.getCodice()%></span>



                                            <%
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
                                                    %>
                                                    <%=aut.getNome()%>
                                                </span>
                                                <%
                                                        i++;
                                                    }
                                                %>

                                                <span><strong>Editore: </strong><%= book.getDenominazioneEditore()%></span>                                               

                                        </td>                                       
                                        <td class="action" data-title="Action">
                                            <div class="">
                                                <form action="" method="GET">
                                                    <input type="hidden" name="isbn" value="<%= book.getCodice()%>">
                                                    
                                                    <button class="btn btn-main">Dettagli</button>
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

                            <div class="row">
                                <div class="col-3">
                                   

                                    <a class="btn-lg btn-transparent" href="cercaLibro?searchKey=<%=request.getParameter("searchKey")%>&criterio=<%=request.getParameter("criterio")%>">Precedente</a>

                                   
                                </div>
                                <div class="col"></div>
                                <div class="col-3">
                                    
                                    <% if(libri.size() >= 3) { %>
                                        <a class="btn-lg btn-transparent" href="cercaLibro?searchKey=<%=request.getParameter("searchKey")%>&criterio=<%=request.getParameter("criterio")%>">Successiva</a>
                                    <% } %>
                                </div>
                            </div>                            
                        </div>

                    </div>
                    
                    
                </div>
            </div>
        </section>
                        
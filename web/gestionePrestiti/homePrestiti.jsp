<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Prestito"%>
<% String nomePagina = "Home Prestiti";
    List<Prestito> prestiti = new ArrayList<Prestito>();
    List<Prestito> allPrestiti = new ArrayList<Prestito>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>
    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        
        <%
            if(request.getAttribute("messageA") == "err"){
            %>
                <div class="container" style="background-color: #ff6347; text-align: center;" id="modifica">
                    <h5 style="color: white;"> Errore durante la restituzione, riprova!</h5>
                </div>
            <%
                }else if(request.getAttribute("messageA") != null){
                %>
                <div class="container" style="background-color: #537fcd; text-align: center;" id="modifica">
                    <h5 style="color: white;"> Restituzione effettuata correttamente!</h5>
                </div>
              <%
                }
                %>

            <section class="user-profile section" style="margin-top: 170px; margin-bottom: 190px;">
            <div class="container">
                <div class="row myr">
                    
                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">

                        <div class="sidebar">

                            <div class="widget user-dashboard-menu bg-light" style="padding: 23px;">
                                <ul>
                                    <li>
                                        <a class="mod" href="../gestionePrestiti/homePrestiti.jsp"><i class="fa fa-child"></i> Prestiti Utente </a>
                                    </li>
                                    <li>
                                        <a class="mod" href="../gestionePrestiti/effettuaPrestito.jsp"><i class="fa fa-pencil"></i> Effettua Prestito </a>
                                    </li>

                                    <li>
                                        <a class="mod" href="<%=application.getContextPath()%>/gestionePrestiti/prestitiInRitardo"><i style="margin-left: -3px;" class="fa fa-list-alt"></i> Visualizza Prestiti </a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
   
                    <div id="ppp" class="col-md-9" style="margin-top: -90px;">
                        
                        <%
                            if(request.getAttribute("messageAll") == null){
                            %>
                        
                        <form id="ppp2" action="<%=application.getContextPath()%>/gestionePrestiti/cercaPrestiti" method="GET">
                            <div id="a" align="center">
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
                            }
                            %>       
                        
                    <%
             
                        if(request.getAttribute("message") != null){
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

                        }else if(request.getAttribute("messageAll") != null){
                            String messageAll = (String) request.getAttribute("messageAll");
                            if (!messageAll.equals("correct")) {
                        
                    %>
                    
                        <h3 class="widget-header" style="margin-left: 300px; margin-bottom: 100px; margin-top: 40px;"><%=messageAll%></h3>
                        
                        <%
                            } else {

                                if(request.getAttribute("allPrestiti") != null){
                                    
                                    allPrestiti = (ArrayList<Prestito>) request.getAttribute("allPrestiti");

                                    }
                                }
                            }
                            %>

                            <%
                                if(request.getAttribute("allPrestiti") != null){
                                %>
                    
                                <table id="ppp3" class="table table-responsive table-striped tableU tablePrestiti" style="  overflow-x: hidden; margin-bottom: 54px;">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">Dettaglio Dei Prestiti</th>
                                            <th style="text-align: center;">Azione</th>
                                        </tr>
                                    <%
                                        }else if(request.getAttribute("prestiti") != null){
                                        %>
                                        <table id="ppp3" class="table table-responsive table-striped tableU tablePrestiti" style="  overflow-x: hidden;">
                                            <thead>
                                            <tr>
                                                <th style="text-align: center;">Dettaglio Dei Prestiti</th>   
                                            </tr>               
                                        <%
                                           }else{
                                            %>
                                            <table id="p" class="table table-responsive table-striped tableU tablePrestiti" style="overflow-x: hidden; margin-bottom: 4px;">
                                            <thead>
                                                  
                                            <%
                                           }
                                            %>
                                </thead>
                                <tbody> 
                                    
                                    
                                    <%
                                        if(request.getAttribute("prestiti") != null){
                                            
                                            for (Prestito prestito : prestiti) {                                                                                   

                                    %>
                                    <tr>
                                        <td align="center" valign="middle" class="product-details">
                                            <h5 class="title"> <%= prestito.getVolume().getTitolo()%> </h5>
                                            <span class="location"><strong>Data Prestito:</strong> <%= prestito.getDataPrestitoString() %></span>
                                            <br/>
                                            <span class="location"><strong>Data Restituzione:</strong> <%= prestito.getDataRestituzioneString() %></span>
                                            <br/>
                                            <span class="location"><strong>Stato:</strong> <span style="color: red;"> <%= prestito.getStato() %></span></span>

                                    </tr>
                                    <%
                                           
                                            }
                                        }else if(request.getAttribute("allPrestiti") != null){

                                            for (Prestito prestito : allPrestiti) {
                                    %>
                                        <tr>
                                            <td align="center" valign="middle" class="product-details">
                                            <h5 class="title"> <%= prestito.getVolume().getTitolo()%> </h5>
                                            <span class="location"><strong>Registrazione Copia:</strong> <%= prestito.getNumRegCopia() %></span>
                                            <br/>
                                            <span class="location"><strong>Data Prestito:</strong> <%= prestito.getDataPrestitoString() %></span>
                                            <br/>
                                            <span class="location"><strong>Data Restituzione:</strong> <%= prestito.getDataRestituzioneString() %></span>
                                            <br/>
                                            <span class="location"><strong>Stato:</strong> <%= prestito.getStato() %></span>
                                            <br/>
                                            <span class="location"><strong>In prestito a:</strong> <span style="color: red;"> <%= prestito.getUtente().getNome() + " " + prestito.getUtente().getCognome() %></span></span>
                                            </td>
                                            
                                            <td class="action" data-title="Action" style="position: relative; top: 30px; right: -3px;">
                                                <div class="">
                                                    <form action="<%=application.getContextPath()%>/gestionePrestiti/restituisciVolume" method="GET">
                                                        <input type="hidden" name="numReg" value="<%= prestito.getNumRegCopia() %>">
                                                        <button class="btn btn-main" style="width: 90px;" type="submit">Restituisci</button>
                                                    </form>
                                                </div>
                                            </td>
                                            
                                            
                                        </tr>
                                    <%
                                            }
                                        }
                                        %>
                                    
                                </tbody>
                            </table>
                    
                    
                    </div>
                    
                    
                    
                 </div>                   
             </div>                      
            </section>

    <%@include file="../skeleton-pages/footer.jsp" %>
        
 </body>
</html>

<script>
             
    $(document).ready(setTimeout(function () {
       $("#modifica").hide();
   }, 3000));

</script>
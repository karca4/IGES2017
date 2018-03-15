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
            if(request.getAttribute("messageIns") == "err"){
            %>
                <div class="container" style="background-color: #ff6347; text-align: center;" id="modifica">
                    <h5 style="color: white;"> Errore inserimento dati!</h5>
                </div>
            <%
                }else if(request.getAttribute("messageIns") != null){
                %>
                <div class="container" style="background-color: #537fcd; text-align: center;" id="modifica">
                    <h5 style="color: white;"> Prestito effettuato correttamente!</h5>
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
                             
                    <div id="ppp" class="col-md-9" style="margin-top: -140px;">                               
                                <div class="bg-light col-md-4 col-md-offset-4" id="box">
                                    <h2>Effettua Prestito</h2>
                                          <hr>
                                              <form class="form-horizontal" action="<%=application.getContextPath()%>/gestionePrestiti/inserimentoNuovoPrestito" method="post" id="contact_form">
                                                  <fieldset> 
                                                      <!-- Text input-->
                                                      <div class="form-group">
                                                          <div class="col-md-12">
                                                              <div class="input-group">
                                                                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                                  <input name="numRegCopia" required placeholder="Numero registrazione copia" class="form-control" type="text">
                                                              </div>
                                                          </div>
                                                      </div>
                                                      <!-- Text input-->
                                                      <div class="form-group">
                                                          <div class="col-md-12">
                                                              <div class="input-group">
                                                                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                                  <input name="scaffale" required placeholder="Codice Scaffale" class="form-control" type="number" min="1" max="999">
                                                              </div>
                                                          </div>
                                                      </div>
                                                      <!-- Text input-->
                                                      <div class="form-group">
                                                          <div class="col-md-12">
                                                              <div class="input-group">
                                                                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                                  <input name="posizione" required placeholder="Posizione" class="form-control" type="number" min="1">
                                                              </div>
                                                          </div>
                                                      </div>
                                                      <!-- Text input-->
                                                      <div class="form-group">
                                                          <div class="col-md-12">
                                                              <div class="input-group">
                                                                  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                                  <input name="tessera" required placeholder="Numero tessera utente" class="form-control" type="number">
                                                              </div>
                                                          </div>
                                                      </div>
                                                      
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <button type="submit" class="btn btn-md btn-primary" style="margin-bottom: 15px !important;">Inserisci</button>
                                                </div>
                                            </div>

                                        </fieldset>
                                    </form>
                                </div>

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
           
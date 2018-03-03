<%@page import="entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String nomePagina = "Home";%> <!-- Parametro che viene passato alla head che definisce il titolo. In questo caso sarà: Book a Book | Home -->
    <%@include file="head.jsp" %> <!-- Includere sempre al posto della head -->
    <body class="body-wrapper">

        <%@include file="../skeleton-pages/header.jsp" %>

        
        <!--===============================
        =            Searchbar            =
        ================================-->

        <%@include file="../skeleton-pages/searchbar.jsp" %>
        
        <!--===============================
        =            Sidebar persona o bibliotecario            =
        ================================-->                                    
           <!-- <section class="user-profile section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
                
                            <div class="sidebar">
                                
                                <div class="widget user-dashboard-menu">
                                    <ul>
                                        
                                          
                                         
                                    </ul>
                                </div>
                            </div>
                        </div>        
                     </div>                   
                 </div>                      
            </section>
            -->
                                     
        
        <!--===============================
        =            Guida alla prenotazione            =
        ================================-->
        
        <!-- Icons Grid -->
    <section class="features-icons bg-light text-center">
      <div class="container">
        <div class="row">
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-magnifier m-auto text-primary"></i>
              </div>
              <h3>1. Cerca</h3>
              <p class="lead mb-0">Cerca un libro tra quelli disponibili nella biblioteca!</p>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-book-open m-auto text-primary"></i>
              </div>
              <h3>2. Richiedi il prestito</h3>
              <p class="lead mb-0">Richiedi il prestito del tuo libro online in pochi passi!</p>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="features-icons-item mx-auto mb-0 mb-lg-3">
              <div class="features-icons-icon d-flex">
                <i class="icon-bag m-auto text-primary"></i>
              </div>
              <h3>3. Ritira</h3>
              <p class="lead mb-0">Recati in biblioteca e ritira il tuo libro!</p>
            </div>
          </div>
        </div>
      </div>
    </section>
        
        <!--===============================
        =           Ricerca Da Home           =
        ================================-->
        
        <%@include file="ricercaDaHome.jsp" %>

        <!--===============================
        =            Footer Area            =
        ================================-->
        
        <%@include file="footer.jsp" %>

    </body>
</html>

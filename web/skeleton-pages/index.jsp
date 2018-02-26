<%@page import="entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String nomePagina = "Home";%> <!-- Parametro che viene passato alla head che definisce il titolo. In questo caso sarà: Book a Book | Home -->
    <%@include file="head.jsp" %> <!-- Includere sempre al posto della head -->
    <body class="body-wrapper">

        <%@include file="../skeleton-pages/header.jsp" %>

        
        <!--===============================
        =            Hero Area            =
        ================================-->

        <!-- Masthead -->
        <header class="masthead text-white text-center">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-xl-9 mx-auto">
                <h1 class="mb-5">Scegli il tuo libro preferito!</h1>
              </div>
              <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                <form>
                  
                  <div class="form-row">
                      <div class="col-12 col-md-2 mb-2 mb-md-0">
                            <select id="selected" class="form-control form-control-lg" name="criterio">
                                <option id="idTitolo" value="titolo">Titolo</option>
                                <option id="idAutore" value="autore">Autore</option>
                                <option id="idEditore" value="editore">Editore</option>
                                <option id="idIsbn" value="isbn">ISBN</option>
                                <option id="idTipo" value="tipo">Tipo</option>

                            </select>
                        </div>
                    <div class="col-12 col-md-7 mb-2 mb-md-0">
                      <input type="email" class="form-control form-control-lg" placeholder="Cerca un libro...">
                    </div>
                    <div class="col-12 col-md-3">
                      <button type="submit" class="btn btn-block btn-lg btn-primary">Cerca!</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </header>
        
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
        =            Footer Area            =
        ================================-->
        
        
        
        <%@include file="footer.jsp" %>

        <script>


        </script>
    </body>
</html>

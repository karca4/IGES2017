<%@page import="entities.Utente"%>
<!-- Navigation -->
    <nav class="navbar navbar-light bg-light static-top">
      <div class="container">
          <!-- NOSTRO LOGO -->
        <a class="navbar-brand" href="../skeleton-pages/index.jsp">
                        <img src="../template/img/logo-with-name.png" alt="" height="38">
                    </a>
          
          <%  if ( (request.getSession().getAttribute("persona") == null ) &&  ( request.getSession().getAttribute("bibliotecario") == null )) {
                                    %>
                                    
                <a class="btn btn-primary" href="../autenticazione/login.jsp">Accedi</a>
          
             
            <%   }else if ( (request.getSession().getAttribute("persona") != null )){
                    %>        
            
                    <li class="nav-link pers"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link pers1" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=((Utente) request.getSession().getAttribute("persona")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="#">Visualizza Prenotazioni</a>
                                    <a class="dropdown-item" href="#">Visualizza Autori</a>
                                     <a class="dropdown-item" href="<%=application.getContextPath()%>/autenticazione/logout">Logout</a>
                                </div>
                            </li>
             
            <%   }else{
             %>   
            
                    <li class="nav-link pers"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link pers1" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=((Utente) request.getSession().getAttribute("bibliotecario")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/gestione/inserimentoLibro">Carica Libro</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/gestione/inserimentoManuale">Carica Manuale</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/gestione/inserimentoPeriodico">Carica Periodico</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/gestione/posizionamento">Posiziona Volume</a>
                                    <a class="dropdown-item" href="#">Visualizza Prenotazioni</a>
                                    <a class="dropdown-item" href="#">Visualizza Collocazione</a>
                                   <a class="dropdown-item" href="<%=application.getContextPath()%>/autenticazione/logout">Logout</a>
                                </div>
                            </li>
                            
            <%      }
                            %>
             
      </div>
    </nav>
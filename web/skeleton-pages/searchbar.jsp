<%-- 
    Document   : searchbar
    Created on : 27-feb-2018, 8.23.22
    Author     : umbertopicariello
--%>

<!-- Masthead -->
        <header class="masthead text-white text-center">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-xl-9 mx-auto">
                 <% if (request.getSession().getAttribute("bibliotecario") != null) { %>
                    <h1 class="mb-5">Cerca un libro nella tua biblioteca!</h1>
                 <% } else { %>
                    <h1 class="mb-5">Scegli il tuo libro preferito!</h1>
                 <% }%>
              </div>
              <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                <form action="<%=application.getContextPath()%>/gestioneLibri/cercaLibro" id="search-form-main" method="GET">
                  
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
                      <input type="text" minlength="2" class="form-control form-control-lg" id="search-main" name="searchKey" required placeholder="Cerca un libro...">
                    </div>
                    <div class="col-12 col-md-3">
                      <button type="submit" class="btn btn-block btn-lg btn-primary" onclick="controlloSearchLibro()">Cerca!</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </header>
                
        <!--<div>
            <p id="erroreSearchLibro"></p>
            </br>
        </div>-->        

<script>
    
    function controlloSearchLibro() {
        var boolean = true;
        var titolo = document.getElementById("idTitolo");
        var autore = document.getElementById("idAutore");
        var editore = document.getElementById("idEditore");
        var isbn = document.getElementById("idIsbn");
        var tipo = document.getElementById("idTipo");
        var search = document.getElementById("search");
        var regexLettere = /^[A-Za-z ]{1,30}$/;
        var regexNumeri = /^[0-9 ]{13}$/;
        document.getElementById("erroreSearchLibro").innerHTML = "";
        
        if (search.value == "") {
            $("#search").focus();
            $("#erroreSearchLibro").text("Il campo non puo' essere vuoto!");
            boolean = false;
        } else if (autore.selected && search.value != "") {
            if (!search.value.match(regexLettere)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo deve contenere solo lettere!");
                boolean = false;
            }
        } else if (editore.selected && search.value != "") {
            if (!search.value.match(regexLettere)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo puo' contenere solo lettere!");
                boolean = false;
            }
        } else if (isbn.selected && search.value != "") {
            if (!search.value.match(regexNumeri)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Inserisci l'isbn correttamente!");
                boolean = false;
            }
        }
        if (boolean == true) {
            document.cercaLibro.submit();
        }
        
    }
    
</script>

<header class="masthead2 text-white text-center" style="height: 400px !important;">

    <div class="container ci">
        <div class="col-xl-9 mx-auto" style="margin-top: -110px;">
                 <% if (request.getSession().getAttribute("bibliotecario") != null) { %>
                    <h1 class="mb-5" style="position: relative; top: -40px;">Cerca un volume nella tua biblioteca!</h1>
                 <% } else { %>
                    <h1 class="mb-5" style="position: relative; top: -40px;">Cerca il tuo volume!</h1>
                 <% }%>
              </div>
              <div class="row" style="margin: auto; max-width: 1000px; padding-left: 300px;">
            
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login"  style="-webkit-box-shadow: none; -moz-box-shadow: none; box-shadow: none;">
					<div class="panel-heading text-center" style="opacity: 0.8;">
						<div class="row" >
							<div class="col-md-4">
								<a href="#" class="active" id="libro-form-link">Libro</a>
							</div>
							<div class="col-md-4">
								<a href="#" id="manuale-form-link">Manuale</a>
							</div>
                                                        <div class="col-md-4">
								<a href="#" id="periodico-form-link">Periodico</a>
                                                        </div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="libro-form" action="<%=application.getContextPath()%>/gestioneLibri/cercaLibro" method="GET" style="display: block;">
									<div class="form-group" style="opacity: 0.8;">
                                                                        <select id="selected" class="form-control" name="criterio">
                                                                          <option id="idTitolo" value="titolo">Titolo</option>
                                                                            <option id="idAutore" value="autore">Autore</option>
                                                                            <option id="idEditore" value="editore">Editore</option>
                                                                            <option id="idIsbn" value="isbn">ISBN</option>
                                                                            <option id="idTipo" value="tipo">Tipo</option>
                                                                        </select>
                                                                      </div>
                                                                        <div class="form-group" style="opacity: 0.8;">
                                                                            <input type="text" required minlength="2" id="search-main" name="searchKey" tabindex="1" class="form-control" placeholder="Cerca un libro...">
									</div>
									<div class="form-group" >
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3" style="margin: auto; max-width: 800px; opacity: 1.0;">
                                                                                            <button type="submit" class="btn  btn-lg btn-primary" onclick="controlloSearchLibro()">Cerca!</button>
											</div>
										</div>
									</div>
									
								</form>
								<form id="manuale-form" action="<%=application.getContextPath()%>/gestioneLibri/cercaLibro" method="GET" style="display: none;">
									<div class="form-group" style="opacity: 0.8;">
                                                                                <input type="hidden" id="critManuale" name="criterioManuale" value=""/>
										<input type="text" required minlength="2" id="search-main" name="searchKey" tabindex="1" class="form-control" placeholder="Cerca un manuale...">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3" style="margin: auto; max-width: 800px;">
												<button type="submit" class="btn  btn-lg btn-primary" onclick="controlloSearchLibro()">Cerca!</button>
											</div>
										</div>
									</div>
								</form>
                                                                <form id="periodico-form" action="<%=application.getContextPath()%>/gestioneLibri/cercaLibro" method="GET" style="display: none;">
									<div class="form-group" style="opacity: 0.8;">
                                                                                <input type="hidden" id="critPeriodico" name="criterioPeriodico" value=""/>
										<input type="text" required minlength="2" id="search-main" name="searchKey" tabindex="1" class="form-control" placeholder="Cerca un periodico...">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3" style="margin: auto; max-width: 800px;">
												<button type="submit" class="btn  btn-lg btn-primary" onclick="controlloSearchLibro()">Cerca!</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>

<script>
    
    $(function() {

    $('#libro-form-link').click(function(e) {
		$("#libro-form").delay(100).fadeIn(100);
 		$("#manuale-form").fadeOut(100);
		$('#manuale-form-link').removeClass('active');
                $("#periodico-form").fadeOut(100);
		$('#periodico-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#manuale-form-link').click(function(e) {
		$("#manuale-form").delay(100).fadeIn(100);
 		$("#libro-form").fadeOut(100);
		$('#libro-form-link').removeClass('active');
                $("#periodico-form").fadeOut(100);
		$('#periodico-form-link').removeClass('active');
		$(this).addClass('active');
                document.getElementById("critManuale").value = "manuale";
		e.preventDefault();
	});
          $('#periodico-form-link').click(function(e) {
		$("#periodico-form").delay(100).fadeIn(100);
 		$("#libro-form").fadeOut(100);
		$('#libro-form-link').removeClass('active');
                $("#manuale-form").fadeOut(100);
		$('#manuale-form-link').removeClass('active');
		$(this).addClass('active');
                document.getElementById("critPeriodico").value = "periodico";
		e.preventDefault();
	});
});
    
</script>

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
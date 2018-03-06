<%-- 
    Document   : inserimentoLibri
    Created on : 26-feb-2018, 18.19.29
    Author     : carmine
--%>
<%@page import="entities.Collana"%>
<%@page import="entities.Autore"%>
<%@page import="entities.CasaEditrice"%>
<%@page import="java.util.List"%>
<%     
    String nomePagina = "inserimento-libro";
    String message = (String) request.getAttribute("message");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        <div class="container-fluid" style="padding: 30px; margin-bottom: 200px;">
                <div class="row text-center" >               
                     <div class="bg-light col-md-4 col-md-offset-4" id="box">
                      <h2>Inserimento Libro</h2>
                            <hr>
                                <form class="form-horizontal" action="insert" method="post" id="contact_form">
                                    <fieldset>  
                                        <input name="scelta" value="libro" hidden type="text">
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="codice" pattern="[0-9]{13}" required placeholder="Codice*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="titolo" required placeholder="Titolo*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="edizione" required placeholder="Edizione*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="dataPub" required placeholder="Data pubblicazione*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="durataMaxPrestito" required placeholder="Durata massima del prestito (in giorni)" class="form-control" type="number" min="1">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="lingua" required placeholder="Lingua*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="genere" required placeholder="Genere*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="tipo" required placeholder="Tipo*" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <hr>
                                        <span class="formSpan">Casa editrice</span>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <select class="form-control" name="casaEditrice">
                                                        <option value="null" selected disabled>Scegli qui</option>
                                                        <%
                                                            List<CasaEditrice> caseEditrici = (List)request.getAttribute("caseEditrici");
                                                            for(CasaEditrice ce : caseEditrici){
                                                        %>
                                                        <option><%=ce.getDenominazione()+" - "+ce.getCitta()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                    </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <button type="button" class="btn-sm btn-secondary" style="width:70%" onclick="alert('Casa Ed')">Nuova casa editrice</button>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <hr>
                                        <span class="formSpan">Autori</span> 
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <select multiple class="form-control" name="autori">
                                                    <option value="null" selected disabled>Scegli qui</option>
                                                        <%
                                                            List<Autore> autori = (List)request.getAttribute("autori");
                                                            for(Autore a : autori){
                                                        %>
                                                        <option><%=a.getNome().charAt(0)+". "+a.getCognome()+" - "+a.getCodFiscale()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select> 
                                                </div>
                                            </div>
                                        </div>
                                        <p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <button type="button" class="btn-sm btn-secondary" style="width:70%" onclick="alert('Autore')">Nuovo autore</button>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <hr>
                                        <span class="formSpan">Collana</span> 
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <select class="form-control" name="collana">
                                                        <option value="null" selected disabled>Scegli qui</option>
                                                        <%
                                                            List<Collana> collane = (List)request.getAttribute("collane");
                                                            for(Collana c : collane){
                                                        %>
                                                        <option><%=c.getNomeCollana()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                    </div>
                                            </div>
                                        </div>
                                        <!-- Text input-->
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="numeroOrdineCollana" placeholder="Numero di ordine nella collana" class="form-control" type="number" min="1">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <button type="button" class="btn-sm btn-secondary" style="width:70%" onclick="alert('Collana')">Nuova collana</button>
                                            </div>
                                        </div>
                                       
                                        <%
                                        if(message!=null) {
                                        %>
                                            <div class="alert alert-danger">
                                                <strong>Errore!</strong> <%= message %>
                                            </div>
                                        <%
                                        }
                                        %>
                                       
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


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>


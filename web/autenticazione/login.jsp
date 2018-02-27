<% String nomePagina = "login";
String message = (String) request.getAttribute("message");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>
        
        <div class="container-fluid" style="padding: 90px; margin-bottom: 200px;">
                <div class="row text-center" >               
                     <div class="center bg-light col-md-offset-4 col-md-4" id="box">
                      <h2>Login</h2>
                            <hr>
                                <form class="form-horizontal" action="login" method="post" id="contact_form">
                                    <fieldset>       
                                        <!-- Text input-->

                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Email" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>
                                       <!-- Text input-->
                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                    <input name="password" required="" placeholder="Password" class="form-control" type="password">
                                                </div>
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
                                                <button type="submit" class="btn btn-md btn-primary pull-right" style="margin-bottom: 15px !important;">Accedi</button>
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

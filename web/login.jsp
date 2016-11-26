<%-- 
    Document   : login
    Created on : 26/11/2016, 02:21:08 PM
    Author     : corre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile support -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Material Design fonts -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Material Design -->
        <link href="dist/css/bootstrap-material-design.css" rel="stylesheet">
        <link href="dist/css/ripples.min.css" rel="stylesheet">

        <!-- Dropdown.js -->
        <link href="//cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.css" rel="stylesheet">

        <!-- Page style -->
        <link href="index.css" rel="stylesheet">

        <!-- jQuery -->
        <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <link rel="stylesheet" href="custom.css">
        <script src="https://use.fontawesome.com/7c2ce1fd9f.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-6">
                        <div class="well bs-component">
                            <form class="form-horizontal" action="./login" method="POST">
                                 <fieldset>
                                    <legend>Inicio de Sesion</legend>
                                    <div class="form-group">
                                        <label for="tipodoc" class="control-label">Seleccione el tipo de documento:</label>
                                        <select name="tipodoc" id="tipodoc" class="form-control">
                                            <option value="CC">Cedula de ciudadania</option>
                                            <option value="TI">Tarjeta de identidad</option>
                                            <option value="RC">Registro civil</option>
                                            <option value="CE">Cedula de extranjeria</option>
                                        </select>
                                    </div>
                                    <div class="form-froup label-floating is-empty">
                                        <label for="numdoc" class="control-label">Numero de documento: </label>
                                        <input class="form-control" type="number" name="numdoc" id="numdoc" min="0" step="1" required="true">
                                    </div>
                                    <div class="form-froup label-floating is-empty">
                                        <label for="pwd" class="control-label">Contraseña: </label>
                                        <input class="form-control" type="password" name="pwd" id="pwd" required="true">
                                    </div>
                                 </fieldset>
                                <br>
                                <button type="submit" class="btn btn-primary" data-dpmaxz-eid="15">Iniciar Sesion</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

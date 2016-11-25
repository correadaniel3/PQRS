<%-- 
    Document   : create
    Created on : 18/10/2016, 08:40:05 PM
    Author     : wondercode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar PQRS</title>
        <meta charset="utf-8">
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
        <br><br>
        <div class="container">
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-md-12">
                        <div class="well bs-component">
                            <form class="form-horizontal" data-dpmaxz-eid="7" action="./createPQRS" method="POST" enctype="multipart/form-data">
                                <fieldset>
                                    <legend>Generar PQRS</legend>
                                    <div class="form-group">
                                        <label for="tipo" class="control-label">Seleccione el tipo:</label>
                                        <select name="tipo" id="tipo" class="form-control">
                                            <option value="Peticion">Peticion</option>
                                            <option value="Queja">Queja</option>
                                            <option value="Reclamo">Reclamo</option>
                                            <option value="Solicitud">Solicitud</option>
                                        </select>
                                    </div>
                                    <br>
                                    <div class="form-froup">
                                        <label for="fecha" class="control-label">Fecha de los hechos: </label>
                                        <script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
                                        <script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
                                        <script>
                                            webshims.setOptions('waitReady', false);
                                            webshims.setOptions('forms-ext', {types: 'date'});
                                            webshims.polyfill('forms forms-ext');
                                        </script>

                                        <input type="date" name="fechaHechos" id="fecha" min="2016-01-02"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="aeropuerto" class="control-label">Seleccione el aeropuerto:</label>
                                        <select name="aeropuerto" id="aeropuerto" class="form-control">
                                           
                                            <c:forEach items="${aeropuertos}" var="aero">
                                                <option value="${aero.codigo}">${aero.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>
                                    <div class="form-froup label-floating is-empty">
                                        <label for="descripcion" class="control-label">Descripcion: </label>
                                        <textarea class="form-control" name="descripcion" id="descripcion" required="true"></textarea>
                                    </div>
                                    <div class="form-group is-empty is-fileinput" style="float:left;">
                                        <label for="inputFile" class="col-md-2 control-label">Archivo</label>

                                        <div class="col-md-10">
                                            <input type="text" readonly="" class="form-control" placeholder="Adjuntar Archivo" data-dpmaxz-eid="13">
                                            <input type="file" id="inputFile" name="file" multiple="">
                                        </div>
                                    </div>
                                </fieldset>
                                <br>
                                <button type="submit" class="btn btn-primary" data-dpmaxz-eid="15">Generar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>





        <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="dist/js/ripples.min.js"></script>
        <script src="dist/js/material.min.js"></script>
        <script src="//fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js"></script>
        <script>
                                            $(function () {
                                                $.material.init();
                                                $(".shor").noUiSlider({
                                                    start: 40,
                                                    connect: "lower",
                                                    range: {
                                                        min: 0,
                                                        max: 100
                                                    }
                                                });

                                                $(".svert").noUiSlider({
                                                    orientation: "vertical",
                                                    start: 40,
                                                    connect: "lower",
                                                    range: {
                                                        min: 0,
                                                        max: 100
                                                    }
                                                });
                                            });
        </script>
    </body>
</html>

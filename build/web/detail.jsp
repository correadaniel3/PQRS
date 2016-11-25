<%-- 
    Document   : detail
    Created on : 18/10/2016, 10:38:16 PM
    Author     : wondercode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle PQRS</title>
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
                            <fieldset>
                                <legend>Detalle</legend>
                                <h3>Codigo PQRS </h3><p>${pqrs.codigo}</p>
                                <h3>Tipo: </h3> <p>${pqrs.tipo}</p>
                                <h3>Fecha de los hechos: </h3> <p>${pqrs.getDate()}</p>
                                <h3>Fecha de la PQRS: </h3> <p>${pqrs.getDateP()}</p>
                                <h3>Aeropuerto: </h3> <p>${aeropuerto}</p>
                                <h3>Descripcion: </h3> <p>${pqrs.descripcion}</p>
                                <h3>Adjunto:</h3> <i class="fa fa-file-pdf-o" aria-hidden="true"></i> anexo1.pdf
                            </fieldset>          
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

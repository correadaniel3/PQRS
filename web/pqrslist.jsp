<%-- 
    Document   : pqrslist
    Created on : 18/10/2016, 05:14:12 PM
    Author     : wondercode
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar PQRS</title>
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
            <div class="col-md-12">
                <div class="bs-component">
                    <div class="list-group">
                        <c:forEach items="${pqrss}" var="pqrs">
                            <a href="./Detail?id=${pqrs.codigo}">
                                <div class="list-group-item">
                                    <div class="row-action-primary">
                                        <i class="material-icons">announcement</i>
                                    </div>
                                    <div class="row-content">
                                        <div class="action-secondary"><i class="material-icons">info</i></div>
                                        <h4 class="list-group-item-heading">${pqrs.tipo}</h4>
                                        <p class="list-group-item-text">${pqrs.descripcion}</p>
                                    </div>
                                </div>
                                <div class="list-group-separator"></div>
                            </a>
                            </c:forEach>                        
                    </div>
                </div>
            </div>
        </div>


        <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="dist/js/ripples.min.js"></script>
        <script src="dist/js/material.min.js"></script>
        <script src="//fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/noUiSlider/6.2.0/jquery.nouislider.min.js"></script>
    </body>
</html>

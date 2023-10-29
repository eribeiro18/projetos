<%-- 
    Document   : index
    Created on : 15 de set. de 2023, 21:50:59
    Author     : evandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Curso JSp</title>

        <style type="text/css">

            form{
                position: absolute;
                top: 40%;
                left: 33%;
                right: 33%;
            }

            h5{
                position: absolute;
                top: 33%;
                left: 33%;
                right: 33%;
            }

            .msg{
                position: absolute;
                top: 10%;
                left: 33%;
                right: 33%;
                font-size: 15px;
                color: #664d03;
                background-color: #fff3cd;
                border-color: #ffecb5;
            }
        </style>

    </head>
    <body>
        <h5>Bem vindo ao custo de JSP</h5>

        <form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
            <input type="hidden" value="<%= request.getParameter("url") %>" name="url">
            <div class="mb-3">

                <label class="form-label">Login</label>
                <input name="login" type="text" class="form-control" required="required">
                <div class="invalid-feedback">Campo obrigatório.</div>
                <div class="valid-feedback">Sucesso!</div>
            </div>
            <div class="mb-3">

                <label class="form-label">Senha</label>
                <input name="senha" type="password"  class="form-control" required="required">
                <div class="invalid-feedback">Campo obrigatório.</div>
                <div class="valid-feedback">Sucesso!</div>
            </div>

            <br><br><br>
            <input type="submit" value="Acessar" class="btn btn-primary">
        </form>
        <div class="alert alert-light msg" role="alert">
            ${msg}
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                        .forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                if (!form.checkValidity()) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }

                                form.classList.add('was-validated')
                            }, false)
                        })
            })()
        </script>
    </body>
</html>



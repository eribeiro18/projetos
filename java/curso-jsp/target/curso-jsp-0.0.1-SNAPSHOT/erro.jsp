<%-- 
    Document   : erro
    Created on : 23 de set. de 2023, 13:27:25
    Author     : evandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela que mostra os erros</title>
    </head>
    <body>
        <h1>Mensagem de Erro, entre em contato com a equipe de suporte do sistema</h1>
        <%
            out.print(request.getAttribute("msg"));
        %>
    </body>
</html>

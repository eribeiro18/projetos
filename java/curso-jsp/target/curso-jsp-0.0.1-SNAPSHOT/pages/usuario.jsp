<%-- 
    Document   : main
    Created on : 16 de set. de 2023, 16:02:53
    Author     : evandro
--%>

<%@page import="model.ModelLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="head.jsp"/>

    <body>
        <!-- Pre-loader start -->
        <jsp:include page="theme-loader.jsp"/>

        <!-- Pre-loader end -->
        <div id="pcoded" class="pcoded">
            <div class="pcoded-overlay-box"></div>
            <div class="pcoded-container navbar-wrapper">
                <jsp:include page="navbar.jsp"/>

                <div class="pcoded-main-container">
                    <div class="pcoded-wrapper">
                        <jsp:include page="navbarmainmenu.jsp"/>
                        <div class="pcoded-content">
                            <!-- Page-header start -->
                            <jsp:include page="page-header.jsp"/>
                            <!-- Page-header end -->
                            <div class="pcoded-inner-content">
                                <!-- Main-body start -->
                                <div class="main-body">
                                    <div class="page-wrapper">
                                        <!-- Page-body start -->
                                        <div class="page-body">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <!-- Basic Form Inputs card start -->
                                                    <div class="card">
                                                        <div class="card-block">
                                                            <h4 class="sub-title">Cadastro de Usuário</h4>
                                                            <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
                                                                <input type="hidden" name="acao" id="acao" value="">
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">ID:</label>
                                                                </div>

                                                                <div class="form-group form-default input-group mb-4">
                                                                    <div class="input-group-prepend">
                                                                        <c:if test="${modelLogin.fotoUser != null && !modelLogin.fotoUser.equals('')}">
                                                                            <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
                                                                                <img alt="Imagem User" id="fotoembase64" src="${modelLogin.fotoUser}" width="70px"/>
                                                                            </a>
                                                                        </c:if>

                                                                        <c:if test="${modelLogin.fotoUser == null || modelLogin.fotoUser.equals('')}">
                                                                            <a>
                                                                                <img alt="Imagem User" id="fotoembase64" src="../assets/images/avatar-blank.jpg" width="70px"/>
                                                                            </a>
                                                                        </c:if>
                                                                    </div>
                                                                    <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px"/>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modelLogin.email}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">E-mail:</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <select class="form-control"
                                                                            aria-label="Default select example" name="perfil" >
                                                                        <option disabled="disabled" >[Selecione o Perfil]</option>

                                                                        <option value="ADMIN" <%

                                                                            ModelLogin modelLogin = (ModelLogin) request.getAttribute("modolLogin");

                                                                            if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");
                                                                            } %> >Admin</option>

                                                                        <option value="SECRETARIA" <%

                                                                            modelLogin = (ModelLogin) request.getAttribute("modolLogin");

                                                                            if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");

                                                                            } %>>Secretária</option>

                                                                        <option value="AUXILIAR" <%
                                                                            modelLogin = (ModelLogin) request.getAttribute("modolLogin");

                                                                            if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");
                                                                            }%>>Auxiliar</option>

                                                                    </select>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Perfil:</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input onblur="pesquisaCep();" type="text" name="cep" id="cep" class="form-control" required="required" autocomplete="off" value="${modolLogin.cep}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Cep</label>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="logradouro" id="logradouro" class="form-control" required="required" autocomplete="off" value="${modolLogin.logradouro}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Logradouro</label>
                                                                </div> 

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="bairro" id="bairro" class="form-control" required="required" autocomplete="off" value="${modolLogin.bairro}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Bairro</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="localidade" id="localidade" class="form-control" required="required" autocomplete="off" value="${modolLogin.localidade}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Localidade</label>
                                                                </div>     

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="uf" id="uf" class="form-control" required="required" autocomplete="off" value="${modolLogin.uf}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Estado</label>
                                                                </div>     

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="numero" id="numero" class="form-control" required="required" autocomplete="off" value="${modolLogin.numero}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Numero</label>
                                                                </div>   
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="login" id="login" class="form-control" required="required" value="${modelLogin.login}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Login:</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modelLogin.senha}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Senha:</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="radio" name="sexo" checked="checked" value="MASCULINO"  

                                                                           <%

                                                                               modelLogin = (ModelLogin) request.getAttribute("modolLogin");

                                                                               if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
                                                                                   out.print(" ");
                                                                                   out.print("checked=\"checked\"");
                                                                                   out.print(" ");
                                                                               }

                                                                           %>
                                                                           >Masculino</>

                                                                    <input type="radio" name="sexo" value="FEMININO" <%                                                                 modelLogin = (ModelLogin) request.getAttribute("modolLogin");

                                                                        if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
                                                                            out.print(" ");
                                                                            out.print("checked=\"checked\"");
                                                                            out.print(" ");
                                                                        }

                                                                           %> >Feminino</>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Nome:</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <textarea class="form-control" name="descricao" id="descricao" required="required">${modelLogin.descricao}</textarea>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Descreva você:</label>
                                                                </div>
                                                                <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                                <button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                                <!--                                                                <button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDelete()">Excluir</button>-->
                                                                <button type="button"  class="btn btn-info waves-effect waves-light" onclick="criarDeleteComAjax();" >Excluir</button>
                                                                <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <span>${msg}</span>
                                <div style="height: 300px; overflow: scroll">
                                    <table class="table" id="tabelaResultadosview">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Nome</th>
                                                <th scope="col">Ver</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${modelLogins}" var="ml">
                                                <tr>
                                                    <td><c:out value="${ml.id}"></c:out></td>
                                                    <td><c:out value="${ml.nome}"></c:out></td>
                                                    <td><a class="btn btn-success" href="<%= request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- Page-body end -->
                        </div>
                        <div id="styleSelector"> </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>    
<!-- Required Jquery -->
<jsp:include page="javascriptfile.jsp"></jsp:include>

<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuário</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
                    </div>
                </div>
                <div style="height: 300px; overflow: scroll">
                    <table class="table" id="tabelaResultados">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Ver</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <span id="totalResultado"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $("#rendamensal").maskMoney({showSymbol: true, symbol: "R$ ", decimal: ",", thousands: "."});

    const formatter = new Intl.NumberFormat('pt-BR', {
        currency: 'BRL',
        minimumFractionDigits: 2
    });

    $("#rendamensal").val(formatter.format($("#rendamensal").val()));

    $("#rendamensal").focus();

    var dataNascimento = $("#dataNascimento").val();

    if (dataNascimento != null && dataNascimento != '') {

        var dateFormat = new Date(dataNascimento);

        $("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));

    }

    $("#nome").focus();


    $(function () {

        $("#dataNascimento").datepicker({
            dateFormat: 'dd/mm/yy',
            dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
            dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
            dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab', 'Dom'],
            monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
            nextText: 'Próximo',
            prevText: 'Anterior'
        });
    });


    $("#numero").keypress(function (event) {
        return /\d/.test(String.fromCharCode(event.keyCode));
    });

    $("#cep").keypress(function (event) {
        return /\d/.test(String.fromCharCode(event.keyCode));
    });




    function pesquisaCep() {
        var cep = $("#cep").val();

        $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

            if (!("erro" in dados)) {
                $("#cep").val(dados.cep);
                $("#logradouro").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#localidade").val(dados.localidade);
                $("#uf").val(dados.uf);
            }


        });
    }

    function visualizarImg(fotoembase64, filefoto) {

        var preview = document.getElementById(fotoembase64); //campo img do html que será mostrado
        var fileUser = document.getElementById(filefoto).files[0];
        var reader = new FileReader();

        reader.onloadend = function () {
            preview.src = reader.result; //carrega a foto na tela
        };
        if (fileUser) {
            reader.readAsDataURL(fileUser); //preview da imagem
        } else {
            preview.src = '';
        }
    }

    function verEditar(id) {
        var urlAction = document.getElementById('formUser').action;
        window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
    }

    function buscarUsuario() {
        var nomeBusca = document.getElementById('nomeBusca').value;
        var urlAction = document.getElementById('formUser').action;
        if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
            $.ajax({

                method: "get",
                url: urlAction,
                data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
                success: function (response) {
                    var json = JSON.parse(response);
                    $('#tabelaResultados > tbody > tr').remove();
                    for (p = 0; p < json.length; p++) {
                        $('#tabelaResultados > tbody').append('<tr><td>' + json[p].id + '</td><td>' + json[p].nome + '</td><td><button type="button" onclick="verEditar(' + json[p].id + ')" class="btn btn-info">Ver</button></td></tr>');
                    }
                    document.getElementById('totalResultado').textContent = 'Resultados: ' + json.length;
                }

            }).fail(function (xhr, status, errorThrown) {
                alert('Erro ao buscar usuario por nome: ' + xhr.responseText);
            });
        }
    }

    function criarDeleteComAjax() {

        if (confirm('Deseja realmente excluir os dados?')) {

            var urlAction = document.getElementById('formUser').action;
            var idUser = document.getElementById('id').value;

            $.ajax({

                method: "get",
                url: urlAction,
                data: "id=" + idUser + '&acao=deletarajax',
                success: function (response) {

                    limparForm();
                    document.getElementById('msg').textContent = response;
                }

            }).fail(function (xhr, status, errorThrown) {
                alert('Erro ao deletar usu�rio por id: ' + xhr.responseText);
            });


        }

    }

    function criarDelete() {
        if (confirm('Deseja realmente excluir os dados?')) {
            document.getElementById("formUser").method = 'get';
            document.getElementById("acao").value = 'deletar';
            document.getElementById("formUser").submit();
        }
    }

    function limparForm() {
        var elementos = document.getElementById("formUser").elements; /* captura todos os elementos do form*/
        for (p = 0; p < elementos.length; p++) {
            elementos[p].value = '';
        }
    }
</script>
</body>
</html>
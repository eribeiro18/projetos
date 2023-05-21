<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
    session_start();
    require 'config.php';
    if(isset($_SESSION['id']) && !empty($_SESSION['id'])){
        echo "Area restrita, varios exemplos em PHP...<br/><br/>";
    }else{
        header("Location: login.php");
    }
    if(isset($_GET['ordem']) && !empty($_GET['ordem'])){
        $ordem = addslashes($_GET['ordem']);
        $sql = "SELECT * FROM usuarios ORDER BY ".$ordem;
    }else{
        $ordem = '';
        $sql = "SELECT * FROM usuarios";
    }
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <a href="adicionar.php">Adicionar Novo Usuário</a><br/><br/>
        <a href="carregarMultArquivos.php">Carregar Multiplos Arquivos</a><br/><br/>
        <form method="GET">
            Escolha uma ordenação:<br>
            <select name="ordem" onchange="this.form.submit()" style="width: 150px;">
                <option></option>
                <option value="id" <?php echo ($ordem=="id")?'selected="selected"':''; ?>>código</option>
                <option value="nome" <?php echo ($ordem=="nome")?'selected="selected"':''; ?>>nome</option>
                <option value="email" <?php echo ($ordem=="email")?'selected="selected"':''; ?>>email</option>
            </select><br><br>
        </form>
            <table border="1" width="100%" >
                <tr>
                    <td>Código</td>
                    <td>Nome</td>
                    <td>E-mail</td>
                    <td>Ações</td>
                </tr>
                <?php
                $sql = $pdo->query($sql);
                if ($sql->rowCount() > 0) {
                    foreach ($sql->fetchAll() as $usuario) {
                        echo '<tr>';
                        echo '<td>' . $usuario['id'] . '</td>';
                        echo '<td>' . $usuario['nome'] . '</td>';
                        echo '<td>' . $usuario['email'] . '</td>';
                        echo '<td><a href="editar.php?id=' . $usuario['id'] . '">Editar</a> - '
                        . '<a href="excluir.php?id=' . $usuario['id'] . '">Excluir</a> </td>';
                        echo '</tr>';
                    }
                }
                ?>
            </table>
        
    </body>
</html>

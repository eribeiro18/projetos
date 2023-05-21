<?php
    session_start();
    require 'config.php';
    //carregando arquivo
    $arquivo = $_FILES['arquivo'];
    if(isset($arquivo['tmp_name']) && !empty($arquivo['tmp_name'])){
        $extesao = strrchr($arquivo['name'], '.');
        $nomeArquivo = md5(time().  rand(0, 99)).$extesao;
        move_uploaded_file($arquivo['tmp_name'], 'arquivos/'.$nomeArquivo);
    }
    if(isset($_POST['nome']) && empty($_POST['nome']) == false){
        $nome = addslashes($_POST['nome']);
        $email = addslashes($_POST['email']);
        $senha = md5(addslashes($_POST['senha']));
        
        $sql = "INSERT INTO usuarios SET nome = '$nome', email = '$email', senha = '$senha'";
        $pdo->query($sql);
        
        header("Location: index.php");
    }
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form method="POST" enctype="multipart/form-data">
            <input type="file" name="arquivo"/><br><br>
            Nome:<br>
            <input type="text" name="nome"/><br><br>
            E-mail:<br>
            <input type="text" name="email"/><br><br>
            Senha:<br>
            <input type="password" name="senha"/><br><br>
            <input type="submit" value="Cadastrar"/>
        </form>
    </body>
</html>

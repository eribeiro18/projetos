<?php
    session_start();
    echo 'Area de Login<br/><br/>';
    require 'config.php';
    
    if(isset($_POST['email']) && !empty($_POST['email'])){
        $email = addslashes($_POST['email']);
        $senha = md5(addslashes($_POST['senha']));
        
        $sql = $pdo->query("SELECT * FROM usuarios where email = '$email' AND senha = '$senha'");
        if($sql->rowCount() > 0){
            $dado = $sql->fetch();
            $_SESSION['id'] = $dado['id'];
            header("Location: index.php");
        }else{
            echo 'Usuario e/ou senha invalido!';
        }
    }
 ?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form method="POST">
            
            E-mail:<br/>
            <input type="email" name="email"/><br/><br/>
            
            Senha:<br/>
            <input type="password" name="senha"/><br/><br/>
            
            <input type="submit" value="Entrar"/>
        </form>        
    </body>
</html>


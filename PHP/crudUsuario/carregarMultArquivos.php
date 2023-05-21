<pre>
<?php
//print_r($_FILES);
if(isset($_FILES['arquivo'])){
    if(count($_FILES['arquivo']['tmp_name']) > 0){
        for($q = 0; $q < count($_FILES['arquivo']['tmp_name']);$q++){
            $extesao = strrchr($_FILES['arquivo']['name'][$q], '.');
            echo 'imagem '.$extesao.'<br/>';
            $nomeArquivo = md5(time().rand(0, 999)).$extesao;
            move_uploaded_file($_FILES['arquivo']['tmp_name'][$q], 'arquivos/'.$nomeArquivo);
        }
    }
}

?>
</pre>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form method="POST" enctype="multipart/form-data">
            Arquivo:<br/>
            <input type="file" name="arquivo[]" multiple/><br/><br/>
            <input type="submit" value="Enviar Arquivos"/>
        </form>
    </body>
</html>

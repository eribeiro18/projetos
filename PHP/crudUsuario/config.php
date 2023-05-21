<?php
$dsn = "mysql:dbname=blog;host=localhost";
$dbuser = "root";
$dbpass = "1339646";

try {
    $pdo = new PDO($dsn, $dbuser, $dbpass);
} catch (PDOException $ex) {
    echo "Falho a conexão: ".$ex->getMessage();
}




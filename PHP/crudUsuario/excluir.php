<?php
session_start();
require 'config.php';
    if(isset($_GET['id']) && empty($_GET['id']) == false){
        $id = addslashes($_GET['id']);
        $sql = "DELETE FROM usuarios where id = '$id'";
        $pdo->query($sql);
        header("Location: index.php");
    }else{
        header("Location: index.php");
    }
    
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


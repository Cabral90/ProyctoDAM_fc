ø<?php

$hostname='localhost';
$database='proyecto_fc';
$username='ifp';
$password='ifp';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio web est√° experimentado problemas";
}


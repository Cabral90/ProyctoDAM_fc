<?php

include 'conexion.php';

$mail = $_REQUEST["mail"];
$pass = $_REQUEST["pass"];

$consulta = "UPDATE registro SET pass='$pass' WHERE mail = '$mail'";

$resultado = $conexion->query($consulta);
while ($fila = $resultado->fetch_array()) {
    $usuarios[] = array_map('utf8_encode', $fila);
}
echo json_encode($usuarios);

$resultado->close();

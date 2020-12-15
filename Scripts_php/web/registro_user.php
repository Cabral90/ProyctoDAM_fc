<?php

$hostname = "localhost";
$database = "proyecto_fc";
$username = "ifp";
$password = "ifp";

$json = array();

if (isset($_GET["nombre"]) && ($_GET["apellidos"]) && isset($_GET["mail"]) && isset($_GET["pass"]) && isset($_GET["fecha_nasc"]) && isset($_GET["calle"]) && isset($_GET["pisto"]) && isset($_GET["puerta"]) && isset($_GET["ciudad"]) && isset($_GET["telefono"]) && isset($_GET["codigo_postal"]) && isset($_GET["provincia"]) && isset($_GET["nomTarjeta"]) && isset($_GET["numTarjeta"]) && isset($_GET["fechaTarjeta"])) {
    $nombre = $_REQUEST['nombre'];
    $apellidos = $_REQUEST['apellidos'];
    $mail = $_REQUEST['mail'];
    $pass = $_REQUEST['pass'];
    $fecha_nasc = $_REQUEST['fecha_nasc'];
    $calle = $_REQUEST['calle'];
    $pisto = $_REQUEST['piso'];
    $puerta = $_REQUEST['puerta'];
    $ciudad = $_REQUEST['ciudad'];
    $telefono = $_REQUEST['telefono'];
    $codigo_postal = $_REQUEST['codigo_postal'];
    $provincia = $_REQUEST['provincia'];
    $nomTarjeta = $_REQUEST['nomTarjeta'];
    $numTarjeta = $_REQUEST['numTarjeta'];
    $fechaTarjeta = $_REQUEST['fechaTarjeta'];


    $conexion = mysqli_connect($hostname, $username, $password, $database); 

    $consulta = "INSERT INTO registro  VALUES (DEFAULT, '{$nombre}','{$apellidos}', '{$mail}', '{$pass}', '{$fecha_nasc}', '{$calle}', '{$pisto}', '{$puerta}', '{$ciudad}', '{$telefono}', '{$codigo_postal}', '{$provincia}', DEFAULT, '{$nomTarjeta}', '{$numTarjeta}', '{$fechaTarjeta}')";
    $resultado = mysqli_query($conexion, $consulta);


    if ($consulta) {
        $consulta = "SELECT * FROM registro  WHERE nombre='{$nombre}'";
        $resultado = mysqli_query($conexion, $consulta);

        if ($reg = mysqli_fetch_array($resultado)) {
            $json['datos'][] = $reg;
        }
        mysqli_close($conexion);
        echo json_encode($json);
    } else {

        $results["nombre"] = '';
        $results["apellidos"] = '';
        $results["mail"] = '';
        $results["pass"] = '';
        $results["fecha_nasc"] = '';
        $results["calle"] = '';
        $results["pisto"] = '';
        $results["puerta"] = '';
        $results["ciudad"] = '';
        $results["telefono"] = '';
        $results["codigo_postal"] = '';
        $results["provincia"] = '';
        $results["numTarjeta"] = '';
        $results["nomTarjeta"] = '';
        $results["fechaTarjeta"] = '';

        $json['datos'][] = $results;
        echo json_encode($json);
    }
} else {
    $results["nombre"] = '';
    $results["apellidos"] = '';
    $results["mail"] = '';
    $results["pass"] = '';
    $results["fecha_nasc"] = '';
    $results["calle"] = '';
    $results["pisto"] = '';
    $results["puerta"] = '';
    $results["ciudad"] = '';
    $results["telefono"] = '';
    $results["codigo_postal"] = '';
    $results["provincia"] = '';
    $results["numTarjeta"] = '';
    $results["nomTarjeta"] = '';
    $results["fechaTarjeta"] = '';

    $json['datos'][] = $results;
    echo json_encode($json);
}

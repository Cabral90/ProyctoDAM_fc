<?PHP

$hostname_localhost = "localhost";
$database_localhost = "proyecto_fc";
$username_localhost = "ifp";
$password_localhost = "ifp";

$json = array();

if (isset($_REQUEST["nombre"])) {
    $nombre = $_REQUEST["nombre"];

    $conexion = mysqli_connect($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

    $consulta = "select * from productos where nombre= '{$nombre}'";
    $resultado = mysqli_query($conexion, $consulta);

    if ($registro = mysqli_fetch_array($resultado)) {

        $result["nombre"] = $registro["nombre"];
        $result["precios"] = $registro["precios"];
        $result["cantidad"] = $registro["cantidad"];
        $result["fecha_fabricacion"] = $registro["fecha_fabricacion"];
        $result["fecha_caducidad"] = $registro["fecha_caducidad"];
        $result["descripcion"] = $registro["descripcion"];
        $result["fecha_insercion"] = $registro["fecha_insercion"];
        $result["imagen"] = base64_encode($registro['imagen']);
                $json[] = $result; 
    } else {


        $result["nombre"] = 'null';
        $result["precios"] = 0.0;
        $result["cantidad"] = 0;
        $result["fecha_fabricacion"] = 'null';
        $result["fecha_caducidad"] = 'null';
        $result["descripcion"] = 'null';
        $result["fecha_insercion"] = 'null';
        $result["imagen"] = 'null';
        $json[] = $result; // 'productos'
    }

    mysqli_close($conexion);
    echo json_encode($json);
} else {
    $resultar["success"] = 0;
    $resultar["message"] = 'Ws no Retorna';
    $json[] = $resultar; // ['productos']
    echo json_encode($json);
}
?>
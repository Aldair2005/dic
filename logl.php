<?php
$servername = "localhost";
$username = "root";
$password = " ";
$database = "bswl.sql";

$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $usuario = $_POST['usuario_nick'];
    $contrasena = $_POST['contraseña'];

    $query = "SELECT * FROM usuario WHERE usuario_nick = '$usuario' AND contraseña = '$contrasena'";
    $result = $conn->query($query);

    if ($result->num_rows > 0) {
        echo "Login exitoso";
    } else {
        echo "Usuario o contraseña incorrectos";
    }
}

$conn->close();
?>

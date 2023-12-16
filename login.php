<?php
// Conexión a la base de datos
$servername = "localhost";
$username = "root ";
$password = "";
$database = "bswl";

$conn = new mysqli($servername, $username, $password, $database);

// Verificar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Recibir datos del usuario
$usuarioNick = $_POST['usuario'];
$contrasena = $_POST['contrasena'];

// Consulta para verificar las credenciales del usuario
$sql = "SELECT usuario_id, usuario_nick, nombre, email, numero FROM usuario INNER JOIN contacto ON usuario.usuario_id = contacto.usuario_id WHERE usuario_nick = '$usuarioNick' AND contraseña = '$contrasena'";
$result = $conn->query($sql);

// Verificar si se encontraron resultados
if ($result->num_rows > 0) {
    // Usuario autenticado correctamente
    $row = $result->fetch_assoc();
    $response = array(
        'usuario_id' => $row['usuario_id'],
        'usuario_nick' => $row['usuario_nick'],
        'nombre' => $row['nombre'],
        'email' => $row['email'],
        'numero' => $row['numero']
    );
    echo json_encode($response);
} else {
    // Credenciales incorrectas
    echo "Credenciales incorrectas";
}

// Cerrar la conexión a la base de datos
$conn->close();
?>

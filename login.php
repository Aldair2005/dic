<?php
session_start(); // Inicia la sesión (si no está iniciada)

// Comprueba si se ha enviado el formulario de inicio de sesión
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Datos de tu base de datos
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basw";

    // Recoge los datos del formulario
    $usernameInput = $_POST['username'];
    $passwordInput = $_POST['password'];

    // Conexión a la base de datos
    $conn = new mysqli($servername, $username, $password, $dbname);

    if ($conn->connect_error) {
        die("Error en la conexión a la base de datos: " . $conn->connect_error);
    }

    // Consulta SQL para verificar las credenciales
    $sql = "SELECT * FROM usuario WHERE usuario_nick = '$usernameInput' AND contraseña = '$passwordInput'";
    $result = $conn->query($sql);

    if ($result->num_rows == 1) {
        // Las credenciales son válidas, inicia la sesión y almacena el nombre de usuario
        $_SESSION['username'] = $usernameInput;
        header("Location: inicio.php"); // Redirige al usuario a la página de inicio
        exit();
    } else {
        // Credenciales incorrectas, muestra un mensaje de error
        $error_message = "Nombre de usuario o contraseña incorrectos.";
    }

    $conn->close();
}
?>


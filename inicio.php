<!DOCTYPE html>
<html>
<head>
    <title>Página de Inicio</title>
</head>
<body>
    <h2>Bienvenido a tu página de inicio</h2>

    <?php
    // Inicia la sesión (si no lo has hecho ya)
    session_start();

    // Verifica si el usuario ha iniciado sesión
    if(isset($_SESSION['username'])) {
        // Conexión a la base de datos
        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "basw";

        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error) {
            die("Error en la conexión a la base de datos: " . $conn->connect_error);
        }

        // Obtén el nombre de usuario del usuario actual
        $username = $_SESSION['username'];

        // Consulta SQL para obtener los datos del usuario
        $sql = "SELECT * FROM usuario INNER JOIN contacto on usuario.usuario_id=contacto.id WHERE usuario_nick = '$username' ";
        $result = $conn->query($sql);

        if ($result->num_rows == 1) {
            $row = $result->fetch_assoc();
            // Mostrar los datos del usuario
            echo "usuario_id: " . $row['usuario_id'] . "<br>";
            echo "nombre " . $row['nombre']. "<br>";
            echo "usuario_nick: " . $row['usuario_nick'] . "<br>";
            echo "mail: " . $row['email'] . "<br>";
            echo "contraseña: " . $row['contraseña'] . "<br>";
            echo "numero: " . $row['numero'] . "<br>";
            
            
            // Agrega más campos aquí según tu base de datos
        }

       

        $conn->close();
    } else {
        echo "Debes iniciar sesión para ver esta página.";
    }
    ?>

    <br><a href="cerrar_sesion.php">Cerrar Sesión</a>
</body>
</html>





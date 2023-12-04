package com.example.cha

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var entradaUsuarioEditText: EditText
    private lateinit var procesarButton: Button
    private lateinit var respuestaTextView: TextView

    private var nombreUsuario: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entradaUsuarioEditText = findViewById(R.id.entradaUsuarioEditText)
        procesarButton = findViewById(R.id.procesarButton)
        respuestaTextView = findViewById(R.id.respuestaTextView)

        procesarButton.setOnClickListener {
            val entradaUsuario = entradaUsuarioEditText.text.toString().trim().toLowerCase()

            // Lógica para aprender el nombre del usuario
            if (entradaUsuario.contains("hola") && entradaUsuario.contains("como te llamas")) {
                nombreUsuario = obtenerNombreDesdeEntrada(entradaUsuario)
                responder("Hola $nombreUsuario, ¿en qué puedo ayudarte?")
            } else {
                // Lógica general de respuestas
                val respuesta = generarRespuesta(entradaUsuario)
                responder(respuesta)
            }
        }
    }

    private fun obtenerNombreDesdeEntrada(entrada: String): String {
        // Lógica para extraer el nombre del usuario desde la entrada
        // Esto podría implicar el uso de expresiones regulares u otros métodos dependiendo del formato esperado
        return "Usuario"
    }

    private fun generarRespuesta(entradaUsuario: String): String {
        // Lógica general de respuestas
        if (nombreUsuario != null && entradaUsuario.contains("como estas")) {
            return "Estoy bien, $nombreUsuario. ¿Y tú?"
        } else {
            return "No entiendo esa entrada. ¿Puedes repetir?"
        }
    }

    private fun responder(respuesta: String) {
        respuestaTextView.text = respuesta
    }
}



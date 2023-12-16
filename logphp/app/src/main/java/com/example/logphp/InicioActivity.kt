package com.example.logphp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import org.json.JSONObject

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val mensajeBienvenida = intent.getStringExtra("mensaje_bienvenida")
        val datosUsuario = intent.getStringExtra("datos_usuario")
        val DatosContacto = intent.getStringExtra("datos_contacto")

        val tvMensajeBienvenida = findViewById<TextView>(R.id.tvMensajeBienvenida)
        val tvDatosUsuario = findViewById<TextView>(R.id.tvDatosUsuario)
        val tvDatosContacto = findViewById<TextView>(R.id.tvDatosContacto)

        tvMensajeBienvenida.text = mensajeBienvenida

        // Mostrar los datos del usuario en la interfaz de usuario
        if (datosUsuario != null) {
            val jsonObject = JSONObject(datosUsuario)
            val usuarioId = jsonObject.getInt("usuario_id")
            val usuarioNick = jsonObject.getString("usuario_nick")
            val contactoNombre = jsonObject.getString("nombre")
            val contactoEmail = jsonObject.getString("email")
            val contactoNumero = jsonObject.getString("numero")

            val mensajeDatos = "ID: $usuarioId\nUsuario: $usuarioNick\nNombre: $contactoNombre\nEmail: $contactoEmail\nNúmero: $contactoNumero"
            tvDatosUsuario.text = mensajeDatos
        }
    }
}


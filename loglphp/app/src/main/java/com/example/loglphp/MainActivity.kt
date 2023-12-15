package com.example.loglphp

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            // Ejecutar la tarea asíncrona para la autenticación
            LoginTask().execute(usuario, contrasena)
        }
    }

    private inner class LoginTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            try {
                val usuario = URLEncoder.encode(params[0], "UTF-8")
                val contrasena = URLEncoder.encode(params[1], "UTF-8")

                // Reemplaza la URL con la ubicación de tu script PHP
                val url = URL("http://tu_servidor/logl.php?usuario=$usuario&contrasena=$contrasena")

                val urlConnection = url.openConnection() as HttpURLConnection
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                val result = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line)
                }

                return result.toString()
            } catch (e: Exception) {
                return "Excepción: ${e.message}"
            }
        }

        override fun onPostExecute(result: String) {
            // Manejar el resultado de la tarea asíncrona
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.logphp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuarioNick = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            // Ejecutar la tarea asíncrona para la autenticación
            LoginTask().execute(usuarioNick, contrasena)
        }
    }

    private inner class LoginTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            try {
                val usuarioNick = URLEncoder.encode(params[0], "UTF-8")
                val contrasena = URLEncoder.encode(params[1], "UTF-8")

                // Reemplaza la URL con la ubicación de tu script PHP
                val url = URL("http://localhost/login.php")
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "POST"

                val postData = "usuario=$usuarioNick&contrasena=$contrasena"
                urlConnection.outputStream.use { it.write(postData.toByteArray()) }

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
            if (result.startsWith("{")) {
                // Parsear el JSON para obtener datos del usuario
                val jsonObject = JSONObject(result)

                // Redirigir a la InicioActivity y pasar el mensaje de bienvenida y los datos del usuario
                val intent = Intent(applicationContext, InicioActivity::class.java)
                intent.putExtra("mensaje_bienvenida", "Bienvenido!")
                intent.putExtra("datos_usuario", jsonObject.toString())
                intent.putExtra("datos_contacto", jsonObject.toString())

                startActivity(intent)
            } else {
                // Mostrar mensaje de error
                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
}












package com.example.sql

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etUsername = findViewById<TextInputEditText>(R.id.etRegUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etRegPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = etFullName.text.toString()
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            if (name.isNotEmpty() && user.isNotEmpty() && pass.isNotEmpty()) {
                registerStudent(name, user, pass)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerStudent(name: String, user: String, pass: String) {
        val url = "http://10.0.2.2/academia_db/register.php"
        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                if (response.trim() == "Registro exitoso") finish()
            },
            { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["nombre"] = name
                params["username"] = user
                params["password"] = pass
                return params
            }
        }
        queue.add(request)
    }
}
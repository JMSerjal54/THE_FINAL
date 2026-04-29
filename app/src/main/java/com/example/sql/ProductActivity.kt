package com.example.sql

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etQuantity: EditText
    private lateinit var btnOrder: Button
    private lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val username = intent.getStringExtra("USERNAME") ?: "Usuario"
        tvWelcome = findViewById(R.id.tvWelcome)
        tvWelcome.text = "Bienvenido, $username"

        etProductName = findViewById(R.id.etProductName)
        etQuantity = findViewById(R.id.etQuantity)
        btnOrder = findViewById(R.id.btnOrder)

        btnOrder.setOnClickListener {
            val product = etProductName.text.toString()
            val quantity = etQuantity.text.toString()

            if (product.isNotEmpty() && quantity.isNotEmpty()) {
                makeOrder(username, product, quantity)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeOrder(username: String, product: String, quantity: String) {

        val url = "http://10.0.2.2/sql_app/order.php"

        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["product"] = product
                params["quantity"] = quantity
                return params
            }
        }
        queue.add(request)
    }
}
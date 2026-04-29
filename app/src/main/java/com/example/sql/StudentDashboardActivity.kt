package com.example.sql

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        val username = intent.getStringExtra("USERNAME") ?: ""
        val tvWelcome = findViewById<TextView>(R.id.tvStudentWelcome)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val btnBack = findViewById<ImageButton>(R.id.btnBackStudent)

        tvWelcome.text = "Hola, $username"

        fetchStudentData(username, tvContent)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun fetchStudentData(username: String, tvContent: TextView) {
        val url = "http://10.0.2.2/academia_db/get_student_data.php?username=$username"
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url,
            { response -> tvContent.text = response },
            { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }
        )
        queue.add(request)
    }
}
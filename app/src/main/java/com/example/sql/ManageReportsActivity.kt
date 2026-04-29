package com.example.sql

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class ManageReportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_reports)

        val btnBack = findViewById<ImageButton>(R.id.btnBackReports)
        val etStudent = findViewById<TextInputEditText>(R.id.etReportStudentUser)
        val etComment = findViewById<TextInputEditText>(R.id.etReportComment)
        val btnSave = findViewById<Button>(R.id.btnSaveReport)

        btnBack.setOnClickListener { finish() }

        btnSave.setOnClickListener {
            val student = etStudent.text.toString()
            val comment = etComment.text.toString()

            if (student.isNotEmpty() && comment.isNotEmpty()) {
                saveReport(student, comment)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveReport(student: String, comment: String) {
        val url = "http://10.0.2.2/academia_db/save_report.php"
        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                if (response.trim() == "Reporte guardado") {
                    findViewById<TextInputEditText>(R.id.etReportComment).text?.clear()
                }
            },
            { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["student_user"] = student
                params["comment"] = comment
                return params
            }
        }
        queue.add(request)
    }
}
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

class ManageGradesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_grades)

        val btnBack = findViewById<ImageButton>(R.id.btnBackGrades)
        val etStudentUser = findViewById<TextInputEditText>(R.id.etStudentUser)
        val etSubject = findViewById<TextInputEditText>(R.id.etSubject)
        val etGrade = findViewById<TextInputEditText>(R.id.etGrade)
        val btnSaveGrade = findViewById<Button>(R.id.btnSaveGrade)
        val btnUpdateGrade = findViewById<Button>(R.id.btnUpdateGrade)
        val btnDeleteGrade = findViewById<Button>(R.id.btnDeleteGrade)

        btnBack.setOnClickListener { finish() }

        btnSaveGrade.setOnClickListener {
            val student = etStudentUser.text.toString()
            val subject = etSubject.text.toString()
            val grade = etGrade.text.toString()
            if (student.isNotEmpty() && subject.isNotEmpty() && grade.isNotEmpty()) {
                sendGradeRequest("http://10.0.2.2/academia_db/save_grade.php", student, subject, grade)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdateGrade.setOnClickListener {
            val student = etStudentUser.text.toString()
            val subject = etSubject.text.toString()
            val grade = etGrade.text.toString()
            if (student.isNotEmpty() && subject.isNotEmpty() && grade.isNotEmpty()) {
                sendGradeRequest("http://10.0.2.2/academia_db/update_grade.php", student, subject, grade)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnDeleteGrade.setOnClickListener {
            val student = etStudentUser.text.toString()
            val subject = etSubject.text.toString()
            if (student.isNotEmpty() && subject.isNotEmpty()) {
                sendGradeRequest("http://10.0.2.2/academia_db/delete_grade.php", student, subject, "")
            } else {
                Toast.makeText(this, "Completa Estudiante y Materia", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendGradeRequest(url: String, student: String, subject: String, grade: String) {
        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            },
            { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["student_user"] = student
                params["subject"] = subject
                if (grade.isNotEmpty()) params["grade"] = grade
                return params
            }
        }
        queue.add(request)
    }
}
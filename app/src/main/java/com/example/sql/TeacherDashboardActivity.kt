package com.example.sql

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class TeacherDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_dashboard)

        val btnBack = findViewById<ImageButton>(R.id.btnBackTeacher)
        val cardGrades = findViewById<MaterialCardView>(R.id.cardManageGrades)
        val cardReports = findViewById<MaterialCardView>(R.id.cardManageReports)

        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        cardGrades.setOnClickListener {
            startActivity(Intent(this, ManageGradesActivity::class.java))
        }

        cardReports.setOnClickListener {
            startActivity(Intent(this, ManageReportsActivity::class.java))
        }
    }
}
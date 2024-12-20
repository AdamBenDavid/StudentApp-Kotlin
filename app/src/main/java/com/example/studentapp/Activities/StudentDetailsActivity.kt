package com.example.studentapp.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.R
import com.example.studentapp.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Set up header
        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val studentId = intent.getStringExtra("STUDENT_ID")
        val student = StudentRepository.students.find { it.id == studentId }

        val nameText: TextView = findViewById(R.id.studentDetailsName)
        val idText: TextView = findViewById(R.id.studentDetailsId)
        val picture: ImageView = findViewById(R.id.studentDetailsPicture)
        val statusText: TextView = findViewById(R.id.studentDetailsStatus)

        student?.let {
            nameText.text = it.name
            idText.text = it.id
            picture.setImageResource(it.pictureResId)
            statusText.text = if (it.isChecked) "Checked: true" else "Checked: false"
        }

        val editButton: Button = findViewById(R.id.editStudentButton)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", studentId)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


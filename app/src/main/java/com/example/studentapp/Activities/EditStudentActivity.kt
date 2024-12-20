package com.example.studentapp.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.R
import com.example.studentapp.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentId = intent.getStringExtra("STUDENT_ID")
        val student = StudentRepository.students.find { it.id == studentId }

        val nameField: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idField: EditText = findViewById(R.id.edit_student_activity_id_edit_text)

        student?.let {
            nameField.setText(it.name)
            idField.setText(it.id)
        }

        val saveButton: Button = findViewById(R.id.edit_student_activity_button_save)
        saveButton.setOnClickListener {
            val updatedName = nameField.text.toString()
            val updatedId = idField.text.toString()

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                student?.name = updatedName
                student?.id = updatedId
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }

        val deleteButton: Button = findViewById(R.id.edit_student_activity_button_delet)
        deleteButton.setOnClickListener {
            StudentRepository.students.remove(student)
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        val cancelButton: Button = findViewById(R.id.edit_student_activity_button_cancel)
        cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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



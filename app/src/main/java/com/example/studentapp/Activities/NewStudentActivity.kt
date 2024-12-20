package com.example.studentapp.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.Models.Student
import com.example.studentapp.R
import com.example.studentapp.StudentRepository
class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameField: EditText = findViewById(R.id.new_student_activity_name_text)
        val idField: EditText = findViewById(R.id.new_student_activity_id_text)
        val saveButton: Button = findViewById(R.id.new_student_activity_button_save)

        saveButton.setOnClickListener {
            val name = nameField.text.toString()
            val id = idField.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                val newStudent = Student(id, name, false, R.drawable.default_student)
                StudentRepository.students.add(newStudent)
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }

        val cancelButton: Button = findViewById(R.id.new_student_activity_button_cancel)
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




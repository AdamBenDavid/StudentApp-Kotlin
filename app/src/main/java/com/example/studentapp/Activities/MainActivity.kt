package com.example.studentapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.Adapters.StudentAdapter
import com.example.studentapp.Models.Student
import com.example.studentapp.R
import com.example.studentapp.StudentRepository

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up header
        supportActionBar?.title = "Student List"

        studentAdapter = StudentAdapter(StudentRepository.students) { selectedStudent ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", selectedStudent.id)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }

        loadStudents()
    }

    override fun onResume() {
        super.onResume()
        studentAdapter.notifyDataSetChanged() // Refresh data when returning to the activity
    }

    private fun loadStudents() {
        if (StudentRepository.students.isEmpty()) {
            StudentRepository.students.addAll(listOf(
                Student("1", "John Doe", false, R.drawable.ic_launcher_foreground),
                Student("2", "Jane Smith", false, R.drawable.ic_launcher_foreground)
            ))
        }
        studentAdapter.notifyDataSetChanged()
    }
}
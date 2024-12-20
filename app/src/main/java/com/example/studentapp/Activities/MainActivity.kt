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

        studentAdapter = StudentAdapter(StudentRepository.students) { selectedStudent ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", selectedStudent.id)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.main_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        val addButton: Button = findViewById(R.id.main_activity_Button_Add)
        addButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }

        loadStudents()
    }

    override fun onResume() {
        super.onResume()
        studentAdapter.notifyDataSetChanged()
    }

    private fun loadStudents() {
        if (StudentRepository.students.isEmpty()) {
            StudentRepository.students.addAll(listOf(
                Student("212292197", "Aviv Menahem", false, R.drawable.default_student),
                Student("208298257", "Adam Ben David", false, R.drawable.default_student)
            ))
        }
        studentAdapter.notifyDataSetChanged()
    }
}
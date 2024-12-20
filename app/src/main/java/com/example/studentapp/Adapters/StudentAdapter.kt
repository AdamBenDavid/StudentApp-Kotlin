package com.example.studentapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.Models.Student
import com.example.studentapp.R

class StudentAdapter(
    private val students: List<Student>,
    private val onStudentClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.studentName)
        private val idText: TextView = view.findViewById(R.id.studentId)
        private val checkBox: CheckBox = view.findViewById(R.id.studentCheckBox)
        private val picture: ImageView = view.findViewById(R.id.studentPicture)

        fun bind(student: Student) {
            nameText.text = student.name
            idText.text = student.id
            checkBox.isChecked = student.isChecked
            picture.setImageResource(student.pictureResId)

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            itemView.setOnClickListener {
                onStudentClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size
}

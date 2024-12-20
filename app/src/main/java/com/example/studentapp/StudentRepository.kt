package com.example.studentapp

import com.example.studentapp.Models.Student

// Singleton: StudentRepository.kt
object StudentRepository {
    val students = mutableListOf<Student>()
}

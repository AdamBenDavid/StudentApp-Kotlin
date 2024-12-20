package com.example.studentapp.Models

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean,
    val pictureResId: Int
)
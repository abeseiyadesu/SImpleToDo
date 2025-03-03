package com.example.simpletodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var isCompleted: Boolean
)
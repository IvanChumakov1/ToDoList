package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
    @PrimaryKey (autoGenerate = true) val id: Long,
    var title: String = "",
    var priority: Int = 0
)
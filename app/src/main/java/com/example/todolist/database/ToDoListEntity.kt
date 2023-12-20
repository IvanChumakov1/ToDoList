package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoListEntity (

    var title: String = "",
    var priority: Int = 0
) {
    @PrimaryKey (autoGenerate = true) var id: Int? = null
}
package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ ToDoEntity::class ], version=1)
abstract class ToDoDataBase : RoomDatabase() {
}
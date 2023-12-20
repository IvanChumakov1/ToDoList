package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoListEntity::class], version = 1)
abstract class ToDoListDB:RoomDatabase () {
    abstract fun ToDoListDao(): ToDoListDao
}
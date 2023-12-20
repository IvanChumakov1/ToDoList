package com.example.todolist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoListDao {
    @Query("SELECT * FROM ToDoListEntity")
    fun getList(): List<ToDoListEntity>

    @Insert
    fun insert(vararg ToDoListEntity: ToDoListEntity)
    @Delete
    fun delete(ToDoListEntity: ToDoListEntity)
}
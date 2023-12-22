package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoList)

    @Delete
    suspend fun delete(todo: ToDoList)

    @Query("SELECT * from todolist_table order by id ASC")
    fun getAllTodos(): LiveData<List<ToDoList>>

    @Query("UPDATE todolist_table set title = :title where id = :id")
    suspend fun update(id: Int?, title: String?)
}
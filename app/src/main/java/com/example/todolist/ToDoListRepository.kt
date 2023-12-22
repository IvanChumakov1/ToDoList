package com.example.todolist

import androidx.lifecycle.LiveData
import com.example.todolist.database.ToDoList
import com.example.todolist.database.ToDoListDao

class ToDoListRepository (private val todoDao: ToDoListDao) {

    val allTodos: LiveData<List<ToDoList>> = todoDao.getAllTodos()

    suspend fun insert(todo: ToDoList) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: ToDoList) {
        todoDao.delete(todo)
    }

    suspend fun update(todo: ToDoList) {
        todoDao.update(todo.id, todo.title)
    }
}
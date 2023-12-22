package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ToDoList
import com.example.todolist.database.ToDoListDB

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoListViewModel (application: Application): AndroidViewModel(application) {
    private val repository: ToDoListRepository
    val allTodo : LiveData<List<ToDoList>>

    init {
        val dao = ToDoListDB.getDatabase(application).getToDoListDao()
        repository = ToDoListRepository(dao)
        allTodo = repository.allTodos
    }

    fun insertTodo(todo: ToDoList) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(todo)
    }

    fun updateTodo(todo: ToDoList) = viewModelScope.launch(Dispatchers.IO){
        repository.update(todo)
    }

    fun deleteTodo(todo: ToDoList) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todo)
    }
}
package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.TextView
import androidx.room.Room
import com.example.todolist.database.ToDoListDB
import com.example.todolist.database.ToDoListEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: ToDoListDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, ToDoListDB::class.java, "Database-TodoList").build()
        val task1 = ToDoListEntity("Тестовая задача",0)

        GlobalScope.launch{
            db.ToDoListDao().insert(task1)

            displayTasks()
        }
    }

    private suspend fun displayTasks(){
        val tasks = db.ToDoListDao().getList()
        val display = findViewById<TextView>(R.id.display)
        var displayText = ""
        for (task in tasks){
            displayText += "${task.title} ${task.priority}\n"
        }
        display.text = displayText
    }
}
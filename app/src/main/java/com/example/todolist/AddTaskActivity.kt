package com.example.todolist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.database.ToDoList
import com.example.todolist.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

        private lateinit var binding: ActivityAddTaskBinding
        private lateinit var todo: ToDoList
        private lateinit var oldTodo: ToDoList
        var isUpdate = false


        @SuppressLint("SimpleDateFormat")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAddTaskBinding.inflate(layoutInflater)
            setContentView(binding.root)

            try {
                oldTodo = intent.getSerializableExtra("current_todo") as ToDoList
                binding.etTitle.setText("Изменить задачу")
                binding.stringText.setText(oldTodo.title)
                binding.saveTask.setText("Изменить")
                isUpdate = true
            }catch (e: Exception){
                e.printStackTrace()
            }

            binding.saveTask.setOnClickListener {
                val title = binding.stringText.text.toString()
                var priority = 1
                if(binding.radioButton2.isChecked){
                    priority=2
                }
                if(binding.radioButton3.isChecked){
                    priority=3
                }

                if(title.isNotEmpty()){

                    if(isUpdate){
                        todo = ToDoList(oldTodo.id, title, priority)
                    }else{
                        todo = ToDoList(null, title, priority)
                    }
                    var intent = Intent()
                    intent.putExtra("todo", todo)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }else{
                    Toast.makeText(this@AddTaskActivity, "please enter some data", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            binding.imgDelete.setOnClickListener {
                var intent = Intent()
                intent.putExtra("todo", oldTodo)
                intent.putExtra("delete_todo", true)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            binding.imgBackArrow.setOnClickListener {
                onBackPressed()
            }
        }
    }
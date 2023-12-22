package com.example.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDoList
import com.example.todolist.database.ToDoListDB
import com.example.todolist.databinding.ActivityMainBinding


class MainActivity :  AppCompatActivity(), ToDoListAdapter.TodoClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ToDoListDB
    lateinit var viewModel: ToDoListViewModel
    lateinit var adapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ToDoListViewModel::class.java)

        viewModel.allTodo.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }

        database = ToDoListDB.getDatabase(this)

    }

    private fun initUI() {
        binding.itemsTasks.setHasFixedSize(true)
        binding.itemsTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ToDoListAdapter(this, this)
        binding.itemsTasks.adapter = adapter

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val todo = result.data?.getSerializableExtra("todo") as? ToDoList
                    if (todo != null) {
                        viewModel.insertTodo(todo)
                    }
                }
            }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            getContent.launch(intent)
        }

    }

    private val updateOrDeleteTodo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todo = result.data?.getSerializableExtra("todo") as ToDoList
                val isDelete = result.data?.getBooleanExtra("delete_todo", false) as Boolean
                if (todo != null && !isDelete) {
                    viewModel.updateTodo(todo)
                }else if(todo != null && isDelete){
                    viewModel.deleteTodo(todo)
                }
            }
        }



    override fun onItemClicked(todo: ToDoList) {
        val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
        intent.putExtra("current_todo", todo)
        updateOrDeleteTodo.launch(intent)
    }

}
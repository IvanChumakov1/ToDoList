package com.example.todolist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDoList

class ToDoListAdapter(private val context: Context, val listener: TodoClickListener):
    RecyclerView.Adapter<ToDoListAdapter.TodoViewHolder>(){

    private val todoList = ArrayList<ToDoList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapter.TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.task_in_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoListAdapter.TodoViewHolder, position: Int) {
        val item = todoList[position]
        holder.title.text = item.title
        holder.title.isSelected = true
        holder.text_priority.text = (item.priority).toString()
        when (item.priority) {
            1 -> holder.text_priority.setBackgroundResource(R.drawable.priority1)
            2 -> holder.text_priority.setBackgroundResource(R.drawable.priority2)
            else -> {
                holder.text_priority.setBackgroundResource(R.drawable.priority3)
            }
        }

        holder.todo_layout.setOnClickListener {
            listener.onItemClicked(todoList[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int {
        return todoList.size
    }


    fun updateList(newList: List<ToDoList>){
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todo_layout = itemView.findViewById<LinearLayout>(R.id.card)
        val title = itemView.findViewById<TextView>(R.id.textView_string)
        val text_priority = itemView.findViewById<TextView>(R.id.textView_priority)
    }

    interface TodoClickListener {
        fun onItemClicked(todo: ToDoList)
    }
}
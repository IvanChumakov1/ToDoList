package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDoListEntity

class tasks_adapter(var items: List<ToDoListEntity>, var context: Context): RecyclerView.Adapter<tasks_adapter.TaskViewHolder>() {
    class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textTask: TextView = view.findViewById(R.id.textView_string)
        val textPriority: TextView = view.findViewById(R.id.textView_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_in_list, parent, false)
        return  TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.textTask.text = items[position].title
        holder.textPriority.text = (items[position].priority+1).toString()
        when (items[position].priority) {
            1 -> holder.textPriority.setBackgroundResource(R.drawable.priority2)
            2 -> holder.textPriority.setBackgroundResource(R.drawable.priority3)
            else -> {
                holder.textPriority.setBackgroundResource(R.drawable.priority1)
            }
        }

    }
}
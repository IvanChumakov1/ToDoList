package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoList::class], version = 1)
abstract class ToDoListDB : RoomDatabase() {
    abstract fun getToDoListDao(): ToDoListDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoListDB? = null

        fun getDatabase(context: Context): ToDoListDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, ToDoListDB::class.java, "todo_table").build()

                INSTANCE = instance
                instance
            }
        }
    }

}
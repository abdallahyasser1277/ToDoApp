package com.abdalllahyascer.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = (arrayOf(TaskItem::class)), version = 1, exportSchema = false)
abstract class TaskItemsDB:RoomDatabase() {

    abstract fun taskItemDao():TaskItemDao

    companion object{
        @Volatile
        private var INSTANCE :TaskItemsDB?= null

        fun getDBInstance(context:Context):TaskItemsDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemsDB::class.java,
                    "task_item_database")
                    .build()
                INSTANCE=instance
                instance
            }

        }
    }

}
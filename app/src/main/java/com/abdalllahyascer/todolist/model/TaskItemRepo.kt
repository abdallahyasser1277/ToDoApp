package com.abdalllahyascer.todolist.model

import androidx.annotation.WorkerThread
import java.util.concurrent.Flow

class TaskItemRepo(private val taskItemDao: TaskItemDao) {

    val allTaskItems = taskItemDao.getAllTasks()

    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem){
        taskItemDao.insertTask(taskItem)
    }

    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem){
        taskItemDao.updateTask(taskItem)
    }
    @WorkerThread
    suspend fun deleteTaskItem(taskItem: TaskItem){
        taskItemDao.deleteTask(taskItem)
    }

}
package com.abdalllahyascer.todolist

import android.app.Application
import com.abdalllahyascer.todolist.model.TaskItemRepo
import com.abdalllahyascer.todolist.model.TaskItemsDB

class TodoApplication:Application() {

    private val database by lazy { TaskItemsDB.getDBInstance(this) }
    val repo by lazy { TaskItemRepo(database.taskItemDao()) }
}
package com.abdalllahyascer.todolist.model

interface TaskItemClickListener {

    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}
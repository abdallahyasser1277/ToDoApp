package com.abdalllahyascer.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.model.TaskItemRepo
import java.lang.IllegalArgumentException

class TaskViewModelFactory(private val repository: TaskItemRepo) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
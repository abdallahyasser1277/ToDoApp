package com.abdalllahyascer.todolist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdalllahyascer.todolist.model.TaskItem
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class TaskViewModel: ViewModel() {

    var taskItems =MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value= mutableListOf()

    }

    fun addTask(task :TaskItem){
        val list=taskItems.value
        list!!.add(task)
        taskItems.value=list
    }
    fun updateTask(id :UUID,name:String,desc:String,dueTime: LocalDateTime?){
        val list=taskItems.value
        val task =list!!.find { it.id==id }!!
        task.name=name
        task.desc=desc
        task.dueDate=dueTime
        taskItems.value=list
    }


    fun setCompleted(taskItem: TaskItem ){
        val list=taskItems.value
        val task =list!!.find { it.id==taskItem.id }!!

        if (task.completedDate==null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                task.completedDate= LocalDate.now()
            }
        }
        taskItems.value=list
    }


}
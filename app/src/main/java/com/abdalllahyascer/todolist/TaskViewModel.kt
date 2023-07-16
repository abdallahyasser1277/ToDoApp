package com.abdalllahyascer.todolist

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItemRepo
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel(private val repo :TaskItemRepo ): ViewModel() {
    var taskItems: LiveData<List<TaskItem>> = repo.allTaskItems.asLiveData()
    fun addTask(taskItem: TaskItem)=viewModelScope.launch{
        repo.insertTaskItem(taskItem)
    }

    fun updateTask(taskItem: TaskItem)=viewModelScope.launch{
        repo.updateTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem)=viewModelScope.launch{
        if (!taskItem.isCompleted())
            taskItem.completedDateString=TaskItem.dateFormatter.format(LocalDate.now())
        repo.updateTaskItem(taskItem)
    }

    fun deleteTask(taskItem: TaskItem?)=viewModelScope.launch {
        if (taskItem!=null)repo.deleteTaskItem(taskItem)
    }

//    init {
//        taskItems.value= mutableListOf()
//
//    }
//    fun addTask(task :TaskItem){
//        val list=taskItems.value
//        list!!.add(task)
//        taskItems.value=list
//    }
//    fun updateTask(id :Int,name:String,desc:String,dueTime: LocalTime?){
//        val list=taskItems.value
//        val task =list!!.find { it.id==id }!!
//        task.name=name
//        task.desc=desc
//        task.dueTimeString=dueTime.toString()
//        task.completedDateString=null
//        taskItems.value=list
//    }
//
//
//    fun setCompleted(taskItem: TaskItem ){
//        val list=taskItems.value
//        val task =list!!.find { it.id==taskItem.id }!!
//
//        if (task.completedDate()==null){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                task.completedDateString= LocalDate.now().toString()
//            }
//        }
//        taskItems.value=list
//    }


}
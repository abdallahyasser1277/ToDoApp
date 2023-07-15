package com.abdalllahyascer.todolist.model

import com.abdalllahyascer.todolist.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem(
    var name: String,
    var desc: String,
    var dueTime: LocalTime?,
    var completedDate: LocalDate?,
    var id :UUID=UUID.randomUUID()
){
    fun isCompleted()= completedDate!=null

    fun getImageResource() :Int =if(isCompleted()) R.drawable.check_circle else R.drawable.unchecked_24


}


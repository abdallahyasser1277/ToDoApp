package com.abdalllahyascer.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdalllahyascer.todolist.R
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity (tableName = "task_item_table")
class TaskItem(
    @ColumnInfo (name = "name")var name: String,
    @ColumnInfo (name = "desc")var desc: String,
    @ColumnInfo (name = "dueTime")var dueTimeString: String?,
    @ColumnInfo (name = "completedDate")var completedDateString: String?,
    @PrimaryKey(autoGenerate = true) var id :Int=0
){

    companion object{
        val timeFormatter:DateTimeFormatter= DateTimeFormatter.ISO_TIME
        val dateFormatter :DateTimeFormatter=DateTimeFormatter.ISO_DATE
        val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    }
    fun completedDate():LocalDate?=
        if (completedDateString==null)
            null
        else
            LocalDate.parse(completedDateString, dateFormatter)

    fun dueTime():LocalTime?=
        if (dueTimeString==null)
            null
        else
            LocalTime.parse(dueTimeString, timeFormatter)

    fun isCompleted()= completedDate()!=null

    fun getImageResource() :Int =if(isCompleted()) R.drawable.check_circle else R.drawable.unchecked_24


}


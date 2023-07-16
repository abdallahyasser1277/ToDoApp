package com.abdalllahyascer.todolist.adapters

import android.content.Context
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abdalllahyascer.todolist.R
import com.abdalllahyascer.todolist.databinding.TodoCardBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItem.Companion.timeFormat
import com.abdalllahyascer.todolist.model.TaskItemClickListener
import java.time.format.DateTimeFormatter


class TodoListHolder(
    private val context: Context,
    private val binding: TodoCardBinding,
    private val clickListener: TaskItemClickListener
):RecyclerView.ViewHolder(binding.root) {


    fun bindTaskItem(taskItem: TaskItem){
        binding.nameView.text=taskItem.name
        binding.checkView.setImageResource(taskItem.getImageResource())

        if (taskItem.isCompleted()){
            binding.checkView.setColorFilter(ContextCompat.getColor(context,R.color.green))
            binding.nameView.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.checkView.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }

        binding.todoCardView.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

        if (taskItem.dueTime()!=null) binding.timeView.text = timeFormat.format(taskItem.dueTime())
        else binding.timeView.text = "--:--"


    }
}
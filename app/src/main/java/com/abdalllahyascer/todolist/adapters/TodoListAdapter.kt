package com.abdalllahyascer.todolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdalllahyascer.todolist.databinding.TodoCardBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItemClickListener

class TodoListAdapter(private val taskItems :List<TaskItem>,
                      private val clickListener: TaskItemClickListener

):RecyclerView.Adapter<TodoListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListHolder {

        val binding=TodoCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoListHolder(parent.context,binding,clickListener)
    }

    override fun getItemCount(): Int =taskItems.size

    override fun onBindViewHolder(holder: TodoListHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }

}
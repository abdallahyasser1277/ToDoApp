package com.abdalllahyascer.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdalllahyascer.todolist.adapters.TodoListAdapter
import com.abdalllahyascer.todolist.databinding.ActivityMainBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItemClickListener

class MainActivity : AppCompatActivity() ,TaskItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this).get(TaskViewModel::class.java)
        setContentView(binding.root)

        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager,"New Task tag")
        }

        setTaskListRecyclerView()

    }
    fun setTaskListRecyclerView(){
        viewModel.taskItems.observe(this){
            binding.todoListRecyclerView.layoutManager=LinearLayoutManager(applicationContext)
            binding.todoListRecyclerView.adapter=TodoListAdapter(it,this)
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager,"New Task tag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        viewModel.setCompleted(taskItem)
    }
}
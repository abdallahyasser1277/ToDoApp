package com.abdalllahyascer.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdalllahyascer.todolist.adapters.TodoListAdapter
import com.abdalllahyascer.todolist.databinding.ActivityMainBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItemClickListener

class MainActivity : AppCompatActivity() ,TaskItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TodoApplication).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
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
            NewTaskSheet(taskItem).show(supportFragmentManager, "New Task tag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        viewModel.setCompleted(taskItem)
    }
}
package com.abdalllahyascer.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this).get(TaskViewModel::class.java)
        setContentView(binding.root)

        binding.newTaskButton.setOnClickListener{
            NewTaskSheet().show(supportFragmentManager,"New Task tag")
        }

        viewModel.taskName.observe(this){
            binding.taskName.text=it
        }

        viewModel.taskDesc.observe(this){
            binding.taskDesc.text=it
        }
    }
}
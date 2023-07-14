package com.abdalllahyascer.todolist

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.databinding.FragmentNewTaskSheetBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(taskItem != null){
            binding.fragmentTitle.text= "Edit Task"
            val editable=Editable.Factory.getInstance()
            binding.taskName.text=editable.newEditable(taskItem!!.name)
            binding.taskDesc.text=editable.newEditable(taskItem!!.desc)

        }
        else{
            binding.fragmentTitle.text= "New Task"
        }

        viewModel=ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.dismissButton.setOnClickListener {
            dismissAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNewTaskSheetBinding.inflate(inflater)

        return binding.root
    }

    private fun saveAction(){
        val name = binding.taskName.text.toString()
        val desc = binding.taskDesc.text.toString()
        if (taskItem==null){
            viewModel.addTask(TaskItem(name,desc,null,null))
        }
        else{
            viewModel.updateTask(taskItem!!.id,name,desc,null)
        }
        binding.taskName.setText("")
        binding.taskDesc.setText("")
        dismiss()// to dismiss the fragment

    }
    private fun dismissAction(){
        binding.taskName.setText("")
        binding.taskDesc.setText("")
        dismiss()// to dismiss the fragment

    }


}
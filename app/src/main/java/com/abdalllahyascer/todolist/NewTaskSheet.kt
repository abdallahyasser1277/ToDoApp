package com.abdalllahyascer.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        viewModel.taskName.value = binding.newTaskName.text.toString()
        viewModel.taskDesc.value = binding.newTaskDesc.text.toString()
        binding.newTaskName.setText("")
        binding.newTaskDesc.setText("")
        dismiss()// to dismiss the fragment

    }
    private fun dismissAction(){
        binding.newTaskName.setText("")
        binding.newTaskDesc.setText("")
        dismiss()// to dismiss the fragment

    }


}
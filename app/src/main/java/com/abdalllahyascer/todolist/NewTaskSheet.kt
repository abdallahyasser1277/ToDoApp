package com.abdalllahyascer.todolist

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.databinding.FragmentNewTaskSheetBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var viewModel: TaskViewModel
    private var dueTime:LocalTime?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(taskItem != null){
            binding.fragmentTitle.text= "Edit Task"
            val editable=Editable.Factory.getInstance()
            binding.taskName.text=editable.newEditable(taskItem!!.name)
            binding.taskDesc.text=editable.newEditable(taskItem!!.desc)

            if(taskItem!!.dueTime !=null){
                dueTime= taskItem!!.dueTime!!
                updateTimeButton()
                }
        }
        else{
            binding.fragmentTitle.text= "New Task"
        }

        viewModel=ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.selectTime.setOnClickListener {
            pickTime()
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

    private fun updateTimeButton(){
        binding.selectTime.text= String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }
    private fun saveAction(){
        val name = binding.taskName.text.toString()
        val desc = binding.taskDesc.text.toString()
        if (taskItem==null){
            viewModel.addTask(TaskItem(name,desc,dueTime,null))
        }
        else{
            viewModel.updateTask(taskItem!!.id,name,desc,dueTime)
        }
        binding.taskName.setText("")
        binding.taskDesc.setText("")
        dismiss()// to dismiss the fragment

    }
    private fun pickTime(){
        if (dueTime==null){
            dueTime= LocalTime.now()
        }
        val listener=TimePickerDialog.OnTimeSetListener{_,hour,minute->
            dueTime= LocalTime.of(hour,minute)
            updateTimeButton()
        }

        val dialog = TimePickerDialog(activity,listener, dueTime!!.hour ,dueTime!!.minute,true)
        dialog.setTitle("Task Due In")
        dialog.show()

    }


}
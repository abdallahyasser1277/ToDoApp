package com.abdalllahyascer.todolist

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.abdalllahyascer.todolist.databinding.FragmentNewTaskSheetBinding
import com.abdalllahyascer.todolist.model.TaskItem
import com.abdalllahyascer.todolist.model.TaskItem.Companion.dateFormatter
import com.abdalllahyascer.todolist.model.TaskItem.Companion.timeFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
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
            binding.completedCheckBox.isChecked=taskItem!!.isCompleted()
            binding.saveButton.text="SAVE UPDATE"
            updateCompletedCheckBox()

            if(taskItem!!.dueTime() !=null){
                dueTime= taskItem!!.dueTime()!!
                updateTimeButton()
                }
        }
        else{
            binding.fragmentTitle.text= "New Task"
            binding.deleteBtn.isVisible=false
        }

        viewModel=ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.selectTime.setOnClickListener {
            pickTime()
        }
        binding.completedCheckBox.setOnClickListener {
            updateCompletedCheckBox()
        }
        binding.deleteBtn.setOnClickListener {
            viewModel.deleteTask(taskItem)
            binding.taskName.setText("")
            binding.taskDesc.setText("")
            dismiss()// to dismiss the fragment
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
    private fun updateCompletedCheckBox(){
        if(binding.completedCheckBox.isChecked)
            binding.completedCheckBox.setText("COMPLETED")
        else
            binding.completedCheckBox.setText("UNCOMPLETED")
    }
    private fun saveAction(){
        val name = binding.taskName.text.toString()
        val desc = binding.taskDesc.text.toString()
        val dueTimeString:String?=
            if (dueTime!=null)
                timeFormatter.format(dueTime)
            else
                null

        val completedDate :String? =
            if (binding.completedCheckBox.isChecked)
                dateFormatter.format(LocalDate.now())
            else
                null

        if (taskItem==null){
            viewModel.addTask(TaskItem(name,desc,dueTimeString,completedDate))
        }
        else{
            taskItem!!.name=name
            taskItem!!.desc=desc
            taskItem!!.dueTimeString=dueTimeString
            taskItem!!.completedDateString = completedDate

            viewModel.updateTask(taskItem!!)
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
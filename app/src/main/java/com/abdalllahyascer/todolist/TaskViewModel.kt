package com.abdalllahyascer.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    var taskName =MutableLiveData<String>()
    var taskDesc =MutableLiveData<String>()

}
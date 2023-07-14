package com.abdalllahyascer.todolist.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class TaskItem(
    var name: String,
    var desc: String,
    var dueDate: LocalDateTime?,
    var completedDate: LocalDate?,
    var id :UUID=UUID.randomUUID()
)
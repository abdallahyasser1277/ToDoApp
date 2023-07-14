package com.abdalllahyascer.todolist.model

import java.time.LocalDateTime
import java.util.UUID

class TaskItem(
    var name: String,
    var desc: String,
    var dueDate: LocalDateTime?,
    var complatedDate: LocalDateTime?,
    var id :UUID=UUID.randomUUID()
)
package com.example.crudmvvm.model

import com.example.crudmvvm.repository.remote.request.TodoRequest

data class TodoModel(

    val id: Int = 0,
    var completed: Boolean = false,
    val title: String,
    val userId: Int = 0
)

fun TodoModel.toRequest() = TodoRequest(completed, title)

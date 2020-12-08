package com.example.crudmvvm.model

import com.example.crudmvvm.repository.request.TodoRequest

data class TodoModel(
    val id: Int,

    val completed: Boolean,

    val title: String,

    val userId: Int
)
fun TodoModel.toRequest() = TodoRequest(id, completed, title, userId)

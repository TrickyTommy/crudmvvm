package com.example.crudmvvm.model

import com.example.crudmvvm.repository.request.TodoRequest
import com.google.gson.annotations.SerializedName

data class TodoModel(

    val id: Int = 0,
    val completed: Boolean = false,
    val title: String ,
    val userId: Int = 0
)

fun TodoModel.toRequest() = TodoRequest(completed, title)

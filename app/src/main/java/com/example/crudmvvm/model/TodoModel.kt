package com.example.crudmvvm.model

data class TodoModel(
    val id: Int,

    val completed: Boolean,

    val title: String,

    val userId: Int
)

